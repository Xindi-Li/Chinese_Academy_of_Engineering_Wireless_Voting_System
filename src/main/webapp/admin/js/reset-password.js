/**
 * Created by lixindi on 2017/3/2.
 */
var reset = angular.module('reset', []);
reset.controller('reset_ctrl', function ($scope, $http) {
    $scope.account = {};
    $scope.get_username = function () {
        $http.get('/admin/get_username')
            .success(function (response) {
                $scope.account.username = response;
            })
    };
    $scope.reset = function () {
        $http.post('/admin/reset_password', $scope.account)
            .success(function (response) {
                if (response) {
                    alert("修改成功");
                    window.location.href = "/admin/admin.html";
                } else {
                    alert("修改失败");
                    var usernameinput = document.querySelector("form > div:nth-child(1) > input");
                    usernameinput.focus();
                }
            })
    };
});