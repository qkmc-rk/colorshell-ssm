/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : colorshell

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-04-22 22:21:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`; 
CREATE TABLE `device` (
  `id` int(11) NOT NULL COMMENT '设备ID',
  `mac` varchar(255) NOT NULL COMMENT '设备的硬件地址',
  `token` varchar(255) NOT NULL COMMENT '设备token，用户设备请求服务器',
  `type` enum('temperature','ph','illumination','humidity','gas') DEFAULT NULL COMMENT '设备类型，枚举类型，默认为空',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for gas
-- ----------------------------
DROP TABLE IF EXISTS `gas`;
CREATE TABLE `gas` (
  `id` int(11) NOT NULL COMMENT '气体ID',
  `gas` double NOT NULL COMMENT '气体浓度',
  `creatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建记录时间',
  `deviceid` int(11) NOT NULL COMMENT '设备ID，标示某台设备采集的数据',
  PRIMARY KEY (`id`),
  KEY `f_gas_device` (`deviceid`),
  CONSTRAINT `f_gas_device` FOREIGN KEY (`deviceid`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for humidity
-- ----------------------------
DROP TABLE IF EXISTS `humidity`;
CREATE TABLE `humidity` (
  `id` int(11) NOT NULL COMMENT '湿度ID',
  `humidity` double NOT NULL COMMENT '湿度',
  `creatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `deviceid` int(11) NOT NULL COMMENT '采集设备的ID',
  PRIMARY KEY (`id`),
  KEY `f_humi_device` (`deviceid`),
  CONSTRAINT `f_humi_device` FOREIGN KEY (`deviceid`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for illumination
-- ----------------------------
DROP TABLE IF EXISTS `illumination`;
CREATE TABLE `illumination` (
  `id` int(11) NOT NULL COMMENT '光强ID',
  `illumination` int(11) NOT NULL COMMENT '光强',
  `creatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `deviceid` int(11) NOT NULL COMMENT '采集设备的ID',
  PRIMARY KEY (`id`),
  KEY `f_illum_devive` (`deviceid`),
  CONSTRAINT `f_illum_devive` FOREIGN KEY (`deviceid`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for ph
-- ----------------------------
DROP TABLE IF EXISTS `ph`;
CREATE TABLE `ph` (
  `id` int(11) NOT NULL COMMENT 'ph值ID',
  `ph` double NOT NULL COMMENT 'ph值',
  `creatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `deviceid` int(11) NOT NULL COMMENT 'ph采集设备ID',
  PRIMARY KEY (`id`),
  KEY `f_ph_device` (`deviceid`),
  CONSTRAINT `f_ph_device` FOREIGN KEY (`deviceid`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for temperatrue
-- ----------------------------
DROP TABLE IF EXISTS `temperatrue`;
CREATE TABLE `temperatrue` (
  `id` int(11) NOT NULL COMMENT '温度ID',
  `temperature` double NOT NULL COMMENT '温度',
  `creatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `deviceid` int(11) NOT NULL COMMENT '温度采集设备ID',
  PRIMARY KEY (`id`),
  KEY `f_temp_device` (`deviceid`),
  CONSTRAINT `f_temp_device` FOREIGN KEY (`deviceid`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `neckname` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `role` enum('admin','user') NOT NULL COMMENT '角色1为管理员0为终端用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
