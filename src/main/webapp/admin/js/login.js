/**
 * Created by lixindi on 2017/2/20.
 */
var login = angular.module('login', []);
login.controller('validate', function ($scope, $http) {
    $scope.log123 = function () {
        $http.post('/j_spring_security_check')
            .then(function successCallback(response) {
                    alert("登录成功");

                }, function errorCallback(response) {
                    alert("登录失败");
                }
            );
    }
});
