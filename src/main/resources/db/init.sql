-- Adminer 4.8.1 PostgreSQL 16.3 (Debian 16.3-1.pgdg120+1) dump

DROP TABLE IF EXISTS "project_assignments";
CREATE TABLE "public"."project_assignments" (
                                                "project_id" integer NOT NULL,
                                                "user_id" integer NOT NULL,
                                                CONSTRAINT "project_assignments_pkey" PRIMARY KEY ("project_id", "user_id")
) WITH (oids = false);

INSERT INTO "project_assignments" ("project_id", "user_id") VALUES
                                                                (4,	1),
                                                                (12,	1),
                                                                (12,	11),
                                                                (12,	6),
                                                                (12,	12),
                                                                (12,	13);

DROP TABLE IF EXISTS "projects";
DROP SEQUENCE IF EXISTS projects_project_id_seq;
CREATE SEQUENCE projects_project_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."projects" (
                                     "project_id" integer DEFAULT nextval('projects_project_id_seq') NOT NULL,
                                     "name" character varying(255) NOT NULL,
                                     "description" character varying(255),
                                     "status" integer DEFAULT '0',
                                     "start_date" date,
                                     "end_date" date,
                                     "weekly_hours" integer DEFAULT '0',
                                     CONSTRAINT "projects_pkey" PRIMARY KEY ("project_id")
) WITH (oids = false);

INSERT INTO "projects" ("project_id", "name", "description", "status", "start_date", "end_date", "weekly_hours") VALUES
                                                                                                                     (1,	'Project Alpha',	'Description for Project Alpha',	0,	'2024-01-01',	'2024-12-31',	40),
                                                                                                                     (2,	'Project Beta',	'Description for Project Beta',	0,	'2024-02-01',	'2024-11-30',	30),
                                                                                                                     (3,	'Project Gamma',	'Description for Project Gamma',	0,	'2024-03-01',	'2024-10-31',	25),
                                                                                                                     (4,	'Project Delta',	'Description for Project Delta',	0,	'2024-04-01',	'2024-09-30',	20),
                                                                                                                     (5,	'Project Epsilon',	'Description for Project Epsilon',	0,	'2024-05-01',	'2024-08-31',	15),
                                                                                                                     (6,	'Project Zeta',	'Description for Project Zeta',	0,	'2024-06-01',	'2024-07-31',	10),
                                                                                                                     (7,	'Project Theta',	'Description for Project Theta',	0,	'2024-07-01',	'2024-06-30',	5),
                                                                                                                     (8,	'Project Iota',	'Description for Project Iota',	0,	'2024-08-01',	'2024-05-31',	0),
                                                                                                                     (9,	'Project Kappa',	'Description for Project Kappa',	0,	'2024-09-01',	'2024-04-30',	0),
                                                                                                                     (10,	'Project Lambda',	'Description for Project Lambda',	0,	'2024-10-01',	'2024-03-31',	0),
                                                                                                                     (12,	'Migracion de DevOps',	'actualizar ssh ',	40,	'2024-06-24',	'2024-06-30',	15);

DROP TABLE IF EXISTS "tasks";
DROP SEQUENCE IF EXISTS tasks_task_id_seq;
CREATE SEQUENCE tasks_task_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."tasks" (
                                  "task_id" integer DEFAULT nextval('tasks_task_id_seq') NOT NULL,
                                  "project_id" integer NOT NULL,
                                  "user_id" integer,
                                  "name" character varying(255) NOT NULL,
                                  "description" character varying(255),
                                  "skill_level" character varying(255),
                                  "status" integer DEFAULT '0',
                                  "start_date" date,
                                  "end_date" date,
                                  CONSTRAINT "tasks_pkey" PRIMARY KEY ("task_id")
) WITH (oids = false);

