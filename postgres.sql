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

INSERT INTO users VALUES('admin','admin123', true);

INSERT INTO users VALUES('sudhir','sudhir', true);
INSERT INTO authorities values('admin','write');

select * from users;





