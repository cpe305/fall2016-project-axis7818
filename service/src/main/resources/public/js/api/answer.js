app.service('nykAnswer', [
   '$http',
   '$q',
   'nowyouknow',
function($http, $q, $nyk) {

   function postAnswer(answer) {
      var request = "/answer/";
      var def = $q.defer();

      console.log("POST " + request);
      $http.post(request, answer).success(function(data, status, headers, config) {
         console.log("posted answer");
         console.log(headers("Location"));
         def.resolve(parseInt(headers("Location").split("/").pop()));
      }).error(function() {
         console.log("An error occurred while posting answer");
         def.resolve(null);
      });

      return def.promise;
   }

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
      postAnswer: postAnswer,
   };
}]);
