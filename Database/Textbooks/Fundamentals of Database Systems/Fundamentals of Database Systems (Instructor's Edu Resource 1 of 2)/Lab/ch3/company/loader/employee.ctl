LOAD DATA
INFILE employee.csv
APPEND INTO TABLE employee
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(fname,minit,lname,ssn,bdate DATE 'yyyy-mm-dd',address,sex,salary,superssn,dno)
