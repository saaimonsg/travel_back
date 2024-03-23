CREATE TABLE `app_city`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `iso`         varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `province_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `app_country`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `iso`        varchar(255) DEFAULT NULL,
    `iso3`       varchar(255) DEFAULT NULL,
    `name`       varchar(255) DEFAULT NULL,
    `nice_name`  varchar(255) DEFAULT NULL,
    `num_code`   int          DEFAULT NULL,
    `phone_code` int          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `app_province`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `country_id` bigint       DEFAULT NULL,
    `iso`        varchar(255) DEFAULT NULL,
    `name`       varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `auth_role`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `description` varchar(500) NOT NULL,
    `is_disabled` bit(1)       NOT NULL,
    `name`        varchar(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_lc1sij60969nsgl5cy8bfgbsm` (`name`),
    UNIQUE KEY `unq_name` (`name`)
) ENGINE = InnoDB;

CREATE TABLE `auth_permission`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `resource_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `join_auth_role_auth_permission`
(
    `role_id`       bigint NOT NULL,
    `permission_id` bigint NOT NULL,
    KEY `FK4pcjbo7b1uc3c0yd45kfj5njv` (`permission_id`),
    KEY `FK1fli9mycv5mpo418tf72cg3er` (`role_id`),
    CONSTRAINT `FK1fli9mycv5mpo418tf72cg3er` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`),
    CONSTRAINT `FK4pcjbo7b1uc3c0yd45kfj5njv` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `app_user`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `email`       varchar(255) DEFAULT NULL,
    `is_driver`   bit(1)       DEFAULT NULL,
    `is_new`      bit(1),
    `name`        varchar(255) DEFAULT NULL,
    `password`    varchar(255) NOT NULL,
    `surname`     varchar(255) DEFAULT NULL,
    `username`    varchar(255) NOT NULL,
    `city_id`     bigint       DEFAULT NULL,
    `country_id`  bigint       DEFAULT NULL,
    `province_id` bigint       DEFAULT NULL,
    `likes`       bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_3k4cplvh82srueuttfkwnylq0` (`username`),
    UNIQUE KEY `UK_1j9d9a06i600gd43uu3km82jw` (`email`),
    CONSTRAINT `FK6wfipo1jh7vl2xi9hopuemt44` FOREIGN KEY (`city_id`) REFERENCES `app_city` (`id`),
    CONSTRAINT `FKgwmtf6n4mts860e4xjj1yit6x` FOREIGN KEY (`country_id`) REFERENCES `app_country` (`id`),
    CONSTRAINT `FKiei8nr88lv9jju54s4ho5o4pe` FOREIGN KEY (`province_id`) REFERENCES `app_province` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `app_trip`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `amount_passengers` bigint       DEFAULT NULL,
    `departure_date`    datetime(6)  DEFAULT NULL,
    `departure_time`    varchar(255) DEFAULT NULL,
    `description`       varchar(255) DEFAULT NULL,
    `is_only_friends`   bit(1) NOT NULL,
    `from_city_id`      bigint       DEFAULT NULL,
    `from_country_id`   bigint       DEFAULT NULL,
    `from_province_id`  bigint       DEFAULT NULL,
    `owner_id`          bigint       DEFAULT NULL,
    `to_city_id`        bigint       DEFAULT NULL,
    `to_country_id`     bigint       DEFAULT NULL,
    `to_province_id`    bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK4vt9ojf4by8m5y7dp500karba` (`from_city_id`),
    KEY `FKsokffeb1a92530csvd6k3lkhn` (`from_country_id`),
    KEY `FKggb8vf3olwo5foywe5yl8q0ys` (`from_province_id`),
    KEY `FKcq7qkn2epwy666s5be9dye5hc` (`owner_id`),
    KEY `FK42gynffr20gyiofksnmg6x9hn` (`to_city_id`),
    KEY `FKr7e8hw1265tg7uf40r5lb92jc` (`to_country_id`),
    KEY `FKnm02779rd4wrvo4tsr62mm7ou` (`to_province_id`),
    CONSTRAINT `FK42gynffr20gyiofksnmg6x9hn` FOREIGN KEY (`to_city_id`) REFERENCES `app_city` (`id`),
    CONSTRAINT `FK4vt9ojf4by8m5y7dp500karba` FOREIGN KEY (`from_city_id`) REFERENCES `app_city` (`id`),
    CONSTRAINT `FKcq7qkn2epwy666s5be9dye5hc` FOREIGN KEY (`owner_id`) REFERENCES `app_user` (`id`),
    CONSTRAINT `FKggb8vf3olwo5foywe5yl8q0ys` FOREIGN KEY (`from_province_id`) REFERENCES `app_province` (`id`),
    CONSTRAINT `FKnm02779rd4wrvo4tsr62mm7ou` FOREIGN KEY (`to_province_id`) REFERENCES `app_province` (`id`),
    CONSTRAINT `FKr7e8hw1265tg7uf40r5lb92jc` FOREIGN KEY (`to_country_id`) REFERENCES `app_country` (`id`),
    CONSTRAINT `FKsokffeb1a92530csvd6k3lkhn` FOREIGN KEY (`from_country_id`) REFERENCES `app_country` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `app_trip_passengers`
(
    `trip_id`       bigint NOT NULL,
    `passengers_id` bigint NOT NULL,
    UNIQUE KEY `UK_m02nsrre0y3r75m5wunwmp7k3` (`passengers_id`),
    KEY `FKj79qjdvx0kkgf2kspiah18msk` (`trip_id`),
    CONSTRAINT `FKj79qjdvx0kkgf2kspiah18msk` FOREIGN KEY (`trip_id`) REFERENCES `app_trip` (`id`),
    CONSTRAINT `FKlkearnt9lfbkxteequk9sc1tg` FOREIGN KEY (`passengers_id`) REFERENCES `app_user` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `join_app_user_auth_role`
(
    `appuser_id` bigint NOT NULL,
    `role_id`    bigint NOT NULL,
    KEY `FK6ep713ri2v42bw7ontr13aeyq` (`role_id`),
    KEY `FKgpmfmvqu2q1t5n2qe8qgt3wd9` (`appuser_id`),
    CONSTRAINT `FK6ep713ri2v42bw7ontr13aeyq` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`),
    CONSTRAINT `FKgpmfmvqu2q1t5n2qe8qgt3wd9` FOREIGN KEY (`appuser_id`) REFERENCES `app_user` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `join_app_user_friends`
(
    `appuser_id` bigint NOT NULL,
    `friend_id`  bigint NOT NULL,
    KEY `FKanrttewnlms5daemowrl7kt09` (`friend_id`),
    KEY `FKoq4u3kvah3606tckvosac7ph6` (`appuser_id`),
    CONSTRAINT `FKanrttewnlms5daemowrl7kt09` FOREIGN KEY (`friend_id`) REFERENCES `app_user` (`id`),
    CONSTRAINT `FKoq4u3kvah3606tckvosac7ph6` FOREIGN KEY (`appuser_id`) REFERENCES `app_user` (`id`)
) ENGINE = InnoDB;



