drop table students cascade constraints;
create table students (
  sid      number(4) primary key,
  password number(5),
  fname    varchar2(20),
  lname    varchar2(20),
  sType    varchar2(5) check (sType in ('GRAD','UGRAD')),
  major    char(4) check (major in ('CSC','MATH','POLS','HIST')),
  gradAssistant char(1) check (gradAssistant in ('Y','N')),
  inState char(1) check (inState in ('Y','N'))
);
insert into students values
  (1111,1111,'John','Davison','UGRAD','CSC','N','Y');
insert into students values
  (2222,2222,'Jacob','Oram','UGRAD','CSC','N','N');
insert into students values
  (3333,3333,'Ashish','Bagai','GRAD','CSC','Y','N');
insert into students values
  (4444,4444,'Joe','Harris','GRAD','CSC','N','Y');
insert into students values
  (5555,5555,'Andy','Blignaut','GRAD','CSC','N','Y');
insert into students values
  (6666,6666,'Pommie','Mbangwa','GRAD','CSC','N','Y');
insert into students values
  (7777,7777,'Ian','Healy','GRAD','CSC','N','Y');
insert into students values
  (8888,8888,'Dougie','Marillier','GRAD','CSC','N','Y');
--
drop table staff cascade constraints;
create table staff (
  tid  number(4) primary key,
  password number(5),
  fname varchar2(20),
  lname varchar2(20),
  staffType varchar2(10) check (staffType in ('REGISTRAR','DEPARTMENT'))
);
insert into staff values
  (1000,1000,'Venette','Rice','DEPARTMENT');
insert into staff values
  (2000,2000,'Alison','Payne','REGISTRAR');
--
create or replace view lunarUsers as
  (select sid, password, 'STUDENT' uType
   from   students) union
  (select tid, password, staffType uType
   from   staff);
