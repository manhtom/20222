create table bbusers (
  email  varchar(50),
  name   varchar(30),
  password varchar(10),
  nickname varchar(30),
  primary key (email)
);

create table postings (
  postId    integer(5) auto_increment,
  postDate  datetime,
  postedBy  varchar(50),
  postSubject varchar(100),
  content   varchar(512),
  ancestorPath  varchar(100),
  primary key (postId),
  foreign key (postedBy) references bbusers
); 

insert into bbusers values ('a@abc.com','Andy Smith','a123','baggy',null,null);
insert into bbusers values ('b@abc.com','Craig Smith','a123','runner',null,null);
insert into bbusers values ('c@abc.com','Craig Rich','a123','slogger',null,null);
insert into bbusers values ('d@abc.com','Mike Arlott','a123','ateam',null,null);

insert into postings values (null,'2003-01-11 10:15:00','a@abc.com','Welcome',
'Welcome to the bulletin board',null);
insert into postings values (null,'2003-01-12 08:00:00','b@abc.com','Welcome',
'This is posting 2','1');
insert into postings values (null,'2003-01-13 09:00:00','c@abc.com','Welcome',
'This is posting 3','1');
insert into postings values (null,'2003-01-14 09:00:00','a@abc.com','Welcome',
'This is posting 4',null);
insert into postings values (null,'2003-01-15 09:00:00','a@abc.com','Welcome',
'This is posting 5',null);
insert into postings values (null,'2003-01-16 09:00:00','a@abc.com','Welcome',
'This is posting 6',null);
insert into postings values (null,'2003-01-17 09:00:00','c@abc.com','Welcome',
'This is posting 7','1:2');
insert into postings values (null,'2003-01-18 09:00:00','d@abc.com','Welcome',
'This is posting 8','1:2');
insert into postings values (null,'2003-01-19 09:00:00','a@abc.com','Welcome',
'This is posting 9','1:2:8');
insert into postings values (null,'2003-01-20 09:00:00','b@abc.com','Welcome',
'This is posting 10','6');

