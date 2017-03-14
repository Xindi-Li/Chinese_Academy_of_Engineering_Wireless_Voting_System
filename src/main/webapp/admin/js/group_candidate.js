/**
 * Created by lixindi on 2017/3/13.
 */
angular.module('admin')
    .controller('group_candidate', function ($scope, $http) {
        $scope.paginationConf = {
            currentPage: 1,
            pagesLength: 15,
            itemsPerPage: 5,
            perPageOptions: [5, 10, 15, 20]
        };
        $scope.get_department = function () {
            $http.get('/admin/r_department')
                .success(function (response) {
                    $scope.departmentList = response.data;
                })
        };
        $scope.get_candidate = function () {
            var postData = {
                currentPage: $scope.paginationConf.currentPage,
                itemsPerPage: $scope.paginationConf.itemsPerPage,
                department: $scope.department
            };
            $http.post('/admin/r_candidate', postData)
                .success(function (response) {
                    $scope.paginationConf.totalItems = response.data.total;
                    $scope.candidate = response.data.candidateInfos;
                })
        };
        $scope.$watch('paginationConf.currentPage + department', $scope.get_candidate);
    });