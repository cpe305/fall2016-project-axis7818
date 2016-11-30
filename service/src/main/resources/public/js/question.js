app.controller("randomController", [
   '$scope',
   'nykQuestion',
function($scope, $question) {
   $scope.question = null;

   $scope.refresh = function() {
      var currentId = $scope.question && $scope.question.id;
      $question.getRandom(currentId).then(function(question) {
         $scope.question = question;
      });
   }

   console.log("Initializing randomController");
   $scope.refresh();
}]);
