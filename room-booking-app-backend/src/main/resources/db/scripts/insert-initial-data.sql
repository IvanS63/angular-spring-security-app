INSERT INTO sys_role VALUES (1,'ADMIN')^
INSERT INTO sys_role VALUES (2,'USER')^

--default password:qwerty
INSERT INTO sys_user(id, email, login, first_name, last_name, birth_date, photo) VALUES
    (1,'admin@mail.com','admin','Admin','Admin','1990-01-01','resources/assets/img/faces/face-3.jpg'),
    (2, 'rooney@mail.com', 'welbeck777', 'Wayne', 'Rooney', '1986-02-02','resources/assets/img/faces/face-2.jpg'),
    (3, 'welbeck@com.com', 'welback777', 'Danny', 'Welbeck', '1990-01-01','resources/assets/img/faces/default.png'),
    (4, 'evans@com.com', 'evans777', 'Johny', 'Evans', '1981-03-03','resources/assets/img/faces/default.png'),
    (5, 'jones@com.com', 'jones777', 'Phil', 'Jones', '1989-04-04','resources/assets/img/faces/default.png'),
    (6, 'walker@com.com', 'walker777', 'Kyle', 'Walker', '1989-05-05','resources/assets/img/faces/default.png'),
    (7, 'shaw@com.com', 'shaw777', 'Luke', 'Shaw', '1992-06-06','resources/assets/img/faces/default.png')^
    
INSERT INTO sys_user_role (user_id, role_id) VALUES
    (1, 1),   
    (1, 2),   
    (2, 2),   
    (3, 2),   
    (4, 2),   
    (5, 2),   
    (6, 2),   
    (7, 2)^   
