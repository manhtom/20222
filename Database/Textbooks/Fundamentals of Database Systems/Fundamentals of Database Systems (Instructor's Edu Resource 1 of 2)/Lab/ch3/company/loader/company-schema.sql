CREATE TABLE employee (
  fname    varchar(15),
  minit    varchar(1),
  lname    varchar(15),
  ssn      char(9),
  bdate    date,
  address  varchar(50),
  sex      char,
  salary   decimal(10,2),
  superssn char(9),
  dno      number(4)
);

CREATE TABLE department (
  dname        varchar(25),
  dnumber      number(4),
  mgrssn       char(9), 
  mgrstartdate date
);

CREATE TABLE dept_locations (
  dnumber   number(4),
  dlocation varchar(15) 
);

CREATE TABLE project (
  pname      varchar(25),
  pnumber    number(4),
  plocation  varchar(15),
  dnum       number(4)
);

CREATE TABLE works_on (
  essn   char(9),
  pno    number(4),
  hours  decimal(4,1)
);

CREATE TABLE dependent (
  essn           char(9),
  dependent_name varchar(15),
  sex            char,
  bdate          date,
  relationship   varchar(8)
);
