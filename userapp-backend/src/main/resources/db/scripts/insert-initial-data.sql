INSERT INTO sys_role VALUES (1,'ROLE_ADMIN')^
INSERT INTO sys_role VALUES (2,'ROLE_USER')^

--default password:12345
INSERT INTO sys_user(id, email, login, password, name, photo) VALUES
    (1,'admin@mail.com','admin', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC','Admin','assets/images/avatar1.png'),
    (2, 'oridnaryuser@mail.com', 'user', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Ordinary User','assets/images/avatar3.png'),
    (3, 'welbeck@com.com', 'hkane', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Harry Kane','assets/images/avatar2.png'),
    (4, 'evans@com.com', 'msala', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Mo Salah','assets/images/avatar4.png'),
    (5, 'jones@com.com', 'mrashford', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Marcus Rashford','assets/images/avatar3.png'),
    (6, 'walker@com.com', 'saguero', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Sergio Aguero','assets/images/avatar2.png'),
    (7, 'shaw@com.com', 'jsvardy', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Jaime Vardy','assets/images/avatar4.png')^
    
INSERT INTO sys_user_role (user_id, role_id) VALUES
    (1, 1),   
    (2, 2),   
    (3, 2),   
    (4, 2),   
    (5, 2),   
    (6, 2),   
    (7, 2)^   
