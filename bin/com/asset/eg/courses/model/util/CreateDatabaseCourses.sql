
-- create the user table  this syntax run on oracle ,may be on mysql

create table course_user(
    id  Number(5) PRIMARY KEY,
    user_name varchar2(30) NOT NULL,
    passwd varchar2(15)  NOT NULL,
    email varchar2(30) NOT NULL,
    country varchar(30),
    cellphone varchar2(20),
    city varchar2(20),
    date_of_birth Date )
        



ALTER TABLE course_user
ADD CONSTRAINT course_user_unique UNIQUE (user_name,passwd,email,cellphone);



create table course(
    id  Number(5) PRIMARY KEY,
    course_code varchar2(20) NOT NULL UNIQUE,
    course_name varchar2(50)  NOT NULL UNIQUE,
     course_or_dibloma varchar2(50) NOT NULL,
    learning_field varchar2(50) NOT NULL, 
    technology_category varchar2(50) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    description varchar2(100)
    )    





create table USC(
 idu Number(5),
 idc NUMBER(5)
)



ALTER TABLE USC ADD CONSTRAINT USCpk primary key (id,idc)


ALTER TABLE USC drop CONSTRAINT USCforeign;


alter table USC rename USC.id to idu;


drop table USC;

ALTER TABLE USC ADD CONSTRAINT  USCforeign foreign key  (idu) references  course_user (id) on delete cascade;

ALTER TABLE USC ADD CONSTRAINT  USCforeignCourse foreign key  (idc) references  course (id) on delete cascade;


create table course_objectives( 
    id NUMBER(5) PRIMARY KEY ,
   course_code varchar2(20) NOT NULL UNIQUE,
    objective varchar2(100) NOT NULL
) 



ALTER TABLE course_objectives  add constraint course_objectives_foreign foreign key(course_code)  references course(course_code) on delete cascade;




create table course_topic( 
    id NUMBER(5) PRIMARY KEY ,
   course_code varchar2(20) NOT NULL UNIQUE,
    topic varchar2(100) NOT NULL
) 


ALTER TABLE course_topic  add constraint course_topic_foreign foreign key (course_code)  references course(course_code) on delete cascade;

--**********************************Sequences 

--course_user table


CREATE SEQUENCE courses_user_seq;


--SELECT table_name FROM user_tables;

--SELECT * FROM COURSES.COURSE_USER;



CREATE OR REPLACE TRIGGER course_user_auto_increment 
BEFORE INSERT ON  COURSES.COURSE_USER
FOR EACH ROW

BEGIN
  SELECT  course_user_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;





-- courses table ********************************************


CREATE SEQUENCE course_seq;

CREATE OR REPLACE TRIGGER course_auto_increment 
BEFORE INSERT ON  COURSES.COURSE
FOR EACH ROW

BEGIN
  SELECT  course_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;




INSERT INTO COURSES.COURse(COURSE_CODE,COURSE_NAME,COURSE_OR_DIBLOMA,LEARNING_FIELD,TECHNOLOGY_CATEGORY,START_DATE,DESCRIPTION) VALUES('PHY123','PHYSICS','','Germany','01066666','Berlin','02-Jan-1999');

ALTER TABLE courses.course MODIFY COURSE_OR_DIbLOMA BOOLEAN  ;



ALTER TABLE courses.course  
DROP COLUMN COURSE_OR_DIBLOMA;





ALTER TABLE courses.course  
ADD (COURSE_OR_DIBLOMA  CHAR(1) NOT NULL)




/*
add unique contraints 
*/

alter table courses.course_user add constraint unique_User_courses 
unique (user_name,email,cellphone);




alter table courses.course add constraint unique_course 
unique (course_code,course_name);



select * from courses.COURSE_topic;


alter table courses.course drop column course_or_dibloma;
alter  table courses.course add  (course_or_dibloma number(1) Not Null);
SELECT * FROM user_cons_columns where table_name='COURSE_TOPIC';


-- course_topic  sequence auto increment

CREATE SEQUENCE course_topic_seq;



CREATE OR REPLACE TRIGGER course_topic_auto_increment 
BEFORE INSERT ON  COURSES.COURSE_TOPIC
FOR EACH ROW

BEGIN
  SELECT  course_topic_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;




-- course_Objective sequence auto increment

CREATE SEQUENCE course_objective_seq;



CREATE OR REPLACE TRIGGER course_obj_auto_increment 
BEFORE INSERT ON  COURSES.COURSE_OBJECTIVES
FOR EACH ROW

BEGIN
  SELECT  course_objective_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;




SELECT * FROM COURSES.COURSE;

INSERT INTO COURSES.COURSE_TOPIC (COURSE_CODE,TOPIC) VALUES('123','PHYSICS');


SELECT * FROM COURSES.course_user;


/*   Test */


--SELECT * FROM COURSES.COURSE_USER WHERE passwd ='meda' and user_name='meda';

--SELECT * FROM COURSES.USC;

--SELECT * FROM COURSES.course_user;

--SELECT * FROM COURSES.course;





/* test 
--INSERT INTO COURSES.COURSE_USER(user_name,passwd,email,country,cellphone,city,date_of_birth) VALUES('amr','amr','amr@yahoo.com','Germany','01066666','Berlin','02-Jan-1999');
--INSERT INTO COURSES.COURSE_USER(user_name,passwd,email,country,cellphone,city,date_of_birth) VALUES('meda','meda','meda@yahoo.com','Germany','0106666600','Berlin','02-Jan-1999');

--SELECT * FROM COURSES.COURSE_USER;
*/

/*
desc courses.course;

*/


--select * from COURSES.COURSE_USER;


