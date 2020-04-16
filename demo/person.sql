/*
Navicat MySQL Data Transfer

Source Server         : zhm
Source Server Version : 50533
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50533
File Encoding         : 65001

Date: 2020-04-16 10:48:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `personid` int(11) NOT NULL AUTO_INCREMENT,
  `personname` varchar(10) NOT NULL,
  `personage` int(11) NOT NULL,
  PRIMARY KEY (`personid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '张三', '13');
INSERT INTO `person` VALUES ('2', '李四', '14');
INSERT INTO `person` VALUES ('3', '王五', '15');
INSERT INTO `person` VALUES ('4', 'zh', '26');
INSERT INTO `person` VALUES ('5', 'z1h', '26');
INSERT INTO `person` VALUES ('6', 'zl', '22');
INSERT INTO `person` VALUES ('7', '赵六', '21');
INSERT INTO `person` VALUES ('8', '哈哈', '13');
INSERT INTO `person` VALUES ('9', '哈哈', '13');
INSERT INTO `person` VALUES ('10', '李四', '14');
INSERT INTO `person` VALUES ('11', '哈哈1', '13');
INSERT INTO `person` VALUES ('12', '哈哈', '15');
INSERT INTO `person` VALUES ('14', '哈哈22', '13');
INSERT INTO `person` VALUES ('15', '哈哈', '18');
INSERT INTO `person` VALUES ('17', '哈哈1111', '111');
INSERT INTO `person` VALUES ('20', '打发士', '131');
