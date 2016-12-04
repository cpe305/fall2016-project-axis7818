app.service('nykTopic', [
   '$http',
   '$q',
function($http, $q) {

   function postTopic(topic) {
      var request = "/topic/";
      var def = $q.defer();

      console.log("POST " + request);
      $http.post(request, topic).then(function(response) {
         def.resolve(response);
      }).catch(function(data, status, heaers, config) {
         console.log(data);
         def.resolve(null);
      });

      return def.promise;
   }

   function getAllTopics() {
      var request = "/topic/";
      var def = $q.defer();

      console.log("GET " + request);
      $http.get(request).success(function(data) {
         console.log("Found " + data.length + " topics.");
         def.resolve(data);
      }).error(function(data, status, headers, config) {
         console.log(data);
         def.resolve([]);
      });

      return def.promise;
   }

   function getTopic(identifier) {
      var request = "/topic/" + identifier;
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

   function getQuestions(identifier) {
      var request = "/topic/" + identifier + "/questions";
      var def = $q.defer();

      console.log("GET " + request);
      $http.get(request).success(function(data) {
         console.log("Found " + data.length + " questions.");
         def.resolve(data);
      }).error(function(data, status, headers, config) {
         console.log(data);
         def.resolve([]);
      });

      return def.promise;
   }

   function deleteTopic(identifier) {
      var request = "/topic/" + identifier;

      console.log("DELETE " + request);
      $http.delete(request).error(function(data, status, headers, config) {
         console.log(data);
      });
   }

   return {
      getAllTopics: getAllTopics,
      getTopic: getTopic,
      getQuestions: getQuestions,
      deleteTopic: deleteTopic,
      postTopic: postTopic,
   };
}]);
