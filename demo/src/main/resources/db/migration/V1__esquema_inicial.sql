CREATE TABLE roles (
                       role_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       name VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE users (
                       user_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       username VARCHAR(45) NOT NULL UNIQUE,
                       password VARCHAR(64) NOT NULL,
                       enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE users_roles (
                             user_id INT NOT NULL,
                             role_id INT NOT NULL,
                             PRIMARY KEY (user_id, role_id),
                             CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
                             CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE
);