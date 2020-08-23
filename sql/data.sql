/*
 Navicat Premium Data Transfer

 Source Server         : txy
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : 203.195.193.153:3306
 Source Schema         : data

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 07/05/2020 17:29:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for CodeConfig
-- ----------------------------
DROP TABLE IF EXISTS `CodeConfig`;
CREATE TABLE `CodeConfig` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `intervalTime` int(6) DEFAULT NULL,
  `active` int(2) DEFAULT NULL,
  `enable` int(2) DEFAULT NULL,
  `mark` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for SinaShare
-- ----------------------------
DROP TABLE IF EXISTS `SinaShare`;
CREATE TABLE `SinaShare` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `yesterdayPrice` double(10,2) DEFAULT NULL,
  `currentPrice` double(10,2) DEFAULT NULL,
  `todayPrice` double(10,2) DEFAULT NULL,
  `range` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `buy1` double(10,0) DEFAULT NULL,
  `buy1price` double(10,2) DEFAULT NULL,
  `buy2` double(10,0) DEFAULT NULL,
  `buy2price` double(10,2) DEFAULT NULL,
  `buy3` double(10,0) DEFAULT NULL,
  `buy3price` double(10,2) DEFAULT NULL,
  `buy4` double(10,0) DEFAULT NULL,
  `buy4price` double(10,2) DEFAULT NULL,
  `buy5` double(10,0) DEFAULT NULL,
  `buy5price` double(10,2) DEFAULT NULL,
  `sell1` double(10,0) DEFAULT NULL,
  `sell1price` double(10,2) DEFAULT NULL,
  `sell2` double(10,0) DEFAULT NULL,
  `sell2price` double(10,2) DEFAULT NULL,
  `sell3` double(10,0) DEFAULT NULL,
  `sell3price` double(10,2) DEFAULT NULL,
  `sell4` double(10,0) DEFAULT NULL,
  `sell4price` double(10,2) DEFAULT NULL,
  `sell5` double(10,0) DEFAULT NULL,
  `sell5price` double(10,2) DEFAULT NULL,
  `buyNum` double(10,0) DEFAULT NULL,
  `money` double(20,0) DEFAULT NULL,
  `currentTime` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `type` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '买大资金',
  `sellType` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '买大资金',
  `create` timestamp(6) NULL DEFAULT NULL,
  `smailBuyType` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '小买资金',
  `smailSellType` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '小卖资金',
  `rangeType` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `currentTime` (`currentTime`,`name`,`create`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2068371 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `value` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for parse_detail
-- ----------------------------
DROP TABLE IF EXISTS `parse_detail`;
CREATE TABLE `parse_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `time` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `am_buy` int(20) DEFAULT NULL,
  `am_sell` int(20) DEFAULT NULL,
  `asp_buy` int(20) DEFAULT NULL,
  `asp_sell` int(20) DEFAULT NULL,
  `pm_buy` int(20) DEFAULT NULL,
  `pm_sell` int(20) DEFAULT NULL,
  `sp_buy` int(20) DEFAULT NULL,
  `sp_sell` int(20) DEFAULT NULL,
  `total_buy` int(20) DEFAULT NULL,
  `total_sell` int(20) DEFAULT NULL,
  `range` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `sellBigCount` int(10) DEFAULT NULL,
  `buyBigCount` int(10) DEFAULT NULL,
  `later5_buy` int(15) DEFAULT NULL,
  `later5_sell` int(15) DEFAULT NULL,
  `later10_buy` int(15) DEFAULT NULL,
  `later10_sell` int(15) DEFAULT NULL,
  `later5_ave` double(10,2) DEFAULT NULL COMMENT '买除以卖的值，取小数点2位',
  PRIMARY KEY (`id`),
  KEY `name` (`name`,`time`) USING BTREE COMMENT '名称和时间添加索引'
) ENGINE=InnoDB AUTO_INCREMENT=3713 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for recommendHistory
-- ----------------------------
DROP TABLE IF EXISTS `recommendHistory`;
CREATE TABLE `recommendHistory` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(6) COLLATE utf8mb4_bin DEFAULT NULL,
  `createTime` timestamp(6) NULL DEFAULT NULL,
  `sellLargeBuyAndRangeL2` int(2) DEFAULT '0',
  `amSellLeBuyAndAfterBuyLeSell` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
