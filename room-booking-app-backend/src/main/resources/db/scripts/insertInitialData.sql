INSERT INTO sys_role (id, name) VALUES (1,'ADMIN'),
                                    (2,'USER');

--default password:qwerty
INSERT INTO sys_user(id, email, login, first_name, last_name, birth_date, photo, role_id) VALUES
    (1,'admin@mail.com','admin','Admin','Admin','1990-01-01','resources/assets/img/faces/face-3.jpg', 1),
    (2, 'rooney@mail.com', 'welbeck777', 'Wayne', 'Rooney', '1986-02-02','resources/assets/img/faces/face-2.jpg', 2),
    (3, 'welbeck@com.com', 'welback777', 'Danny', 'Welbeck', '1990-01-01','resources/assets/img/faces/default.png', 2),
    (4, 'evans@com.com', 'evans777', 'Johny', 'Evans', '1981-03-03','resources/assets/img/faces/default.png', 2),
    (5, 'jones@com.com', 'jones777', 'Phil', 'Jones', '1989-04-04','resources/assets/img/faces/default.png', 2),
    (6, 'walker@com.com', 'walker777', 'Kyle', 'Walker', '1989-05-05','resources/assets/img/faces/default.png', 2),
    (7, 'shaw@com.com', 'shaw777', 'Luke', 'Shaw', '1992-06-06','resources/assets/img/faces/default.png', 2);

INSERT INTO badge(id, path) VALUES
	(1, 'resources/assets/img/badges/new.png'),
	(2, 'resources/assets/img/badges/best-choice.png'),
	(3, 'resources/assets/img/badges/fox.png'),
	(4, 'resources/assets/img/badges/good-job.png'),
	(5, 'resources/assets/img/badges/helping-hand.png'),
	(6, 'resources/assets/img/badges/knowledgeShare.png'),
	(7, 'resources/assets/img/badges/smile.png'),
	(8, 'resources/assets/img/badges/thank-you.png'),
	(9, 'resources/assets/img/badges/workingHard.png');

INSERT INTO gratitude(id, user_from_id, user_to_id, message, date, badge_id) VALUES
	(1, 2, 1, 'Welcome to our team!','2018-02-01', 1),
	(2, 1, 2, 'Thank you!!!','2018-02-01', 8),
	(3, 1, 2, 'Hi, nice to meet you!','2018-02-02', 7),
	(4, 3, 1, 'Good job!!!','2018-02-03', 4),
	(5, 5, 1, 'Thanks for responding to my request!','2018-02-03', 9),
	(6, 1, 2, 'Can you please help me with the system permissions?','2018-02-02', 5),
	(7, 5, 1, 'Please advise some Java books?','2018-02-02', 7),
	(8, 6, 1, 'Welcome to our team!','2018-02-01', 3),
	(9, 4, 1, 'Welcome to our team!','2018-02-01', 5);
