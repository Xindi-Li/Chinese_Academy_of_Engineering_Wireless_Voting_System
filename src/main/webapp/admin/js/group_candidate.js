/**
 * Created by lixindi on 2017/3/13.
 */
angular.module('admin')
    .controller('group_candidate', function ($scope, $http) {
        $scope.paginationConf = {
            currentPage: 1,
            totalItems: 8000,
            itemsPerPage: 50,
            pagesLength: 15,
            perPageOptions: [10, 20, 30, 40, 50],
            onChange: function () {
            }
        };
        $scope.get_department = function () {
            $http.get('/admin/r_department')
                .success(function (response) {
                    $scope.department = response.data;
                })
        };
        $scope.get_candidate = function () {
            $http.post('/admin/r_candidate', $scope.select)
                .success(function (response) {
                    $scope.candidate = response.data;
                })
        };

        $scope.$watch($scope.select, function () {
            get_candidate();
        });
    });