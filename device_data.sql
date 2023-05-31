/*
 Navicat Premium Data Transfer

 Source Server         : local_data
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : device_data

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 12/05/2023 21:47:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` int NOT NULL,
  `no` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `x` float NOT NULL,
  `y` float NOT NULL,
  `z` float NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_x`(`x` ASC) USING BTREE,
  INDEX `index_y`(`y` ASC) USING BTREE,
  INDEX `index_z`(`z` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for device29fa1867
-- ----------------------------
DROP TABLE IF EXISTS `device29fa1867`;
CREATE TABLE `device29fa1867`  (
  `x` float NOT NULL,
  `y` float NOT NULL,
  `z` float NOT NULL,
  `id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` datetime NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for device3326f78d
-- ----------------------------
DROP TABLE IF EXISTS `device3326f78d`;
CREATE TABLE `device3326f78d`  (
  `x` float NOT NULL,
  `y` float NOT NULL,
  `z` float NOT NULL,
  `id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` datetime NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for device350e6eff
-- ----------------------------
DROP TABLE IF EXISTS `device350e6eff`;
CREATE TABLE `device350e6eff`  (
  `x` float NOT NULL,
  `y` float NOT NULL,
  `z` float NOT NULL,
  `id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` datetime NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for devicea77c5238
-- ----------------------------
DROP TABLE IF EXISTS `devicea77c5238`;
CREATE TABLE `devicea77c5238`  (
  `x` float NOT NULL,
  `y` float NOT NULL,
  `z` float NOT NULL,
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` datetime NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for devicee43ac643
-- ----------------------------
DROP TABLE IF EXISTS `devicee43ac643`;
CREATE TABLE `devicee43ac643`  (
  `x` float NOT NULL,
  `y` float NOT NULL,
  `z` float NOT NULL,
  `id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` datetime NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for devicef853ed49
-- ----------------------------
DROP TABLE IF EXISTS `devicef853ed49`;
CREATE TABLE `devicef853ed49`  (
  `x` float NOT NULL,
  `y` float NOT NULL,
  `z` float NOT NULL,
  `id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` datetime NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int NOT NULL,
  `no` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sps` int NOT NULL,
  `start` datetime NOT NULL,
  `stop` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
