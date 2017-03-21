/**
 * Created by lixindi on 2017/3/10.
 */
angular.module('admin')
    .controller('view_candidate', function ($scope, $http, $location, paginationConf, candidateInfo) {

        $scope.paginationConf = paginationConf.paginationConf;

        $scope.get_department = function () {
            $http.get('/admin/r_department')
                .success(function (response) {
                    $scope.departmentList = response.data;
                })
        };

        $scope.get_group = function () {
            var department = {
                department: $scope.department
            };
            $http.post('/admin/r_group', department)
                .success(function (response) {
                    $scope.groupList = response.data;
                });
        };
        $scope.$watch('department', $scope.get_group);

        $scope.get_candidate = function () {
            var postData = {
                currentPage: $scope.paginationConf.currentPage,
                itemsPerPage: $scope.paginationConf.itemsPerPage,
                department: $scope.department,
                group: $scope.group,
                name: $scope.name
            };
            $http.post('/admin/r_candidate', postData)
                .success(function (response) {
                    $scope.paginationConf.totalItems = response.data.total;
                    $scope.candidate = response.data.candidateInfos;
                });
        };

        $scope.$watch('paginationConf.currentPage', $scope.get_candidate);

        $scope.edit_candidate = function (candidate) {
            candidateInfo.setValue(candidate);
            $location.url('/edit_candidate');
        }
    });