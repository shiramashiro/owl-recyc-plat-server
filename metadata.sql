/*
 Navicat MySQL Data Transfer

 Source Server         : 1.116.123.44
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : 1.116.123.44:3306
 Source Schema         : owl

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 11/11/2021 20:24:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `price` decimal(6, 2) NOT NULL COMMENT '价格',
  `author` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
  `cover_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '封面',
  `nomination` int(255) NOT NULL DEFAULT 0 COMMENT '是否推荐，0为不推荐，1为推荐，默认值为0',
  `discount` decimal(4, 2) NOT NULL DEFAULT 1.00 COMMENT '打折，默认值为1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (1, 'living', '为何家会伤人（武志红代表作2018百万畅销纪念版）', 29.90, '武志红', '知名心理学家武志红从业25年来公认口碑代表作！深深触动和改变千万人的心理疗愈经典。家是港湾，爱是退路。', 'http://img3m8.ddimg.cn/28/29/25351948-3_u_3.jpg', 1, 0.80);
INSERT INTO `books` VALUES (2, 'living', '日本深度游 京都', 46.00, 'TAC出版编辑部', '古刹、庭园、红叶……邂逅千年古都的四季之美', 'http://img3m1.ddimg.cn/63/29/29301291-1_w_2.jpg', 0, 0.85);
INSERT INTO `books` VALUES (3, 'living', '户外生存图鉴', 17.90, '澳大利亚威尔登·欧文出版有限公司', '上演真正的绝地求生。', 'http://img3m5.ddimg.cn/89/9/28481795-1_b_6.jpg', 1, 0.82);
INSERT INTO `books` VALUES (4, 'living', '为梦而活：亚历山大·多巴的越洋探险记', 35.90, '阿加塔·洛特·伊格纳克', '单人单艇三次横渡大西洋，谱写“老人与海”的传奇。极限运动类绘本，向青少年普及皮艇运动的知识。曾获波兰麦哲伦zuijia儿童旅游指南奖。', 'http://img3m2.ddimg.cn/61/17/28972312-1_b_3.jpg', 0, 0.80);
INSERT INTO `books` VALUES (5, 'living', '爱的博弈', 37.40, 'John Gottman', '20世纪*影响力的心理治疗大师、美国“婚姻教皇”约翰戈特曼亲密关系四部曲之三，美国亚马逊热销13年，让婚姻持久保鲜的人际关系圣经。揭示关于爱的谜团：该如何定义爱情？为何有的爱情持久，有的早逝？该如何建立信任、避免不忠与背叛？用科学的答案教你把握爱情的来龙去脉，识别暗示爱情冷却的蛛丝马迹， 修复被生活磨损褪色的关系，让一段感情重新焕发活力。源自实验室中对近700对伴侣所做的科学研究，收录了独具开创性的理论、大量丰富翔实的案例和测试题，介绍了在婚姻与爱情中如何衡量信任、应对背叛。适用于已婚、恋爱中的人士及希望提高人际交往技能的人群。', 'http://img3m5.ddimg.cn/21/32/23406195-1_b_4.jpg', 1, 0.75);
INSERT INTO `books` VALUES (6, 'technology', '行星：THE PLANETS: A JOURNEY THROUGH THE SOLAR SYSTEM', 126.00, '[英]贾尔斯·斯帕罗（GilesSparrow) ', '行星：THE PLANETS: A JOURNEY THROUGH THE SOLAR SYSTEM', 'http://img3m9.ddimg.cn/1/6/24180949-1_l_12.jpg', 0, 0.65);
INSERT INTO `books` VALUES (7, 'technology', '时间简史（普及版）', 32.10, '史蒂芬·霍金', '在重新组织本书之际，作者得以扩展特别有兴趣的领域并收入*的进展，从弦论的*发展，到寻求物理学中各种力的完备统一理论令人神往的进展。如同本书早先的版本——甚至有过之而无不及——在寻找时间和空间核心可望而不可及的秘密这一未竞之业中，《时间简史(普及版)》将引导世界各地的普通读者。', 'http://img3m9.ddimg.cn/42/7/9125169-1_b_1.jpg', 1, 0.55);

-- ----------------------------
-- Table structure for carts
-- ----------------------------
DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '属于谁的购物车',
  `book_id` int(11) NOT NULL COMMENT '购物车收藏的哪一个书籍',
  `num` int(11) NOT NULL DEFAULT 1 COMMENT '本书籍有几本',
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_cart_user_id`(`user_id`) USING BTREE,
  INDEX `fk_cart_book_id`(`book_id`) USING BTREE,
  CONSTRAINT `fk_cart_book_id` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_cart_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of carts
-- ----------------------------
INSERT INTO `carts` VALUES (26, 1, 1, 1, '2021-11-11 15:54:21');
INSERT INTO `carts` VALUES (35, 2, 1, 1, '2021-11-11 18:56:21');
INSERT INTO `carts` VALUES (36, 2, 3, 1, '2021-11-11 18:56:40');
INSERT INTO `carts` VALUES (37, 1, 3, 1, '2021-11-11 20:20:32');
INSERT INTO `carts` VALUES (38, 1, 5, 1, '2021-11-11 20:20:34');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NULL DEFAULT NULL COMMENT '书籍Id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户Id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '发表内容',
  `create_date` datetime NULL DEFAULT NULL COMMENT '发表日期',
  `oppose` int(255) NULL DEFAULT 0 COMMENT '反对',
  `agree` int(255) NULL DEFAULT 0 COMMENT '赞成',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_comment_book_id`(`book_id`) USING BTREE,
  INDEX `fk_comment_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_comment_book_id` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (1, 1, 1, '这是一个测试评论', '2021-11-06 23:47:26', 7, 7);
INSERT INTO `comments` VALUES (2, 1, 2, '这是一个测试评论', '2021-11-06 23:52:32', 0, 0);
INSERT INTO `comments` VALUES (3, 1, 1, '这是第二个测试评论。', '2021-11-07 18:37:50', 1, 1);
INSERT INTO `comments` VALUES (4, 1, 1, '这是第三个测试评论哦~', '2021-11-07 18:39:26', 1, 0);
INSERT INTO `comments` VALUES (5, 1, 1, '在远程地址中测试接口，检查是否发成功发表评论。', '2021-11-07 18:45:51', 0, 0);
INSERT INTO `comments` VALUES (6, 1, 1, '牛逼！！！！', '2021-11-07 18:52:02', 0, 0);
INSERT INTO `comments` VALUES (8, 1, 1, '这是一段在手机上发表的评论。', '2021-11-08 01:04:48', 1, 0);
INSERT INTO `comments` VALUES (9, 1, 1, '这是一个测试评论，由于修改了接口，测试一下。', '2021-11-09 22:53:13', 1, 1);
INSERT INTO `comments` VALUES (10, 3, 1, '一个测试评论，在另一个书籍中评论。', '2021-11-10 17:02:59', 0, 2);
INSERT INTO `comments` VALUES (11, 1, 1, '很好，这是一本很不戳的书籍', '2021-11-11 18:27:39', 0, 0);
INSERT INTO `comments` VALUES (12, 1, 1, '这本书很棒，很值得一读', '2021-11-11 18:37:53', 0, 0);
INSERT INTO `comments` VALUES (13, 5, 1, '这本书好好看，我非常喜欢', '2021-11-11 18:49:21', 1, 1);
INSERT INTO `comments` VALUES (14, 3, 1, '这本书很好看，很值得一读', '2021-11-11 18:56:59', 1, 0);

-- ----------------------------
-- Table structure for covers
-- ----------------------------
DROP TABLE IF EXISTS `covers`;
CREATE TABLE `covers`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NULL DEFAULT NULL COMMENT '书籍ID',
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '封面地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_cover_book_id`(`book_id`) USING BTREE,
  CONSTRAINT `fk_cover_book_id` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of covers
-- ----------------------------
INSERT INTO `covers` VALUES (1, 1, 'http://img3m8.ddimg.cn/28/29/25351948-3_u_3.jpg');
INSERT INTO `covers` VALUES (2, 1, 'http://img3m8.ddimg.cn/28/29/25351948-2_u_3.jpg');
INSERT INTO `covers` VALUES (3, 1, 'http://img3m8.ddimg.cn/28/29/25351948-1_u_3.jpg');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `receiver` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0为运输中，1为已收货',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_orders_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_orders_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('762178b1-c5cd-475c-8f50-471f6092feba', 2, '2021-11-11 18:49:54', '13989289680', '西南财经大学天府学院', '刘昱辰', 0);
INSERT INTO `orders` VALUES ('9d1bbcd8-9467-476d-ad27-be068599aefc', 2, '2021-11-11 18:52:13', '13989289680', '西南财经大学天府学院', '刘昱', 0);
INSERT INTO `orders` VALUES ('d6be5be9-c753-4339-a709-61fbef2bd63b', 1, '2021-11-11 15:01:51', '18508153489', '四川省绵阳市西南财经大学天府学院', '郑人滏', 1);

-- ----------------------------
-- Table structure for orders_products
-- ----------------------------
DROP TABLE IF EXISTS `orders_products`;
CREATE TABLE `orders_products`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_orders_products_book_id`(`book_id`) USING BTREE,
  INDEX `fk_orders_products_order_id`(`order_id`) USING BTREE,
  CONSTRAINT `fk_orders_products_book_id` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_orders_products_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders_products
-- ----------------------------
INSERT INTO `orders_products` VALUES (21, 7, 'd6be5be9-c753-4339-a709-61fbef2bd63b');
INSERT INTO `orders_products` VALUES (25, 5, '762178b1-c5cd-475c-8f50-471f6092feba');
INSERT INTO `orders_products` VALUES (26, 1, '9d1bbcd8-9467-476d-ad27-be068599aefc');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '头像地址',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `signature` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `background_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '背景图片',
  `create_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'shiramashiro', '123456', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.0Y_I1Dsg0c2_rFqHa1dXdwAAAA?w=210&h=210&c=7&r=0&o=5&dpr=1.25&pid=1.7', '18508153489', 'Time tick away,dream faded away!', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.UTSDc89X-GEoa6-ozQUz_QHaEo?w=311&h=194&c=7&r=0&o=5&dpr=1.25&pid=1.7', '2021-11-11 16:52:55');
INSERT INTO `users` VALUES (2, 'kito', '123456', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.0Y_I1Dsg0c2_rFqHa1dXdwAAAA?w=210&h=210&c=7&r=0&o=5&dpr=1.25&pid=1.7', '18236537580', 'Time tick away,dream faded away!', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.UTSDc89X-GEoa6-ozQUz_QHaEo?w=311&h=194&c=7&r=0&o=5&dpr=1.25&pid=1.7', '2021-11-11 18:47:30');

SET FOREIGN_KEY_CHECKS = 1;
