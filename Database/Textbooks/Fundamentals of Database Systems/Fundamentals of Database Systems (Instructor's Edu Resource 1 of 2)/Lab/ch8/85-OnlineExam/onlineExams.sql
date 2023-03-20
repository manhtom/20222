--------------------------------------
-- ONLINE EXAM MANAGEMENT SYSTEM   ---
-- Database Schema and Sample Data ---
-- Designed by: Raj Sunderraman    ---
-- 12 September, 2006              ---
--------------------------------------
drop table exam cascade constraints;
create table exam (
  eno number(5),
  etitle varchar2(50),
  timeAllowed  number(8), -- minutes 
  numberOfQuestionsPerPage number(3), 
  primary key (eno)
);

drop table question cascade constraints;
create table question (
  eno number(5),
  qno number(5),
  qtext varchar2(2048), -- maybe be CLOB object
  correctAnswer char(1),  -- must be one of the options  
  foreign key (eno) references exam,
  primary key (eno,qno)
);

drop table answerOption cascade constraints;
create table answerOption (
  eno number(5), 
  qno number(5),
  ono char(1) check (ono in ('A','B','C','D','E')),
  optionText varchar2(256),
  foreign key (eno,qno) references question,
  primary key (eno,qno,ono)
);

drop table users cascade constraints;
create table users (
  uno number(5), -- primary key; system generated starting at 1 
                 -- first user gets 1 and subsequent users get max+1
  email varchar2(64),  -- unique key used for signing in
  password varchar2(64),
  fname varchar2(64) not null,
  lname varchar2(64) not null,
  address1 varchar2(64),
  address2 varchar2(64),
  city varchar2(64),
  state varchar2(64),
  zip number(5),
  primary key (uno)
);
 
drop table enrolls cascade constraints;
create table enrolls (
  uno number(5),
  eno number(5),
  startTime date,
  finishTime date,
  foreign key (uno) references users,
  foreign key (eno) references exam,
  primary key (uno,eno)
);

drop table userResponse cascade constraints;
create table userResponse (
  uno number(5),
  eno number(5),
  qno number(5),
  response char(1) 
    check (response in ('A','B','C','D','E','N')), -- N for No Answer
  foreign key (uno,eno) references enrolls,
  foreign key (eno,qno) references question,
  primary key (uno,eno,qno)
);

-- Some data

insert into exam values (3,'Elementary History',10,3);

insert into question values
  (3,1,'The Battle of Gettysburg was fought during which war?','C');
insert into answerOption values (3,1,'A','World War II'); 
insert into answerOption values (3,1,'B','The Revolutionary War');
insert into answerOption values (3,1,'C','The Civil War');
insert into answerOption values (3,1,'D','World War I');
 
insert into question values
  (3,2,'Neil Armstrong and Buzz Aldrin walked how many \n' ||
       'minutes on the moon in 1696?','B') ;
insert into answerOption values (3,2,'A','123'); 
insert into answerOption values (3,2,'B','None'); 
insert into answerOption values (3,2,'C','10'); 
insert into answerOption values (3,2,'D','51'); 
 
insert into question values
  (3,3,'Which Presidents held office during World War II?','D'); 
insert into answerOption values (3,3,'A','Franklin D. Roosevelt'); 
insert into answerOption values (3,3,'B','Dwight D. Eisenhower'); 
insert into answerOption values (3,3,'C','Harry Truman'); 
insert into answerOption values (3,3,'D','Both A and C'); 
 
insert into question values
  (3,4,'In a communist economic system, people:','B'); 
insert into answerOption values (3,4,'A','Are forced to work as slaves'); 
insert into answerOption values (3,4,'B','Work for the common good'); 
insert into answerOption values (3,4,'C','Work from home computers'); 
insert into answerOption values (3,4,'D','Don''t work'); 
 
 
insert into question values
  (3,5,'Which president did not die while in office?','D'); 
insert into answerOption values (3,5,'A','John F. Kennedy'); 
insert into answerOption values (3,5,'B','Franklin D. Roosevelt'); 
insert into answerOption values (3,5,'C','Abraham Lincoln'); 
insert into answerOption values (3,5,'D','Ronald Reagan'); 
insert into answerOption values (3,5,'E','James A. Garfield'); 
 
insert into question values
  (3,6,'Which state refused to attend the Constitutional Convention \n' ||
       'in 1787 because it didn''t want the United States government \n' ||
       'to interfere with already established state affairs?','A'); 
insert into answerOption values (3,6,'A','Rhode Island'); 
insert into answerOption values (3,6,'B','New Hampshire'); 
insert into answerOption values (3,6,'C','New Jersey'); 
insert into answerOption values (3,6,'D','New York'); 

insert into question values
  (3,7,'Who founded Buddhism?','A'); 
insert into answerOption values (3,7,'A','Siddharta Gautama'); 
insert into answerOption values (3,7,'B','Jesus Christ'); 
insert into answerOption values (3,7,'C','Mahatma Gandhi'); 
insert into answerOption values (3,7,'D','Muhammad'); 
 
insert into question values
  (3,8,'Where is India?','D'); 
insert into answerOption values (3,8,'A','Australia'); 
insert into answerOption values (3,8,'B','America'); 
insert into answerOption values (3,8,'C','Africa'); 
insert into answerOption values (3,8,'D','Asia'); 
 
 
insert into question values
  (3,9,'What is the dominant religion in India?','B'); 
insert into answerOption values (3,9,'A','Islam'); 
insert into answerOption values (3,9,'B','Hinduism'); 
insert into answerOption values (3,9,'C','Christianity'); 
insert into answerOption values (3,9,'D','Buddhism'); 
 
insert into question values
  (3,10,'Near which river did archaeologists find India''s \n' ||
        'first civilization?','B'); 
insert into answerOption values (3,10,'A','The Tiber River'); 
insert into answerOption values (3,10,'B','The Indus River'); 
insert into answerOption values (3,10,'C','The Yellow River'); 
insert into answerOption values (3,10,'D','The Nile River'); 


insert into exam values (1,'Java Programming',60,1);

insert into question values
  (1,1,'Choose the operations that can be performed on String objects:(A) +\n(B) + =\n(C) -\n(D) %\n(E) ^','B');
insert into question values
  (1,2,'What will be the result of the expression\na % b\nwhen a and b are of type int and their values are a = -17 and b = -6?','A');
insert into question values
  (1,3,'What will be the result of the expression\na % b\nwhen a and b are of type int and their values are a = 10 and b = 6?','B');
insert into question values
  (1,4,'Consider the following code:\nint x, y, z;\ny = 1;\nz = 5;\nx = 0 - (++y) + z++;\nAfter execution of this, what will be the values of x, y and z?','C');
insert into question values
  (1,5,'Identify the statements that are correct:\n(A) int a = 13, a>>2 = 3\n(B) int b = -8, b>>1 = -4\n(C) int a = 13, a>>>2 = 3\n(D) int b = -8, b>>>1 = -4','E');
insert into question values
  (1,6,'If not assigned a value, a variable of type char has the following default value:','A');
insert into question values
  (1,7,'What will be the value of a after execution of the following statements:\nint a = 23, b = 34;\na = ((a < b) ? (b + a) : (b - a);','B');
insert into question values
  (1,8,'Which of the following is not a hexadecimal number?\n(A) 999\n(B) (hex)23\n(C) 0x556\n(D) 0x1F2','C');
insert into question values
  (1,9,'if(check4Biz(storeNum) != null) {}\nReferring to the above, what datatype could be returned by method check4Biz()?','D');
insert into question values
  (1,10,'Select the invalid assignment statements from the following:\n(A) float x = 238.88;\n(B) double y = 0x443;\n(C) int n = (int) true;\n(D) long m =778645;','A');
insert into question values
  (1,11,'int j;\nfor(int i=0;i<14;i++) {\nif(i<10) {\nj = 2 + i;\n}\nSystem.out.println("j: " + j + " i: " + i);\n}\nWhat is WRONG with the above code?','E');
insert into question values
  (1,12,'Consider the following code:\nclass ClassA\n{\npublic static void main(String args [ ])\n{\nClassB b = classB();\n}\nClassA(int x) { }\n}\nclass ClassB extends ClassA\n{\n}\nWhat will happen when we compile and run this code?','A');
insert into question values
  (1,13,'The keywords reserved but not used in the initial version of Java are:\n(A) union\n(B) const\n(C) inner\n(D) goto\n(E) boolean\n(F) synchronized','C');
insert into question values
  (1,14,'We would like to make a member of a class visible in all subclasses regardless of what package they are in. Which one of the following keywords would achieve this?','D');
insert into question values
  (1,15,'Which of the following are not keywords?\n(A) NULL\n(B) implements\n(C) protected\n(D) extended\n(E) string','B');

insert into answerOption values
  (1,1,'A','(D) & (E)');
insert into answerOption values
  (1,1,'B',' (A) & (B)');
insert into answerOption values
  (1,1,'C','(D)');
insert into answerOption values
  (1,1,'D','(A)');
insert into answerOption values
  (1,1,'E','None of these');
insert into answerOption values
  (1,2,'A','-17');
insert into answerOption values
  (1,2,'B','5');
insert into answerOption values
  (1,2,'C','3');
insert into answerOption values
  (1,2,'D','-5');
insert into answerOption values
  (1,2,'E','None of these');
insert into answerOption values
  (1,3,'A','1.66');
insert into answerOption values
  (1,3,'B','4');
insert into answerOption values
  (1,3,'C','2');
insert into answerOption values
  (1,3,'D','1');
insert into answerOption values
  (1,3,'E','None of these');
insert into answerOption values
  (1,4,'A','x = 4, y = 1, z = 5');
insert into answerOption values
  (1,4,'B',' x = -7, y = 1, z = 5');
insert into answerOption values
  (1,4,'C','x = 3, y = 2, z = 6');
insert into answerOption values
  (1,4,'D',' x = 4, y = 2, z = 6');
insert into answerOption values
  (1,4,'E',' x = 8, y = 2, z = 1');
insert into answerOption values
  (1,5,'A','(C) & (D) ');
insert into answerOption values
  (1,5,'B',' (A), (B) & (C) ');
insert into answerOption values
  (1,5,'C','(A), (B), (C) & (D)');
insert into answerOption values
  (1,5,'D','(A) & (B) ');
insert into answerOption values
  (1,5,'E','None of the above');
insert into answerOption values
  (1,6,'A','\uffff');
insert into answerOption values
  (1,6,'B','\u0000');
insert into answerOption values
  (1,6,'C','" " (space)');
insert into answerOption values
  (1,6,'D','\u0001');
insert into answerOption values
  (1,6,'E','None of the above');
insert into answerOption values
  (1,7,'A','23');
insert into answerOption values
  (1,7,'B','Error. Cannot be executed.');
insert into answerOption values
  (1,7,'C','57');
insert into answerOption values
  (1,7,'D','11');
insert into answerOption values
  (1,7,'E','34');
insert into answerOption values
  (1,8,'A','(A), (B) & (C)');
insert into answerOption values
  (1,8,'B','(C)');
insert into answerOption values
  (1,8,'C','(A) & (B) ');
insert into answerOption values
  (1,8,'D','(A)');
insert into answerOption values
  (1,8,'E','(D)');
insert into answerOption values
  (1,9,'A','Boolean');
insert into answerOption values
  (1,9,'B','Int');
insert into answerOption values
  (1,9,'C','String');
insert into answerOption values
  (1,9,'D','Char');
insert into answerOption values
  (1,9,'E','Byte');
insert into answerOption values
  (1,10,'A','(A) & (C)');
insert into answerOption values
  (1,10,'B','(A) & (B)');
insert into answerOption values
  (1,10,'C','(B) ');
insert into answerOption values
  (1,10,'D','(B) & (D) ');
insert into answerOption values
  (1,10,'E','(D)');
insert into answerOption values
  (1,11,'A','Integer "j" is not initialized.');
insert into answerOption values
  (1,11,'B','Nothing.');
insert into answerOption values
  (1,11,'C','You cannot declare integer i inside the for-loop declaration.');
insert into answerOption values
  (1,11,'D','The syntax of the "if" statement is incorrect.');
insert into answerOption values
  (1,11,'E','You cannot print integer values without converting them to strings.');
insert into answerOption values
  (1,12,'A','Will compile and run successfully ');
insert into answerOption values
  (1,12,'B','Error. ClassA does not define a no-argument constructor ');
insert into answerOption values
  (1,12,'C','Error. There is no code in the constructor ClassA(int x) ');
insert into answerOption values
  (1,12,'D','Error. ClassB does not define a no-argument constructor ');
insert into answerOption values
  (1,12,'E','Error. There is no code in the class ClassB ');
insert into answerOption values
  (1,13,'A','(C) & (E) ');
insert into answerOption values
  (1,13,'B','(B),(C) & (D)');
insert into answerOption values
  (1,13,'C','(A), (C) & (E)');
insert into answerOption values
  (1,13,'D','All of these. ');
insert into answerOption values
  (1,13,'E','None of these. ');
insert into answerOption values
  (1,14,'A','private ');
insert into answerOption values
  (1,14,'B','public');
insert into answerOption values
  (1,14,'C','protected');
insert into answerOption values
  (1,14,'D','private OR protected');
insert into answerOption values
  (1,14,'E','All of the above');
insert into answerOption values
  (1,15,'A','(C), (D) & (E)');
insert into answerOption values
  (1,15,'B','(D) ');
insert into answerOption values
  (1,15,'C','(A), (D) & (E)');
insert into answerOption values
  (1,15,'D','(D) & (E) ');
insert into answerOption values
  (1,15,'E','(A)');
