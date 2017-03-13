/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : graduation_project

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-03-13 10:54:09
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
  `candidateNum` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `age` int(3) NOT NULL,
  `major` varchar(50) NOT NULL,
  `company` varchar(50) NOT NULL,
  `department` varchar(50) NOT NULL,
  `group` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `candidateNum` (`candidateNum`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of candidate
-- ----------------------------
INSERT INTO `candidate` VALUES ('13', '12', 'abc', '20', 'abc', 'abc', 'abc', null);
INSERT INTO `candidate` VALUES ('14', '2', 'abc', '20', 'abc', 'abc', 'abc', null);
INSERT INTO `candidate` VALUES ('16', '3', 'a', '1', 'a', 'a', 's', null);
INSERT INTO `candidate` VALUES ('17', '4', '1', '1', '1', '1', '1', null);
INSERT INTO `candidate` VALUES ('19', '5', '1', '1', '1', '1', '1', null);
