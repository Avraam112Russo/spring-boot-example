--liquibase formatted sql

--changeset felix:1
create table t_departments(
                            id serial primary key ,
                            department_name varchar(32) unique
);

--changeset felix:2
create table t_employees(
                            id serial primary key ,
                            first_name varchar (32) ,
                            last_name varchar (32) ,
                            user_name varchar(64) unique ,
                            department_id int,
                            foreign key (department_id) references t_departments(id)
);

--changeset felix:3
create table city_and_region(
                                city_and_region_id serial PRIMARY KEY ,
                                region int unique ,
                                city varchar(32) unique,
                                foreign key (city_and_region_id) references t_departments(id)
);