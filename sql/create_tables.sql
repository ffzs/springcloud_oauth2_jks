DROP TABLE IF EXISTS `sso_role`;
CREATE TABLE `sso_role` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `sso_role` VALUES ('1', 'ADMIN'), ('2', 'IT'), ('3', 'OP'), ('4', 'HR');
COMMIT;


DROP TABLE IF EXISTS `sso_user`;
CREATE TABLE `sso_user` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `password` varchar(255) DEFAULT NULL,
                          `username` varchar(255) NOT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `name` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `sso_user` VALUES ('1', '$2a$10$uuFQKbr2q/8aqYlPEBlRw.Z9UrtEPrydIh7IUXaEGVWBowY8mZrUq', 'ffzs'),('2', '$2a$10$QgQ9OtiCMnGzYGPabDzOkeBda0Sb8wqzwnTSErJWPx4GfeNOUvh7q', 'sleepycat');
COMMIT;


DROP TABLE IF EXISTS `sso_user_role`;
CREATE TABLE `sso_user_role` (
                             `user_id` bigint(20) NOT NULL,
                             `role_id` bigint(20) NOT NULL,
                             KEY `role_id` (`role_id`),
                             KEY `user_id` (`user_id`),
                             CONSTRAINT `sso_user_id` FOREIGN KEY (`user_id`) REFERENCES `sso_user` (`id`),
                             CONSTRAINT `sso_role_id` FOREIGN KEY (`role_id`) REFERENCES `sso_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `sso_user_role` VALUES ('1', '1'), ('2', '2');
COMMIT;