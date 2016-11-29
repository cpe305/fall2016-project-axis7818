app.service('nykTopic', [
   '$http',
   'nykMain',
function($http, $nykMain) {

   // Retrieve a list of all topics and call `callback`
   // with the topics array as the first parameter.
   function getAll(callback) {
      var request = "/topic/";
      console.log("GET " + request);
      $http.get(request).then(
         function(response) {
            console.log("Found " + response.data.length + " topics.");
            if (callback)
               callback(response.data);
         }, $nykMain.logError
      );
   }

   // Retrieve a topic by its id/name and call `callback`
   // with the resulting topic as the first parameter.
   function get(identifier, callback) {
      var request = "/topic/" + identifier;
      console.log("GET " + request);
      $http.get(request).then(
         function(response) {
            console.log("Found Topic (" + identifier + ")");
            if (callback)
               callback(response.data);
         }, $nykMain.logError
      );
   }

   // Retrieve an array of questions for a topic and call
   // `callback` with the array as the first parameter
   function getQuestions(identifier, callback) {
      var request = "/topic/" + identifier + "/questions";
      console.log("GET " + request);
      $http.get(request).then(
         function(response) {
            console.log("Found " + response.data.length + " questions.");
            if (callback)
               callback(response.data);
         }, $nykMain.logError
      );
   }

   // Deletes a topic by id/name
   function deleteOne(identifier) {
      var request = "/topic/" + identifier;
      console.log("DELETE " + request);
      $http.delete(request).then(
         function(response) {
            console.log("Deleted Topic (" + identifier + ")");
         }, $nykMain.logError
      );
   }

   return {
      getAll: getAll,
      get: get,
      getQuestions: getQuestions,
      deleteOne: deleteOne,
   };
}]);
