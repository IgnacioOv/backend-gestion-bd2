-- Crear la tabla de usuarios
CREATE TABLE Users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(50) NOT NULL CHECK (role IN ('Admin', 'Employee')),
                       name VARCHAR(100),
                       last_name VARCHAR(100),
                       email VARCHAR(100),
                       weekly_hours INT DEFAULT 0,
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
                       status VARCHAR(50),
                       start_date DATE,
                       end_date DATE,
                       FOREIGN KEY (project_id) REFERENCES Projects(project_id),
                       CHECK (status IN ('To Do', 'In Progress', 'Done', 'Cancelled', 'Waiting'))
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
INSERT INTO Projects (name, description, start_date, end_date, weekly_hours) VALUES
                                                                                 ('Project Alpha', 'Description for Project Alpha', '2024-01-01', '2024-12-31', 40),
                                                                                 ('Project Beta', 'Description for Project Beta', '2024-02-01', '2024-11-30', 30);

-- Insertar datos en Users
INSERT INTO Users (username, password, role, name, email, weekly_hours) VALUES
                                                                            ('admin_user', 'admin_password', 'Admin', 'Admin User', 'admin@example.com', 40),
                                                                            ('employee_user1', 'employee_password1', 'Employee', 'Employee User1', 'employee1@example.com', 35),
                                                                            ('employee_user2', 'employee_password2', 'Employee', 'Employee User2', 'employee2@example.com', 30);

-- Insertar datos en Tasks
INSERT INTO Tasks (project_id, name, description, status, start_date, end_date) VALUES
                                                                                    (1, 'Task 1', 'Description for Task 1', 'In Progress', '2024-01-01', '2024-06-30'),
                                                                                    (1, 'Task 2', 'Description for Task 2', 'To Do', '2024-02-01', '2024-07-31'),
                                                                                    (2, 'Task 3', 'Description for Task 3', 'Done', '2024-03-01', '2024-05-31');

-- Insertar datos en TaskAssignments
INSERT INTO TaskAssignments (task_id, user_id) VALUES
                                                   (1, 3),
                                                   (2, 3),
                                                   (3, 3);

-- Insertar datos en ProjectAssignments
INSERT INTO ProjectAssignments (project_id, user_id) VALUES
                                                         (1, 3),
                                                         (2, 3);
