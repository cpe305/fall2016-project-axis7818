app.controller("randomController", [
   '$scope',
   'nykQuestion',
function($scope, $question) {
   $scope.question = null;

   $scope.refresh = function() {
      var currentId = $scope.question && $scope.question.id;
      $question.getRandom(currentId).then(function(question) {
         $scope.question = question;
         $scope.question.answers = [];
         $question.getQuestionAnswers(question.id).then(function(answers) {
            $scope.question.answers = answers;
         });
      });
   }

   console.log("Initializing randomController");
   $scope.refresh();
}]);

app.controller("questionController", [
   '$scope',
   '$routeParams',
   'nykQuestion',
function($scope, $routeParams, $question) {
   $scope.question = null;

   $question.getQuestion($routeParams.questionId).then(function(question) {
      $scope.question = question;
      $scope.question.answers = [];
      $question.getQuestionAnswers(question.id).then(function(answers) {
         $scope.question.answers = answers;
      });
   });

   console.log("Initializing questionController");
}]);

app.directive("nykQuestion", [function() {
   return {
      restrict: 'E',
      templateUrl: "templates/directives/question.html",
      scope: {
         question: '=',
      },
   };
}]);
