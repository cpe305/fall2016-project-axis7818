app.service('nykQuestion', [
   '$http',
   '$q',
   'nowyouknow',
function($http, $q, $nyk) {

   function getRandom(excludeId) {
      var request = "/question/random";
      var def = $q.defer();

      console.log("GET " + request);
      $http.get(request, {
         params: {
            excludeId: excludeId,
         },
      }).success(function(data) {
         console.log(data);
         def.resolve(data);
      }).error(function(data, status, headers, config) {
         console.log(data);
         def.resolve(null);
      });

      return def.promise;
   }

   function getQuestion(questionId) {
      var request = "/question/" + questionId;
      var def = $q.defer();

      console.log("GET " + request);
      $http.get(request).success(function(data) {
         console.log(data);
         def.resolve(data);
      }).error(function(data, status, headers, config) {
         console.log(data);
         def.resolve(null);
      });

      return def.promise;
   }

   function getQuestionAnswers(questionId) {
      var request = "/question/" + questionId + "/answers";
      var def = $q.defer();

      console.log("GET " + request);
      $http.get(request).success(function(data) {
         console.log("Got " + data.length + " answers.");
         def.resolve(data);
      }).error(function(data, status, headers, config) {
         console.log(data);
         def.resolve([]);
      });

      return def.promise;
   }

   function reactToQuestion(questionId, reactionType) {
      var request = "/question/" + questionId + "/react/" + reactionType;
      var def = $q.defer();
      if (!$nyk.validateReactionType(reactionType)) {
         def.resolve(false);
         return def.promise;
      }

      console.log("PUT " + request);
      $http.put(request).success(function() {
         console.log(reactionType + " question success");
         def.resolve(true);
      }).error(function(data, status, headers, config) {
         console.log(data);
         def.resolve(false);
      });

      return def.promise;
   }

   function postQuestion(question) {
      var request = "/question/";
      var def = $q.defer();

      console.log("POST " + request);
      $http.post(request, question).success(function(data, status, headers) {
         console.log("posted question: " + headers("Location"));
         def.resolve(parseInt(headers("Location").split("/").pop()));
      }).error(function() {
         console.log("An error occurred while posting question");
         def.resolve(null);
      });

      return def.promise;
   }

   function getAllQuestions() {
      var request = "/question/";
      var def = $q.defer();

      console.log("GET " + request);
      $http.get(request).success(function(data) {
         console.log("found " + data.length + " questions");
         def.resolve(data);
      }).error(function(data) {
         console.log(data);
         def.resolve([]);
      });

      return def.promise;
   }

   return {
      getRandom: getRandom,
      getQuestion: getQuestion,
      getQuestionAnswers: getQuestionAnswers,
      reactToQuestion: reactToQuestion,
      getAllQuestions: getAllQuestions,
      postQuestion: postQuestion,
   };
}]);
