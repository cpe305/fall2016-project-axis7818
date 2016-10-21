drop database if exists nowyouknow;
create database nowyouknow;
use nowyouknow;

CREATE TABLE Topic (
	id int(11) AUTO_INCREMENT primary key,
	name varchar(32) NOT NULL DEFAULT ''
);

CREATE TABLE Reaction (
	id int(11) AUTO_INCREMENT primary key,
	likes int(11) NOT NULL DEFAULT 0,
	dislikes int(11) NOT NULL DEFAULT 0,
	laughs int(11) NOT NULL DEFAULT 0
);

CREATE TABLE Question (
	id int(11) AUTO_INCREMENT primary key,
	topicId int(11),
	text varchar(255) NOT NULL DEFAULT '',
	reactionId int(11) NOT NULL,
	open bool DEFAULT 1,
	whenAsked datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	constraint FKQuestion_topicId foreign key (topicId)
		references Topic(id) on delete cascade on update cascade,
	constraint FKQuestion_reactionId foreign key (reactionId)
		references Reaction(id) on delete cascade on update cascade
);

CREATE TABLE Answer (
	id int(11) AUTO_INCREMENT primary key,
	questionId int(11) NOT NULL,
	reactionId int(11) NOT NULL,
	
	constraint FKAnswer_questionId foreign key (questionId)
		references Question(id) on delete cascade on update cascade,
	constraint FKAnswer_reactionId foreign key (reactionId)
		references Reaction(id) on delete cascade on update cascade
);

create table QuestionXQuestion (
	questionAId int(11),
	questionBId int(11),
	
	constraint FKQuestionXQuestion_questionAId foreign key (questionAId)
		references Question(id) on delete cascade on update cascade,
	constraint FKQuestionXQuestion_questionBId foreign key (questionBId)
		references Question(id) on delete cascade on update cascade
);