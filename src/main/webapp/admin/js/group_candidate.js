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
    });