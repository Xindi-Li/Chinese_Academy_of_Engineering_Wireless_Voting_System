/**
 * Created by lixindi on 2017/3/2.
 */
var register = angular.module('register', []);
register.controller('register_ctrl', function ($scope, $http) {
    $scope.register = function () {
        $http.post('/register', $scope.account)
            .success(function (response) {
                if (response) {
                    alert("注册成功");
                    window.location.href = "/admin/admin.html";
                } else {
                    alert("账号已存在");
                    var usernameinput = document.querySelector("form > div:nth-child(1) > input");
                    usernameinput.focus();
                }
            })
    }
});