var app = angular.module('nowyouknow', ['ngRoute', 'ui.bootstrap']);

app.config(function($routeProvider) {
   $routeProvider
   .when("/", {
      templateUrl: "templates/browse.html",
      controller: "browseController",
   })
   .when("/browse", {
      templateUrl: "templates/browse.html",
      controller: "browseController",
   })
   .when("/ask", {
      templateUrl: "templates/ask.html",
      controller: "askController",
   })
   .when("/ask/:topicId", {
      templateUrl: "templates/ask.html",
      controller: "askController",
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
      redirect: "/browse",
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

   $scope.slogan = "A website where you can ask and answer simple/stupid questions.";

   $scope.goHome = function() {
      $location.path("/browse");
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

app.service("nowyouknow", [function() {
   var reactionTypes = ["like", "dislike", "laugh"];

   return {
      validateReactionType: function(reactionType) {
         if (reactionTypes.indexOf(reactionType) == -1) {
            throw "Invalid reaction type: " + reactionType;
            return false;
         }
         return true;
      },
   };
}]);

app.directive("nykReaction", [
   'nykQuestion',
   'nykAnswer',
function($question, $answer) {
   var linker = function(scope, elem, attrs) {
      console.log(scope.reaction);
      var reactFunction = null;

      if (scope.type === 'q') {
         console.log("linking nykReaction as question");
         reactFunction = $question.reactToQuestion;
      }
      else if (scope.type === 'a') {
         console.log("linking nykReaction as answer");
         reactFunction = $answer.reactToAnswer;
      }
      else {
         throw "Unknown reaction type: " + scope.type;
         return;
      }

      var makeReactionFunction = function(reactionType) {
         return function() {
            if (reactFunction) {
               reactFunction(scope.reaction.id, reactionType).then(function(success) {
                  if (success) {
                     scope.onReact(reactionType);
                  }
               });
            }
         };
      };
      scope.like = makeReactionFunction("like");
      scope.dislike = makeReactionFunction("dislike");
      scope.laugh = makeReactionFunction("laugh");
   };

   return {
      restrict: 'E',
      templateUrl: "templates/directives/reaction.html",
      scope: {
         reaction: '=',
         type: '@',
         onReact: '=',
      },
      link: linker,
   };
}]);
