/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : cbdatabase

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 29/04/2023 19:02:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cb_history
-- ----------------------------
DROP TABLE IF EXISTS `cb_history`;
CREATE TABLE `cb_history`  (
  `player_one_id` int NOT NULL,
  `player_two_id` int NOT NULL,
  `num` int NOT NULL,
  `play_add` int NOT NULL,
  `rating` int NOT NULL,
  `time` datetime NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cb_history
-- ----------------------------
INSERT INTO `cb_history` VALUES (3, 4, 1, 0, 1, '2023-04-27 23:47:21');
INSERT INTO `cb_history` VALUES (9, 337, 98, 918, 920, '2013-12-22 23:11:26');
INSERT INTO `cb_history` VALUES (242, 321, 59, 763, 636, '2021-04-18 03:56:45');
INSERT INTO `cb_history` VALUES (3, 528, 114, 335, 687, '2020-10-14 11:05:05');
INSERT INTO `cb_history` VALUES (906, 479, 764, 463, 90, '2006-09-30 11:04:00');
INSERT INTO `cb_history` VALUES (3, 783, 738, -11, 453, '2005-11-16 06:22:57');
INSERT INTO `cb_history` VALUES (140, 876, 95, 971, 340, '2015-03-17 05:55:14');
INSERT INTO `cb_history` VALUES (861, 719, 359, 462, 760, '2011-02-25 02:10:26');
INSERT INTO `cb_history` VALUES (400, 362, 656, 205, 517, '2011-10-29 09:37:52');
INSERT INTO `cb_history` VALUES (174, 981, 294, 844, 607, '2002-04-02 02:56:18');
INSERT INTO `cb_history` VALUES (3, 832, 460, 296, 565, '2022-10-05 21:57:46');
INSERT INTO `cb_history` VALUES (979, 256, 235, 893, 311, '2003-11-16 17:43:10');
INSERT INTO `cb_history` VALUES (720, 647, 900, 318, 494, '2003-07-12 07:21:16');
INSERT INTO `cb_history` VALUES (186, 313, 110, 558, 149, '2003-07-12 04:16:19');
INSERT INTO `cb_history` VALUES (626, 746, 612, 685, 951, '2010-09-17 15:39:16');
INSERT INTO `cb_history` VALUES (281, 703, 333, 778, 338, '2023-03-30 14:48:06');
INSERT INTO `cb_history` VALUES (837, 559, 122, 242, 975, '2002-04-27 09:48:04');
INSERT INTO `cb_history` VALUES (942, 287, 204, 843, 55, '2003-03-03 01:21:09');
INSERT INTO `cb_history` VALUES (891, 191, 912, 983, 749, '2002-08-10 08:15:25');
INSERT INTO `cb_history` VALUES (270, 392, 219, 187, 946, '2012-03-31 06:36:00');
INSERT INTO `cb_history` VALUES (377, 348, 368, 69, 736, '2016-05-29 22:38:30');

-- ----------------------------
-- Table structure for cb_history_detail
-- ----------------------------
DROP TABLE IF EXISTS `cb_history_detail`;
CREATE TABLE `cb_history_detail`  (
  `number` int NOT NULL,
  `player_id` int NOT NULL,
  `score` int NOT NULL DEFAULT 0,
  `total_score` int NOT NULL,
  INDEX `number_id_index`(`number` ASC, `player_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cb_history_detail
-- ----------------------------
INSERT INTO `cb_history_detail` VALUES (1, 3, 3, 1000);
INSERT INTO `cb_history_detail` VALUES (1, 4, -3, 997);

-- ----------------------------
-- Table structure for cb_menu
-- ----------------------------
DROP TABLE IF EXISTS `cb_menu`;
CREATE TABLE `cb_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '是否删除（0未删除 1已删除）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cb_menu
-- ----------------------------
INSERT INTO `cb_menu` VALUES (1, '主页', '/index', 'index', '0', '0', 'cb:index', 'house', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `cb_menu` VALUES (2, '排行榜', '/ranking', 'ranking', '0', '0', 'cb:ranking', 'trophy', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `cb_menu` VALUES (3, '历史记录', '/history', 'history', '0', '0', 'cb:history', 'documentCopy', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `cb_menu` VALUES (4, '好友对战', '/friend', 'friend', '0', '0', 'cb:friend', 'GoldMedal', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `cb_menu` VALUES (5, '天梯对战', '/ladder', 'ladder', '0', '0', 'cb:ladder', 'TrophyBase', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `cb_menu` VALUES (6, '个人中心', '/centre', 'centre', '0', '0', 'cb:centre', 'user', NULL, NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for cb_role
-- ----------------------------
DROP TABLE IF EXISTS `cb_role`;
CREATE TABLE `cb_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` int NULL DEFAULT 0 COMMENT 'del_flag',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cb_role
-- ----------------------------
INSERT INTO `cb_role` VALUES (1, 'CEO', 'ceo', '0', 0, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for cb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `cb_role_menu`;
CREATE TABLE `cb_role_menu`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `menu_id` bigint NOT NULL DEFAULT 0 COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cb_role_menu
-- ----------------------------
INSERT INTO `cb_role_menu` VALUES (1, 1);
INSERT INTO `cb_role_menu` VALUES (1, 2);
INSERT INTO `cb_role_menu` VALUES (1, 3);
INSERT INTO `cb_role_menu` VALUES (1, 4);
INSERT INTO `cb_role_menu` VALUES (1, 5);
INSERT INTO `cb_role_menu` VALUES (1, 6);

-- ----------------------------
-- Table structure for cb_test_data
-- ----------------------------
DROP TABLE IF EXISTS `cb_test_data`;
CREATE TABLE `cb_test_data`  (
  `title_id` int NOT NULL,
  `input` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `output` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  INDEX `title_id`(`title_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cb_test_data
-- ----------------------------
INSERT INTO `cb_test_data` VALUES (1, NULL, 'Hello World!');
INSERT INTO `cb_test_data` VALUES (2, '1 4', '5');
INSERT INTO `cb_test_data` VALUES (2, '10 10', '20');
INSERT INTO `cb_test_data` VALUES (2, '1 1', '2');
INSERT INTO `cb_test_data` VALUES (3, 'it is so 666 really 6666 what else can I say 6666666666', 'it is so 666 really 9 what else can I say 27');

-- ----------------------------
-- Table structure for cb_title
-- ----------------------------
DROP TABLE IF EXISTS `cb_title`;
CREATE TABLE `cb_title`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `input_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ouput_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sample_input` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sample_output` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `length_limit` tinyint UNSIGNED NOT NULL,
  `time_limit` smallint UNSIGNED NOT NULL,
  `memory_limit` smallint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cb_title
-- ----------------------------
INSERT INTO `cb_title` VALUES (1, '输入输出格式化控制 Hello World!', '本题要求编写程序，输出一个短句“Hello World!”。', '本题目没有输入。', '在一行中输出短句“Hello World!”。', NULL, NULL, 16, 400, 64);
INSERT INTO `cb_title` VALUES (2, 'a+b', '请输出a+b的和', '输入两个整数a和b', '输出a+b的和', '1 2', '3', 16, 400, 64);
INSERT INTO `cb_title` VALUES (3, '6翻了', '“666”是一种网络用语，大概是表示某人很厉害、我们很佩服的意思。最近又衍生出另一个数字“9”，意思是“6翻了”，实在太厉害的意思。如果你以为这就是厉害的最高境界，那就错啦 —— 目前的最高境界是数字“27”，因为这是 3 个 “9”！\r\n\r\n本题就请你编写程序，将那些过时的、只会用一连串“6666……6”表达仰慕的句子，翻译成最新的高级表达。', '输入在一行中给出一句话，即一个非空字符串，由不超过 1000 个英文字母、数字和空格组成，以回车结束。', '从左到右扫描输入的句子：如果句子中有超过 3 个连续的 6，则将这串连续的 6 替换成 9；但如果有超过 9 个连续的 6，则将这串连续的 6 替换成 27。其他内容不受影响，原样输出。', 'it is so 666 really 6666 what else can I say 6666666666', 'it is so 666 really 9 what else can I say 27', 16, 400, 64);

-- ----------------------------
-- Table structure for cb_user
-- ----------------------------
DROP TABLE IF EXISTS `cb_user`;
CREATE TABLE `cb_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `user_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  `rating` int UNSIGNED NULL DEFAULT 0 COMMENT '战力值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1650147699182088199 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cb_user
-- ----------------------------
INSERT INTO `cb_user` VALUES (3, 'zs', '张三', '$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W', '0', NULL, NULL, NULL, 'images\\2.jpg', '1', NULL, NULL, NULL, NULL, 0, 22);
INSERT INTO `cb_user` VALUES (4, 'lisi', '李四', '$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W', '0', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, 0, 10);
INSERT INTO `cb_user` VALUES (1650147699182088194, 'Yuan Shihan', 'Yuan Shihan', 'dVZ3H43toK', 'y', 'ysh@outlook.com', '52-314-2287', 'F', 'FjdYNvBcNi', 'A', 156, '2018-07-31 19:23:47', 399, '2000-01-03 02:49:14', 230, 116);
INSERT INTO `cb_user` VALUES (1650147699182088195, 'Fan Jialun', 'Fan Jialun', 'IG6aAHOqVS', 'w', 'fan47@gmail.com', '(161) 240 2278', 'M', 'nRz9kgkFat', 'b', 737, '2000-05-17 08:48:20', 596, '2020-05-06 21:48:22', 68, 14);
INSERT INTO `cb_user` VALUES (1650147699182088196, 'Ti Kwok Wing', 'Ti Kwok Wing', 'CJdmBGerDC', 'X', 'tkw@outlook.com', '80-3136-5863', 'M', '2EkrEGQ4yF', '2', 37, '2015-03-28 16:29:09', 236, '2011-02-28 21:58:12', 433, 268);
INSERT INTO `cb_user` VALUES (1650147699182088197, 'Liu Zhiyuan', 'Liu Zhiyuan', 'ajFjnpHxGH', 'U', 'zhili@outlook.com', '90-1801-7259', 'M', 'apCCNTbT8t', 'X', 555, '2003-01-10 10:57:45', 544, '2008-12-23 00:29:26', 546, 15);
INSERT INTO `cb_user` VALUES (1650147699182088198, 'Nicholas Meyer', 'Nicholas Meyer', 'vDUn5sfGHy', 'r', 'mnicholas@icloud.com', '330-647-5513', 'M', 'eA7PUB45SI', 'v', 502, '2023-01-30 02:32:33', 261, '2023-01-20 06:21:51', 823, 428);

-- ----------------------------
-- Table structure for cb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `cb_user_role`;
CREATE TABLE `cb_user_role`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint NOT NULL DEFAULT 0 COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cb_user_role
-- ----------------------------
INSERT INTO `cb_user_role` VALUES (3, 1);
INSERT INTO `cb_user_role` VALUES (4, 1);

SET FOREIGN_KEY_CHECKS = 1;
