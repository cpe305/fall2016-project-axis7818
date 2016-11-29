app.service('nykMain', [function() {
   function logError(response) {
      console.log("Error: " + response.status);
   }

   return {
      logError: logError,
   };
}]);
