app.controller("askController", [
   '$scope',
   '$routeParams',
   '$location',
   'nykTopic',
   'nykQuestion',
function($scope, $routeParams, $location, $topic, $question) {
   console.log("Initializing the askController");

   $scope.topic = null;

   $scope.question = {
      text: "",         // the question text
      topicId: null,    // the id of the topic (optional)
   };

   $scope.askQuestion = function() {
      $question.postQuestion($scope.question).then(function(questionId) {
         if (questionId) {
            $location.path("/question/" + questionId);
         }
      });
   };

   if ($routeParams.topicId) {
      $topic.getTopic($routeParams.topicId).then(function(topic) {
         $scope.topic = topic;
         $scope.question.topicId = topic.id;
      });
   }

}]);