INSERT INTO "tasks" ("task_id", "project_id", "user_id", "name", "description", "skill_level", "status", "start_date", "end_date") VALUES
                                                                                                                                       (20,	3,	NULL,	'Nueva Tarea',	'Descripción de la nueva tarea',	'FRONTEND_MID',	0,	'2024-06-16',	'2024-06-23'),
                                                                                                                                       (1,	1,	NULL,	'Task 1 for Project Alpha',	'Descripción actualizada de la tarea',	'BACKEND_SENIOR',	90,	'2024-07-01',	'2024-08-01'),
                                                                                                                                       (2,	1,	NULL,	'Task 2 for Project Alpha',	'Description for Task 2',	'FRONTEND_MID',	0,	'2024-02-01',	'2024-07-31'),
                                                                                                                                       (3,	1,	NULL,	'Task 3 for Project Alpha',	'Description for Task 3',	'FULLSTACK_SENIOR',	100,	'2024-03-01',	'2024-05-31'),
                                                                                                                                       (4,	2,	NULL,	'Task 1 for Project Beta',	'Description for Task 1',	'DEVOPS_JUNIOR',	60,	'2024-02-01',	'2024-07-31'),
                                                                                                                                       (5,	2,	NULL,	'Task 2 for Project Beta',	'Description for Task 2',	'BACKEND_SENIOR',	10,	'2024-03-01',	'2024-08-31'),
                                                                                                                                       (6,	2,	NULL,	'Task 3 for Project Beta',	'Description for Task 3',	'FULLSTACK_MID',	90,	'2024-04-01',	'2024-09-30'),
                                                                                                                                       (7,	3,	NULL,	'Task 1 for Project Gamma',	'Description for Task 1',	'FRONTEND_JUNIOR',	70,	'2024-03-01',	'2024-10-31'),
                                                                                                                                       (8,	3,	NULL,	'Task 2 for Project Gamma',	'Description for Task 2',	'DEVOPS_SENIOR',	20,	'2024-04-01',	'2024-11-30'),
                                                                                                                                       (9,	3,	NULL,	'Task 3 for Project Gamma',	'Description for Task 3',	'FULLSTACK_JUNIOR',	80,	'2024-05-01',	'2024-12-31'),
                                                                                                                                       (23,	12,	NULL,	'cambios en la interfaces',	'se cambiaran las interfaces del cliente',	'FRONTEND_JUNIOR',	0,	'2024-06-25',	'2024-07-04'),
                                                                                                                                       (24,	12,	NULL,	'creacion de notificvaciones',	'dasdasfgaf',	'FULLSTACK_MID',	40,	'2024-06-29',	'2024-07-03'),
                                                                                                                                       (10,	4,	NULL,	'Task 1 for Project Delta',	'Description for Task 1',	'BACKEND_JUNIOR',	50,	'2024-07-01',	'2024-08-31'),
                                                                                                                                       (11,	4,	NULL,	'Task 2 for Project Delta',	'Description for Task 2',	'FRONTEND_SENIOR',	75,	'2024-08-01',	'2024-09-30'),
                                                                                                                                       (12,	4,	NULL,	'Task 3 for Project Delta',	'Description for Task 3',	'FULLSTACK_MID',	25,	'2024-09-01',	'2024-10-31'),
                                                                                                                                       (13,	5,	NULL,	'Task 1 for Project Epsilon',	'Description for Task 1',	'DEVOPS_SENIOR',	40,	'2024-08-01',	'2024-09-30'),
                                                                                                                                       (14,	5,	NULL,	'Task 2 for Project Epsilon',	'Description for Task 2',	'FULLSTACK_JUNIOR',	85,	'2024-09-01',	'2024-10-31'),
                                                                                                                                       (15,	5,	NULL,	'Task 3 for Project Epsilon',	'Description for Task 3',	'BACKEND_SENIOR',	60,	'2024-10-01',	'2024-11-30'),
                                                                                                                                       (16,	6,	NULL,	'Task 1 for Project Zeta',	'Description for Task 1',	'FRONTEND_MID',	10,	'2024-09-01',	'2024-10-31'),
                                                                                                                                       (17,	6,	NULL,	'Task 2 for Project Zeta',	'Description for Task 2',	'DEVOPS_JUNIOR',	95,	'2024-10-01',	'2024-11-30'),
                                                                                                                                       (18,	6,	NULL,	'Task 3 for Project Zeta',	'Description for Task 3',	'FULLSTACK_SENIOR',	30,	'2024-11-01',	'2024-12-31'),
                                                                                                                                       (31,	12,	13,	'cambios de version de ssh',	'sadasdadasd',	'DEVOPS_JUNIOR',	0,	'2024-06-26',	'2024-07-05'),
                                                                                                                                       (22,	12,	11,	'cambio en la api de deploy',	'esta api te permite hacer a un deploy remoto',	'BACKEND_JUNIOR',	32,	'2024-06-27',	'2024-06-30');

