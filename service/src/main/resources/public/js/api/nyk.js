app.service('nykApi', [
   '$http',
function($http) {
   function nuke() {
      var request = "/end/of/the/universe";

      console.log("DELETE " + request);
      $http.delete(request);
   }

   return {
      nuke: nuke,
   };
}]);
