var app = angular.module('nowyouknow', ['ngRoute']);

app.config(function($routeProvider) {
   $routeProvider
   .when("/", {
      templateUrl: "templates/home.html",
   })
   .when("/ask", {
      templateUrl: "templates/ask.html",
   })
   .when("/random", {
      templateUrl: "templates/random.html",
      controller: "randomController",
   })
   .when("/topics", {
      templateUrl: "templates/topics.html",
      controller: "topicsController",
   })
   .when("/topics/:topicId", {
      templateUrl: "templates/topic.html",
      controller: "topicController",
   })
   .when("/about", {
      templateUrl: "templates/about.html",
   })
   .otherwise({
      redirect: "/",
   });
});

app.controller('indexController', [
   '$scope',
   '$location',
   'nykTopic',
   'nykQuestion',
   'nykAnswer',
function($scope, $location, $topic, $question, $answer) {
   console.log("Initializing indexController");

   $scope.slogan = "A web service where you can ask and answer simple/stupid questions.";

   $scope.goHome = function() {
      $location.path("/");
   }
}]);
