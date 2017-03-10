/**
 * Created by lixindi on 2017/3/9.
 */
angular.module('admin')
    .controller('signup_ctrl', function ($scope, $http) {
        $scope.register = function () {
            $http.post('/admin/register', $scope.account)
                .success(function (response) {
                    if (response.data) {
                        alert("注册成功");
                    } else {
                        alert("账号已存在");
                        var usernameinput = document.querySelector("form > div:nth-child(1) > input");
                        usernameinput.focus();
                    }
                })
        }
    });
