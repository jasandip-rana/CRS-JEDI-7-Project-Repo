/*
MySQL Data Transfer
Source Host: localhost
Source Database: crs_database
Target Host: localhost
Target Database: crs_database
Date: 28-01-2022 18:06:36
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminId` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `contactNumber` varchar(10) default NULL,
  PRIMARY KEY  (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for catalog
-- ----------------------------
DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `catalogId` varchar(10) NOT NULL,
  `courseId` varchar(10) NOT NULL
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
  `gpa` float(10,2) NOT NULL,
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
  `gpa` float(10,2) NOT NULL default '0.00',
  PRIMARY KEY  (`studentId`,`semester`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `notificationId` varchar(10) NOT NULL,
  `studentId` varchar(10) default NULL,
  `referenceId` varchar(10) default NULL,
  `notificationType` varchar(10) default NULL,
  `message` varchar(255) default NULL,
  PRIMARY KEY  (`notificationId`)
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

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
  `name` varchar(20) NOT NULL,
  `contactNumber` varchar(10) default NULL,
  `salary` float(10,0) default NULL,
  `department` varchar(20) NOT NULL,
  `doj` varchar(10) NOT NULL,
  PRIMARY KEY  (`professorId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `userId` varchar(10) NOT NULL,
  `role` varchar(15) NOT NULL,
  PRIMARY KEY  (`userId`),
  CONSTRAINT `role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
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
  `name` varchar(50) NOT NULL,
  `contactNumber` varchar(10) NOT NULL,
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
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `course` VALUES ('101', 'first', '1234', 'cse', 'P12858129', '0000000006');
INSERT INTO `course` VALUES ('102', 'second', '1234', 'cse', 'P12858129', '0000000003');
INSERT INTO `course` VALUES ('103', 'third', '1234', 'cse', 'P12858129', '0000000005');
INSERT INTO `course` VALUES ('104', 'fourth', '1234', 'cse', 'P12858129', '0000000002');
INSERT INTO `course` VALUES ('105', 'fifth', '1234', 'cse', 'P12858129', '0000000003');
INSERT INTO `course` VALUES ('106', 'sixth', '1234', 'cse', 'P12858129', '0000000004');
INSERT INTO `course` VALUES ('107', 'seventh', '1234', 'cse', 'P12858129', '0000000001');
INSERT INTO `course` VALUES ('109', 'demo', '2365', 'cce', 'P12858129', '0000000000');
INSERT INTO `grade` VALUES ('S12592479', '101', '10.00', '1');
INSERT INTO `grade` VALUES ('S12592479', '102', '9.00', '1');
INSERT INTO `grade` VALUES ('S12592479', '103', '3.00', '1');
INSERT INTO `grade` VALUES ('S12592479', '104', '9.00', '1');
INSERT INTO `grade` VALUES ('S19295094', '101', '8.00', '1');
INSERT INTO `grade` VALUES ('S19295094', '103', '5.00', '1');
INSERT INTO `grade` VALUES ('S19295094', '105', '9.00', '1');
INSERT INTO `grade` VALUES ('S19295094', '106', '10.00', '1');
INSERT INTO `gradecard` VALUES ('S12592479', '1', '8.00');
INSERT INTO `gradecard` VALUES ('S19295094', '1', '8.00');
INSERT INTO `optedcourses` VALUES ('S19295094', '1', '105', '17');
INSERT INTO `optedcourses` VALUES ('S19295094', '1', '103', '18');
INSERT INTO `optedcourses` VALUES ('S19295094', '1', '106', '19');
INSERT INTO `optedcourses` VALUES ('S19295094', '1', '101', '20');
INSERT INTO `optedcourses` VALUES ('S12592479', '1', '101', '21');
INSERT INTO `optedcourses` VALUES ('S12592479', '1', '102', '22');
INSERT INTO `optedcourses` VALUES ('S12592479', '1', '103', '23');
INSERT INTO `optedcourses` VALUES ('S12592479', '1', '104', '24');
INSERT INTO `payment` VALUES ('S109622548', '104140040', '4936', 'Card');
INSERT INTO `payment` VALUES ('S12592479', '106486519', '4936', 'Cash');
INSERT INTO `payment` VALUES ('S19295094', '163098942', '4936', 'Cheque');
INSERT INTO `professor` VALUES ('P12858129', 'prof2', '2344', '73468', 'fcsf', '24f');
INSERT INTO `role` VALUES ('A10201837', 'Admin');
INSERT INTO `role` VALUES ('P12858129', 'Professor');
INSERT INTO `role` VALUES ('S12592479', 'Student');
INSERT INTO `role` VALUES ('S19295094', 'Student');
INSERT INTO `semesterregistration` VALUES ('S12592479', '1', '1');
INSERT INTO `semesterregistration` VALUES ('S19295094', '1', '1');
INSERT INTO `student` VALUES ('S12592479', 'demo', '1234', 'cde', '234', '1', '1');
INSERT INTO `student` VALUES ('S19295094', 'shubham', '8786', 'cse', '2018', '1', '1');
INSERT INTO `user` VALUES ('A10201837', 'admin', 'admin');
INSERT INTO `user` VALUES ('P12858129', 'prof2', 'prof2');
INSERT INTO `user` VALUES ('S12592479', 'demo', 'demo');
INSERT INTO `user` VALUES ('S19295094', 'abcd', 'abcd');
