CREATE TABLE tb_party(
					id BIGINT AUTO_INCREMENT PRIMARY KEY,
					party_name VARCHAR(255) NOT NULL,
					party_initials VARCHAR(255) NOT NULL
					);
					
CREATE UNIQUE INDEX uidx_name
ON tb_party (party_name, party_initials);


CREATE TABLE tb_politician (
					id BIGINT AUTO_INCREMENT PRIMARY KEY, 
					name VARCHAR(255) NOT NULL,
					position VARCHAR(255) NOT NULL,
					cpf VARCHAR(14) NOT NULL,
					street_name VARCHAR(255) NOT NULL,
					number VARCHAR(255) NOT NULL,
					complement VARCHAR(255),
					zipcode VARCHAR(255) NOT NULL,
					phone_number VARCHAR(255) NOT NULL,
					is_leader BOOLEAN NOT NULL,
					file_type VARCHAR(15),
					file_folder VARCHAR(255) NOT NULL,
					file_size BIGINT,
					file_name VARCHAR(50) NOT NULL,
					id_party BIGINT NOT NULL,
					FOREIGN KEY (id_party) REFERENCES tb_party(id)
					);



CREATE TABLE tb_bill(
					id BIGINT AUTO_INCREMENT PRIMARY KEY,
					description VARCHAR(500) NOT NULL,
					status VARCHAR(255) NOT NULL,
					id_politician BIGINT NOT NULL,
					FOREIGN KEY (id_politician) REFERENCES tb_politician(id)
					);
					
CREATE TABLE tb_process(
					id BIGINT AUTO_INCREMENT PRIMARY KEY,
					description VARCHAR(500) NOT NULL,
					status VARCHAR(255) NOT NULL,
					id_politician BIGINT NOT NULL,
					FOREIGN KEY (id_politician) REFERENCES tb_politician(id)
					);