# NowYouKnow

[![Build Status](https://travis-ci.org/cpe305/fall2016-project-axis7818.svg?branch=master)](https://travis-ci.org/cpe305/fall2016-project-axis7818)

A simple web app and API for asking and answering simple/stupid questions.

Cameron Taylor

---

# Data Entities

These objects are the main entities that are stored in the database. They exist in the `nowyouknow.common.data` package and represent database rows. Interactions with the database are done with the `nowyouknow.common.dao` package with CrudRepository interfaces.

## Topic

<dl>
   <dt>id: Long</dt>
   <dd>A unique identifier.</dd>

   <dt>name: String</dt>
   <dd>The name of the topic.</dd>

   <dt>questions: List<Question></dt>
   <dd>A list of all questions under this topic.</dd>
</dl>

## Question

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

## Answer

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

## Reaction

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

# REST Resources

## /topic

## /question

## /answer

---

# Testing

## Data Entities

## REST Resources

---
