/**
 * Created by lixindi on 2017/3/20.
 */
angular.module('admin')
    .controller('vote_setting', function ($scope, $http, $rootScope) {
        $scope.voteData = {
            candidates: [],
            vote_begin: false
        };


        $scope.get_department = function () {
            $http.get('/admin/r_department')
                .success(function (response) {
                    $scope.departmentList = response.data;
                })
        };
        $scope.get_group = function () {
            var department = {
                department: $scope.voteData.department
            };
            $http.post('/admin/r_group', department)
                .success(function (response) {
                    $scope.groupList = response.data;
                });
        };
        $scope.$watch('voteData.department', $scope.get_group);

        $scope.get_candidate = function () {
            var postData = {
                department: $scope.voteData.department,
                group: $scope.voteData.group
            };
            $http.post('/admin/r_candidate', postData)
                .success(function (response) {
                    $scope.voteData.candidates = response.data.candidateInfos;
                });
        };

        $scope.begin_vote = function () {
            init();
            startit();
            $rootScope.vote_begin = true;
            $scope.voteData.vote_begin = true;
            $scope.elec_begin = true;
            if ($scope.voteData.candidates.length == 0) {
                $scope.get_candidate();
            }
        };

        $scope.end_vote = function () {
            var confirm = window.confirm("确定结束投票吗？");
            if (confirm) {
                $rootScope.vote_begin = false;
                $scope.voteData.vote_begin = false;
            }
        };

        $scope.end_election = function () {
            var confirm = window.confirm("确定结束选举吗？");
            if (confirm) {
                $rootScope.vote_begin = false;
                $scope.elec_begin = false;
                $scope.voteData.vote_begin = false;
            }
        };

        window.onbeforeunload = function (event) {
            if ($rootScope.vote_begin) {
                return "Leave";
            }
        };
    });