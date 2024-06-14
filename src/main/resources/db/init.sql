-- Crear la tabla de usuarios
CREATE TABLE Users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(50) NOT NULL CHECK (role IN ('Admin', 'Employee')),
                       name VARCHAR(100),
                       last_name VARCHAR(100),
                       email VARCHAR(100) NOT NULL UNIQUE,
                       weekly_hours INT DEFAULT 0,
                       skill_level VARCHAR(50) CHECK (
                           skill_level IN ('BACKEND_JUNIOR', 'BACKEND_MID', 'BACKEND_SENIOR',
                                           'FRONTEND_JUNIOR', 'FRONTEND_MID', 'FRONTEND_SENIOR',
                                           'FULLSTACK_JUNIOR', 'FULLSTACK_MID', 'FULLSTACK_SENIOR',
                                           'DEVOPS_JUNIOR', 'DEVOPS_MID', 'DEVOPS_SENIOR')
                           ),
                       CONSTRAINT email_format CHECK (email ~* '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$')
);

-- Crear la tabla de proyectos
CREATE TABLE Projects (
                          project_id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description TEXT,
                          start_date DATE,
                          end_date DATE,
                          weekly_hours INT DEFAULT 0
);

-- Crear la tabla de tareas
CREATE TABLE Tasks (
                       task_id SERIAL PRIMARY KEY,
                       project_id INTEGER NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       description TEXT,
                       skill_level VARCHAR(50) CHECK (
                           skill_level IN ('BACKEND_JUNIOR', 'BACKEND_MID', 'BACKEND_SENIOR',
                                           'FRONTEND_JUNIOR', 'FRONTEND_MID', 'FRONTEND_SENIOR',
                                           'FULLSTACK_JUNIOR', 'FULLSTACK_MID', 'FULLSTACK_SENIOR',
                                           'DEVOPS_JUNIOR', 'DEVOPS_MID', 'DEVOPS_SENIOR')
                           ),
                       status INTEGER DEFAULT 0 CHECK (status >= 0 AND status <= 100),
                       start_date DATE,
                       end_date DATE,
                       FOREIGN KEY (project_id) REFERENCES Projects(project_id)
);

