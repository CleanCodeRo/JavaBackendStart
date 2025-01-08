
CREATE TABLE roles
(
    id   UUID         NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id           UUID         NOT NULL,
    display_name VARCHAR(255),
    password     VARCHAR(255),
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    email        VARCHAR(255) NOT NULL,
    role_id      UUID         NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE password_reset_codes
(
    id         UUID         NOT NUll,
    email      VARCHAR(255) NOT NULL,
    code       VARCHAR(12)  NOT NULL,
    expires_at TIMESTAMP    NOT NULL,
    user_id UUID,
    CONSTRAINT pk_password_reset_codes PRIMARY KEY (id)
);

ALTER TABLE roles
    ADD CONSTRAINT uc_roles_name UNIQUE (name);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

insert into roles(id, name) values
                                ('9367c292-12ab-472f-bf5f-4d8e232e342d', 'WORKER'),
                                ('8c3c9bbd-d195-4ea4-88d1-7ccb3263570b', 'DIRECTOR'),
                                ('0e236a7c-5224-4716-a2a9-f6b0953f87ca', 'ACCOUNTANT'),
                                ('07b0edbc-1ebd-4c09-bb71-2e75c5d0ad40', 'ADMIN');

-- Seed users
-- Workers
INSERT INTO users (id, display_name, password, first_name, last_name, email, role_id) VALUES
                                                                                          ('b7f9a9b1-d6c1-4974-b094-86d99f58d001', 'pm_worker1', '$2a$12$lgT6AkvcE8mO/6ilTtoFs.YR.UZF9izJFcpIadWJqjdnIIXgMpG36', 'Worker1', 'Worker', 'pm_worker1@cleancodeacademy.ro', '9367c292-12ab-472f-bf5f-4d8e232e342d'),
                                                                                          ('b7f9a9b1-d6c1-4974-b094-86d99f58d002', 'pm_worker2', '$2a$12$lgT6AkvcE8mO/6ilTtoFs.YR.UZF9izJFcpIadWJqjdnIIXgMpG36', 'Worker2', 'Worker', 'pm_worker2@cleancodeacademy.ro', '9367c292-12ab-472f-bf5f-4d8e232e342d'),
                                                                                          ('b7f9a9b1-d6c1-4974-b094-86d99f58d003', 'pm_worker3', '$2a$12$lgT6AkvcE8mO/6ilTtoFs.YR.UZF9izJFcpIadWJqjdnIIXgMpG36', 'Worker3', 'Worker', 'pm_worker3@cleancodeacademy.ro', '9367c292-12ab-472f-bf5f-4d8e232e342d'),
                                                                                          ('b7f9a9b1-d6c1-4974-b094-86d99f58d004', 'pm_worker4', '$2a$12$lgT6AkvcE8mO/6ilTtoFs.YR.UZF9izJFcpIadWJqjdnIIXgMpG36', 'Worker4', 'Worker', 'pm_worker4@cleancodeacademy.ro', '9367c292-12ab-472f-bf5f-4d8e232e342d'),
                                                                                          ('b7f9a9b1-d6c1-4974-b094-86d99f58d005', 'pm_worker5', '$2a$12$lgT6AkvcE8mO/6ilTtoFs.YR.UZF9izJFcpIadWJqjdnIIXgMpG36', 'Worker5', 'Worker', 'pm_worker5@cleancodeacademy.ro', '9367c292-12ab-472f-bf5f-4d8e232e342d'),
                                                                                          ('b7f9a9b1-d6c1-4974-b094-86d99f58d006', 'pm_worker6', '$2a$12$lgT6AkvcE8mO/6ilTtoFs.YR.UZF9izJFcpIadWJqjdnIIXgMpG36', 'Worker6', 'Worker', 'pm_worker6@cleancodeacademy.ro', '9367c292-12ab-472f-bf5f-4d8e232e342d');

-- Directors
INSERT INTO users (id, display_name, password, first_name, last_name, email, role_id) VALUES
                                                                                          ('8f2c8375-6c6a-4b9b-bc29-86204f61d007', 'director1', '$2a$12$lgT6AkvcE8mO/6ilTtoFs.YR.UZF9izJFcpIadWJqjdnIIXgMpG36', 'Director1', 'Boss', 'director1@cleancodeacademy.ro', '8c3c9bbd-d195-4ea4-88d1-7ccb3263570b'),
                                                                                          ('8f2c8375-6c6a-4b9b-bc29-86204f61d008', 'director2', '$2a$12$lgT6AkvcE8mO/6ilTtoFs.YR.UZF9izJFcpIadWJqjdnIIXgMpG36', 'Director2', 'Boss', 'director2@cleancodeacademy.ro', '8c3c9bbd-d195-4ea4-88d1-7ccb3263570b'),
                                                                                          ('8f2c8375-6c6a-4b9b-bc29-86204f61d009', 'director3', '$2a$12$lgT6AkvcE8mO/6ilTtoFs.YR.UZF9izJFcpIadWJqjdnIIXgMpG36', 'Director3', 'Boss', 'director3@cleancodeacademy.ro', '8c3c9bbd-d195-4ea4-88d1-7ccb3263570b');

-- Admin
INSERT INTO users (id, display_name, password, first_name, last_name, email, role_id) VALUES
    ('5e1b3b64-c05a-4b90-902b-02e08388d010', 'admin', '$2a$12$lgT6AkvcE8mO/6ilTtoFs.YR.UZF9izJFcpIadWJqjdnIIXgMpG36', 'Admin', 'User', 'admin@cleancodeacademy.ro', '07b0edbc-1ebd-4c09-bb71-2e75c5d0ad40');

-- Accountant
INSERT INTO users (id, display_name, password, first_name, last_name, email, role_id) VALUES
    ('6a2e739b-b47b-497e-87ff-81a1765ed011', 'accountant', '$2a$12$lgT6AkvcE8mO/6ilTtoFs.YR.UZF9izJFcpIadWJqjdnIIXgMpG36', 'Accountant', 'User', 'accountant@cleancodeacademy.ro', '0e236a7c-5224-4716-a2a9-f6b0953f87ca');


