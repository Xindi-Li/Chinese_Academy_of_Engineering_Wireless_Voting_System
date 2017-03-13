/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : graduation_project

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-03-13 19:41:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('4', '123', '202cb962ac59075b964b07152d234b70');
INSERT INTO `admin` VALUES ('5', '1234', '81dc9bdb52d04dc20036dbd8313ed055');
INSERT INTO `admin` VALUES ('6', 'abc', '900150983cd24fb0d6963f7d28e17f72');

-- ----------------------------
-- Table structure for candidate
-- ----------------------------
DROP TABLE IF EXISTS `candidate`;
CREATE TABLE `candidate` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `candidate_num` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `age` int(3) NOT NULL,
  `major` varchar(50) NOT NULL,
  `company` varchar(50) NOT NULL,
  `department` varchar(50) NOT NULL,
  `group` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `candidate_num` (`candidate_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of candidate
-- ----------------------------
INSERT INTO `candidate` VALUES ('13', '12', 'abc', '20', 'abc', 'abc', 'abc', null);
INSERT INTO `candidate` VALUES ('14', '2', 'abc', '20', 'abc', 'abc', 'abc', null);
INSERT INTO `candidate` VALUES ('16', '3', 'a', '1', 'a', 'a', 's', null);
INSERT INTO `candidate` VALUES ('17', '4', '1', '1', '1', '1', '1', null);
INSERT INTO `candidate` VALUES ('19', '5', '1', '1', '1', '1', '1', null);
INSERT INTO `candidate` VALUES ('20', '13', '张金麟', '70', '机械', '浙江大学', '机械与运载工程学部', null);
INSERT INTO `candidate` VALUES ('21', '14', '杨凤田', '66', '机械', '装甲兵工程学院', '机械与运载工程学部', null);
INSERT INTO `candidate` VALUES ('22', '15', '陈福田', '68', '机械', '中国航天科技集团公司第一研究院', '机械与运载工程学部', null);
INSERT INTO `candidate` VALUES ('23', '16', '臧克茂', '70', '机械', '中国船舶重工集团第七一九研究所', '机械与运载工程学部', null);
INSERT INTO `candidate` VALUES ('24', '17', '谭建荣', '70', '机械', '中国航空工业第一集团公司沈阳飞机设计研究所', '机械与运载工程学部', null);
INSERT INTO `candidate` VALUES ('25', '18', '张尧学', '61', '信息', '教育部', '信息与电子工程学部', null);
INSERT INTO `candidate` VALUES ('26', '19', '徐扬生', '57', '信息', '香港中文大学', '信息与电子工程学部', null);
