/*
Navicat MySQL Data Transfer

Source Server         : 08
Source Server Version : 50550
Source Host           : 192.168.0.8:3306
Source Database       : deploy

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2016-07-13 15:58:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for java_web_config
-- ----------------------------
DROP TABLE IF EXISTS `java_web_config`;
CREATE TABLE `java_web_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `linuxid` int(11) DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL COMMENT '配置文件路径',
  `suffix` varchar(255) DEFAULT NULL COMMENT '后缀',
  `filename` varchar(255) DEFAULT NULL COMMENT '文件名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for java_web_linux
-- ----------------------------
DROP TABLE IF EXISTS `java_web_linux`;
CREATE TABLE `java_web_linux` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `loginname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `projectid` int(11) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL COMMENT '服务器路径',
  `name` varchar(255) DEFAULT NULL COMMENT '服务器名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for java_web_project
-- ----------------------------
DROP TABLE IF EXISTS `java_web_project`;
CREATE TABLE `java_web_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `profile` varchar(255) DEFAULT NULL COMMENT 'Maven profile',
  `type` tinyint(4) DEFAULT NULL COMMENT '版本控制工具类型(1.SVN;2.GIT)',
  `url` varchar(255) DEFAULT NULL COMMENT 'svn/git地址',
  `contextpath` varchar(255) DEFAULT NULL,
  `port` int(11) DEFAULT NULL COMMENT '端口',
  `war` varchar(255) DEFAULT NULL COMMENT 'war包路径',
  `suffix` varchar(255) DEFAULT NULL COMMENT '后缀',
  `filename` varchar(255) DEFAULT NULL COMMENT '文件名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
