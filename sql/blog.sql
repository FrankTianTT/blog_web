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
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `userId` int(11) NOT NULL,
    `categoryId` int(11) NOT NULL,
    `userName` varchar(255) NOT NULL,
    `categoryName` varchar(255) NOT NULL,
    # COLLATE utf8_general_ci的含义:
    # 数据库的校验规则,ci是case insensitive的缩写,意思是大小写不敏感; 相对的是cs,即case sensitive,大小写敏感。
    `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `view_times` int(11) DEFAULT 0,
    `create_date` date DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `blog`
--

BEGIN;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,1,8,'测试一号','文学','摘抄《挪威的森林》','  一九六九年这一年，总是令我想起进退两难的泥沼——每迈一步都几乎把整只鞋仙掉那般的滞重而深沉的泥沼。而我就在这片泥沼中气喘吁吁地挪动脚步，前方一无所有，后面渺无来者，只有昏暗的泥沼无边无际的延展开去。\n  甚至时光都随着我的步调而流淌得十分吃力。身边的人早已经遥遥领先，惟独我和我的时间在泥沼中艰难的来回爬行。我四周的世界则面临一切沧桑巨变。约翰.科尔兰特死了，还有很多人死了。人们在呼喊变革，仿佛变革正在席卷每一个角落。然而这些无一不是虚构的毫无意义的背景画面而已。我则几乎没有抬头，日复一日的打发时光。在我眼里，只有漫无边际的泥沼。往前落下右脚，拔起左脚，再拨起右脚。我判断不出我位于何处，也不具有自己是在朝正确方向前进的信心。我之所以一步步挪动步履，只是因为我必须挪动，而无论去哪里。','村上春树',0,'2020-02-09'),
                          (2,1,8,'测试一号','文学','摘抄《我是猫》，刘振瀛译','休息是万物向苍天要求的当然权利。那些在这个世上有生息的义务而又纷然蠢动的人类，为了完成生息的义务，还是非休养不可的。假如果真有上帝，他说什么“尔等为劳动而生，非为睡眠而生也”的话，那么我会回答上帝说：“诚如尊谕，在下是为劳动而生，所以也要求允许为劳动而休息。”','夏目漱石',12,'2020-05-15'),
                          (3,1,1,'测试一号','日记','十二月七日 情书','在大家用手机就能传情说爱的世代里，寄信是怎么一回事呢？\n  让我来解释，它是这样子的。你先拿出一张纸，再用笔写上一句“你好吗？我很好”，然后折进信封，贴上邮票，第二天早上把它投进邮筒。接下来几天甚至几周，你思量对方是否已经收到你的信？他有什么反应？他回信了吗？他会回信吗？有时候，你会后悔之前的信太短，言不及义，于是隔天又补上一封，又有些时候，你等得太久，所以痛省自己的愚蠢，为什么还要寄给他信呢？\n  不懂，是因为十年以前，那还是个恋爱需要时间的年代。','梁文道',0,'2020-05-10'),
                          (4,1,2,'测试一号','技术','hello world,第一个java程序','System.out.print(“hello world!”);','Java',1211,'2020-06-02');
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
INSERT INTO `category` VALUES (1,'日记'), (2,'技术'), (3,'学习'), (4,'美食'), (5, '旅游'), (6, '生活'), (7, '时尚'), (8, '文学');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
COMMIT;


-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `email` varchar(255) NOT NULL,
    `password` varchar(20) NOT NULL,
    `userName` varchar(255) NOT NULL,
    `userType` varchar(255) DEFAULT NULL,
    `profile` varchar(255) DEFAULT NULL,
    `qqNumber` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`email`),
    UNIQUE KEY id_unique (id)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

BEGIN;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1042033333@qq.com','123456','测试一号','Blogger','测试个人简介1','2135234'),
                          (2,'KyleBroflovski@gmail.com','123456','测试二号（管理员）','Admin','测试个人简介2222','3564754');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
COMMIT;


-- ----------------------------
--  Table structure for `user_class`
-- ----------------------------
/* user所写的blog分类及数目 */

DROP TABLE IF EXISTS `user_category`;
CREATE TABLE `user_category` (
    `userId` int(11) NOT NULL,
    `categoryId` int(11) NOT NULL,
    `number` int(11) DEFAULT 0,
    PRIMARY KEY (`userId`,`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Room`
--

BEGIN;
/*!40000 ALTER TABLE `user_category` DISABLE KEYS */;
INSERT INTO `user_category` VALUES (1,3,2), (1,4,2), (2,4,1);
/*!40000 ALTER TABLE `user_category` ENABLE KEYS */;
COMMIT;


-- ----------------------------
--  Table structure for `comment`
-- ----------------------------

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `blogId` int(11) NOT NULL,
    `userId` int(11) NOT NULL,
    `authorName` varchar(255) NOT NULL,
    `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `comment_time` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment`
--

BEGIN;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (20,1,2,'测试二号（管理员）','这是测试评论一1111111111','2020-05-09 16:59:04'),
                             (21,2,1,'测试一号','好好好好好好好好好好','2020-06-01 10:35:01');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
COMMIT;


-- ----------------------------
--  Table structure for `collection`
-- ----------------------------

DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
    `blogId` int(11) NOT NULL,
    `userId` int(11) NOT NULL,
    `collect_time` datetime DEFAULT NULL,
    PRIMARY KEY (`blogId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `collection`
--

BEGIN;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
INSERT INTO `collection` VALUES (1,1,'2020-05-09 17:03:01'),(2,1,'2020-05-15 12:18:43');
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
COMMIT;

-- Dump completed
SET FOREIGN_KEY_CHECKS = 1;
