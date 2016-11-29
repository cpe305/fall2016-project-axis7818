var app = angular.module('nowyouknow', []);

app.controller('homeController', [
   '$scope',
   'nykTopic',
   'nykQuestion',
   'nykAnswer',
function($scope, $topic, $question, $answer) {

   $topic.getAll(function(topics) {
      topics.forEach(function(topic) {

         $topic.get(topic.id, function(topic) {
            $topic.getQuestions(topic.id, function(questions) {
               console.log("---");
               questions.forEach(function(question) {
                  console.log(question);
               });
            });
         });

         $topic.deleteOne(topic.id);
      });
   });

}]);
