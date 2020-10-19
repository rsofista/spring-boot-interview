insert into user (id, username, password) values (1, 'lucas', '$2a$10$q2BMIcWPMza54kEI8tLptuy6AYMv23bxOdLKXOagVI2HaAWnCBi3a');
insert into state (id, name) values (1, 'santa catarina');
insert into city (name, state_id) values ('tilambuco', 1);
insert into client (id, name, sex, birth_date, current_city_id) values (1, 'JOsé', 'MALE', '1980-01-05', 1);
insert into client (id, name, sex, birth_date, current_city_id) values (2, 'Maria', 'FEMALE', '1981-08-02', 1);
insert into client (id, name, sex, birth_date, current_city_id) values (3, 'MARTA', 'UNDEFINED', '2001-02-06', 1);