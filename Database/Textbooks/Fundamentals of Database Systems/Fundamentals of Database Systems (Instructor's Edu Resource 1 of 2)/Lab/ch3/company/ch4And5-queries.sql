--Query 0
--Retrieve the birthdate and address of the employee(s) whose name is
--John B. Smith
--
select bdate,address
from   employee
where  fname='John' and minit='B' and lname='Smith';
--
--Query 1
--Retrieve the name and address of all employees who work for the Research
--department.
--
select fname,lname,address
from   employee,department
where  dnumber=dno and
       dname='Research';
--
--Query 2
--For every project located in Stafford, list the project number, the
--controlling department number, and the department manager's last name,
--address, and birthdate.
--
select pnumber,dnum,lname,address,bdate
from   project,department,employee
where  dnum=dnumber and mgrssn=ssn and plocation='Stafford';
--
--Query 3
--Retrieve the names of each employee who works in all projects controlled
--by department 5
--
select lname,fname
from   employee
where  not exists (
  select *
  from   works_on b
  where  (b.pno in (select pnumber from project where dnum=5) and not exists (
    select *
    from   works_on c
    where  ssn = c.essn and b.pno = c.pno));
--
--Query 4
--Make a list of all project numbers for projects that involve an employee whose
--last name is Smith either as a worker or as a manager of the department that 
--controls the project.
--
(select distinct pnumber
 from   project,department,employee
 where  dnum=dnumber and mgrssn=ssn and lname='Smith')
union
(select pnumber
 from   project,works_on,employee
 where  pnumber=pno and essn=ssn and lname='Smith');
--
--Query 5
--Retrieve the names of all employees who have two or more dependents.
--
select lname,fname
from   employee
where  (select count(*) from dependent where ssn=essn) >=2;
--
--Query 6
--Retrieve the names of employees who have no dependents.
--
select fname,lname
from   employee
where  not exists (select * from dependent where ssn=essn);
--
--Query 7
--List the names of managers who have at least one dependent.
--
select fname,lname
from   employee
where  exists (select * from department where ssn=mgrssn) and
       exists (select * from dependent where ssn=essn);
--
--Query 8
--For each employee, retrieve the employee's first and last name and the first
--and last name of his or her immediate supervisor
--
select e.fname,e.lname,s.fname,s.lname
from   employee as e, employee as s
where  e.superssn = s.ssn;
--
--Query 9
--Retrieve all employee SSNs
--
select ssn
from   employee;
--
--Query 10
--Retrieve all combinations of SSN and DEPARTMENT NAME
--
select ssn,dname
from   employee,department;
--
--Query 11
--Retrieve the salary of every employee
--
select all salary
from   employee;
--
--Query 11a`
--Retrieve the distinct salary values
--
select distinct salary
from   employee;
--
--Query 12
--Retrieve all employees whose address is in Houston, Texas
--
select fname,lname
from   employee
where  address like '%Houston, TX%';
--
--Query 13
--Show the resulting salaries if every employee working on the ProductX project
--is given a 10% raise.
--
select fname,lname,1.1*salary as increased_sal
from   employee,works_on,project
where  ssn=essn and pno=pnumber and pname='ProductX';
--
--Query 14
--Retrieve all employees in department 5 whose salary is between $30,000 and
--$40,000
--
select *
from   employee
where  dno=5 and (salary between 30000 and 40000);
--
--Query 15
--Retrieve a list of employees and the projects they are working on, ordered
--by department and, within each department, ordered alphabetically by last
--name, first name
--
select dname,lname,fname,pname
from   department,employee,works_on,project
where  dnumber=dno and ssn=essn and pno=pnumber
order by dname,lname,fname;
--
--Query 16
--Retrieve the name of each employee who has a dependent with the same first
--name and same sex as the employee
--
select e.fname,e.lname
from   employee as e
where  e.ssn in (select essn
                 from   dependent
                 where  e.fname=dependent_name and e.sex=sex);
--
--Query 17
--Retrieve the social security numbers of all employees who work on project
--numbers 1, 2, or 3.
--
select distinct essn
from   works_on
where  pno in (1,2,3);
--
--Query 18
--Retrieve the names of all employees who do not have a supervisor
--
select fname,lname
from   employee
where  superssn is null;
--
--Query 19
--Find the sum of the salaries of all employees, the maximum salary, the
--minimum salary, and the average salary.
--
select sum(salary),max(salary),min(salary),avg(salary)
from   employee
--
--Query 20
--Find the sum of the salaries of all employees of the Research department,
--as well as the maximum salary, the minimum salary, and the average salary
--in this department.
select sum(salary),max(salary),min(salary),avg(salary)
from   employee, department
where  dno=dnumber and dname='Research';
--
--Query 21
--Retrieve the total number of employees in the company.
--
select count(*)
from   employee;
--
--Query 22
--Retrieve the total number of employees in the Research department.
-- 
select count(*)
from   employee,department
where  dno=dnumber and dname='Research';
--
--Query 23
--Count the number of distinct salary values in the database.
--
select count(distinct salary)
from   employee;
--
--Query 24
--For each department, retrieve the department number, the number of
--employees in the department, and their average salary
--
select dno,count(*),avg(salary)
from   employee
group by dno;
--
--Query 25
--For each project, retrieve the project number, the project name, and the
--number of employees who work on that project.
--
select pnumber,pname,count(*)
from   project,works_on
where  pnumber=pno
group by pnumber,pname;
--
--Query 26
--For each project on which more than two employees work, retrieve the project
--number, the project name, and the number of employees who work on the project.
--
select pnumber,pname,count(*)
from   project,works_on
where  pnumber=pno
group by pnumber,pname
having count(*)>2;
--
--Query 27
--For each project, retrieve the project number, the project name,
--and the number of employees from department 5 who work on the project.
--
select pnumber,pname,count(*)
from   project,works_on,employee
where  pnumber=pno and ssn=essn and dno=5
group by pnumber,pname;
--
--Query 28
--For each department that has more than five employees, retrieve the
--department number and the number of its employees who are making more
--than $40,000.
--
select dnumber,count(*)
from   department,employee
where  dnumber=dno and salary>40000 and 
  dno in (select dno from employee group by dno having count(*)>5)
group by dnumber;
--
