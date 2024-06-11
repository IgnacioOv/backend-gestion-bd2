
-- Crear tablas
CREATE TABLE Projects (
                          project_id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description TEXT,
                          start_date DATE,
                          end_date DATE,
                          status VARCHAR(50)
);

CREATE TABLE Roles (
                       role_id SERIAL PRIMARY KEY,
                       role_name VARCHAR(50) NOT NULL
);

CREATE TABLE Users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       role INT REFERENCES Roles(role_id),
                       nombre VARCHAR(100),
                       email VARCHAR(100)
);

CREATE TABLE Tasks (
                       task_id SERIAL PRIMARY KEY,
                       project_id INT REFERENCES Projects(project_id),
                       name VARCHAR(100) NOT NULL,
                       description TEXT,
                       assigned_to INT REFERENCES Users(user_id),
                       status VARCHAR(50),
                       start_date DATE,
                       end_date DATE
);

CREATE TABLE TaskAssignments (
                                 assignment_id SERIAL PRIMARY KEY,
                                 task_id INT REFERENCES Tasks(task_id),
                                 user_id INT REFERENCES Users(user_id)
);

-- Insertar datos de prueba
-- Insertar datos en Projects
INSERT INTO Projects (name, description, start_date, end_date, status) VALUES
                                                                           ('Project Alpha', 'Description for Project Alpha', '2024-01-01', '2024-12-31', 'In Progress'),
                                                                           ('Project Beta', 'Description for Project Beta', '2024-02-01', '2024-11-30', 'Not Started');

-- Insertar datos en Roles
INSERT INTO Roles (role_name) VALUES
                                  ('Admin'),
                                  ('Leader'),
                                  ('User');

-- Insertar datos en Users
INSERT INTO Users (username, password, role, nombre, email) VALUES
                                                                ('admin_user', 'admin_password', 1, 'Admin User', 'admin@example.com'),
                                                                ('leader_user', 'leader_password', 2, 'Leader User', 'leader@example.com'),
                                                                ('regular_user', 'user_password', 3, 'Regular User', 'user@example.com');

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
