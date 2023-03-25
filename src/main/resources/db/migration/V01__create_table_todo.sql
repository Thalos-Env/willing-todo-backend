CREATE TABLE tb_todo (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	description VARCHAR(255),
	target_date VARCHAR(30),
	username VARCHAR(255),
	done BOOLEAN
);

