


##Create Table
create table PERSON(
person_id int(10) not null, 
first_name varchar(100), 
prefered_first_name varchar(100), 
last_name varchar(100) not null, 
date_of_birth DATE,
hire_date DATE,
occupation varchar(25),
primary key (person_id)
);

create table ADDRESS(
address_id int(10) not null,
person_id int(10) not null, 
address_type varchar(4), 
street_line_1 varchar(100), 
city varchar(100), 
state varchar(100), 
zip_code varchar(30), 
primary key(address_id), 
foreign key(person_id) references PERSON(person_id)
);

##Insert Data
INSERT INTO person (person_id, first_name, prefered_first_name, last_name, date_of_birth, hire_date,occupation)
VALUES ('101', 'aman', 'aaaaman', 'bidla', '2008-7-04', '2010-7-04','d');


INSERT INTO person (person_id, first_name, prefered_first_name, last_name, date_of_birth, hire_date,occupation)
VALUES ('102', null, null, 'chaman', null, null,null);

INSERT INTO address (address_id, person_id, address_type, street_line_1, city, state,zip_code)
VALUES ('202', '101', 'home', '50 brower', 'westhaven', 'ct','06516');

INSERT INTO address (address_id, person_id, address_type, street_line_1, city, state,zip_code)
VALUES ('201', '102', 'bill', null, null, null,null);

INSERT INTO person (person_id, first_name, prefered_first_name, last_name, date_of_birth, hire_date,occupation)
VALUES ('103', 'menaka', 'meenu', 'antony', '1980-7-04', '2017-7-04','j');


##Query 1
select prefered_first_name As REPORTING_NAME from person where prefered_first_name is not null;




##Query 2
select * from person where occupation is Null;




##Query 3
select * from person where date_of_birth < '1990-08-07';





##Query 4
select * from person where hire_date <= date_sub(current_date(), INTERVAL 100 day);


##Query 5
select * from person inner join address on person.person_id = address.person_id where address_type = 'home';


##Query 6
select pr.person_id, pr.first_name, pr.prefered_first_name,pr.last_name,pr.date_of_birth,pr.hire_date,pr.occupation,ad.address_id,ad.person_id,(case ad.address_type when 'bill' then ad.address_type else 'None' end),ad.street_line_1,ad.city,ad.state,ad.zip_code from person pr left join address ad on pr.person_id = ad.person_id;

##Query 7
select address_type, count(distinct address_type) as counting from address group by address_type;


##Query 8
select last_name, (select concat(street_line_1,",",city,",",state,",",zip_code) from 
address where address_type='home') as 'home_address', 
(select concat(street_line_1,",",city,",",state,",",zip_code) from 
address where address_type='bill') as 'billing_address' from 
person pr inner join address ad on pr.person_id = ad.person_id;


##Query 9
update person pr set pr.occupation='X' where pr.person_id in 
(select person_id from address where address_type='bill');

select * from person;
