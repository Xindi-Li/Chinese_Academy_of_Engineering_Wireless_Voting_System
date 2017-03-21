/**
 * Created by lixindi on 2017/3/20.
 */
angular.module('vote', ['admin'])
    .controller('vote_ctrl', function ($scope, voteParam) {
        $scope.voteParam = voteParam;
    });