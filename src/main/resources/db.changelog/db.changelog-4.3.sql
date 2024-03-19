--liquibase formatted sql

--changeset felix:1
create table t_users(
    id BIGSERIAL primary key ,
    user_name varchar(64) not null unique ,
    email varchar(64) not null unique ,
    birth_date date not null
);