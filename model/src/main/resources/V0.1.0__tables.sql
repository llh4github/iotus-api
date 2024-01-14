CREATE TABLE `auth_role` (
 `id` bigint unsigned NOT NULL,
 `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
 `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
 `created_time` datetime DEFAULT NULL,
 `updated_time` datetime DEFAULT NULL,
 `updated_by_user_id` bigint unsigned DEFAULT NULL,
 `created_by_user_id` bigint unsigned DEFAULT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `auth_user` (
 `id` bigint unsigned NOT NULL,
 `username` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
 `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
 `created_time` datetime DEFAULT NULL,
 `updated_time` datetime DEFAULT NULL,
 `updated_by_user_id` bigint unsigned DEFAULT NULL,
 `created_by_user_id` bigint unsigned DEFAULT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `auth_user_role_link` (
 `user_id` bigint unsigned NOT NULL,
 `role_id` bigint unsigned NOT NULL,
 PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `auth_url_resource` (
 `id` bigint unsigned NOT NULL,
 `path` varchar(500) COLLATE utf8mb4_general_ci NOT NULL,
 `method` tinyint NOT NULL COMMENT 'http method枚举。0:all,1:get,2:post,3:delete',
 `created_time` datetime DEFAULT NULL,
 `updated_time` datetime DEFAULT NULL,
 `updated_by_user_id` bigint unsigned DEFAULT NULL,
 `created_by_user_id` bigint unsigned DEFAULT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `path` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
CREATE TABLE `auth_role_url_resource_link` (
 `role_id` bigint unsigned NOT NULL,
 `url_resource_id` bigint unsigned NOT NULL,
 PRIMARY KEY (`url_resource_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
