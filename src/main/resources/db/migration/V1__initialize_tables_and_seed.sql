
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

