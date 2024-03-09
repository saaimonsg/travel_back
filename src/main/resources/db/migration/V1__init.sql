
CREATE TABLE `app_user`
(
    `id`       bigint       NOT NULL AUTO_INCREMENT,
    `email`    varchar(255) NOT NULL,
    `name`     varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `surname`  varchar(255) NOT NULL,
    `username` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_1j9d9a06i600gd43uu3km82jw` (`email`),
    UNIQUE KEY `UK_3k4cplvh82srueuttfkwnylq0` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `auth_permission`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `resource_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `auth_role`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `description` varchar(500) NOT NULL,
    `is_disabled` bit(1)       NOT NULL,
    `name`        varchar(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_lc1sij60969nsgl5cy8bfgbsm` (`name`)
) ENGINE = InnoDB;

CREATE TABLE `join_app_user_auth_role`
(
    `appuser_id` bigint NOT NULL,
    `role_id`    bigint NOT NULL,
    KEY `FK6ep713ri2v42bw7ontr13aeyq` (`role_id`),
    KEY `FKgpmfmvqu2q1t5n2qe8qgt3wd9` (`appuser_id`),
    CONSTRAINT `FK6ep713ri2v42bw7ontr13aeyq` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`),
    CONSTRAINT `FKgpmfmvqu2q1t5n2qe8qgt3wd9` FOREIGN KEY (`appuser_id`) REFERENCES `app_user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `join_auth_role_auth_permission`
(
    `role_id`       bigint NOT NULL,
    `permission_id` bigint NOT NULL,
    KEY `FK4pcjbo7b1uc3c0yd45kfj5njv` (`permission_id`),
    KEY `FK1fli9mycv5mpo418tf72cg3er` (`role_id`),
    CONSTRAINT `FK1fli9mycv5mpo418tf72cg3er` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`),
    CONSTRAINT `FK4pcjbo7b1uc3c0yd45kfj5njv` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;



# username:string
# password: string
INSERT INTO app_user (email, name, password, surname, username)
VALUES ('string@string.com', 'string',
        '$2a$10$rQHngaa1FrHUHI/o/4rao.rkKqjuzeAKhwta.Qhyl5h6ce3WQsWxO', 'string', 'string'),
       ('string@string2.com', 'user',
        '$2a$10$rQHngaa1FrHUHI/o/4rao.rkKqjuzeAKhwta.Qhyl5h6ce3WQsWxO', 'string', 'user');


INSERT INTO auth_role (description, is_disabled, name)
VALUES ('visitor', false, 'visitor'),
       ('admin management', false, 'admin');

INSERT INTO join_app_user_auth_role (appuser_id, role_id)
VALUES (1, 1),
       (1, 2);
