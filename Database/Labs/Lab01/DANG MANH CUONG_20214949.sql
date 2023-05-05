/* SQL lab01 submission from DMC_20214949 */

create database db_20214949;

create table regions(
    region_id int not null primary key,
    region_name varchar(25)
);

create table jobs(
    job_id varchar(10) not null primary key,
    job_title varchar(35),
    min_salary int,
    max_salary int
);

create table countries(
    country_id char(2) not null primary key,
    country_name varchar(40),
    region_id int,
    constraint c1 foreign key (region_id) references regions(region_id)
);

create table locations(
    location_id int not null primary key,
    street_address varchar(25),
    postal_code varchar(12),
    city varchar(30),
    state_province varchar(12),
    country_id char(2),
    constraint l1 foreign key (country_id) references countries(country_id)
);

create table departments(
    department_id int not null primary key,
    department_name varchar(30),
    manager_id int,
    location_id int,
    constraint d1 foreign key (location_id) references locations(location_id)
);

create table job_history(
    employee_id int not null,
    start_date date not null ,
    end_date date,
    job_id varchar(10) not null,
    department_id int,
    primary key (employee_id, start_date),
    constraint jh1 foreign key (department_id) references departments(department_id),
    constraint jh2 foreign key (job_id) references jobs(job_id)
);

create table employees(
    employee_id int not null primary key,
    first_name varchar(20),
    last_name varchar(25),
    email varchar(25),
    phone_number varchar(20),
    hire_date date,
    job_id varchar(20),
    salary int,
    commission_pct decimal(18,3),
    manager_id int,
    department_id int,
    constraint e1 foreign key (employee_id) references job_history(employee_id),
    constraint e2 foreign key (department_id) references departments(department_id),
    constraint e3 foreign key (job_id) references jobs(job_id)
);

create table job_grades(
    grade_level varchar(2) not null primary key,
    lowest_sal int,
    highest_sal int
);