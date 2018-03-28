'use strict';
const myApp2 = angular.module('myApp');

myApp2.controller('webGrabbingCtrl', function ($scope, $http) {
    $scope.formUrl = "http://www.cangqionglongqi.com/fanrenxiuxianzhuan/";
    $scope.formUrlData = [{"id": "one", "info": ""}, {"id": "two", "info": ""}];
    $scope.getFormUrlData = function () {
        $http.get("http://localhost:8080/getUrlData?url=" + $scope.formUrl).then(function (data) {
            $scope.formUrlData = data.data;
        }, function () {

        });
    }
});

myApp2.controller('chapterController', function ($scope, $http, $stateParams) {
    $scope.chapter = {"title": "", "info": ""};
    $http.get("http://localhost:8080/getChapterInfo?url=" + $scope.formUrl + $stateParams.cid).then(function (res) {
        $scope.chapter = res.data;
    }, function () {

    })
});

myApp2.service('formUrlService', function ($http, $q) {

});