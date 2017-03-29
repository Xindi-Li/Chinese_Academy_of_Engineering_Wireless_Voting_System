/**
 * Created by lixindi on 2017/3/20.
 */
angular.module('admin')
    .controller('vote_setting', function ($scope, $http, $rootScope, $q, ModalService) {
        $scope.voteData = {
            candidates: [],
            vote_begin: false
        };


        $scope.get_department = function () {
            $http.get('/admin/r_department')
                .success(function (response) {
                    $scope.departmentList = response.data;
                    $scope.voteData.department = $scope.departmentList[0];
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
            var deferred = $q.defer();
            var postData = {
                department: $scope.voteData.department,
                group: $scope.voteData.group
            };
            $http.post('/admin/r_candidate', postData)
                .success(function (response) {
                    deferred.resolve(response.data.candidateInfos);
                });
            return deferred.promise;
        };

        $scope.begin_vote = function () {
            init();
            startit();
            $rootScope.elec_begin = true;
            $scope.voteData.vote_begin = true;
            $scope.elec_begin = true;
            $scope.get_candidate().then(function (data) {
                $scope.voteData.candidates = data;
                $http.post('/admin/w_vote_setting', $scope.voteData);
            });
            $http.get('/admin/generate_qrcode?num=' + $scope.voteData.voter_num);
        };

        $scope.show_modal = function () {
            ModalService.showModal({
                templateUrl: "yesno.html",
                controller: "YesNoController"
            }).then(function (modal) {
                modal.element.modal();
            });

        };

        $scope.end_vote = function () {
            var confirm = window.confirm("确定结束投票吗？");
            if (confirm) {
                $scope.voteData.vote_begin = false;

            }
        };

        $scope.end_election = function () {
            var confirm = window.confirm("确定结束选举吗？");
            if (confirm) {
                $rootScope.elec_begin = false;
                $scope.elec_begin = false;
                $scope.voteData.vote_begin = false;
            }
        };

    });

admin.controller('YesNoController', ['$scope', 'close', function ($scope, close) {

    $scope.close = function (result) {
        close(result, 500); // close, but give 500ms for bootstrap to animate
    };

}]);