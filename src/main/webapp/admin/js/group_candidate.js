/**
 * Created by lixindi on 2017/3/13.
 */
angular.module('admin')
    .controller('group_candidate', function ($scope, $http, candidate_service) {
        $scope.candidates = [];
        $scope.paginationConf = candidate_service.paginationConf;

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
                });
            $scope.candidates = [];
        };
        $scope.$watch('paginationConf.currentPage + department', $scope.get_candidate);

        $scope.changelist = function (checked, candidate) {
            if (checked) {
                $scope.candidates.push(candidate);
            } else {
                var index = $scope.candidates.indexOf(candidate);
                if (index > -1) {
                    $scope.candidates.splice(index, 1);
                }
            }
        };

        $scope.submit = function () {
            var postData = {
                names: $scope.candidates,
                department: $scope.department,
                group: $scope.group
            };
            $http.post('/admin/w_group', postData)
                .success(function (response) {
                    if (response.data) {
                        alert("分组成功");
                    } else {
                        alert("分组失败");
                    }
                });
        }
    });