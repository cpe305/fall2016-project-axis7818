# NowYouKnow

[![Build Status](https://travis-ci.org/cpe305/fall2016-project-axis7818.svg?branch=master)](https://travis-ci.org/cpe305/fall2016-project-axis7818)

A simple web app and API for asking and answering simple/stupid questions.

Cameron Taylor

---

![topic](https://raw.githubusercontent.com/cpe305/fall2016-project-axis7818/master/docs/topic.png)

---

![question](https://raw.githubusercontent.com/cpe305/fall2016-project-axis7818/master/docs/question.png)

---

![random](https://raw.githubusercontent.com/cpe305/fall2016-project-axis7818/master/docs/random.png)

---

# System Architecture

Now You Know is built with a 3 tier service oriented architecture. All of the database infrastructure is stored in the `nowyouknow.common` package, all of the service infrastructure is stored in the `nowyouknow.service` package, and all of the front-end code is stored in `service/src/main/resources/public` and served statically.

![system architecture](https://raw.githubusercontent.com/cpe305/fall2016-project-axis7818/master/docs/SystemArchitecture.png)

---

# Data Entities

These objects are the main entities that are stored in the database. They exist in the `nowyouknow.common.data` package and represent database rows. Interactions with the database are done with the `nowyouknow.common.dao` package with CrudRepository interfaces. All Dao objects extend  Spring's [CrudRepository](http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html). Only additional methods are documented here.

## Class Diagram
![class diagram](https://raw.githubusercontent.com/cpe305/fall2016-project-axis7818/master/docs/Class%20Diagram.png)

## [Topic](https://github.com/cpe305/fall2016-project-axis7818/blob/master/common/src/main/java/nowyouknow/common/data/Topic.java)

<dl>
   <dt>id: Long</dt>
   <dd>A unique identifier.</dd>

   <dt>name: String</dt>
   <dd>The name of the topic.</dd>

   <dt>description: String</dt>
   <dd>A short description of the topic.</dd>

   <dt>questions: List<Question></dt>
   <dd>A list of all questions under this topic.</dd>
</dl>

### [TopicDao](https://github.com/cpe305/fall2016-project-axis7818/blob/master/common/src/main/java/nowyouknow/common/dao/TopicDao.java)

<dl>
   <dt>findByName(name: String): Topic</dt>
   <dd>Finds a Topic object by its name.</dd>
</dl>

## [Question](https://github.com/cpe305/fall2016-project-axis7818/blob/master/common/src/main/java/nowyouknow/common/data/Question.java)

<dl>
   <dt>id: Long</dt>
   <dd>A unique identifier.</dd>

   <dt>text: String</dt>
   <dd>The question itself.</dd>

   <dt>open: Boolean</dt>
   <dd>Whether or not a question is open for being answered or edited.</dd>

   <dt>whenAsked: Date</dt>
   <dd>The point in time when the question was asked.</dd>

   <dt>topic: Topic</dt>
   <dd>The topic that the question falls under. Can be null.</dd>

   <dt>reaction: Reaction</dt>
   <dd>The reaction object for this question. Cannot be null</dd>

   <dt>answers: List<Answer></dt>
   <dd>A list of all answers for this question.</dd>
</dl>

## [Answer](https://github.com/cpe305/fall2016-project-axis7818/blob/master/common/src/main/java/nowyouknow/common/data/Answer.java)

<dl>
   <dt>id: Long</dt>
   <dd>A unique identifier</dd>

   <dt>text: String</dt>
   <dd>The actual answer.</dd>

   <dt>whenAnswered: Date</dt>
   <dd>The point in time when the answer was made.</dd>

   <dt>question: Question</dt>
   <dd>The question that this Answer answers. Cannot be null.</dd>

   <dt>reaction: Reaction</dt>
   <dd>The reaction object for this Answer. Cannot be null</dd>
</dl>

## [Reaction](https://github.com/cpe305/fall2016-project-axis7818/blob/master/common/src/main/java/nowyouknow/common/data/Reaction.java)

<dl>
   <dt>id: Long</dt>
   <dd>A unique identifier.</dd>

   <dt>likes: Integer</dt>
   <dd>The number of times this object was 'liked'.</dd>

   <dt>dislikes: Integer</dt>
   <dd>The number of times this object was 'disliked'.</dd>

   <dt>laughs: Integer</dt>
   <dd>The number of times this object was 'laughed'.</dd>
</dl>

---

# System Design

## Architecture

### MVC

The MVC pattern is used in two places: service controllers and Angular's front end.
The service controllers hold methods (controller) that house business logic and access the database (model) accordingly.
The results of these operations are passed to the front-end (view) when they have completed.

Angular takes the resulting data from API calls and builds its own javascript objects to act as a model local to the browser.
These model objects are presented in html form and controllers written in javascript handle user interactions, form validation, and communication with the service.

### Dependency Injection

The pattern of dependency injection is used throughout the entire project.
All java classes are valid beans and all javascript components are wrapped as appropriate angular components.
This means that every object in the system can be mocked and injected into other objects according to the needs of that object.
For example, when testing the controllers, the DAO objects are mocked so that they do not perform any database accesses.
This allows for true unit testing that isolates the controllers' behavior.

## Design Patterns

As it stands now, there are no GoF design patterns present in the NowYouKnow codebase.
This is on purpose as there were no opportunities to leverage any of these design patterns in an appropriate fashion.
I had intended to add a `Reactable` interface to use a bridge pattern, but since my Question and Answer classes needed to map directly to database tables, this was hard to do while preserving ORM (Object Relational Mapping).

---

# Key Learning

The thing that I liked the best about my project was the use of dependency injection. Not only did this allow for strong and thorough unit tests, but it also allowed me to better take advantage of functionality offered by the Spring framework. The code that I wrote in this project follows "best practice" conventions closer than any other code I have written, and even though the overall project is not super complex, it is beautiful code to look at.

The most important lesson that I learned was that Software Engineering is not just writing code. I already knew this, but I never had an appreciation for just how important it is to write good documentation and testing. Writing actual system code only took about 20% of my time working on the project.

---

# REST Resources

Topic, Question, and Answer objects each have a corresponding class in `nowyouknow.service.results` that represents a flattened version of the object. These are the entities that are converted to/from json request bodies.

[JsonTopic](https://github.com/cpe305/fall2016-project-axis7818/blob/master/service/src/main/java/nowyouknow/service/results/JsonTopic.java)
```
{
   "id": 1,
   "name": "Hitchhiker's Guide To The Galaxy"
}
```

[JsonQuestion](https://github.com/cpe305/fall2016-project-axis7818/blob/master/service/src/main/java/nowyouknow/service/results/JsonQuestion.java)
```
{
   "id": 1,
   "text": "What is the answer to life, the universe, and everything?",
   "open": true,
   "whenAsked": 57392084732,
   "topicId": 1,
   "likes": 894,
   "dislikes": 32,
   "laughs": 123
}
```

[JsonAnswer](https://github.com/cpe305/fall2016-project-axis7818/blob/master/service/src/main/java/nowyouknow/service/results/JsonAnswer.java)
```
{
   "id": 1,
   "text": "42",
   "questionId": 1,
   "likes": 42,
   "dislikes": 0,
   "laughs": 3
}
```

## `/topic`

Requests to this URI are handled by [TopicController](https://github.com/cpe305/fall2016-project-axis7818/blob/master/service/src/main/java/nowyouknow/service/controllers/TopicController.java).

### `POST /topic/`
```
{
   "name": "New Topic"
}
```
Post a new topic. The name must be less than 257 characters and a topic of the same name cannot already exist in the database.

### `GET /topic/`
Retrieves a list of all topics.

### `GET /topic/{identifier}`
Retrieve a single topic. The identifier can be the topic's name or id.

### `GET /topic/{identifier}/questions`
Retrieve a list of all questions for a given topic. The identifier can be the topic's name or id.

### `PUT /topic/{identifier}`
```
{
   "name": "New Name"
}
```
Update a topic identified by `{identifier}` with the body parameters. A topic with the same name cannot exist in the database.

### `DELETE /topic/{identifier}`
Delete a topic. Identifier can be the id or name.

## `/question`

Requests to this URI are handled by [QuestionController](https://github.com/cpe305/fall2016-project-axis7818/blob/master/service/src/main/java/nowyouknow/service/controllers/QuestionController.java).

### `POST /question/`

### `GET /question/{id}`

### `GET /question/{id}/answers`

### `PUT /question/{id}`

### `DELETE /question/{id}`

## `/answer`

Requests to this URI are handled by [AnswerController](https://github.com/cpe305/fall2016-project-axis7818/blob/master/service/src/main/java/nowyouknow/service/controllers/AnswerController.java).

### `POST /answer/`

### `PUT /answer/{id}`

### `GET /answer/{id}`

---
