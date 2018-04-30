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
  `id` int(11) NOT NULL COMMENT '�豸ID',
  `mac` varchar(255) NOT NULL COMMENT '�豸��Ӳ����ַ',
  `token` varchar(255) NOT NULL COMMENT '�豸token���û��豸���������',
  `type` enum('temperature','ph','illumination','humidity','gas') DEFAULT NULL COMMENT '�豸���ͣ�ö�����ͣ�Ĭ��Ϊ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for gas
-- ----------------------------
DROP TABLE IF EXISTS `gas`;
CREATE TABLE `gas` (
  `id` int(11) NOT NULL COMMENT '����ID',
  `gas` double NOT NULL COMMENT '����Ũ��',
  `creatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '������¼ʱ��',
  `deviceid` int(11) NOT NULL COMMENT '�豸ID����ʾĳ̨�豸�ɼ�������',
  PRIMARY KEY (`id`),
  KEY `f_gas_device` (`deviceid`),
  CONSTRAINT `f_gas_device` FOREIGN KEY (`deviceid`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for humidity
-- ----------------------------
DROP TABLE IF EXISTS `humidity`;
CREATE TABLE `humidity` (
  `id` int(11) NOT NULL COMMENT 'ʪ��ID',
  `humidity` double NOT NULL COMMENT 'ʪ��',
  `creatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `deviceid` int(11) NOT NULL COMMENT '�ɼ��豸��ID',
  PRIMARY KEY (`id`),
  KEY `f_humi_device` (`deviceid`),
  CONSTRAINT `f_humi_device` FOREIGN KEY (`deviceid`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for illumination
-- ----------------------------
DROP TABLE IF EXISTS `illumination`;
CREATE TABLE `illumination` (
  `id` int(11) NOT NULL COMMENT '��ǿID',
  `illumination` int(11) NOT NULL COMMENT '��ǿ',
  `creatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `deviceid` int(11) NOT NULL COMMENT '�ɼ��豸��ID',
  PRIMARY KEY (`id`),
  KEY `f_illum_devive` (`deviceid`),
  CONSTRAINT `f_illum_devive` FOREIGN KEY (`deviceid`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for ph
-- ----------------------------
DROP TABLE IF EXISTS `ph`;
CREATE TABLE `ph` (
  `id` int(11) NOT NULL COMMENT 'phֵID',
  `ph` double NOT NULL COMMENT 'phֵ',
  `creatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `deviceid` int(11) NOT NULL COMMENT 'ph�ɼ��豸ID',
  PRIMARY KEY (`id`),
  KEY `f_ph_device` (`deviceid`),
  CONSTRAINT `f_ph_device` FOREIGN KEY (`deviceid`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for temperatrue
-- ----------------------------
DROP TABLE IF EXISTS `temperatrue`;
CREATE TABLE `temperatrue` (
  `id` int(11) NOT NULL COMMENT '�¶�ID',
  `temperature` double NOT NULL COMMENT '�¶�',
  `creatime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `deviceid` int(11) NOT NULL COMMENT '�¶Ȳɼ��豸ID',
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
  `role` enum('admin','user') NOT NULL COMMENT '��ɫ1Ϊ����Ա0Ϊ�ն��û�',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
