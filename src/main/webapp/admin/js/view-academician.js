/**
 * Created by JamesLee on 2017/4/24.
 */
angular.module('admin')
    .controller('view_academician', function ($scope, $http, paginationConf) {

        $scope.paginationConf = paginationConf.paginationConf;

        $scope.get_department = function () {
            $http.get('/admin/r_depart')
                .success(function (response) {
                    $scope.departmentList = response.data;
                })
        };

        $scope.get_academician = function () {
            var postData = {
                currentPage: $scope.paginationConf.currentPage,
                itemsPerPage: $scope.paginationConf.itemsPerPage,
                department: $scope.department,
                name: $scope.name
            };
            $http.post('/admin/r_academician', postData)
                .success(function (response) {
                    $scope.paginationConf.totalItems = response.data.total;
                    $scope.academician = response.data.academician;
                });
        };

        $scope.$watch('paginationConf.currentPage', $scope.get_academician);
    });