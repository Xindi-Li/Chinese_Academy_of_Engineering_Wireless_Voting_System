/**
 * Created by lixindi on 2017/3/20.
 */
angular.module('admin')
    .controller('vote_setting', function ($scope, $http, $rootScope, ModalService, $timeout, $route) {
        var voteResultList = [];
        $scope.timesList = [];
        var conditionList = [];

        $scope.round = 1;
        $scope.times = 1;
        $scope.vote_begin = false;

        $scope.get_conditions = function () {
            if ($scope.is_group) {
                $http.get('/admin/r_conditions')
                    .success(function (response) {
                        $scope.conditions = response.data;
                    })
            } else {
                $http.get('/admin/r_department')
                    .success(function (response) {
                        $scope.conditions = response.data;
                    })
            }
            conditionList = [];
        };

        $scope.select_condition = function (condition) {
            var index = conditionList.indexOf(condition);
            if (index == -1) {
                conditionList.push(condition);
            } else {
                conditionList.splice(index, 1);
            }
        };

        $scope.set_candidate = function () {
            if ($scope.round == 1 && $scope.times == 1) {
                var postData = [];
                if ($scope.is_group) {
                    var dict = [];
                    conditionList.forEach(function (element) {
                        var splited = element.split(/学部|组/);
                        if (dict.hasOwnProperty(splited[0])) {
                            dict[splited[0]].push(splited[1]);
                        } else {
                            if (splited[1] == "") {
                                dict[splited[0]] = null;
                            } else {
                                dict[splited[0]] = [splited[1]];
                            }
                        }
                    });
                    for (item in dict) {
                        postData.push({
                            department: item,
                            groups: dict[item]
                        });
                    }
                } else {
                    conditionList.forEach(function (element) {
                        postData.push({
                            department: element
                        })
                    });
                }

                $http.post('/admin/r_nominees_db', postData)
                    .success(function (response) {
                        $http.post('/admin/w_nominees', response.data);
                    });
            }
        };

        $scope.begin_vote = function () {
            if (conditionList.length == 0) {
                alert("至少选择一个候选人范围");
            } else {
                init();
                startit();
                $rootScope.elec_begin = true;
                $scope.vote_begin = true;
                $scope.elec_begin = true;
                $scope.round_end = false;
                $scope.set_candidate();
                $http.post('/admin/w_vote_setting?num=' + $scope.voteData.voter_num, $scope.voteData);
            }
        };

        $scope.show_modal = function (times) {
            ModalService.showModal({
                templateUrl: "vote-result.html",
                controller: "vote_result",
                inputs: {
                    result: {
                        voteResult: voteResultList[times - 1],
                        round: $scope.round,
                        times: times
                    }
                }
            }).then(function (modal) {
                modal.element.modal();
            });
        };

        $scope.end_vote = function () {
            $http.get('/vote/r_votednum')
                .success(function (response) {
                    var confirm = window.confirm("已有" + response.data.voted + "/" + response.data.total + "人完成投票，" +
                        "确定结束本次投票吗？");
                    if (confirm) {
                        $http.get('/vote/r_vote_result')
                            .success(function (response) {
                                voteResultList.push(response.data);
                                $scope.vote_begin = false;
                                $scope.timesList.push($scope.times);
                                $scope.times++;
                            });
                    }
                });
        };

        $scope.timeout = function () {
            alert("投票结束");
            $http.get('/vote/r_vote_result')
                .success(function (response) {
                    voteResultList.push(response.data);
                    $scope.vote_begin = false;
                    $scope.timesList.push($scope.times);
                    $scope.times++;
                });
        };

        $scope.end_round = function () {
            var confirm = window.confirm("确定结束本轮投票吗");
            if (confirm) {
                ModalService.showModal({
                    templateUrl: "round-result.html",
                    controller: "round_result",
                    inputs: {
                        result: {
                            voteResult: voteResultList,
                            round: $scope.round,
                            timesList: Array.from(new Array(voteResultList.length), (val, index)=>index + 1)
                        }
                    }
                }).then(function (modal) {
                    modal.element.modal();
                    modal.closed.then(function () {
                        $scope.round_end = true;
                        $scope.round++;
                        $scope.times = 1;
                        voteResultList = [];
                        $scope.timesList = [];
                    });
                });
            }
        };

        $scope.end_election = function () {
            var confirm = window.confirm("确定结束选举吗？");
            if (confirm) {
                $http.get('/vote/r_nominees')
                    .success(function (response) {
                        ModalService.showModal({
                            templateUrl: "election-result.html",
                            controller: "elec_result",
                            inputs: {
                                academician: response.data
                            }
                        }).then(function (modal) {
                            modal.element.modal();
                            modal.closed.then(function () {
                                $http.post('/admin/w_academician', response.data);
                                $rootScope.elec_begin = false;
                                $scope.elec_begin = false;
                                $scope.vote_begin = false;
                                $route.reload();
                            });
                        });
                    });
            }
        };


        var init = function () {
            var timer = document.getElementById("timer");
            hour = $scope.hour;
            minute = $scope.minute;
            second = 0;
            timer.innerHTML = j(hour) + ":" + j(minute) + ":" + j(second);
            $timeout.cancel(t);
        };
        var hour, minute, second;
        var t;

        var startit = function () {
            second--;
            if (second < 0) {
                second = 59;
                minute--;
            }
            if (minute < 0) {
                hour--;
                if (hour < 0) {
                    $timeout.cancel(t);
                    $scope.timeout();
                    return;
                } else {
                    minute = 59;
                }
            }
            timer.innerHTML = j(hour) + ":" + j(minute) + ":" + j(second);
            t = $timeout(startit, 1000);
        };

        var j = function (arg) {
            return arg >= 10 ? arg : "0" + arg;
        };


    });

admin.controller('vote_result', function ($scope, result) {
    $scope.result = result;
});

admin.controller('elec_result', function ($scope, academician, close) {
    $scope.academician = academician;
    $scope.dismissModal = function () {
        close(null, 200);
    }
});

admin.controller('round_result', function ($scope, $http, result, $element, close) {
    $scope.result = result;
    $scope.times = result.timesList[0];
    $scope.get_result = function () {
        $scope.candidates = result.voteResult[$scope.times - 1].candidates;
        $scope.advance_score = result.voteResult[$scope.times - 1].advance_score;
    };
    $scope.$watch('times', $scope.get_result);
    $scope.submit = function () {
        var confirm = window.confirm("确定选择第" + $scope.times + "次结果作为本轮投票的结果吗？");
        if (confirm) {
            var advance_candidates = result.voteResult[$scope.times - 1].candidates.filter(function (element) {
                element.score = null;
                return element.is_advance;
            });
            $http.post('/admin/w_nominees', advance_candidates);
            $element.modal('hide');
            close(null, 200);
        }
    }
});
