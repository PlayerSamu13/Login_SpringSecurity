-- 1. Crear los roles
INSERT INTO roles (name) VALUES ('ADMIN'), ('PACIENTE'), ('MEDICO');

-- 2. Insertar la información

INSERT INTO users(username, password, enabled) VALUES('edward_paciente', '$2a$10$liPrAeV7ZM9GlnV9ERkQHe76yoITEyZDpI.cPDNrYR5yICb2eu4lW', true);
INSERT INTO users(username, password, enabled) VALUES ('edward_admin', '$2a$10$liPrAeV7ZM9GlnV9ERkQHe76yoITEyZDpI.cPDNrYR5yICb2eu4lW', true);
INSERT INTO users(username, password, enabled) VALUES ('edward_medico', '$2a$10$liPrAeV7ZM9GlnV9ERkQHe76yoITEyZDpI.cPDNrYR5yICb2eu4lW', true);

-- 3. Sujeto con ROL admin
INSERT INTO users_roles (user_id, role_id)
SELECT u.user_id, r.role_id FROM users u, roles r
WHERE u.username = 'edward_admin' AND r.name = 'ADMIN';

-- 4. Sujeto con ROL usuario
INSERT INTO users_roles (user_id, role_id)
SELECT u.user_id, r.role_id FROM users u, roles r
WHERE u.username = 'edward_user' AND r.name = 'USER';

-- 5. Sujeto con ROL medico
INSERT INTO users_roles (user_id, role_id)
SELECT u.user_id, r.role_id FROM users u, roles r
WHERE u.username = 'edward_medico' AND r.name = 'MEDICO';