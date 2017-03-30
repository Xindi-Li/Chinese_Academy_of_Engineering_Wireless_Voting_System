/**
 * Created by lixindi on 2017/3/20.
 */
var vote = angular.module('vote', []);

vote.config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});

vote.controller('vote_ctrl', function ($scope, $http, $location) {
        $scope.init = function () {
            $scope.urlParam = $location.search();
            if ($scope.urlParam.token == undefined || $scope.urlParam.id == undefined) {
                $scope.is_start = false;
                alert("您没有权限投票");
            } else {
                $http.get('/vote/validate_querystring?token=' + $scope.urlParam.token + "&id=" + $scope.urlParam.id)
                    .success(function (response) {
                        if (response.status == 3) {
                            $scope.is_start = false;
                            alert("您没有权限投票");
                        } else if (response.status == 4) {
                            $scope.is_start = false;
                            alert("您已完成投票");
                        } else {
                            $http.get('/vote/r_vote_setting')
                                .success(function (response) {
                                    if (response.status == 1) {
                                        $scope.is_start = false;
                                        alert("投票尚未开始");
                                    } else {
                                        $scope.is_start = true;
                                        $scope.voteData = response.data;
                                    }
                                });
                        }
                    })
            }
        };
        $scope.submit = function () {
            var confirm = window.confirm("确定提交吗？");
            if (confirm) {
                var postData = {
                    voterID: $scope.urlParam.id,
                    candidates: $scope.voteData.candidates,
                    department: $scope.voteData.department,
                    advance_num: $scope.voteData.advance_num
                };
                $http.post('/vote/submit_vote?token=' + $scope.urlParam.token, postData)
                    .success(function (response) {
                        if (response.data) {
                            alert("投票成功");
                        } else {
                            alert("投票已关闭");
                        }
                        window.location.href = "about:blank";
                    })
            }
        };
    }
);


