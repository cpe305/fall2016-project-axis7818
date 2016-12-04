var app = angular.module('nowyouknow', ['ngRoute', 'ui.bootstrap']);

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
   .when("/question/:questionId", {
      templateUrl: "templates/question.html",
      controller: "questionController",
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
      controller: "aboutController",
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

app.controller('aboutController', [
   '$scope',
   'nykDialog',
   'nykApi',
function($scope, $dialog, $nyk) {
   console.log("Initializing aboutController");

   var warning = "This action deletes everything from the database. Are you sure you want to continue?";

   $scope.nuke = function() {
      $dialog.confirm($scope, "Warning!", warning).then(function() {
         $nyk.nuke();
         $dialog.notify($scope, "Don't Panic.", "So long, and thanks for all the fish.");
      });
   };
}]);

app.directive("nykReaction", [
   'nykQuestion',
   'nykAnswer',
function($question, $answer) {
   var linker = function(scope, elem, attrs) {
      if (scope.type === 'q') {
         console.log("linking nykReaction as question");
      }
      else if (scope.type === 'a') {
         console.log("linking nykReaction as answer");
      }
      else {
         throw "Unknown reaction type: " + scope.type;
      }

      scope.like = function() {
         console.log("like");
      };

      scope.dislike = function() {
         console.log("dislike");
      };

      scope.laugh = function() {
         console.log("laugh");
      };
   };

   return {
      restrict: 'E',
      templateUrl: "templates/directives/reaction.html",
      scope: {
         reaction: '=',
         type: '@',
      },
      link: linker,
   };
}]);