-- Crear la tabla de asignaciones de tareas
CREATE TABLE TaskAssignments (
                                 task_id INTEGER,
                                 user_id INTEGER,
                                 PRIMARY KEY (task_id, user_id),
                                 FOREIGN KEY (task_id) REFERENCES Tasks(task_id),
                                 FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Crear la tabla de asignaciones de proyectos
CREATE TABLE ProjectAssignments (
                                    project_id INTEGER,
                                    user_id INTEGER,
                                    PRIMARY KEY (project_id, user_id),
                                    FOREIGN KEY (project_id) REFERENCES Projects(project_id),
                                    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Insertar datos de prueba

-- Insertar datos en Projects
INSERT INTO Projects (name, description, start_date, end_date, weekly_hours)
VALUES
    ('Project Alpha', 'Description for Project Alpha', '2024-01-01', '2024-12-31', 40),
    ('Project Beta', 'Description for Project Beta', '2024-02-01', '2024-11-30', 30),
    ('Project Gamma', 'Description for Project Gamma', '2024-03-01', '2024-10-31', 25),
    ('Project Delta', 'Description for Project Delta', '2024-04-01', '2024-09-30', 20),
    ('Project Epsilon', 'Description for Project Epsilon', '2024-05-01', '2024-08-31', 15),
    ('Project Zeta', 'Description for Project Zeta', '2024-06-01', '2024-07-31', 10),
    ('Project Theta', 'Description for Project Theta', '2024-07-01', '2024-06-30', 5),
    ('Project Iota', 'Description for Project Iota', '2024-08-01', '2024-05-31', 0),
    ('Project Kappa', 'Description for Project Kappa', '2024-09-01', '2024-04-30', 0),
    ('Project Lambda', 'Description for Project Lambda', '2024-10-01', '2024-03-31', 0);


-- Insertar datos en Users con contraseñas encriptadas en Base64
INSERT INTO Users (username, password, role, name, last_name, email, weekly_hours, skill_level)
VALUES
    ('admin_user', 'QWRtaW5fMTIzNDU2', 'Admin', 'Admin', 'User', 'admin@example.com', 40, 'BACKEND_SENIOR'),
    ('employee_user1', 'RW1wbG95ZWVfMTIzNDU2', 'Employee', 'Employee', 'User1', 'employee1@example.com', 35, 'FRONTEND_MID'),
    ('employee_user2', 'RW1wbG95ZWVfMTIzNDU2', 'Employee', 'Employee', 'User2', 'employee2@example.com', 30, 'DEVOPS_JUNIOR'),
    ('user3', 'VXNlcm5hbWVfMTIzNDU2', 'Employee', 'User', 'Three', 'user3@example.com', 25, 'BACKEND_JUNIOR'),
    ('user4', 'VXNlcm5hbWVfMTIzNDU2', 'Employee', 'User', 'Four', 'user4@example.com', 20, 'FRONTEND_SENIOR'),
    ('user5', 'UGFzc3dvcmRfMTIzNDU2', 'Employee', 'User', 'Five', 'user5@example.com', 15, 'FULLSTACK_MID'),
    ('user6', 'UGFzc3dvcmRfMTIzNDU2', 'Employee', 'User', 'Six', 'user6@example.com', 10, 'BACKEND_MID'),
    ('user7', 'UGFzc3dvcmRfMTIzNDU2', 'Employee', 'User', 'Seven', 'user7@example.com', 5, 'DEVOPS_SENIOR'),
    ('user8', 'UGFzc3dvcmRfMTIzNDU2', 'Employee', 'User', 'Eight', 'user8@example.com', 0, 'FRONTEND_JUNIOR'),
    ('user9', 'UGFzc3dvcmRfMTIzNDU2', 'Employee', 'User', 'Nine', 'user9@example.com', 0, 'FULLSTACK_SENIOR');



-- Insertar datos en Tasks
-- Insertar datos en Tasks para Project Alpha
INSERT INTO Tasks (project_id, name, description, status, start_date, end_date, skill_level)
VALUES
    (1, 'Task 1 for Project Alpha', 'Description for Task 1', 50, '2024-01-01', '2024-06-30', 'BACKEND_JUNIOR'),
    (1, 'Task 2 for Project Alpha', 'Description for Task 2', 0, '2024-02-01', '2024-07-31', 'FRONTEND_MID'),
    (1, 'Task 3 for Project Alpha', 'Description for Task 3', 100, '2024-03-01', '2024-05-31', 'FULLSTACK_SENIOR');

-- Insertar datos en Tasks para Project Beta
INSERT INTO Tasks (project_id, name, description, status, start_date, end_date, skill_level)
VALUES
    (2, 'Task 1 for Project Beta', 'Description for Task 1', 60, '2024-02-01', '2024-07-31', 'DEVOPS_JUNIOR'),
    (2, 'Task 2 for Project Beta', 'Description for Task 2', 10, '2024-03-01', '2024-08-31', 'BACKEND_SENIOR'),
    (2, 'Task 3 for Project Beta', 'Description for Task 3', 90, '2024-04-01', '2024-09-30', 'FULLSTACK_MID');

-- Insertar datos en Tasks para Project Gamma
INSERT INTO Tasks (project_id, name, description, status, start_date, end_date, skill_level)
VALUES
    (3, 'Task 1 for Project Gamma', 'Description for Task 1', 70, '2024-03-01', '2024-10-31', 'FRONTEND_JUNIOR'),
    (3, 'Task 2 for Project Gamma', 'Description for Task 2', 20, '2024-04-01', '2024-11-30', 'DEVOPS_SENIOR'),
    (3, 'Task 3 for Project Gamma', 'Description for Task 3', 80, '2024-05-01', '2024-12-31', 'FULLSTACK_JUNIOR');


-- Insertar más datos en Tasks para Project Delta con skill_level y status
INSERT INTO Tasks (project_id, name, description, skill_level, status, start_date, end_date)
VALUES
    (4, 'Task 1 for Project Delta', 'Description for Task 1', 'BACKEND_JUNIOR', 50, '2024-07-01', '2024-08-31'),
    (4, 'Task 2 for Project Delta', 'Description for Task 2', 'FRONTEND_SENIOR', 75, '2024-08-01', '2024-09-30'),
    (4, 'Task 3 for Project Delta', 'Description for Task 3', 'FULLSTACK_MID', 25, '2024-09-01', '2024-10-31');

-- Insertar más datos en Tasks para Project Epsilon con skill_level y status
INSERT INTO Tasks (project_id, name, description, skill_level, status, start_date, end_date)
VALUES
    (5, 'Task 1 for Project Epsilon', 'Description for Task 1', 'DEVOPS_SENIOR', 40, '2024-08-01', '2024-09-30'),
    (5, 'Task 2 for Project Epsilon', 'Description for Task 2', 'FULLSTACK_JUNIOR', 85, '2024-09-01', '2024-10-31'),
    (5, 'Task 3 for Project Epsilon', 'Description for Task 3', 'BACKEND_SENIOR', 60, '2024-10-01', '2024-11-30');

-- Insertar más datos en Tasks para Project Zeta con skill_level y status
INSERT INTO Tasks (project_id, name, description, skill_level, status, start_date, end_date)
VALUES
    (6, 'Task 1 for Project Zeta', 'Description for Task 1', 'FRONTEND_MID', 10, '2024-09-01', '2024-10-31'),
    (6, 'Task 2 for Project Zeta', 'Description for Task 2', 'DEVOPS_JUNIOR', 95, '2024-10-01', '2024-11-30'),
    (6, 'Task 3 for Project Zeta', 'Description for Task 3', 'FULLSTACK_SENIOR', 30, '2024-11-01', '2024-12-31');

-- Insertar datos en TaskAssignments
INSERT INTO TaskAssignments (task_id, user_id)
VALUES
    (1, 2), -- Asignar Task 1 del proyecto Alpha al usuario employee_user1
    (2, 3), -- Asignar Task 2 del proyecto Alpha al usuario employee_user2
    (3, 3), -- Asignar Task 3 del proyecto Beta al usuario employee_user2
    (4, 1), -- Asignar Task 1 del proyecto Delta al usuario admin_user
    (5, 2), -- Asignar Task 1 del proyecto Epsilon al usuario employee_user1
    (6, 3); -- Asignar Task 1 del proyecto Zeta al usuario employee_user2

-- Insertar datos en ProjectAssignments
INSERT INTO ProjectAssignments (project_id, user_id)
VALUES
    (1, 3), -- Asignar Project Alpha al usuario employee_user2
    (2, 3), -- Asignar Project Beta al usuario employee_user2
    (4, 1), -- Asignar Project Delta al usuario admin_user
    (5, 2), -- Asignar Project Epsilon al usuario employee_user1
    (6, 3); -- Asignar Project Zeta al usuario employee_user2
