/**
 * Created by lixindi on 2017/3/9.
 */
angular.module('admin')
    .controller('reset_ctrl', function ($scope, $http) {
        $scope.account = {};
        $scope.get_username = function () {
            $http.get('/admin/get_username')
                .success(function (response) {
                    $scope.account.username = response.data;
                })
        };
        $scope.reset = function () {
            $http.post('/admin/reset_password', $scope.account)
                .success(function (response) {
                    if (response.data) {
                        alert("修改成功");
                        $scope.account.password = null;
                    } else {
                        alert("修改失败");
                        var usernameinput = document.querySelector("form > div:nth-child(1) > input");
                        usernameinput.focus();
                    }
                })
        };
    });