/**
 * Created by lixindi on 2017/3/10.
 */
angular.module('admin')
    .controller('view_candidate', function ($scope, $http) {

        $scope.get_department = function () {
            $http.get('/admin/r_department')
                .success(function (response) {
                    $scope.departmentList = response.data;
                });
        };

        $scope.get_group = function () {
            var department = {
                department: $scope.department
            };
            $http.post('/admin/r_group', department)
                .success(function (response) {
                    $scope.groupList = response.data;
                })
        };
        $scope.$watch('department', $scope.get_group);


    });