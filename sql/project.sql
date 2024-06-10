-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 59.110.28.248    Database: project
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cb_comment`
--

DROP TABLE IF EXISTS `cb_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cb_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论内容',
  `user_id` bigint DEFAULT NULL COMMENT '评论人Id',
  `time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论时间',
  `pid` int DEFAULT NULL COMMENT '父Id',
  `origin_id` int DEFAULT NULL COMMENT '最上级评论Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_comment`
--

LOCK TABLES `cb_comment` WRITE;
/*!40000 ALTER TABLE `cb_comment` DISABLE KEYS */;
INSERT INTO `cb_comment` VALUES (1,'这是第一条评论_李四的',4,'2024-06-04 15:00:00',NULL,NULL),(3,'这是第一条评论_张三的',3,'2024-06-04 15:00:00',NULL,NULL),(7,'666',6,'2022-10-23 14:05:12',1,1),(8,'444',7,'2022-10-23 14:06:02',7,1),(9,'999',21,'2022-10-23 14:06:02',7,1),(10,'888',21,'2022-10-23 14:10:51',8,1),(11,'哈哈哈',21,'2022-10-23 14:12:34',NULL,NULL),(12,'111',21,'2022-10-23 14:12:42',11,11),(14,'你好！',21,'2022-10-23 17:18:46',1,1),(19,'这是第一条评论_id19',19,'2024-06-04 15:00:00',1,1),(20,'这是第一条评论_id20',21,'2024-06-04 15:00:00',0,1),(31,'这是对哈梅整风的评论',3,'2024-06-05 23:13:50',19,1),(32,'哈哈哈哈哈，成功了',21,'2024-06-05 23:16:07',26,26),(33,'hhhhhh',21,'2024-06-05 23:22:51',3,3),(34,'好好好',21,'2024-06-05 23:24:11',NULL,NULL),(35,'人才',21,'2024-06-05 23:24:36',1,1),(36,'人才',21,'2024-06-05 23:24:41',1,1),(37,'人才',21,'2024-06-05 23:24:45',1,1),(38,'牛呀',3,'2024-06-05 23:37:45',1,1),(39,'很牛',21,'2024-06-05 23:39:27',NULL,NULL),(40,'非常好',3,'2024-06-05 23:39:35',NULL,NULL),(42,'测试一下功能',3,'2024-06-06 10:15:35',NULL,NULL),(43,'非常好',3,'2024-06-06 10:45:18',34,34),(45,'测试',3,'2024-06-06 12:08:50',38,1),(47,'测试',3,'2024-06-06 12:29:37',35,1),(48,'好好好，非常完美',3,'2024-06-06 12:29:47',NULL,NULL),(49,'你很牛呀，罗小皮',3,'2024-06-06 12:30:08',12,11),(58,'fdfdf',22,'2024-06-06 17:41:22',51,51),(59,'fdfdf',3,'2024-06-06 22:14:15',50,50),(60,'这是一级评论',3,'2024-06-06 22:15:14',NULL,NULL),(62,'dsdsd',28,'2024-06-06 23:17:19',59,50),(63,'fdfdfd',22,'2024-06-06 23:19:10',43,34),(64,'fdfdfdfd',22,'2024-06-06 23:19:25',45,1),(65,'huhuh',22,'2024-06-06 23:19:36',63,34),(66,'koko',22,'2024-06-06 23:19:45',42,42),(67,'cefd',22,'2024-06-07 10:52:20',39,39),(68,'二十二的评论',22,'2024-06-07 10:52:38',NULL,NULL),(75,'你好',1799473443726286849,'2024-06-09 00:10:43',60,60),(76,'你好',1799473443726286849,'2024-06-09 00:10:52',NULL,NULL),(77,'哈老梅',1799473443726286849,'2024-06-09 00:11:02',76,76),(78,'测试\n',4,'2024-06-09 00:23:45',NULL,NULL);
/*!40000 ALTER TABLE `cb_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cb_history`
--

DROP TABLE IF EXISTS `cb_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cb_history` (
  `player_one_id` int NOT NULL,
  `player_two_id` int NOT NULL,
  `num` int NOT NULL,
  `play_add` int NOT NULL,
  `rating` int NOT NULL,
  `time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_history`
--

LOCK TABLES `cb_history` WRITE;
/*!40000 ALTER TABLE `cb_history` DISABLE KEYS */;
INSERT INTO `cb_history` VALUES (3,4,1,0,1,'2023-04-27 23:47:21'),(9,337,98,918,920,'2013-12-22 23:11:26'),(242,321,59,763,636,'2021-04-18 03:56:45'),(3,528,114,335,687,'2020-10-14 11:05:05'),(906,479,764,463,90,'2006-09-30 11:04:00'),(3,783,738,-11,453,'2005-11-16 06:22:57'),(140,876,95,971,340,'2015-03-17 05:55:14'),(861,719,359,462,760,'2011-02-25 02:10:26'),(400,362,656,205,517,'2011-10-29 09:37:52'),(174,981,294,844,607,'2002-04-02 02:56:18'),(3,832,460,296,565,'2022-10-05 21:57:46'),(979,256,235,893,311,'2003-11-16 17:43:10'),(720,647,900,318,494,'2003-07-12 07:21:16'),(186,313,110,558,149,'2003-07-12 04:16:19'),(626,746,612,685,951,'2010-09-17 15:39:16'),(281,703,333,778,338,'2023-03-30 14:48:06'),(837,559,122,242,975,'2002-04-27 09:48:04'),(942,287,204,843,55,'2003-03-03 01:21:09'),(891,191,912,983,749,'2002-08-10 08:15:25'),(270,392,219,187,946,'2012-03-31 06:36:00'),(377,348,368,69,736,'2016-05-29 22:38:30'),(4,3,1,20,30,'2024-05-25 23:20:48'),(3,4,1,-10,12,'2024-05-25 23:20:48'),(4,3,1,20,50,'2024-05-26 13:40:48'),(3,4,1,-10,2,'2024-05-26 13:40:48'),(4,3,1,20,70,'2024-05-26 13:42:47'),(3,4,1,-10,-8,'2024-05-26 13:42:47'),(4,3,1,20,90,'2024-05-26 13:48:11'),(3,4,1,-10,-8,'2024-05-26 13:48:11'),(4,3,1,20,110,'2024-05-26 13:48:32'),(3,4,1,-10,-8,'2024-05-26 13:48:32'),(4,3,1,20,130,'2024-05-26 13:53:05'),(3,4,1,-10,-8,'2024-05-26 13:53:05'),(4,3,1,20,150,'2024-05-26 13:53:27'),(3,4,1,-10,-8,'2024-05-26 13:53:27'),(4,3,1,20,170,'2024-05-26 14:01:30'),(3,4,1,-10,-8,'2024-05-26 14:01:30'),(4,3,1,20,190,'2024-05-26 14:01:48'),(3,4,1,-10,-8,'2024-05-26 14:01:48'),(4,3,1,20,210,'2024-05-26 14:03:28'),(3,4,1,-10,-8,'2024-05-26 14:03:28'),(3,4,1,20,22,'2024-05-26 16:15:45'),(4,3,1,-10,200,'2024-05-26 16:15:45'),(3,4,2,20,42,'2024-05-26 16:16:52'),(4,3,2,-10,190,'2024-05-26 16:16:52'),(3,4,1,20,62,'2024-05-26 16:30:11'),(4,3,1,-10,180,'2024-05-26 16:30:11'),(4,3,1,20,200,'2024-05-26 16:51:09'),(3,4,1,-10,52,'2024-05-26 16:51:09'),(4,3,1,-10,190,'2024-05-26 16:53:03'),(3,4,1,20,220,'2024-05-26 16:53:03'),(4,3,4,-10,210,'2024-05-26 17:03:39'),(4,4,4,20,210,'2024-05-26 17:03:39'),(4,3,4,-10,200,'2024-05-26 17:07:28'),(4,4,4,20,230,'2024-05-26 17:07:28'),(4,3,1,20,250,'2024-05-29 12:47:49'),(3,4,1,-10,190,'2024-05-29 12:47:49'),(3,4,1,20,210,'2024-05-29 12:51:11'),(4,3,1,-10,240,'2024-05-29 12:51:11'),(3,4,2,20,230,'2024-05-29 12:52:23'),(4,3,2,-10,230,'2024-05-29 12:52:23'),(3,5,1,-10,220,'2024-05-29 13:24:32'),(5,3,1,20,250,'2024-05-29 13:24:32'),(3,5,1,-10,210,'2024-05-29 13:30:08'),(5,3,1,20,240,'2024-05-29 13:30:08'),(5,3,1,-10,230,'2024-05-29 13:33:07'),(3,5,1,20,260,'2024-05-29 13:33:07'),(5,3,1,-10,220,'2024-05-29 13:42:01'),(3,5,1,20,250,'2024-05-29 13:42:01');
/*!40000 ALTER TABLE `cb_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cb_history_detail`
--

DROP TABLE IF EXISTS `cb_history_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cb_history_detail` (
  `number` int NOT NULL,
  `player_id` int NOT NULL,
  `score` int NOT NULL DEFAULT '0',
  `total_score` int NOT NULL,
  KEY `number_id_index` (`number`,`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_history_detail`
--

LOCK TABLES `cb_history_detail` WRITE;
/*!40000 ALTER TABLE `cb_history_detail` DISABLE KEYS */;
INSERT INTO `cb_history_detail` VALUES (1,3,3,1000),(1,4,-3,997);
/*!40000 ALTER TABLE `cb_history_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cb_ladder_history`
--

DROP TABLE IF EXISTS `cb_ladder_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cb_ladder_history` (
  `owner_id` int NOT NULL,
  `my_id` int NOT NULL,
  `winner_id` int NOT NULL,
  `play_add` int NOT NULL,
  `rating` int NOT NULL,
  `time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_ladder_history`
--

LOCK TABLES `cb_ladder_history` WRITE;
/*!40000 ALTER TABLE `cb_ladder_history` DISABLE KEYS */;
INSERT INTO `cb_ladder_history` VALUES (5,3,3,20,270,'2024-05-30 17:41:48'),(5,5,3,-10,210,'2024-05-30 17:41:48'),(5,3,5,-10,260,'2024-05-30 17:46:27'),(5,5,5,20,230,'2024-05-30 17:46:27'),(5,3,3,20,280,'2024-05-30 17:46:50'),(5,5,3,-10,220,'2024-05-30 17:46:50'),(5,3,5,-10,270,'2024-05-31 14:35:13'),(5,5,5,20,240,'2024-05-31 14:35:13'),(3,4,3,-10,220,'2024-05-31 15:41:03'),(3,5,3,-10,230,'2024-05-31 15:41:03'),(3,3,3,20,290,'2024-05-31 15:41:03'),(3,4,3,-10,210,'2024-05-31 16:54:45'),(3,5,3,-10,220,'2024-05-31 16:54:45'),(3,3,3,20,310,'2024-05-31 16:54:45');
/*!40000 ALTER TABLE `cb_ladder_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cb_menu`
--

DROP TABLE IF EXISTS `cb_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cb_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '组件路径',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_menu`
--

LOCK TABLES `cb_menu` WRITE;
/*!40000 ALTER TABLE `cb_menu` DISABLE KEYS */;
INSERT INTO `cb_menu` VALUES (1,'主页','/index','index','0','0','cb:index','house',NULL,NULL,NULL,NULL,0,NULL),(2,'排行榜','/ranking','ranking','0','0','cb:ranking','trophy',NULL,NULL,NULL,NULL,0,NULL),(3,'历史记录','/history','history','0','0','cb:history','documentCopy',NULL,NULL,NULL,NULL,0,NULL),(4,'好友对战','/friend','friend','0','0','cb:friend','GoldMedal',NULL,NULL,NULL,NULL,0,NULL),(5,'天梯对战','/ladder','ladder','0','0','cb:ladder','TrophyBase',NULL,NULL,NULL,NULL,0,NULL),(6,'个人中心','/centre','centre','0','0','cb:centre','user',NULL,NULL,NULL,NULL,0,NULL),(7,'天梯对战记录','/ladder_history','ladder_history','0','0','cb:ladder_history','TrophyBase',NULL,NULL,NULL,NULL,0,NULL),(8,'好友对战记录','/history','history','0','0','cb:history','GoldMedal',NULL,NULL,NULL,NULL,0,NULL);
/*!40000 ALTER TABLE `cb_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cb_role`
--

DROP TABLE IF EXISTS `cb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cb_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色权限字符串',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` int DEFAULT '0' COMMENT 'del_flag',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_role`
--

LOCK TABLES `cb_role` WRITE;
/*!40000 ALTER TABLE `cb_role` DISABLE KEYS */;
INSERT INTO `cb_role` VALUES (1,'CEO','ceo','0',0,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `cb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cb_role_menu`
--

DROP TABLE IF EXISTS `cb_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cb_role_menu` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `menu_id` bigint NOT NULL DEFAULT '0' COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_role_menu`
--

LOCK TABLES `cb_role_menu` WRITE;
/*!40000 ALTER TABLE `cb_role_menu` DISABLE KEYS */;
INSERT INTO `cb_role_menu` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8);
/*!40000 ALTER TABLE `cb_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cb_test_data`
--

DROP TABLE IF EXISTS `cb_test_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cb_test_data` (
  `title_id` int NOT NULL,
  `input` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `output` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  KEY `title_id` (`title_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_test_data`
--

LOCK TABLES `cb_test_data` WRITE;
/*!40000 ALTER TABLE `cb_test_data` DISABLE KEYS */;
INSERT INTO `cb_test_data` VALUES (1,NULL,'Hello World!'),(2,'1 4','5'),(2,'10 10','20'),(2,'1 1','2'),(3,'it is so 666 really 6666 what else can I say 6666666666','it is so 666 really 9 what else can I say 27');
/*!40000 ALTER TABLE `cb_test_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cb_title`
--

DROP TABLE IF EXISTS `cb_title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cb_title` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `title_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `input_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `output_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sample_input` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sample_output` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `length_limit` tinyint unsigned NOT NULL,
  `time_limit` smallint unsigned NOT NULL,
  `memory_limit` smallint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_title`
--

LOCK TABLES `cb_title` WRITE;
/*!40000 ALTER TABLE `cb_title` DISABLE KEYS */;
INSERT INTO `cb_title` VALUES (1,'输入输出格式化控制 Hello World!','本题要求编写程序，输出一个短句“Hello World!”。','本题目没有输入。','在一行中输出短句“Hello World!”。',NULL,NULL,16,400,64),(2,'a+b','请输出a+b的和','输入两个整数a和b','输出a+b的和','1 2','3',16,400,64),(3,'6翻了','“666”是一种网络用语，大概是表示某人很厉害、我们很佩服的意思。最近又衍生出另一个数字“9”，意思是“6翻了”，实在太厉害的意思。如果你以为这就是厉害的最高境界，那就错啦 —— 目前的最高境界是数字“27”，因为这是 3 个 “9”！\r\n\r\n本题就请你编写程序，将那些过时的、只会用一连串“6666……6”表达仰慕的句子，翻译成最新的高级表达。','输入在一行中给出一句话，即一个非空字符串，由不超过 1000 个英文字母、数字和空格组成，以回车结束。','从左到右扫描输入的句子：如果句子中有超过 3 个连续的 6，则将这串连续的 6 替换成 9；但如果有超过 9 个连续的 6，则将这串连续的 6 替换成 27。其他内容不受影响，原样输出。','it is so 666 really 6666 what else can I say 6666666666','it is so 666 really 9 what else can I say 27',16,400,64);
/*!40000 ALTER TABLE `cb_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cb_user`
--

DROP TABLE IF EXISTS `cb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
  `user_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
  `create_by` bigint DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  `rating` int unsigned DEFAULT '0' COMMENT '战力值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1799473443726286850 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_user`
--

LOCK TABLES `cb_user` WRITE;
/*!40000 ALTER TABLE `cb_user` DISABLE KEYS */;
INSERT INTO `cb_user` VALUES (1,'ess','二十三','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W',NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL),(2,'de','杜二','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W',NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL),(3,'zs','张三','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W','0',NULL,NULL,NULL,'5b7e6580c42a49cba6115a012f7013dc','1',NULL,NULL,NULL,NULL,0,310),(4,'lisi','李四','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W','0',NULL,NULL,NULL,'f7dd3e247ef64cbea53e5ef168cefef5','1',NULL,NULL,NULL,NULL,0,210),(5,'ww','王五','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W','r','mnicholas@icloud.com','330-647-5513','M','7f71b8d89bd642f7bf8cb9a0e52a4de1','v',502,'2023-01-30 02:32:33',261,'2023-01-20 06:21:51',823,220),(6,'zl','赵六','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W','r','mnicholas@icloud.com','330-647-5513','M','4d52e12defd14c7c9bf0956753af3734','v',502,'2023-01-30 02:32:33',261,'2023-01-20 06:21:51',823,220),(7,'lb','刘七','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W','r','mnicholas@icloud.com','330-647-5513','M','51f008b94f5040a9a06b0b3f2f1c5b5c','v',502,'2023-01-30 02:32:33',261,'2023-01-20 06:21:51',823,220),(8,'wb','王巴','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W','r','mnicholas@icloud.com','330-647-5513','M','51f008b94f5040a9a06b0b3f2f1c5b5c','v',502,'2023-01-30 02:32:33',261,'2023-01-20 06:21:51',823,220),(19,'sj','十九','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W','r','mnicholas@icloud.com','330-647-5513','M','51f008b94f5040a9a06b0b3f2f1c5b5c','v',502,'2023-01-30 02:32:33',261,'2023-01-20 06:21:51',823,220),(20,'es','二十','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W','r','mnicholas@icloud.com','330-647-5513','M','51f008b94f5040a9a06b0b3f2f1c5b5c','v',502,'2023-01-30 02:32:33',261,'2023-01-20 06:21:51',823,220),(21,'esy','二十一','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W','r','mnicholas@icloud.com','330-647-5513','M','5b9d24c02c1a464e9478d24d2d8aa4a6','v',502,'2023-01-30 02:32:33',261,'2023-01-20 06:21:51',823,220),(22,'ese','二十二','$2a$10$4htUy/FTpMXpjxPDYrYjT.AW7FVdKcE210B9xvQ.TITPoEeQW1/7W',NULL,NULL,NULL,NULL,'597b132ec9014f20939a9305f6714bfc','1',NULL,NULL,NULL,NULL,NULL,0),(24,'Fan Jialun','Fan Jialun','IG6aAHOqVS','w','fan47@gmail.com','(161) 240 2278','M','nRz9kgkFat','b',737,'2000-05-17 08:48:20',596,'2020-05-06 21:48:22',68,14),(25,'Ti Kwok Wing','Ti Kwok Wing','CJdmBGerDC','X','tkw@outlook.com','80-3136-5863','M','2EkrEGQ4yF','2',37,'2015-03-28 16:29:09',236,'2011-02-28 21:58:12',433,268),(26,'Liu Zhiyuan','Liu Zhiyuan','ajFjnpHxGH','U','zhili@outlook.com','90-1801-7259','M','apCCNTbT8t','X',555,'2003-01-10 10:57:45',544,'2008-12-23 00:29:26',546,15),(27,'Nicholas Meyer','Nicholas Meyer','vDUn5sfGHy','r','mnicholas@icloud.com','330-647-5513','M','eA7PUB45SI','v',502,'2023-01-30 02:32:33',261,'2023-01-20 06:21:51',823,428),(1799462557294718977,'zsf','张三丰','$2a$10$p5P97hpGcZp7D5pW9Uk4Qu7jmTdimAZU2aIgd6ESA8RPDjD2O892m','0',NULL,NULL,NULL,NULL,'1',NULL,NULL,NULL,NULL,0,0),(1799473443726286849,'mhb','没红兵','$2a$10$25dbJBhOk2XxjSPwFaEfJ.HqJGtwRbQPhEdBOxKSJ6AcAMH4GaSq2','0',NULL,NULL,NULL,'eeeb2fbcf8eb49af85ec5e168911d455','1',NULL,NULL,NULL,NULL,0,0);
/*!40000 ALTER TABLE `cb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `insert_user_role` AFTER INSERT ON `cb_user` FOR EACH ROW BEGIN
    INSERT INTO cb_user_role (user_id, role_id) VALUES (NEW.id, 1);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `cb_user_role`
--

DROP TABLE IF EXISTS `cb_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cb_user_role` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint NOT NULL DEFAULT '1' COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1799473443726286850 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_user_role`
--

LOCK TABLES `cb_user_role` WRITE;
/*!40000 ALTER TABLE `cb_user_role` DISABLE KEYS */;
INSERT INTO `cb_user_role` VALUES (3,1),(4,1),(5,1),(6,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(1798922535902613505,1),(1798925317980262401,1),(1799118426550800386,1),(1799118693983817730,1),(1799118914604208129,1),(1799120145422716929,1),(1799121229692243970,1),(1799122554001788930,1),(1799123880383320065,1),(1799124698050301953,1),(1799126311037018114,1),(1799126415127060481,1),(1799126728508678146,1),(1799127194130948098,1),(1799459454457614337,1),(1799460416702255106,1),(1799460617005436930,1),(1799462557294718977,1),(1799473443726286849,1);
/*!40000 ALTER TABLE `cb_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-09  0:29:57
