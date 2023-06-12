create table users(
	username varchar(50) not null primary key,
	password varchar(500) not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);


INSERT INTO users VALUES('admin','12345', true);
INSERT INTO authorities values('admin','write');

CREATE TABLE customer(email varchar(50),pwd varchar(250), roles varchar(250));
INSERT INTO customer VALUES('c1@gmail.com','c1234','admin');
INSERT INTO customer VALUES('c2@gmail.com','c234','user');
INSERT INTO customer VALUES('c3@gmail.com','c34','user');

