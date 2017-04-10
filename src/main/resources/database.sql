-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.8.2
-- PostgreSQL version: 9.5
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database
-- ;
-- -- ddl-end --
-- 

-- object: todolist | type: SCHEMA --
-- DROP SCHEMA IF EXISTS todolist CASCADE;
CREATE SCHEMA todolist;
-- ddl-end --
ALTER SCHEMA todolist OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,todolist;
-- ddl-end --

-- object: todolist.project | type: TABLE --
-- DROP TABLE IF EXISTS todolist.project CASCADE;
CREATE TABLE todolist.project(
	id bigint NOT NULL,
	name varchar(80) NOT NULL,
	active boolean NOT NULL DEFAULT true,
	id_user bigint,
	CONSTRAINT pk_project PRIMARY KEY (id)

);
-- ddl-end --
COMMENT ON TABLE todolist.project IS 'Tabela que armazena os projetos da lista de tarefas';
-- ddl-end --
COMMENT ON COLUMN todolist.project.id IS 'Identificador único do Projeto';
-- ddl-end --
COMMENT ON COLUMN todolist.project.name IS 'Nome do Projeto';
-- ddl-end --
COMMENT ON COLUMN todolist.project.active IS 'Define se o projeto está ativo ou não';
-- ddl-end --
COMMENT ON CONSTRAINT pk_project ON todolist.project  IS 'Chave primária da tabela de projetos';
-- ddl-end --
ALTER TABLE todolist.project OWNER TO postgres;
-- ddl-end --

-- object: todolist.task | type: TABLE --
-- DROP TABLE IF EXISTS todolist.task CASCADE;
CREATE TABLE todolist.task(
	id bigint NOT NULL,
	description varchar(255) NOT NULL,
	created_at timestamp NOT NULL DEFAULT now(),
	completed boolean NOT NULL DEFAULT false,
	id_project bigint,
	CONSTRAINT pk_task PRIMARY KEY (id)

);
-- ddl-end --
COMMENT ON COLUMN todolist.task.id IS 'Identificador único da tarefa';
-- ddl-end --
COMMENT ON COLUMN todolist.task.description IS 'Descrição da Tarefa';
-- ddl-end --
COMMENT ON COLUMN todolist.task.created_at IS 'Data em que foi criado a tarefa';
-- ddl-end --
COMMENT ON COLUMN todolist.task.completed IS 'Define se a tarefa foi finalizada ou não';
-- ddl-end --
COMMENT ON CONSTRAINT pk_task ON todolist.task  IS 'Chave primária da tabela de tarefas';
-- ddl-end --
ALTER TABLE todolist.task OWNER TO postgres;
-- ddl-end --

-- object: todolist."user" | type: TABLE --
-- DROP TABLE IF EXISTS todolist."user" CASCADE;
CREATE TABLE todolist."user"(
	id bigint NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	email varchar(60) NOT NULL,
	passsword varchar(300) NOT NULL,
	created_at timestamp NOT NULL DEFAULT now(),
	expired smallint NOT NULL DEFAULT false,
	CONSTRAINT pk_user PRIMARY KEY (id),
	CONSTRAINT unique_email UNIQUE (email)

);
-- ddl-end --
COMMENT ON TABLE todolist."user" IS 'Tabela que armazena os dados do usuário';
-- ddl-end --
COMMENT ON COLUMN todolist."user".id IS 'Identificador unico da tabela de usuarios';
-- ddl-end --
COMMENT ON COLUMN todolist."user".first_name IS 'Primeiro nome do Usuário';
-- ddl-end --
COMMENT ON COLUMN todolist."user".last_name IS 'Sobrenome do Usuário';
-- ddl-end --
COMMENT ON COLUMN todolist."user".email IS 'E-mail do usuário';
-- ddl-end --
COMMENT ON COLUMN todolist."user".passsword IS 'Senha encriptada do usuário';
-- ddl-end --
COMMENT ON COLUMN todolist."user".created_at IS 'Data em que o usuário foi criado';
-- ddl-end --
COMMENT ON COLUMN todolist."user".expired IS 'Define se a conta do usuário está expirado ou não';
-- ddl-end --
COMMENT ON CONSTRAINT pk_user ON todolist."user"  IS 'Chave primária da tabela user';
-- ddl-end --
COMMENT ON CONSTRAINT unique_email ON todolist."user"  IS 'Define o e-mail único';
-- ddl-end --
ALTER TABLE todolist."user" OWNER TO postgres;
-- ddl-end --

-- object: index_email | type: INDEX --
-- DROP INDEX IF EXISTS todolist.index_email CASCADE;
CREATE INDEX index_email ON todolist."user"
	USING btree
	(
	  email
	);
-- ddl-end --

-- object: project_fk | type: CONSTRAINT --
-- ALTER TABLE todolist.task DROP CONSTRAINT IF EXISTS project_fk CASCADE;
ALTER TABLE todolist.task ADD CONSTRAINT project_fk FOREIGN KEY (id_project)
REFERENCES todolist.project (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: user_fk | type: CONSTRAINT --
-- ALTER TABLE todolist.project DROP CONSTRAINT IF EXISTS user_fk CASCADE;
ALTER TABLE todolist.project ADD CONSTRAINT user_fk FOREIGN KEY (id_user)
REFERENCES todolist."user" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


