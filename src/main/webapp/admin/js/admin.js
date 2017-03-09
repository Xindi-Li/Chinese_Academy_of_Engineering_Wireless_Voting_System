/**
 * Created by lixindi on 2017/3/1.
 */
var admin = angular.module('admin', ['ngCsvImport', 'ngRoute']);
admin.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/add_candidate', {templateUrl: 'add-candidate.html'})
        .when('/signup', {templateUrl: 'signup.html'})
        .when('/reset_password', {templateUrl: 'reset-password.html'});
}]);

admin.controller('logout_ctrl', function ($scope, $http) {
    $scope.logout = function () {
        var confirm = window.confirm("确定注销吗？");
        if (confirm) {
            $http.delete('/admin/logout')
                .success(function () {
                    alert("注销成功");
                    window.location.href = "/login/login.html";
                })
        }
    }
});

admin.controller('register_ctrl', function ($scope, $http) {
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

admin.controller('reset_ctrl', function ($scope, $http) {
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
                } else {
                    alert("修改失败");
                    var usernameinput = document.querySelector("form > div:nth-child(1) > input");
                    usernameinput.focus();
                }
            })
    };
});

admin.controller('upload_csv', function ($scope) {
    $scope.csv = {
        content: null,
        header: true,
        headerVisible: true,
        separator: ',',
        separatorVisible: false,
        result: null,
        uploadButtonLabel: "上传一个CSV文件"
    };
    $scope.is_clicked = false;
    $scope.clicked = function () {
        $scope.is_clicked = true;
    };

});
