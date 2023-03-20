LOAD DATA
INFILE dependent.csv
APPEND INTO TABLE dependent
FIELDS TERMINATED BY ','  OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(essn,dependent_name,sex,bdate DATE 'yyyy-mm-dd',relationship)
