var App = angular.module('google-buffer', []);

App.config(['$interpolateProvider', function ($interpolateProvider) {
    $interpolateProvider.startSymbol('{[');
    $interpolateProvider.endSymbol(']}');
}]);

var ProtoBuf = dcodeIO.ProtoBuf;

builder = ProtoBuf.loadProtoFile("/protos/domain.proto");
var root = builder.build();
Developer = root.com.aleksandrbogomolov.domain.Developer;
Software = root.com.aleksandrbogomolov.domain.Developer.Software;

App.controller('buffer-controller', ['$scope', '$http', function ($scope, $http) {
    // $scope.dev = {id: '', name: '', email : '', soft: []};
    $scope.newSoftware = {name: ""};

    $scope.getDeveloper = function () {
        var software = new Software({"name": $scope.name});
        var buffer = software.encode();

        var req = {
            method: 'POST',
            url: '/search',
            responseType: 'arraybuffer',
            transformRequest: function (r) {
                return r;
            },
            data: buffer.toArrayBuffer(),
            headers: {
                'Content-Type': 'application/x-protobuf'
            }
        };

        $http(req).success(function (data) {
            var dev = Developer.decode(data);
            // $scope.dev = data;
        });
    }
}]);
