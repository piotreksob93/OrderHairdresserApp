INSERT INTO user (user_id,email,password,username,first_name,last_name) VALUES
(1,'leslieandrews@o2.pl','$2a$10$1eHcsFWmK.KHlKeeW1s6OONO7v2qRT5r5BaqHmJSqFlOrG1QOpS/y','leslieAndrews','Leslie','Andrews');
INSERT INTO user (user_id,email,password,username,first_name,last_name) VALUES
(2,'emmaBau@gmail.com','$2a$10$1eHcsFWmK.KHlKeeW1s6OONO7v2qRT5r5BaqHmJSqFlOrG1QOpS/y','emmaBau','Emma','Baumgarten');
INSERT INTO user (user_id,email,password,username,first_name,last_name) VALUES
(3,'avani123@mail.com','$2a$10$1eHcsFWmK.KHlKeeW1s6OONO7v2qRT5r5BaqHmJSqFlOrG1QOpS/y','avani','Avani','Gupta');
INSERT INTO user (user_id,email,password,username,first_name,last_name) VALUES
(4,'YuriPetrov@o2.pl','$2a$10$1eHcsFWmK.KHlKeeW1s6OONO7v2qRT5r5BaqHmJSqFlOrG1QOpS/y','yuri','Yuri','Petrov');
INSERT INTO user (user_id,email,password,username,first_name,last_name) VALUES
(5,'Juan@mail.me','$2a$10$1eHcsFWmK.KHlKeeW1s6OONO7v2qRT5r5BaqHmJSqFlOrG1QOpS/y','JuanVega','Juan','Vega');
INSERT INTO user (user_id,email,password,username,first_name,last_name) VALUES
(6,'admin@gmail.com','$2a$10$1eHcsFWmK.KHlKeeW1s6OONO7v2qRT5r5BaqHmJSqFlOrG1QOpS/y','admin','admin','admin');
INSERT INTO user (user_id,email,password,username,first_name,last_name) VALUES
(7,'sknersuDusiKase@gmail.com','$2a$10$1eHcsFWmK.KHlKeeW1s6OONO7v2qRT5r5BaqHmJSqFlOrG1QOpS/y','Sknerus','Sknerus','Andrews');
INSERT INTO user (user_id,email,password,username,first_name,last_name) VALUES
(8,'Bill@mail.com','$2a$10$1eHcsFWmK.KHlKeeW1s6OONO7v2qRT5r5BaqHmJSqFlOrG1QOpS/y','bill','Bill','Gates');
INSERT INTO user (user_id,email,password,username,first_name,last_name) VALUES
(9,'keanuReeves@gmail.com','$2a$10$1eHcsFWmK.KHlKeeW1s6OONO7v2qRT5r5BaqHmJSqFlOrG1QOpS/y','keanu','Keanu','Reeves');
INSERT INTO user (user_id,email,password,username,first_name,last_name) VALUES
(10,'piotreksob93@gmail.com','$2a$10$1eHcsFWmK.KHlKeeW1s6OONO7v2qRT5r5BaqHmJSqFlOrG1QOpS/y','piotrek','Piotr','Sobiborowicz');


INSERT INTO `hairdresser` (`hairdresser_id`, `first_name`, `last_name`, `work_days`, `work_hours`,`salon_id`)
VALUES
(1, 'Hary', 'Style', '8:00-16:00', 'PN-PT',1),
(2, 'Mary', 'Scizors', '10:00-18:00', 'PN-PT',2),
(3, 'Gary', 'Spray', '8:00-16:00', 'PN-PT',3);


INSERT INTO `hair_salon`.`hair_service` (`service_id`, `service_name`, `execution_time`, `price`)
VALUES
(1, 'Strzyżenie włosów', 50, 60),
(2, 'Strzyżenie brody z pielęgnacją',35, 50),
(3, 'Golenie głowy brzytwą', 40, 60),
(4, 'Golenie twarzy brzytwą', 40, 60);

INSERT INTO hair_salon(`salon_id` , `city` , `salon_name`,  `salon_address`,`phone_number`,`owner_id`)
VALUES
(1,'Warszawa', 'BestHairSalon', 'Lipowa 40','123 456 789',7),
(2, 'Lublin', 'LocalHairs', 'Krótka 5','987 654 321',8),
(3, 'Wrocław', 'Tępe nożyce', 'Grzybowa 9', '852 741 963',9);


INSERT INTO salons_users(salon_id,user_id)
VALUES
(1,2),
(1,3),
(2,2),
(2,4),
(3,5);

INSERT INTO `hair_salon`.`salon_rating` (`rating_id`, `rating`, `opinion`,`user_id`,`salon_id`)
VALUES
(1, 4, 'Świetnie tam strzygą!',1,1),
(2, 5, 'Popłakałem się jak zobaczyłem moją wspaniałą fryzurę',3,1),
(3, 5, 'Niebo na głowie :D !',5,3);

INSERT INTO hairdresser_rating (`rating_id`, `rating`, `opinion`,`user_id`,hairdresser_id)
VALUES
(1, 4, 'Świetnie tam strzygą!',2,1),
(2, 5, 'Popłakałem się jak zobaczyłem moją wspaniałą fryzurę',1,1),
(3, 5, 'Niebo na głowie :D !',4,2);

INSERT INTO reservation (reservation_id,reservation_date,reservation_status,user_id,salon_id)
VALUES
(1,'2008-11-11 13:23:44','ANULOWANA',2,1),
(2,'2019-11-21 13:22:44','ANULOWANA',2,2),
(3,'2008-11-11 15:23:44','ANULOWANA',5,3);

INSERT INTO salons_services(salon_id,service_id)
VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(2,1),
(2,2),
(2,4),
(3,1),
(3,4);

INSERT INTO `hairdresser_service`(hairdresser_id,service_id)
VALUES
(1,1),
(1,2),
(1,3),
(2,2),
(2,3),
(2,4),
(3,1),
(3,4);

INSERT INTO hair_service_on_reservation (hairservice_id, hairdresser_id, reservation_id)
VALUES
(1,1,1),
(2,1,1),
(3,1,2),
(4,2,2),
(3,2,3);

INSERT INTO role (id,role_name)
VALUES
(1,'ROLE_ADMIN'),
(2,'ROLE_OWNER'),
(3,'ROLE_CLIENT');

INSERT INTO users_roles(user_id,role_id)
VALUES
(1,3),
(2,3),
(3,3),
(4,3),
(5,3),
(6,1),
(7,2),
(8,2),
(9,2),
(10,2);