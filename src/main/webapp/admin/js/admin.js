/**
 * Created by lixindi on 2017/3/1.
 */
var admin = angular.module('admin', ['ngCsvImport', 'ngRoute','tm.pagination']);
admin.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/add_candidate', {
        templateUrl: 'add-candidate.html',
        controller: 'add_candidate'
    }).when('/signup', {
        templateUrl: 'signup.html',
        controller: 'signup_ctrl'
    }).when('/reset_password', {
        templateUrl: 'reset-password.html',
        controller: 'reset_ctrl'
    }).when('/view_candidate', {
        templateUrl: 'view-candidate.html',
        controller: ''
    }).when('/group_candidate', {
        templateUrl: 'group-candidate.html',
        controller: 'group_candidate'
    });
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



