app.controller("randomController", [
   '$scope',
   'nykQuestion',
   'nykTopic',
function($scope, $question, $topic) {
   $scope.question = null;

   $scope.refresh = function() {
      var currentId = $scope.question && $scope.question.id;
      $question.getRandom(currentId).then(function(question) {
         $scope.question = question;

         // Get Answers
         $scope.question.answers = [];
         $question.getQuestionAnswers(question.id).then(function(answers) {
            $scope.question.answers = answers;
         });

         // Get Topic
         $scope.question.topic = null;
         if ($scope.question.topicId) {
            $topic.getTopic($scope.question.topicId).then(function(topic){
               $scope.question.topic = topic;
            });
         }
      });
   }

   console.log("Initializing randomController");
   $scope.refresh();
}]);

app.controller("questionController", [
   '$scope',
   '$routeParams',
   'nykQuestion',
   'nykTopic',
function($scope, $routeParams, $question, $topic) {
   $scope.question = null;

   $question.getQuestion($routeParams.questionId).then(function(question) {
      $scope.question = question;

      // get answers
      $scope.question.answers = [];
      $question.getQuestionAnswers(question.id).then(function(answers) {
         $scope.question.answers = answers;
      });

      // get topic
      $scope.question.topic = null;
      if (question.topicId) {
         $topic.getTopic(question.topicId).then(function(topic) {
            $scope.question.topic = topic;
         });
      }
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
