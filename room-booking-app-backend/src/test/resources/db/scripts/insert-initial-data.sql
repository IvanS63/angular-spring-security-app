INSERT INTO sys_role VALUES (1,'ROLE_ADMIN')^
INSERT INTO sys_role VALUES (2,'ROLE_USER')^

--default password:12345
INSERT INTO sys_user(id, email, login, password, name, photo) VALUES
    (1,'admin@mail.com','admin', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC','Admin','assets/images/avatar1.png'),
    (2, 'oridnaryuser@mail.com', 'user', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Ordinary User','assets/images/avatar3.png'),
    (3, 'welbeck@com.com', 'welback777', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Danny Welbeck','assets/images/avatar2.png'),
    (4, 'evans@com.com', 'evans777', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Johny Evans','assets/images/avatar4.png'),
    (5, 'jones@com.com', 'jones777', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Phil Jones','assets/images/avatar3.png'),
    (6, 'walker@com.com', 'walker777', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Kyle Walker','assets/images/avatar2.png'),
    (7, 'shaw@com.com', 'shaw777', '$2a$10$kIMlRgpErZXbSf0c1XTCcu1PBsSwgvajVaXAUlGawteknCkjrAhnC', 'Luke Shaw','assets/images/avatar4.png')^
    
INSERT INTO sys_user_role (user_id, role_id) VALUES
    (1, 1),   
    (2, 2),   
    (3, 2),   
    (4, 2),   
    (5, 2),   
    (6, 2),   
    (7, 2)^

INSERT INTO message (id, user_from_id, user_to_id, msg_text, read) VALUES
    (1, 1, 2, 'Message 1', FALSE),
    (2, 2, 3, 'Message 2', FALSE),
    (3, 3, 4, 'Message 3', TRUE)^
