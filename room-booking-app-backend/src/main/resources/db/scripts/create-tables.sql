CREATE TABLE sys_role(
  id INTEGER,
  name VARCHAR (10) NOT NULL,
  PRIMARY KEY (id)
)^

CREATE TABLE sys_user (
    id INTEGER,
    email VARCHAR(50),
    login VARCHAR (10),
    password VARCHAR (255) DEFAULT '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.',
    first_name VARCHAR (50),
    last_name VARCHAR (50),
    birth_date DATE,
    photo VARCHAR (255) DEFAULT 'resources/assets/img/faces/default.png',
    role_id INTEGER DEFAULT 2,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES sys_role (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)^

CREATE TABLE booking_event(
    id INTEGER,
    start_date DATE,
    duration INTEGER,
    description VARCHAR (255),
    user_id INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES sys_user (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)^




