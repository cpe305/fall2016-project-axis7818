var app = angular.module('nowyouknow', ['ngRoute']);

// Configure page routing
app.config(function($routeProvider) {
   $routeProvider

   .when('/', {
      templateUrl: 'templates/home.html',
      controller: 'homeController',
   })

   .when('/topics', {
      templateUrl: 'templates/topics.html',
      controller: 'topicsController',
   });
});

app.controller('homeController', ['$scope', function($scope) {
   $scope.greeting = "Greetings from HOME!";
}]);

app.controller('topicsController', ['$scope', function($scope) {
   $scope.greeting = "Greetings from TOPIC!";
}])
