/**
 * Created by lixindi on 2017/2/20.
 */
var login = angular.module('login', []);
login.controller('login_ctrl', function ($scope, $http) {
    $scope.validate = function () {
        $http.post('/validate', {username: $scope.username, password: $scope.password})
            .success(function (response) {
                if (response) {
                    alert("登陆成功");
                    window.location.href = "/admin/admin.html";
                } else {
                    alert("登录失败");
                }
            })
    };
    $scope.logout = function () {
        $http.get('/logout')
            .success(function (response) {
                alert("注销成功");
                window.location.href = "/login/login.html";
            })
    };
});
