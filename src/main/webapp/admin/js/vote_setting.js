/**
 * Created by lixindi on 2017/3/20.
 */
angular.module('admin')
    .controller('vote_setting', function ($scope, $http, voteParam) {
        $scope.voteParam = {};
        $scope.get_department = function () {
            $http.get('/admin/r_department')
                .success(function (response) {
                    $scope.departmentList = response.data;
                })
        };
        $scope.get_group = function () {
            var department = {
                department: $scope.voteParam.department
            };
            $http.post('/admin/r_group', department)
                .success(function (response) {
                    $scope.groupList = response.data;
                });
        };
        $scope.$watch('voteParam.department', $scope.get_group);

        $scope.get_candidate = function () {
            var postData = {
                department: $scope.department,
                group: $scope.group
            };
            $http.post('/admin/r_candidate', postData)
                .success(function (response) {
                    voteParam.candidates = response.data.candidateInfos;
                });
        };

        $scope.begin_vote = function () {
            init();
            startit();
            $scope.this_begin = true;
            $scope.elec_begin = true;
            voteParam.this_begin = true;
            voteParam.elec_begin = true;
            voteParam.setValue($scope.voteParam);
            if (voteParam.candidates.length == 0) {
                $scope.get_candidate();
            }
        };

        $scope.end_this = function () {
            var confirm = window.confirm("确定结束投票吗？");
            if (confirm) {
                $scope.this_begin = false;
                voteParam.is_begin = false;
            }
        };

        $scope.end_election = function () {
            var confirm = window.confirm("确定结束选举吗？");
            if (confirm) {
                $scope.this_begin = false;
                $scope.elec_begin = false;
                voteParam.this_begin = false;
                voteParam.elec_begin = false;
            }
        }
    });