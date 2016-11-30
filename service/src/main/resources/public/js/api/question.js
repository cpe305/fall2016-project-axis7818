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

   return {
      getRandom: getRandom,
   };
}]);
