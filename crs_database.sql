/*
MySQL Data Transfer
Source Host: localhost
Source Database: crs_database
Target Host: localhost
Target Database: crs_database
Date: 26-01-2022 14:25:03
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminId` varchar(10) NOT NULL,
  `adminName` varchar(20) NOT NULL,
  PRIMARY KEY  (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseId` varchar(10) NOT NULL,
  `courseName` varchar(20) NOT NULL,
  `courseFee` float(20,0) NOT NULL,
  `department` varchar(20) NOT NULL,
  `professorId` varchar(10) default NULL,
  `studentCount` int(10) unsigned zerofill NOT NULL,
  PRIMARY KEY  (`courseId`),
  KEY `professorId` (`professorId`),
  CONSTRAINT `professorId` FOREIGN KEY (`professorId`) REFERENCES `professor` (`professorId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `studentId` varchar(10) NOT NULL,
  `courseId` varchar(10) NOT NULL,
  `gpa` float(10,0) NOT NULL,
  `semester` varchar(10) NOT NULL,
  PRIMARY KEY  (`studentId`,`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for gradecard
-- ----------------------------
DROP TABLE IF EXISTS `gradecard`;
CREATE TABLE `gradecard` (
  `studentId` varchar(10) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `gpa` float(10,0) NOT NULL default '0',
  PRIMARY KEY  (`studentId`,`semester`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for optedcourses
-- ----------------------------
DROP TABLE IF EXISTS `optedcourses`;
CREATE TABLE `optedcourses` (
  `studentId` varchar(10) NOT NULL,
  `isAlloted` int(1) NOT NULL,
  `courseId` varchar(10) NOT NULL,
  `optedNumber` int(10) NOT NULL auto_increment,
  PRIMARY KEY  (`optedNumber`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `courseId` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `studentId` varchar(10) NOT NULL,
  `referenceId` varchar(10) NOT NULL default '',
  `amount` float(10,0) NOT NULL,
  `paymentMode` varchar(10) NOT NULL,
  PRIMARY KEY  (`referenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for professor
-- ----------------------------
DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor` (
  `professorId` varchar(10) NOT NULL,
  `professorName` varchar(20) NOT NULL,
  `salary` float(10,0) default NULL,
  `department` varchar(20) NOT NULL,
  `doj` date NOT NULL,
  PRIMARY KEY  (`professorId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for semesterregistration
-- ----------------------------
DROP TABLE IF EXISTS `semesterregistration`;
CREATE TABLE `semesterregistration` (
  `studentId` varchar(10) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `registrationStatus` int(1) NOT NULL default '0',
  PRIMARY KEY  (`studentId`),
  CONSTRAINT `studentId` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentId` varchar(10) NOT NULL,
  `branch` varchar(20) NOT NULL,
  `batch` varchar(10) NOT NULL,
  `verificationStatus` int(1) NOT NULL default '0',
  `feeStatus` int(1) NOT NULL default '0',
  PRIMARY KEY  (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(10) NOT NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `course` VALUES ('101', 'first', '1234', 'cse', null, '0000000001');
INSERT INTO `course` VALUES ('102', 'second', '1234', 'cse', null, '0000000001');
INSERT INTO `course` VALUES ('103', 'third', '1234', 'cse', null, '0000000001');
INSERT INTO `course` VALUES ('104', 'fourth', '1234', 'cse', null, '0000000000');
INSERT INTO `course` VALUES ('105', 'fifth', '1234', 'cse', null, '0000000000');
INSERT INTO `course` VALUES ('106', 'sixth', '1234', 'cse', null, '0000000001');
INSERT INTO `course` VALUES ('107', 'seventh', '1234', 'cse', null, '0000000000');
INSERT INTO `optedcourses` VALUES ('S109622548', '1', '103', '1');
INSERT INTO `optedcourses` VALUES ('S109622548', '1', '101', '3');
INSERT INTO `optedcourses` VALUES ('S109622548', '1', '106', '4');
INSERT INTO `optedcourses` VALUES ('S109622548', '1', '102', '5');
INSERT INTO `student` VALUES ('S109622548', 'cse', '2018', '0', '0');
INSERT INTO `student` VALUES ('S178371999', 'cse', '2018', '0', '0');
INSERT INTO `user` VALUES ('A10201837', 'Admin', 'admin', 'admin', '0');
INSERT INTO `user` VALUES ('S109622548', 'shubham', 'abcd', 'abcd', '2');
