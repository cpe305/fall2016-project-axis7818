CREATE TABLE 'Topic' (
	'id' int(11) unsigned NOT NULL AUTO_INCREMENT,
	'name' varchar(32) NOT NULL DEFAULT ''
);

CREATE TABLE 'Reaction' (
	'id' int(11) unsigned NOT NULL AUTO_INCREMENT,
	'likes' int(11) NOT NULL DEFAULT 0,
	'dislikes' int(11) NOT NULL DEFAULT 0,
	'laughs' int(11) NOT NULL DEFAULT 0
);

CREATE TABLE 'Question' (
	'id' int(11) unsigned NOT NULL AUTO_INCREMENT,
	'topicId' int(11) unsigned,
	'text' varchar(255) NOT NULL DEFAULT '',
	'reactionId' int(11) unsigned NOT NULL,
	'open' bool DEFAULT 1,
	'whenAsked' datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	constraint FKQuestion_topicId foreign key (topicId)
		references Topic(id) on delete cascade on update cascade,
	constraint FKQuestion_reactionId foreign key (reactionId)
		references Reaction(id) on delete cascade on update cascade
);

CREATE TABLE 'Answer' (
	'id' int(11) unsigned NOT NULL AUTO_INCREMENT,
	'questionId' int(11) unsigned NOT NULL,
	'reactionId' int(11) unsigned NOT NULL,
	
	constraint FKAnswer_questionId foreign key (questionId)
		references Question(id) on delete cascade on update cascade,
	constraint FKAnswer_reactionId foreign key (reactionId)
		references Reaction(id) on delete cascade on update cascade
);