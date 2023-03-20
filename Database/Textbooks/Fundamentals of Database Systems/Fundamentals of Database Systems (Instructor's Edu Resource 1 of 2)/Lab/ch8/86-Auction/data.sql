insert into member values
  ('a100','a@cs.gsu.edu','Tom','Jones','120 Main Street','Atlanta','GA',30303,
   '404-111-1110','a123');
insert into member values
  ('m100','m@cs.gsu.edu','Jim','Smith','121 Main Street','Atlanta','GA',30303,
   '404-111-1111','m123');
insert into member values
  ('p100','p@cs.gsu.edu','Don','Fleming','122 Main Street','Atlanta','GA',30303,
   '404-111-1112','p123');
insert into member values
  ('q100','q@cs.gsu.edu','James','John','123 Main Street','Atlanta','GA',30303,
   '404-111-1113','q123');
insert into member values
  ('s100','s@cs.gsu.edu','Monty','Jones','124 Main Street','Atlanta','GA',30303,
   '404-111-1114','s123');
--
insert into category values ('Books:Biology');
insert into category values ('Books:Computers');
insert into category values ('Books:Economics');
insert into category values ('Books:Fiction');
insert into category values ('Computers:Apple:Desktops');
insert into category values ('Computers:Apple:Laptops');
insert into category values ('Computers:PCs:Desktops');
insert into category values ('Computers:PCs:Laptops');
insert into category values ('Computers:Storage:Hard Drives');
insert into category values ('Computers:Storage:Flash Drives');
insert into category values ('DVDs:Action');
insert into category values ('DVDs:Comedy');
insert into category values ('Music:Blues');
insert into category values ('Music:Jazz');
insert into category values ('Music:World');
insert into category values ('Video Games:Systems:XBox 360');
insert into category values ('Video Games:Systems:Wii');
insert into category values ('Video Games:Systems:Playstation');
insert into category values ('Video Games:Systems:Nintendo DS');
insert into category values ('Video Games:Games:XBox 360');
insert into category values ('Video Games:Games:Wii');
insert into category values ('Video Games:Games:Playstation');
insert into category values ('Video Games:Games:Nintendo DS');
--
insert into item values
  (1000,'Mario Party IV','Video Games:Games:Wii','Excellent Condition ' ||
      'Best Seller; Super Graphics',
      to_date('21-APR-2008 1700','DD-MON-YYYY HH24MI'),
      'a100',20.00,2.00,
      to_date('28-APR-2008 1700','DD-MON-YYYY HH24MI'),
      null);

