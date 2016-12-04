app.service('nykAnswer', [
   '$http',
   '$q',
   'nowyouknow',
function($http, $q, $nyk) {

   function reactToAnswer(answerId, reactionType) {
      var request = "/answer/" + answerId + "/react/" + reactionType;
      var def = $q.defer();
      if (!$nyk.validateReactionType(reactionType)) {
         def.resolve(false);
         return def.promise;
      }

      console.log("PUT " + request);
      $http.put(request).success(function() {
         console.log(reactionType + " answer success");
         def.resolve(true);
      }).error(function(data) {
         console.log(data);
         def.resolve(false);
      });

      return def.promise;
   }

   return {
      reactToAnswer: reactToAnswer,
   };
}]);