--
drop table courses cascade constraints;
create table courses (
  cprefix  char(4),
  cno      number(4),
  ctitle   varchar2(50),
  chours   number(2),
  primary key (cprefix,cno)
);
insert into courses values ('CSC',1010,'Computers and Applications',3);
insert into courses values ('CSC',2010,'Introduction to Computer Science',3);
insert into courses values ('CSC',2310,'Introduction to Programming in Java',3);
insert into courses values ('CSC',2311,'Introduction to Programming in C++',3);
insert into courses values ('CSC',3410,'Data Structures',3);
insert into courses values ('CSC',3210,'Computer Organization',3);
insert into courses values ('CSC',3320,'System Level Programming in Unix and C',3);
insert into courses values ('MATH',2211,'Calculus I',5);
insert into courses values ('MATH',2212,'Calculus II',5);
insert into courses values ('MATH',2420,'Discrete Mathematics',3);
insert into courses values ('CSC',6220,'Networks',4);
insert into courses values ('CSC',8220,'Advanced Networks',4);
insert into courses values ('CSC',6710,'Database',4);
insert into courses values ('CSC',8710,'Advanced Database',4);
insert into courses values ('CSC',6820,'Graphics',4);
insert into courses values ('CSC',8820,'Advanced Graphics',4);
insert into courses values ('POLS',1200,'Intro Political Sci',3);
--
drop table sections cascade constraints;
create table sections (
  term        char(2) check (term in ('FA','SP','SU')),
  year        number(4),
  crn         number(5),
  cprefix     char(4),
  cno         number(4),
  section     number(2),
  days        char(6),
  startTime   char(5), -- example 08.15, 13.30 etc.
  endTime     char(5),
  room        varchar2(10),
  cap         number(3),
  instructor  varchar2(30),
  auth        char(1) check (auth in ('Y','N')),
  primary key (term,year,crn),
  foreign key (cprefix,cno) references courses
);
--
insert into sections values 
('SU',2002,10101,'CSC',1010,1,'MWF','09.00','09.50','105G',35,'Bhola','N');
insert into sections values 
('SU',2002,10701,'POLS',1200,1,'TR','09.00','09.50','205Sp',25,'Jones','N');
--
insert into sections values 
('FA',2002,10101,'CSC',2010,1,'MWF','09.00','09.50','105G',35,'Bhola','N');
insert into sections values 
('FA',2002,10102,'CSC',2010,2,'MWF','10.00','10.50','105CS',40,'Henry','N');
insert into sections values 
('FA',2002,10103,'CSC',2310,1,'MWF','12.00','12.50','106G',30,'Henry','N');
insert into sections values 
('FA',2002,10104,'CSC',2311,1,'MWF','15.00','15.50','205G',35,'Liu','N');
insert into sections values 
('FA',2002,10201,'CSC',6220,1,'TR','19.00','20.40','405G',25,'Hundewale','N');
insert into sections values 
('FA',2002,10202,'CSC',6710,1,'TR','16.00','17.15','115CS',25,'Madiraju','N');
insert into sections values 
('FA',2002,10203,'CSC',8820,1,'MWF','09.00','09.50','605G',25,'Owen','N');
insert into sections values 
('FA',2002,10301,'MATH',2211,1,'TR','11.00','12.50','305G',35,'Li','N');
insert into sections values 
('FA',2002,10302,'MATH',2211,2,'MWF','09.00','10.50','106GB',35,'Davis','N');
--
-- This data will be loaded into the database in your application program
--insert into sections values 
--('SP',2003,10101,'CSC',2010,1,'MWF','09.00','09.50','105G',35,'Bhola','N');
--insert into sections values 
--('SP',2003,10102,'CSC',2010,2,'MWF','10.00','10.50','105CS',40,'Henry','N');
--insert into sections values 
--('SP',2003,10103,'CSC',2310,1,'MWF','12.00','12.50','106G',30,'Henry','N');
--insert into sections values 
--('SP',2003,10104,'CSC',2311,1,'MWF','15.00','15.50','205G',35,'Liu','N');
--insert into sections values 
--('SP',2003,10201,'CSC',6220,1,'TR','19.00','20.40','405G',25,'Hundewale','N');
--insert into sections values 
--('SP',2003,10202,'CSC',6710,1,'TR','16.00','17.15','115CS',25,'Madiraju','N');
--insert into sections values 
--('SP',2003,10203,'CSC',8220,1,'MWF','09.00','09.50','605G',25,'Bourgeois','Y');
--insert into sections values 
--('SP',2003,10301,'MATH',2211,1,'TR','11.00','12.50','305G',35,'Li','N');
--insert into sections values 
--('SP',2003,10302,'MATH',2211,2,'MWF','09.00','10.50','606GB',35,'Miller','N');
--insert into sections values 
--('SP',2003,10303,'MATH',2212,1,'MWF','09.00','10.50','706GB',35,'Davis','N');
--insert into sections values 
--('SP',2003,10304,'MATH',2420,1,'TR','14.00','14.50','106GB',35,'Domke','N');
--insert into sections values 
--('SP',2003,10405,'CSC',8710,1,'MW','17.30','18.45','206GB',35,'Dogdu','N');
--insert into sections values 
--('SP',2003,10406,'CSC',8820,1,'TR','19.15','20.55','306GB',3,'Owen','N');
--
drop table enrolls cascade constraints;
create table enrolls (
  sid    number(4),
  term   char(2) check (term in ('FA','SP','SU')),
  year   number(4),
  crn    number(5),
  grade  char(2) check (grade in ('A','B','C','D','F','I','IP','S','U')),
  primary key (sid,term,year,crn),
  foreign key (sid) references students,
  foreign key (term,year,crn) references sections
);
--
insert into enrolls values (1111,'SU',2002,10101,'A');
insert into enrolls values (1111,'SU',2002,10701,'C');
--
insert into enrolls values (1111,'FA',2002,10101,null);
insert into enrolls values (1111,'FA',2002,10103,null);
insert into enrolls values (1111,'FA',2002,10301,null);
insert into enrolls values (3333,'FA',2002,10201,null);
insert into enrolls values (3333,'FA',2002,10202,null);
insert into enrolls values (3333,'FA',2002,10203,null);
--
drop table authorizations cascade constraints;
create table authorizations (
  term   char(2) check (term in ('FA','SP','SU')),
  year   number(4),
  crn    number(5),
  sid    number(4),
  authType char(4) check (authType in ('OVFL','AUTH')),
  primary key (term,year,crn,sid,authType),
  foreign key (sid) references students,
  foreign key (term,year,crn) references sections
);
--
drop table fixedFee cascade constraints;
create table fixedFee (
  feeName varchar2(30) primary key,
  fee number(5,2)
);
--
insert into fixedFee values ('Technology Fee',75.00);
insert into fixedFee values ('Health Fee',30.00);
insert into fixedFee values ('Activity Fee',65.00);
insert into fixedFee values ('Transportation Fee',25.00);
--
drop table variableFeeRate cascade constraints;
create table variableFeeRate (
  sType varchar2(6) check (sType in ('GRAD','UGRAD')),
  inOrOutOfState varchar2(10) check (inOrOutOfState in ('INSTATE','OUTOFSTATE')),
  fee number(6,2),
  primary key (sType,inOrOutOfState)
);
--
insert into variableFeeRate values ('GRAD','INSTATE',125.00);
insert into variableFeeRate values ('GRAD','OUTOFSTATE',500.00);
insert into variableFeeRate values ('UGRAD','INSTATE',100.00);
insert into variableFeeRate values ('UGRAD','OUTOFSTATE',400.00);

