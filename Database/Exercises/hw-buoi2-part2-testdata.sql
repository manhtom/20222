create schema company

create table employee(
    fname varchar(16),
    minit varchar(16),
    lname varchar(16),
    ssn int not null primary key,
    bdate date,
    address varchar(16),
    sex char(1),
    salary int,
    superssn int not null,
    dno int not null,
    constraint employee1 foreign key (superssn) references employee(ssn),
    constraint employee2 foreign key (dno) references department(dnumber)
)

create table department(
    dname varchar(16),
    dnumber int not null primary key,
    mgrssn int not null,
    mgrstartdate date,
    constraint dep1 foreign key (mgrssn) references employee(ssn)
)

create table dept_locations(
    dnumber int not null,
    dlocation int not null,
    primary key (dnumber,dlocation),
    constraint dept foreign key (dnumber) references department(dnumber)
)

create table project(
    pname varchar(16),
    pnumber int not null primary key,
    plocation varchar(16),
    dnum int,
    constraint pro foreign key (dnum) references department(dnumber)
)

create table works_on(
    essn int not null,
    pno int not null,
    hours int,
    primary key (essn,pno),
    constraint wo foreign key (essn) references employee(ssn)
    constraint wo foreign key (pno) references project(pnumber)
)

create table dependent(
    essn int not null,
    dependent_name varchar(16) not null,
    sex char(1),
    bdate date,
    relationship varchar(12),
    primary key (essn,dependent_name),
    constraint depend1 foreign key (essn) references employee(ssn)
)

/* further insert data ... i need to read the problem  */

insert into employee values("A",1,"B",1234,"2003-01-01","12345678","M",650000,1234,5);

insert into department values("C",2,"D",5678,"2003-02-02","87654321","M",600000,)