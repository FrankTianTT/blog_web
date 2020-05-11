/*
MySQL Backup
Source Server Version: 8.0.19
Source Database: blog
Date: 2020/5/9 16:30:09
*/



-- ----------------------------
--  Table structure for `blog`
-- ----------------------------

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
    `blog_id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) NOT NULL,
    `category_id` int(11) NOT NULL,
    # COLLATE utf8_general_ci的含义:
    # 数据库的校验规则,ci是case insensitive的缩写,意思是大小写不敏感; 相对的是cs,即case sensitive,大小写敏感。
    `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `view_times` int(11) DEFAULT 0,
    `create_date` date DEFAULT NULL,
    PRIMARY KEY (`blog_id`)
#     FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
#     FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `blog`
--

BEGIN;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES ('1','1','8','测试标题一：摘抄，第305页','  一九六九年这一年，总是令我想起进退两难的泥沼——每迈一步都几乎把整只鞋仙掉那般的滞重而深沉的泥沼。而我就在这片泥沼中气喘吁吁地挪动脚步，前方一无所有，后面渺无来者，只有昏暗的泥沼无边无际的延展开去。\n  甚至时光都随着我的步调而流淌得十分吃力。身边的人早已经遥遥领先，惟独我和我的时间在泥沼中艰难的来回爬行。我四周的世界则面临一切沧桑巨变。约翰.科尔兰特死了，还有很多人死了。人们在呼喊变革，仿佛变革正在席卷每一个角落。然而这些无一不是虚构的毫无意义的背景画面而已。我则几乎没有抬头，日复一日的打发时光。在我眼里，只有漫无边际的泥沼。往前落下右脚，拔起左脚，再拨起右脚。我判断不出我位于何处，也不具有自己是在朝正确方向前进的信心。我之所以一步步挪动步履，只是因为我必须挪动，而无论去哪里。','村上春树',0,'2020-05-09');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
COMMIT;


-- ----------------------------
--  Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
    `id` int(11) NOT NULL,
    `content` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

BEGIN;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('1','日记'), ('2','技术'), ('3','学习'), ('4','美食'), ('5', '旅游'), ('6', '生活'), ('7', '时尚'), ('8', '文学');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
COMMIT;


-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `email` varchar(255) DEFAULT NULL,
    `username` varchar(255) NOT NULL,
    `password` varchar(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

BEGIN;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1','1042033333@qq.com','测试一号','123456'), ('2','KyleBroflovski@gmail.com','测试二号','123456');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
COMMIT;



-- ----------------------------
--  Table structure for `user_class`
-- ----------------------------
/* user所写的blog分类及数目 */

DROP TABLE IF EXISTS `user_category`;
CREATE TABLE `user_category` (
    `user_id` int(11) NOT NULL,
    `category_id` int(11) NOT NULL,
    `number` int(11) DEFAULT 0,
    PRIMARY KEY (`user_id`,category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Room`
--

BEGIN;
/*!40000 ALTER TABLE `user_category` DISABLE KEYS */;
INSERT INTO `user_category` VALUES ('1','3','2'), ('1','4','2'), ('2','4','1');
/*!40000 ALTER TABLE `user_category` ENABLE KEYS */;
COMMIT;


-- ----------------------------
--  Table structure for `comment`
-- ----------------------------

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `blog_id` int(11) NOT NULL,
    `user_id` int(11) NOT NULL,
    `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `comment_time` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
#     FOREIGN KEY (`blog_id`) REFERENCES `blog` (`blog_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
#     FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment`
--

BEGIN;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES ('20','1','2','这是测试评论一','2020-05-09 16:59:04');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
COMMIT;


-- ----------------------------
--  Table structure for `collection`
-- ----------------------------

DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
    `blog_id` int(11) NOT NULL,
    `user_id` int(11) NOT NULL,
    `collect_time` datetime DEFAULT NULL,
    PRIMARY KEY (`blog_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `collection`
--

BEGIN;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
INSERT INTO `collection` VALUES ('1','1','2020-05-09 17:03:01');
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
COMMIT;

-- Dump completed
SET FOREIGN_KEY_CHECKS = 1;
