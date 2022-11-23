insert into address (id, house_number, city, post_code, street)
values (nextval('address_seq'),'22','Leuven','3000','Langestraat');

insert into customer (id, first_name, last_name, email, address_id, phone_number, password)
values (nextval('customer_seq'),'Giga','Chad','gigachad@based.com',1,'1111111111111','password');

insert into customer_privilege (customer_id, privilege)
values (1,'CUSTOMER');

insert into customer_privilege (customer_id, privilege)
values (1,'ADMIN');