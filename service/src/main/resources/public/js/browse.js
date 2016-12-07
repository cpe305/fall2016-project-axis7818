app.controller("browseController", [
   '$scope',
   'nykQuestion',
   'nykTopic',
function($scope, $question, $topic) {
   console.log("initializing browseController");

   $scope.recentlyAsked = [];
   $scope.mostLiked = [];

   $question.getAllQuestions().then(function(questions) {
      // find the most recently asked
      questions.sort(function(a, b) {
         return a.whenAsked < b.whenAsked;
      });
      $scope.recentlyAsked = questions.slice(0, 5);

      // find the most liked
      questions.sort(function(a, b) {
         return a.likes < b.likes;
      })
      $scope.mostLiked = questions.slice(0, 5);

      // set any topics
      $topic.getAllTopics().then(function(data) {
         var topics = {};
         data.forEach(function(topic) {
            topics[topic.id] = topic;
         });

         var setTopic = function(question) {
            if (question.topicId) {
               question.topic = topics[question.topicId];
            }
         };
         $scope.recentlyAsked.forEach(setTopic);
         $scope.mostLiked.forEach(setTopic);
      });
   });
}]);
