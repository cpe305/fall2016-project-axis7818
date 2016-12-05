app.controller("topicsController", [
   '$scope',
   '$location',
   'nykTopic',
   'nykDialog',
function($scope, $location, $topic, $dialog) {
   console.log("Initializing topicsController");

   $scope.topics = [];

   $scope.topicClicked = function(id) {
      console.log("Topic " + id + " clicked.");
      $location.path("/topics/" + id);
   };

   $scope.newTopicClicked = function() {
      $dialog.createTopic($scope).then(function(newTopic) {
         $topic.postTopic(newTopic).then(function() {
            console.log("topic made");
            loadTopics();
         });
      });
   }

   function loadTopics() {
      $topic.getAllTopics().then(function(topics) {
         $scope.topics = topics;
      });
   }

   loadTopics();
}]);

app.controller("topicController", [
   '$scope',
   '$routeParams',
   '$location',
   'nykDialog',
   'nykTopic',
function($scope, $routeParams, $location, $dialog, $topic) {
   var gotoTopics = function() {
      $location.path("/topics");
   };

   $scope.topic = {};

   $scope.askQuestion = function() {
      if ($scope.topic && $scope.topic.id) {
         $location.path("/ask/" + $scope.topic.id);
      }
   };

   $scope.deleteTopic = function(topicId) {
      var warning = "Are you sure you want to delete " + $scope.topic.name + " and all of the questions under it?";
      $dialog.confirm($scope, "Delete Topic?", warning).then(function() {
         $topic.deleteTopic(topicId);
         $dialog.notify($scope, "Don't Panic.", "So long, and thanks for all the fish.").then(gotoTopics).catch(gotoTopics);
      });
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
