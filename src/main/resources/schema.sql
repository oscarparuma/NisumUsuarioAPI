CREATE TABLE usuario (
	id varchar(255) not null,
	created timestamp,
	email varchar(255),
	isactive boolean,
	last_login timestamp,
	modified timestamp,
	name varchar(255),
	password varchar(255),
	token varchar(255),
	primary key (id)
);

CREATE TABLE telefono (
	id bigint not null,
	citycode varchar(255),
	contrycode varchar(255),
	number varchar(255),
	usuario_id varchar(255),
	primary key (id)
);
