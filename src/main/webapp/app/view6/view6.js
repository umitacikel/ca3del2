'use strict';

angular.module('myApp.view6', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view6', {
    templateUrl: 'app/view6/view6.html',
    controller: 'View6Ctrl',
    controllerAs : 'ctrl6'
  });
}])

.controller('View6Ctrl', ["InfoFactory","InfoService",function(InfoFactory,InfoService) {
 
}]);