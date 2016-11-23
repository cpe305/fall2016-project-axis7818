var app = angular.module('nowyouknow', []);

app.controller('home', function($scope) {
   $scope.greeting = {
      id: 'xxx',
      content: "Hello World!",
   };
});
