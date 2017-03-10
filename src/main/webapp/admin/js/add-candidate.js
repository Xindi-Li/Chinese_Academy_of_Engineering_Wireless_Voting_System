/**
 * Created by lixindi on 2017/3/7.
 */
angular.module('admin').controller('add_candidate', function ($scope, $http) {
    $scope.candidate = {};
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

    $scope.submit_one = function () {
        $http.post('/w_candidate', [$scope.candidate])
            .success(function (response) {
                if (response.data) {
                    alert("提交成功");
                    $scope.candidate = {};
                } else if (response.status == 2) {
                    alert("提名书号重复，请重新填写");
                } else {
                    alert("提交失败");
                }
            })

    };
    $scope.upload_csv = function () {
        $http.post('/w_candidate', $scope.csv.result)
            .success(function (response) {
                if (response.data) {
                    alert("上传成功");
                } else if (response.status == 2) {
                    alert("提名书号重复，请重新上传");
                } else {
                    alert("上传失败");
                }
            })
    }
});