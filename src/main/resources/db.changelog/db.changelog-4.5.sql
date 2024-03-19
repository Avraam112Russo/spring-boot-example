--liquibase formatted sql

--changeset felix:1
ALTER TABLE t_users
    ADD COLUMN user_password varchar(128) DEFAULT '{noop}123';
ALTER TABLE t_users
    ADD COLUMN user_role varchar(128) DEFAULT 'USER_ROLE';