DROP TABLE IF EXISTS "users";
DROP SEQUENCE IF EXISTS users_user_id_seq;
CREATE SEQUENCE users_user_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."users" (
                                  "user_id" integer DEFAULT nextval('users_user_id_seq') NOT NULL,
                                  "username" character varying(255) NOT NULL,
                                  "password" character varying(255) NOT NULL,
                                  "role" character varying(255) NOT NULL,
                                  "name" character varying(255),
                                  "last_name" character varying(255),
                                  "email" character varying(255) NOT NULL,
                                  "weekly_hours" integer DEFAULT '0',
                                  "skill_level" character varying(255),
                                  CONSTRAINT "users_email_key" UNIQUE ("email"),
                                  CONSTRAINT "users_pkey" PRIMARY KEY ("user_id")
) WITH (oids = false);

INSERT INTO "users" ("user_id", "username", "password", "role", "name", "last_name", "email", "weekly_hours", "skill_level") VALUES
                                                                                                                                 (1,	'admin_user',	'$2a$10$guXKfCk.dutXNVe11yoxRuk1DfxUsYXsYBUlUI0v0zyjt3UZzKLLW',	'Admin',	'Admin',	'User',	'admin@example.com',	40,	'BACKEND_SENIOR'),
                                                                                                                                 (6,	'user5',	'8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92',	'Employee',	'User',	'Five',	'user5@example.com',	15,	'FULLSTACK_MID'),
                                                                                                                                 (11,	'agusAused',	'$2a$10$.xROmM9Ji/EGUvIlKXfvBehyDEdevYini8GR9.Zb4M0amdzexH4aS',	'Admin',	'Agustin',	'Aused',	'agus.aused@gmail.com',	50,	'BACKEND_SENIOR'),
                                                                                                                                 (12,	'jAcebedo',	'$2a$10$WEAT8iOdErLeGYkwUWbLHeWtgp2p1ioCxavbQIzv37rQq7MT/Lzuu',	'Employee',	'Jose',	'Acebedo',	'hola@example.com',	45,	'DEVOPS_JUNIOR'),
                                                                                                                                 (13,	'jAused',	'$2a$10$GcbFE435imcoO163ufoggO463/tp9h6qmEBxHSMAnW/VQBtCLcKa6',	'Employee',	'Jose',	'Aused',	'jose@mail.com',	50,	'DEVOPS_SENIOR'),
                                                                                                                                 (14,	'nAused',	'$2a$10$wOTvRZQ8rr8ZX2xhSE/q0uJwDo1KrddQKbAufa5lnXg71TcGSkzo2',	'Employee',	'Nicolas',	'Aused',	'agus@mail.com',	50,	'FULLSTACK_SENIOR');

ALTER TABLE ONLY "public"."project_assignments" ADD CONSTRAINT "project_assignments_project_id_fkey" FOREIGN KEY (project_id) REFERENCES projects(project_id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."project_assignments" ADD CONSTRAINT "project_assignments_user_id_fkey" FOREIGN KEY (user_id) REFERENCES users(user_id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."tasks" ADD CONSTRAINT "tasks_project_id_fkey" FOREIGN KEY (project_id) REFERENCES projects(project_id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."tasks" ADD CONSTRAINT "tasks_user_id_fkey" FOREIGN KEY (user_id) REFERENCES users(user_id) NOT DEFERRABLE;

-- 2024-06-25 00:51:59.43152+00