/**
 * Created by lixindi on 2017/3/20.
 */
angular.module('admin')
    .controller('vote_setting', function ($scope, $http, $rootScope, $q, ModalService) {
        var voteResultList = [];
        $scope.timesList = [];

        $scope.voteData = {
            round: 1,
            times: 1,
            candidates: [],
            vote_begin: false
        };

        $scope.get_department = function () {
            $http.get('/admin/r_department')
                .success(function (response) {
                    $scope.departmentList = response.data;
                    $scope.voteData.department = $scope.departmentList[0];
                })
        };
        $scope.get_group = function () {
            var department = {
                department: $scope.voteData.department
            };
            $http.post('/admin/r_group', department)
                .success(function (response) {
                    $scope.groupList = response.data;
                });
        };
        $scope.$watch('voteData.department', $scope.get_group);

        $scope.get_candidate = function () {
            var deferred = $q.defer();
            var postData = {
                department: $scope.voteData.department,
                group: $scope.voteData.group
            };
            $http.post('/admin/r_candidate', postData)
                .success(function (response) {
                    deferred.resolve(response.data.candidateInfos);
                });
            return deferred.promise;
        };

        $scope.begin_vote = function () {
            init();
            startit();
            $rootScope.elec_begin = true;
            $scope.voteData.vote_begin = true;
            $scope.elec_begin = true;
            $scope.round_end = false;
            $scope.get_candidate().then(function (data) {
                $scope.voteData.candidates = data;
                $http.post('/admin/w_vote_setting', $scope.voteData);
            });
            $http.get('/admin/generate_qrcode?num=' + $scope.voteData.voter_num);
        };

        $scope.show_modal = function (times) {
            ModalService.showModal({
                templateUrl: "vote-result.html",
                controller: "vote_result",
                inputs: {
                    result: {
                        voteResult: voteResultList[times - 1],
                        round: $scope.voteData.round,
                        times: times,
                        department: $scope.voteData.department
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
                                $scope.voteData.vote_begin = false;
                                $scope.timesList.push($scope.voteData.times);
                                $scope.voteData.times++;
                            });
                    }
                });
        };

        $scope.end_round = function () {
            var confirm = window.confirm("确定结束本轮投票吗");
            if (confirm) {
                $scope.round_end = true;
                ModalService.showModal({
                    templateUrl: "round-result.html",
                    controller: "round_result",
                    inputs: {
                        result: {
                            voteResult: voteResultList,
                            round: $scope.voteData.round,
                            timesList: Array.from(new Array(voteResultList.length), (val, index)=>index + 1),
                            department: $scope.voteData.department
                        }
                    }
                }).then(function (modal) {
                    modal.element.modal();
                });
            }
        };

        $scope.end_election = function () {
            var confirm = window.confirm("确定结束选举吗？");
            if (confirm) {
                $rootScope.elec_begin = false;
                $scope.elec_begin = false;
                $scope.voteData.vote_begin = false;
            }
        };


    });

admin.controller('vote_result', function ($scope, result) {
    $scope.result = result;
});

admin.controller('round_result', function ($scope, result) {
    $scope.result = result;
    $scope.times = result.timesList[0];
    $scope.$watch('times', function () {
        $scope.candidates = result.voteResult[$scope.times - 1].candidates;
        $scope.advance_score = result.voteResult[$scope.times - 1].advance_score;
    });
    $scope.submit= function () {
        var confirm = window.confirm("确定选择第"+$scope.times+"次结果作为本轮投票的结果吗？");
        if(confirm){
            $('.modal').modal('hide');
        }
    }
});
