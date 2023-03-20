LOAD DATA
INFILE department.csv
APPEND INTO TABLE department
FIELDS TERMINATED BY ','  OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(dname,dnumber,mgrssn,mgrstartdate DATE 'yyyy-mm-dd')
