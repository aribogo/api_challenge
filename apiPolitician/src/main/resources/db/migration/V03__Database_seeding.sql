
INSERT INTO TB_ROLE (NAME) VALUES('MANAGER');
INSERT INTO tb_user (username, password, role_id) VALUES('Admin', '$2a$12$ipBs2PI.xWhVw8g4O41KEO6c4rSjmYRTCmzYc44mr5ZcKhCIoKQq.', 1);

INSERT INTO tb_party (party_name, party_initials) VALUES('Partido A', 'ParA');
INSERT INTO tb_party (party_name, party_initials) VALUES('Partido B', 'ParB');

INSERT INTO tb_politician 
		(name, position, cpf, street_name, number, zipcode, phone_number, is_leader, file_type, file_folder, file_name, id_party)
 		VALUES('Carolina Milena Jaqueline Silva', 1, '463.389.173-10', 'Rua Soldado Sinésio de Aragão', '895', '51160-380', '8135867148', FALSE, 'png', 'ParA', 'profileCarolina.png', 1);
INSERT INTO tb_politician 
		(name, position, cpf, street_name, number, zipcode, phone_number, is_leader, file_type, file_folder, file_name, id_party)
 		VALUES('Raquel Isabela Barros', 3, '887.268.973-26', 'Travessa Manoel Pontes', '363', '69303-072', '9535959289', TRUE, 'png', 'ParA', 'profileRaquel.png', 1);
INSERT INTO tb_politician 
		(name, position, cpf, street_name, number, zipcode, phone_number, is_leader, file_type, file_folder, file_name, id_party)
 		VALUES('Renato Osvaldo Roberto da Mota', 3, '766.749.779-69', 'Rua Nilo Alves', '549', '62042-350', '8829574386', FALSE, 'jpg', 'ParA', 'profileRenato.jpg', 1);
INSERT INTO tb_politician 
		(name, position, cpf, street_name, number, zipcode, phone_number, is_leader, file_type, file_folder, file_name, id_party)
 		VALUES('Luís Guilherme Danilo Santos', 3, '709.917.991-86', 'Travessa João Paulo II', '102', '59114-151', '8427371626', TRUE, 'jpg', 'ParB', 'profileLuis.jpg', 2);
INSERT INTO tb_politician 
		(name, position, cpf, street_name, number, zipcode, phone_number, is_leader, file_type, file_folder, file_name, id_party)
 		VALUES('Betina Louise Hadassa Souza', 7, '124.723.172-04', 'Rua Uruguai', '428', '96400-520', '5337402283', FALSE, 'jpg', 'ParB', 'profileBetina.jpg', 2);
INSERT INTO tb_politician 
		(name, position, cpf, street_name, number, zipcode, phone_number, is_leader, file_type, file_folder, file_name, id_party)
 		VALUES('Fernanda Tereza Nunes', 3, '030.171.612-93', 'Rua Albino Cabral de Vasconcelos', '732', '58416-257', '(83) 3767-3290', FALSE, 'jpg', 'ParB', 'profileFernanda.jpg', 2);
INSERT INTO tb_politician 
		(name, position, cpf, street_name, number, zipcode, phone_number, is_leader, file_type, file_folder, file_name, id_party)
 		VALUES('Débora Tânia Santos', 0, '290.500.992-62', 'Rua Albino Cabral de Vasconcelos', '732', '58416-257', '(82) 3914-6306', FALSE, 'jpg', 'ParB', 'profileDebora.jpg', 2);
