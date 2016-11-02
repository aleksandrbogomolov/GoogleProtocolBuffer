'use strict';
var App = angular.module('google-buffer', []);

App.config(['$interpolateProvider', function ($interpolateProvider) {
    $interpolateProvider.startSymbol('{[');
    $interpolateProvider.endSymbol(']}');
}]);

var ProtoBuf = dcodeIO.ProtoBuf;

var builder = ProtoBuf.loadProtoFile("/protos/domain.proto");
var root = builder.build();
var Software = root.com.aleksandrbogomolov.domain.Developer.Software;

App.controller('buffer-controller', ['$scope', '$http', function ($scope, $http) {

    $scope.getDeveloper = function () {
        var software = new Software({"name": $scope.name});
        var buffer = software.encode();

        var req = {
            method: 'POST',
            url: '/search',
            responseType: 'buffer',
            transformRequest: function (r) {
                return r;
            },
            data: buffer.toArrayBuffer(),
            headers: {
                'Content-Type': 'application/x-protobuf'
            }
        };

        $http(req).success(function (data) {
            var div = document.getElementById('dev');
            var p = document.createElement('p');
            p.innerHTML = 'id:' + data.id + ', name: ' + data.name + ', email: ' + data.email + ', Software: ' + data.soft[0].name + ', ' + data.soft[1].name;
            div.appendChild(p);
        });
    }
}]);
