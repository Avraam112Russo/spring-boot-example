--liquibase formatted sql

--changeset felix:1
create table t_dep_new(
                            id serial primary key ,
                            username varchar(32) unique
)