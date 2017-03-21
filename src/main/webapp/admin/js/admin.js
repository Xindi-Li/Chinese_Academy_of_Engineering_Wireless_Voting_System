/**
 * Created by lixindi on 2017/3/1.
 */
var admin = angular.module('admin', ['ngCsvImport', 'ngRoute', 'tm.pagination']);
admin.config(function ($routeProvider) {
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
        controller: 'view_candidate'
    }).when('/group_candidate', {
        templateUrl: 'group-candidate.html',
        controller: 'group_candidate'
    }).when('/edit_candidate', {
        templateUrl: 'edit-candidate.html',
        controller: 'edit_candidate'
    }).when('/vote_setting', {
        templateUrl: 'vote-setting.html',
        controller: 'vote_setting'
    });
});

admin.run(function ($rootScope, voteParam) {
    $rootScope.$on('$routeChangeStart', function (evt, next, current) {
        if (current.templateUrl == "vote-setting.html" && voteParam.elec_begin == true) {
            alert("选举尚未结束");
            evt.preventDefault();
        }
    });

});

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

admin.constant('paginationConf', {
    paginationConf: {
        currentPage: 1,
        pagesLength: 15,
        itemsPerPage: 5,
        perPageOptions: [5, 10, 15, 20]
    }
});

admin.service('candidateInfo', function () {
    this.setValue = function (candidate) {
        this.name = candidate.name;
        this.candidate_num = candidate.candidate_num;
        this.age = candidate.age;
        this.major = candidate.major;
        this.company = candidate.company;
        this.department = candidate.department;
    };
});

admin.service('voteParam', function () {
    this.candidates = [];
    this.this_begin = false;
    this.elec_begin = false;
    this.setValue = function (voteParam) {
        this.department = voteParam.department;
        this.group = voteParam.group;
        this.type = voteParam.type;
        this.round = voteParam.round;
        this.times = voteParam.times;
        this.advance_num = voteParam.advance_num;
    }
});

