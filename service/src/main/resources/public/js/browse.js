app.controller("browseController", [
   '$scope',
   'nykQuestion',
function($scope, $question) {
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
   });
}]);
