'use strict';

// Declare app level module which depends on views, and components
var myApp = angular.module('myApp', [
    'ui.router'
]);
myApp.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/main');
    $stateProvider
        .state('main', {
            url: '/main',
            templateUrl: '../tpls/main.html'
        }).state('bookList',
        {
            url: '/bookList',
            templateUrl: '../tpls/bookshelf/bookList.html'
        }).state('bookList.historyList',
        {
            url: "/historyList",
            templateUrl: '../tpls/bookshelf/bookListInfo.html',
            controller: 'app-controller'
        }).state('bookList.kungfuList',
        {
            url: "/kungfuList",
            template: `<div> 武侠 <div>`
        }).state('bookList.fictionalList',
        {
            url: "/fictionalList",
            template: `<div> 玄幻 <div>`
        }).state('bookList.fairyTaleList',
        {
            url: "/fairyTaleList",
            template: `<div> 仙侠 <div>`
        }).state('bookList.urbanList',
        {
            url: "/urbanList",
            template: `<div> 都市 <div>`
        }).state('bookInfo',
        {
            url: "/bookInfo/{bid}",
            templateUrl: "../tpls/bookshelf/bookInfo.html",
            controller: 'book-info-controller'
        }).state('bookStore',
        {
            url: '/bookStore',
            templateUrl: '../tpls/bookstore/boostore.html'
        })


}]);
myApp.service('getBookListService', function ($http, $q) {
    this.name = "service";
    this.getHisBookList = function () {
        var res = $q.defer();
        $http({
            method: "GET",
            url: '../data/hisBookList.json'
        }).then(function (data) {
            res.resolve(data);
        }, function (da) {
            res.reject('error');
        });
        return res.promise;
    }
});
myApp.controller('app-controller', function ($scope, getBookListService) {
    $scope.message = 'test';
    getBookListService.getHisBookList().then(function (data) {
        $scope.hisbooks = data.data;
    }, function (data) {
        console.log(data);
    });
});
myApp.controller('book-info-controller', function ($scope, $http, $stateParams) {

    $http.get("../data/hisBookList.json").then(function (data) {
        for (var i = 0; i < data.data.length; i++) {
            if ($stateParams.bid == parseInt(data.data[i].id)) {
                $scope.book = data.data[i];
            }
        }
    }, function (rrr) {
        console.log(rrr);
    });
});

