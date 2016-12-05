app.controller("askController", [
   '$scope',
   '$routeParams',
   'nykTopic',
   'nykQuestion',
function($scope, $routeParams, $topic, $question) {
   console.log("Initializing the askController");

   $scope.topicChoice = {
      topics: [],       // a list of topic objects
      topicId: null,    // the id of the currently selected topic
   };

   $scope.question = {
      text: "",         // the question text
      topicId: null,    // the id of the topic (optional)
   };

   // get all topics
   $topic.getAllTopics().then(function(topics) {
      $scope.topicChoice.topics = topics;
   }).then(function() {
      if (!$routeParams.topicId) return;

      // set the current topic
      if ($scope.topicChoice.topics.find(function(topic) { return topic.id == $routeParams.topicId })) {
         $scope.topicChoice.topicId = $routeParams.topicId;
      }
   });
}]);