INSERT INTO tb_politician 
		(name, position, cpf, street_name, number, zipcode, phone_number, is_leader, file_type, file_folder, file_name, id_party)
 		VALUES('Otávio Heitor Marcelo de Paula', 2, '162.347.098-69', 'Rua G', '600', '77433-664', '(63) 2846-4260', FALSE, 'jpg', 'ParB', 'profileOtavio.jpg', 2);
 INSERT INTO tb_politician 
		(name, position, cpf, street_name, number, zipcode, phone_number, is_leader, file_type, file_folder, file_name, id_party)
 		VALUES('Thales Ruan Bento Rocha', 4, '325.175.729-68', 'Rua Havana', '625', '69097-225', '(92) 3743-9938', TRUE, 'jpg', 'ParA', 'profileThales.jpg', 1);
 INSERT INTO tb_politician 
		(name, position, cpf, street_name, number, zipcode, phone_number, is_leader, file_type, file_folder, file_name, id_party)
 		VALUES('Anthony Mateus Gael Silva', 5, '126.677.839-05', 'Avenida Espírito Santo', '525', '58030-110', '(83) 3930-8740', FALSE, 'jpg', 'ParA', 'profileAnthony.jpg', 1);
 INSERT INTO tb_politician 
		(name, position, cpf, street_name, number, zipcode, phone_number, is_leader, file_type, file_folder, file_name, id_party)
 		VALUES('Francisco Julio Caio Barbosa', 6, '126.677.839-05', '1ª Travessa Bom Jesus', '525', '65081-424', '(98) 3840-3296', FALSE, 'jpg', 'ParB', 'profileFrancisco.jpg', 2);


INSERT INTO tb_bill (description, status, id_politician) 
			VALUES('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer et odio viverra, molestie tortor et, eleifend odio.', 0, 1); 		

INSERT INTO tb_bill (description, status, id_politician) 
			VALUES('Aenean in placerat sem, et sollicitudin ligula. Nullam nec pretium orci. Duis sollicitudin rutrum nulla et blandit.', 1, 5); 	

INSERT INTO tb_bill (description, status, id_politician) 
			VALUES(' Duis erat arcu, ultricies at tincidunt eget, rutrum non sapien.', 4, 5); 

INSERT INTO tb_bill (description, status, id_politician) 
			VALUES(' Nam eu pretium justo. Nulla in ullamcorper mi.', 3, 5); 
INSERT INTO tb_bill (description, status, id_politician) 
			VALUES(' Donec imperdiet non mi vel mollis. Suspendisse non ante sed ante fermentum ultricies.', 0, 5); 
			
INSERT INTO tb_bill (description, status, id_politician) 
			VALUES('Cras scelerisque tortor elit. Morbi et ligula at ex dapibus aliquam. Aenean a fringilla purus.', 3, 10); 

INSERT INTO tb_bill (description, status, id_politician) 
			VALUES('Proin rutrum dolor non magna porttitor iaculis.', 0, 10); 

INSERT INTO tb_bill (description, status, id_politician) 
			VALUES('Nullam condimentum nunc et feugiat iaculis. Vestibulum posuere nibh quis vulputate rhoncus.', 1, 10); 
			
INSERT INTO tb_bill (description, status, id_politician) 
			VALUES('Suspendisse ullamcorper tristique accumsan.', 2, 10); 
			
INSERT INTO tb_bill (description, status, id_politician) 
			VALUES('Aenean fermentum pretium sem vel accumsan.', 4, 10); 
			
INSERT INTO tb_bill (description, status, id_politician) 
			VALUES('Aliquam feugiat condimentum fermentum.', 4, 10); 
			
			
			
INSERT INTO tb_process (description, status, id_politician) 
			VALUES('Aliquam feugiat condimentum fermentum.', 0, 4); 

INSERT INTO tb_process (description, status, id_politician) 
			VALUES('Etiam euismod urna id posuere maximus. ', 1, 6); 
			
INSERT INTO tb_process (description, status, id_politician) 
			VALUES(' Aliquam viverra ornare justo nec vestibulum.', 1, 11); 
			
INSERT INTO tb_process (description, status, id_politician) 
			VALUES('Phasellus nec convallis lorem.', 2, 11); 

		
CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 20 INCREMENT BY 1;