
-- Usar la base de datos creada
\c gestion_bd2;

-- Crear la tabla de usuarios
CREATE TABLE Users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(50) NOT NULL CHECK (role IN ('Admin', 'Employee')),
                       nombre VARCHAR(100),
                       email VARCHAR(100),
                       weekly_hours INT DEFAULT 0
);

-- Crear la tabla de proyectos
CREATE TABLE Projects (
                          project_id SERIAL PRIMARY KEY,
                          project_name VARCHAR(100) NOT NULL,
                          description TEXT,
                          start_date DATE,
                          end_date DATE,
                          weekly_hours INT DEFAULT 0
);

-- Crear la tabla de tareas
CREATE TABLE Tasks (
                       task_id SERIAL PRIMARY KEY,
                       project_id INTEGER REFERENCES Projects(project_id),
                       name VARCHAR(100) NOT NULL,
                       description TEXT,
                       status VARCHAR(50),
                       assigned_to INTEGER REFERENCES Users(user_id),
                       start_date DATE,
                       end_date DATE
);

-- Crear la tabla de asignaciones de tareas
CREATE TABLE TaskAssignments (
                                 task_id INTEGER REFERENCES Tasks(task_id),
                                 user_id INTEGER REFERENCES Users(user_id),
                                 PRIMARY KEY (task_id, user_id)
);

-- Insertar datos de prueba
-- Insertar datos en Projects
INSERT INTO Projects (name, description, start_date, end_date, status, weekly_hours) VALUES
                                                                                         ('Project Alpha', 'Description for Project Alpha', '2024-01-01', '2024-12-31', 'In Progress', 40),
                                                                                         ('Project Beta', 'Description for Project Beta', '2024-02-01', '2024-11-30', 'Not Started', 30);

-- Insertar datos en Users
INSERT INTO Users (username, password, role, nombre, email, weekly_hours) VALUES
                                                                              ('admin_user', 'admin_password', 'ADMIN', 'Admin User', 'admin@example.com', 40),
                                                                              ('employee_user1', 'employee_password1', 'EMPLOYEE', 'Employee User1', 'employee1@example.com', 35),
                                                                              ('employee_user2', 'employee_password2', 'EMPLOYEE', 'Employee User2', 'employee2@example.com', 30);

-- Insertar datos en Tasks
INSERT INTO Tasks (project_id, name, description, assigned_to, status, start_date, end_date) VALUES
                                                                                                 (1, 'Task 1', 'Description for Task 1', 1, 'In Progress', '2024-01-01', '2024-06-30'),
                                                                                                 (1, 'Task 2', 'Description for Task 2', 2, 'Not Started', '2024-02-01', '2024-07-31'),
                                                                                                 (2, 'Task 3', 'Description for Task 3', 1, 'Completed', '2024-03-01', '2024-05-31');

-- Insertar datos en TaskAssignments
INSERT INTO TaskAssignments (task_id, user_id) VALUES
                                                   (1, 3),
                                                   (2, 3),
                                                   (3, 3);
