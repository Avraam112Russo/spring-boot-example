--liquibase formatted sql

--changeset felix:1
ALTER TABLE t_users ADD COLUMN user_image varchar(128);