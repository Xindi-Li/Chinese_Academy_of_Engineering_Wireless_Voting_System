/**
 * Created by lixindi on 2017/2/20.
 */
var login = angular.module('login', []);
login.controller('login_ctrl', function ($scope, $http) {
    $scope.validate = function () {
        $http.post('/validate', $scope.account)
            .success(function (response) {
                if (response.data) {
                    alert("登陆成功");
                    window.location.href = "/admin/admin.html";
                } else {
                    alert("登录失败");
                    var usernameinput = document.querySelector("form > div:nth-child(1) > input");
                    usernameinput.focus();
                }
            })
    };
});
