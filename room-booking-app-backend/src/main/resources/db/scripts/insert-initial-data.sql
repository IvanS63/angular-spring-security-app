INSERT INTO sys_role VALUES (1,'ROLE_ADMIN')^
INSERT INTO sys_role VALUES (2,'ROLE_USER')^

--default password:12345
INSERT INTO sys_user(id, email, login, password, name, birth_date, photo) VALUES
    (1,'admin@mail.com','admin', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC','Admin','1990-01-01','assets/images/default.png'),
    (2, 'oridnaryuser@mail.com', 'user', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Ordinary User', '1986-02-02','assets/images/default.png'),
    (3, 'welbeck@com.com', 'welback777', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Danny Welbeck', '1990-01-01','assets/images/default.png'),
    (4, 'evans@com.com', 'evans777', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Johny Evans', '1981-03-03','assets/images/default.png'),
    (5, 'jones@com.com', 'jones777', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Phil Jones', '1989-04-04','assets/images/default.png'),
    (6, 'walker@com.com', 'walker777', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Kyle Walker', '1989-05-05','assets/images/default.png'),
    (7, 'shaw@com.com', 'shaw777', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Luke Shaw', '1992-06-06','assets/images/default.png')^
    
INSERT INTO sys_user_role (user_id, role_id) VALUES
    (1, 1),   
    (2, 2),   
    (3, 2),   
    (4, 2),   
    (5, 2),   
    (6, 2),   
    (7, 2)^   
