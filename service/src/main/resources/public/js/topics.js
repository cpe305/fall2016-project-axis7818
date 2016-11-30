app.controller("topicsController", [
   '$scope',
   '$location',
   'nykTopic',
function($scope, $location, $topic) {
   $scope.topics = [];

   $scope.topicClicked = function(id) {
      console.log("Topic " + id + " clicked.");
      $location.path("/topics/" + id);
   };

   console.log("Initializing topicsController");
   $topic.getAllTopics().then(function(topics) {
      $scope.topics = topics;
   });
}]);

app.controller("topicController", [
   '$scope',
   '$routeParams',
   '$location',
   'nykTopic',
function($scope, $routeParams, $location, $topic) {
   $scope.topic = {};

   $scope.deleteTopic = function(topicId) {
      $topic.deleteTopic(topicId);
      $location.path("/topics");
   };

   console.log("Initializing topicController");
   $topic.getTopic($routeParams.topicId).then(function(topic) {
      $scope.topic = topic;
      $scope.topic.questions = [];

      if (topic != null) {
         $topic.getQuestions(topic.id).then(function(questions) {
            $scope.topic.questions = questions;
         });
      }
   });
}]);

app.directive("nykTopic", [
   '$location',
function($location) {
   function handleQuestionClicked(question) {
      $location.path("/question/" + question.id);
   }

   return {
      restrict: 'E',
      templateUrl: "templates/directives/topic.html",
      scope: {
         topic: '=',
      },
      link: function(scope, elem, attrs) {
         console.log("linking nykTopic");
         scope.questionClick = handleQuestionClicked;
      }
   };
}]);
