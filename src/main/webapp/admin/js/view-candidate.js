/**
 * Created by lixindi on 2017/3/10.
 */
angular.module('admin').controller('view_candidate', function ($scope, $http) {
    $scope.department = {};
    $scope.group = {};
    $scope.name = {};
    $scope.candidate=[];
    $scope.getCandidate= function () {
        $http.get()
    }

});