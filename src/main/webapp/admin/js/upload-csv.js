/**
 * Created by lixindi on 2017/3/7.
 */
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