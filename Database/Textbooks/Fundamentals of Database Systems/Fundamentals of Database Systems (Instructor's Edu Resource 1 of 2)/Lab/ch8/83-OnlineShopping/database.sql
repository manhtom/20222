drop table parts cascade;
create table parts(
  pno      integer(5) not null,
  pname    varchar(30),
  qoh      integer,
  price    decimal(6,2),
  olevel   integer
  primary key (pno)
);

drop table customers cascade;
create table customers (
  cno      integer(10) not null auto_increment=100,
  cname    varchar(30),
  street   varchar(50),
  city     varchar(30),
  state    varchar(30),
  zip      integer(5),
  phone    char(12),
  email    varchar(50),
  password varchar(15),
  primary key (cno)
);
 
drop table cart cascade;
create table cart(
  cartno   integer(10) not null auto_increment,
  cno      integer(10) not null,
  pno      integer(5) not null,
  qty      integer not null,
  primary key (cartno, pno),
  foreign key (cno) references customers,
  foreign key (pno) references parts
); 

drop table orders cascade;
create table orders (
  ono      integer(5) not null auto_increment=1000,
  cno      integer(10) references customers,
  received date,
  shipped  date,
  primary key (ono),
  foreign key (cno) references customers
);

drop table odetails cascade;
create table odetails (
  ono      integer(5) not null,
  pno      integer(5) not null,
  qty      integer,
  primary key (ono,pno),
  foreign key (ono) references orders,
  foreign key (pno) references parts
);

insert into parts values
  (10506,'Land Before Time I',200,19.99,20);
insert into parts values
  (10507,'Land Before Time II',156,19.99,20);
insert into parts values
  (10508,'Land Before Time III',190,19.99,20); 
insert into parts values
  (10509,'Land Before Time IV',60,19.99,20);
insert into parts values
  (10601,'Sleeping Beauty',300,24.99,20);
insert into parts values
  (10701,'When Harry Met Sally',120,19.99,30);
insert into parts values
  (10800,'Dirty Harry',140,14.99,30);
insert into parts values
  (10900,'Dr. Zhivago',100,24.99,30);
insert into parts values 
  (20001, 'Braveheart', 1000, 15.99, 20);
insert into parts values 
  (20002, 'Lawrence of Arabia', 1000, 15.99, 20);
insert into parts values 
  (20003, 'Casablanca', 1000, 15.99, 20);
insert into parts values 
  (20004, 'Mickey Blue Eyes', 1000, 15.99, 20);
insert into parts values 
  (20005, 'The Godfather I', 1000, 15.99, 20);
insert into parts values 
  (20006, 'The Godfather II', 1000, 15.99, 20);
insert into parts values 
  (20007, 'The Godfather III', 1000, 15.99, 20);
insert into parts values 
  (20008, 'The Mummy', 1000, 15.99, 20);
insert into parts values 
  (20009, 'Instinct', 1000, 15.99, 20);
insert into parts values 
  (20010, 'Star Wars', 1000, 15.99, 20);
insert into parts values 
  (20011, 'Sci-Fi', 1000, 15.99, 20);
insert into parts values 
  (20012, 'Payback', 1000, 15.99, 20);
insert into parts values 
  (20013, 'Matrix', 1000, 15.99, 20);
insert into parts values 
  (20014, 'Titanic', 1000, 15.99, 20);
insert into parts values 
  (20015, 'Mission Impossible', 1000, 15.99, 20);
insert into parts values 
  (20016, 'Broken Arrow', 1000, 15.99, 20);
insert into parts values 
  (20017, 'Braveheart', 1000, 15.99, 20);
insert into parts values 
  (20018, 'Air Force One', 1000, 15.99, 20);
insert into parts values 
  (20019, 'Entrapment', 1000, 15.99, 20);
insert into parts values 
  (20020, 'Gone with the Wind', 1000, 15.99, 20);
