app.service('nykQuestion', [
   '$http',
   '$q',
function($http, $q) {

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

   return {
      getRandom: getRandom,
      getQuestion: getQuestion,
      getQuestionAnswers: getQuestionAnswers,
   };
}]);
