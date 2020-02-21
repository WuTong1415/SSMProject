/*
 Navicat Premium Data Transfer

 Source Server         : Connect
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : springmvc-mybatis-book

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 20/02/2020 17:02:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '评论的ID',
  `moodid` int(0) NOT NULL COMMENT '被评论的说说ID',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `userid` int(0) NOT NULL COMMENT '写评论的用户',
  `createtime` datetime(0) NOT NULL COMMENT '评论的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (42, 25, '这也太强了8', 1, '2020-02-20 16:53:54');
INSERT INTO `comment` VALUES (43, 24, '真有你的', 2, '2020-02-20 16:54:03');

-- ----------------------------
-- Table structure for mood
-- ----------------------------
DROP TABLE IF EXISTS `mood`;
CREATE TABLE `mood`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '说说ID',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说说内容',
  `user_id` int(0) NOT NULL COMMENT '写说说的用户ID',
  `publish_time` timestamp(0) NOT NULL COMMENT '发表时间',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mood
-- ----------------------------
INSERT INTO `mood` VALUES (24, '我真是日了gou了', 1, '2020-02-20 16:52:53', '2020/02/201582188773318QQ截图20190226132641.png');
INSERT INTO `mood` VALUES (25, '牛的', 2, '2020-02-20 16:53:31', '2020/02/201582188810674QQ截图20191212224105.png');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名字(昵称)',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账户',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '小壹', 'user1', '123');
INSERT INTO `user` VALUES (2, '小贰', 'user2', '123');
INSERT INTO `user` VALUES (3, '小叁', 'user3', '123');
INSERT INTO `user` VALUES (5, '小肆', 'user4', '123');

SET FOREIGN_KEY_CHECKS = 1;
