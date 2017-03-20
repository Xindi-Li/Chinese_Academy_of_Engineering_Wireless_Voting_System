/**
 * Created by lixindi on 2017/3/17.
 */
angular.module('admin')
    .controller('edit_candidate', function ($scope, $http, candidateInfo) {
        $scope.candidate = candidateInfo;
        $scope.update = function () {
            $http.post('/admin/u_candidate', $scope.candidate)
                .success(function (response) {
                    if (response.data) {
                        alert("修改成功");
                    } else {
                        alert("修改失败");
                    }
                })
        }
    });