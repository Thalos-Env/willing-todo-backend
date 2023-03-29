CREATE TABLE tb_profile (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100)
);


CREATE TABLE tb_user (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(100),
	password VARCHAR(100),
	profile_id BIGINT NOT NULL
);
