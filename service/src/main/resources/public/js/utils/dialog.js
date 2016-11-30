app.service("nykDialog", [
   '$uibModal',
function($modal) {
   function confirmDlg() {
      console.log("confirmDlg");
   }

   function notifyDlg() {
      console.log("notifyDlg");
   }

   function createTopicDlg() {
      console.log("createTopicDlg");
   }

   return {
      confirm: confirmDlg,
      notify: notifyDlg,
      createTopic: createTopicDlg,
   };
}]);
