app.service("nykDialog", [
   '$uibModal',
function($uibModal) {
   function confirmDlg(scope, header, body) {
      scope.header = header;
      scope.bodyContent = body;
      scope.showCancel = true;
      return $uibModal.open({
         templateUrl: "templates/dialog/dialog.html",
         scope: scope,
         size: 'sm',
      }).result;
   }

   function notifyDlg(scope, header, body) {
      scope.header = header;
      scope.bodyContent = body;
      scope.showCancel = false;
      return $uibModal.open({
         templateUrl: "templates/dialog/dialog.html",
         scope: scope,
         size: 'sm',
      }).result;
   }

   function createTopicDlg(scope) {
      scope.dlgTopic = {
         name: "",
         description: "",
      };

      scope.validateTopic = function(newTopic) {
         console.log("validateTopic");
         var result = true;

         // validate the name
         if (!!!newTopic.name) {
            console.log("bad name...");
            scope.showTopicNameRequred = true;
            result = false;
         }

         return result;
      };

      scope.showTopicNameRequred = false;

      return $uibModal.open({
         templateUrl: "templates/dialog/createTopic.html",
         scope: scope,
         size: 'lg',
      }).result;
   }

   return {
      confirm: confirmDlg,
      notify: notifyDlg,
      createTopic: createTopicDlg,
   };
}]);
