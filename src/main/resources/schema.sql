SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for box
-- ----------------------------
CREATE TABLE IF NOT EXISTS `box` (
                       `id` bigint NOT NULL AUTO_INCREMENT,
                       `name` varchar(255) DEFAULT NULL,
                       `desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                       `location` longtext,
                       `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                       `updated_by` varchar(255) DEFAULT NULL,
                       `deleted_at` datetime DEFAULT NULL,
                       `deleted_by` varchar(255) DEFAULT NULL,
                       `is_deleted` boolean DEFAULT false,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `product` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `desc` longtext,
                           `box_id` bigint DEFAULT NULL,
                           `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                           `updated_by` varchar(255) DEFAULT NULL,
                           `deleted_at` datetime DEFAULT NULL,
                           `deleted_by` varchar(255) DEFAULT NULL,
                           `is_deleted` boolean DEFAULT false,
                           PRIMARY KEY (`id`),
                           KEY `fk_box_id` (`box_id`),
                           CONSTRAINT `fk_box_id` FOREIGN KEY (`box_id`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;



