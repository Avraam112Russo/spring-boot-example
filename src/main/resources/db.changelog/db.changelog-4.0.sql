--liquibase formatted sql

--changeset felix:1
ALTER TABLE t_departments
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE t_departments
    ADD COLUMN updated_at TIMESTAMP;

ALTER TABLE t_departments
    ADD COLUMN created_by VARCHAR(32);

ALTER TABLE t_departments
    ADD COLUMN modified_by VARCHAR(32);