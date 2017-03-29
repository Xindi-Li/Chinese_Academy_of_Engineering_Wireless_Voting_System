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
            $http.get('/vote/validate_token?token=' + $scope.urlParam.token)
                .success(function (response) {
                    if (!response.data) {
                        $scope.is_start = false;
                        alert("您没有权限投票");
                    } else {
                        $http.get('/vote/validate_id?id=' + $scope.urlParam.id)
                            .success(function (response) {
                                if (!response.data) {
                                    $scope.is_start = false;
                                    alert("您已经完成了投票");
                                } else {
                                    $http.get('/vote/r_vote_setting')
                                        .success(function (response) {
                                            if (response.status == 1) {
                                                $scope.is_start = false;
                                                alert("投票尚未开始");
                                            } else {
                                                $scope.is_start = true;
                                                $scope.voteData = response.data;
                                                init();
                                                startit();
                                            }
                                        });
                                }
                            })
                    }
                })
        }
    };

    $scope.submit = function () {
        var confirm = window.confirm("确定提交吗？");
        if (confirm) {

        }
    };
});


