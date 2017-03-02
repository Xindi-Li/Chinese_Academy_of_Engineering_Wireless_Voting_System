/**
 * Created by lixindi on 2017/3/1.
 */
var admin = angular.module('admin', []);
admin.controller('logout_ctrl', function ($scope, $http) {
    $scope.logout = function () {
        var confirm = window.confirm("确定注销吗？");
        if (confirm) {
            $http.delete('/logout')
                .success(function () {
                    alert("注销成功");
                    window.location.href = "/login/login.html";
                })
        }
    }
});
