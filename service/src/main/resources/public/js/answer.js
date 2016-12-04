app.directive("nykAnswer", [function() {
   function linker(scope, elem, attrs) {
      console.log("linking nykAnswer directive");

      scope.reactToAnswer = function(reactionType) {
         if (reactionType === "like") {
            ++scope.answer.likes;
         }
         else if (reactionType === "dislike") {
            ++scope.answer.dislikes;
         }
         else if (reactionType === "laugh") {
            ++scope.answer.laughs;
         }
      };
   }

   return {
      restrict: 'E',
      templateUrl: "templates/directives/answer.html",
      scope: {
         answer: '=',
      },
      link: linker,
   };
}]);
