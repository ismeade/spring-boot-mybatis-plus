DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id BIGINT,
    name VARCHAR(30),
    email VARCHAR(50),
    PRIMARY KEY (id)
);