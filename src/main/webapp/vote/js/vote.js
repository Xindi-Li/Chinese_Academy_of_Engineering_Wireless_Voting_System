/**
 * Created by lixindi on 2017/3/20.
 */
var vote = angular.module('vote', []);

vote.controller('vote_ctrl', function ($scope, $http) {
    $scope.init = function () {
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
    };
    window.onload = function () {
        init();
        startit();
    }
});


