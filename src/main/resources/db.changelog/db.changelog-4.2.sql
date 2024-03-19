--liquibase formatted sql

--changeset felix:1
INSERT INTO t_departments (id, department_name) values (6, 'MEDICINE');