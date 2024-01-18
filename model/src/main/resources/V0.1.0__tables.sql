/*
 Navicat Premium Data Transfer

 Source Server         : my-tx
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : 101.43.75.134:3306
 Source Schema         : lotus

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 18/01/2024 10:16:42
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role`
(
    `id`                 bigint UNSIGNED NOT NULL,
    `title`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `code`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created_time`       datetime NULL DEFAULT NULL,
    `updated_time`       datetime NULL DEFAULT NULL,
    `updated_by_user_id` bigint UNSIGNED NULL DEFAULT NULL,
    `created_by_user_id` bigint UNSIGNED NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `code`(`code` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for auth_role_url_resource_link
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_url_resource_link`;
CREATE TABLE `auth_role_url_resource_link`
(
    `role_id`         bigint UNSIGNED NOT NULL,
    `url_resource_id` bigint UNSIGNED NOT NULL,
    PRIMARY KEY (`url_resource_id`, `role_id`) USING BTREE,
    INDEX             `role_id`(`role_id` ASC) USING BTREE,
    CONSTRAINT `auth_role_url_resource_link_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `auth_role_url_resource_link_ibfk_2` FOREIGN KEY (`url_resource_id`) REFERENCES `auth_url_resource` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for auth_url_resource
-- ----------------------------
DROP TABLE IF EXISTS `auth_url_resource`;
CREATE TABLE `auth_url_resource`
(
    `id`                 bigint UNSIGNED NOT NULL,
    `path`               varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `method`             tinyint UNSIGNED NOT NULL COMMENT ' http method枚举。0:all,1:get,2:post,3:delete',
    `created_time`       datetime NULL DEFAULT NULL,
    `updated_time`       datetime NULL DEFAULT NULL,
    `updated_by_user_id` bigint UNSIGNED NULL DEFAULT NULL,
    `created_by_user_id` bigint UNSIGNED NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `path`(`path` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user`
(
    `id`                 bigint UNSIGNED NOT NULL,
    `username`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `password`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created_time`       datetime NULL DEFAULT NULL,
    `updated_time`       datetime NULL DEFAULT NULL,
    `updated_by_user_id` bigint UNSIGNED NULL DEFAULT NULL,
    `created_by_user_id` bigint UNSIGNED NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for auth_user_role_link
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role_link`;
CREATE TABLE `auth_user_role_link`
(
    `user_id` bigint UNSIGNED NOT NULL,
    `role_id` bigint UNSIGNED NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
    INDEX     `role_id`(`role_id` ASC) USING BTREE,
    CONSTRAINT `auth_user_role_link_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `auth_user_role_link_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
