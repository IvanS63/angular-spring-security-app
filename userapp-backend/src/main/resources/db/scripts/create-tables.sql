CREATE TABLE sys_role(
  id INTEGER,
  name VARCHAR (10) NOT NULL,
  PRIMARY KEY (id)
)^

CREATE TABLE sys_user (
    id INTEGER,
    email VARCHAR(50),
    login VARCHAR (10),
    password VARCHAR (255),
    name VARCHAR (50),
    photo VARCHAR (255),
    PRIMARY KEY (id)
)^
    
CREATE TABLE sys_user_role(
    user_id INTEGER,
    role_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES sys_user(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES sys_role(id) ON DELETE CASCADE ON UPDATE CASCADE
)^

CREATE SEQUENCE user_seq
   INCREMENT 1
   START 10
   MINVALUE 10
   MAXVALUE 100000
   CACHE 1^

CREATE TABLE message(
    id INTEGER,
    user_from_id INTEGER,
    user_to_id INTEGER,
    msg_text VARCHAR (255),
    msg_date TIMESTAMP,
    read BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (user_from_id) REFERENCES sys_user(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_to_id) REFERENCES sys_user(id) ON DELETE CASCADE ON UPDATE CASCADE
)^

CREATE SEQUENCE message_seq
   INCREMENT 1
   START 1
   MINVALUE 1
   MAXVALUE 100000
   CACHE 1^



