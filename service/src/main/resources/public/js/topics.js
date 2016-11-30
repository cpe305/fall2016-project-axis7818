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
   'nykTopic',
function($scope, $routeParams, $topic) {
   $scope.topic = {};
   $scope.questions = [];

   console.log("Initializing topicController");
   $topic.getTopic($routeParams.topicId).then(function(topic) {
      $scope.topic = topic;
      $topic.getQuestions(topic.id).then(function(questions) {
         $scope.questions = questions;
      })
   });
}]);
