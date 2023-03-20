drop table member cascade constraints;
create table member (
  mid varchar2(10) not null,
  email varchar2(40) not null,
  fname varchar2(20) not null,
  lname varchar2(20) not null,
  street varchar2(50) not null,
  city varchar2(30) not null,
  state varchar2(20) not null,
  zip number(5) not null,
  phone varchar2(12),
  password varchar2(20),
  primary key (mid)
);
--
drop table category cascade constraints;
create table category (
  cname varchar2(120),
  primary key (cname)
);
--
drop table item cascade constraints;
create table item (
  ino number(5),
  title varchar2(128) not null,
  category varchar2(120) not null,
  description varchar2(2000),
  openDateTime date,
  sellerId varchar2(10) not null,
  startingBid number(7,2) not null,
  bidIncrement number(7,2) not null,
  closeDateTime Date,
  winnerId varchar2(10),
  primary key (ino),
  foreign key (category) references category,
  foreign key (sellerId) references member,
  foreign key (winnerId) references member
);
--
drop table bid cascade constraints;
create table bid (
  ino number(5),
  buyerId varchar2(10),
  bidPrice number(7,2),
  timeOfBid date,
  primary key (ino,buyerId,timeOfBid),
  foreign key (ino) references item,
  foreign key (buyerId) references member
);
--
drop table rating cascade constraints;
create table rating (
  ino number(5),
  buyerRating number(1) check (buyerRating between 1 and 5),
  buyerComment varchar2(100),
  sellerRating number(1) check (sellerRating between 1 and 5),
  sellerComment varchar2(100),
  primary key (ino),
  foreign key (ino) references item
);
