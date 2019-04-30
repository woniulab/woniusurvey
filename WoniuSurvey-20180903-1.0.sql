/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql5.6
Source Server Version : 50615
Source Host           : 127.0.0.1:3306
Source Database       : survey

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2018-09-04 16:40:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('1', '1', '非常满意', '0', '2018-09-04 16:05:25');
INSERT INTO `answer` VALUES ('2', '1', '满意', '1', '2018-09-04 16:05:29');
INSERT INTO `answer` VALUES ('3', '1', '不太满意', '0', '2018-09-04 16:05:33');
INSERT INTO `answer` VALUES ('4', '1', '很不满意', '0', '2018-09-04 16:05:36');
INSERT INTO `answer` VALUES ('5', '2', '非常愿意', '0', '2018-09-04 16:06:03');
INSERT INTO `answer` VALUES ('6', '2', '愿意', '0', '2018-09-04 16:06:06');
INSERT INTO `answer` VALUES ('7', '2', '不太愿意', '1', '2018-09-04 16:06:10');
INSERT INTO `answer` VALUES ('8', '2', '很不愿意', '0', '2018-09-04 16:06:19');
INSERT INTO `answer` VALUES ('9', '3', '硬件设施完善', '0', '2018-09-04 16:20:31');
INSERT INTO `answer` VALUES ('10', '4', '#textarea#', '1', '2018-09-04 16:20:46');
INSERT INTO `answer` VALUES ('11', '3', '老师认真负责', '1', '2018-09-04 16:32:59');
INSERT INTO `answer` VALUES ('12', '3', '就业薪水高', '1', '2018-09-04 16:33:11');
INSERT INTO `answer` VALUES ('13', '3', '学习资料完善', '0', '2018-09-04 16:33:35');
INSERT INTO `answer` VALUES ('14', '5', '非常满意', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('15', '5', '满意', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('16', '5', '不太满意', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('17', '5', '很不满意', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('18', '6', '非常愿意', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('19', '6', '愿意', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('20', '6', '不太愿意', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('21', '6', '很不愿意', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('22', '7', '硬件设施完善', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('23', '7', '老师认真负责', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('24', '7', '就业薪水高', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('25', '7', '学习资料完善', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('26', '8', '#textarea#', '0', '2018-09-04 16:37:35');
INSERT INTO `answer` VALUES ('27', '9', '非常满意', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('28', '9', '满意', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('29', '9', '不太满意', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('30', '9', '很不满意', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('31', '10', '非常愿意', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('32', '10', '愿意', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('33', '10', '不太愿意', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('34', '10', '很不愿意', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('35', '11', '硬件设施完善', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('36', '11', '老师认真负责', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('37', '11', '就业薪水高', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('38', '11', '学习资料完善', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('39', '12', '#textarea#', '0', '2018-09-04 16:37:45');
INSERT INTO `answer` VALUES ('40', '13', '非常满意', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('41', '13', '满意', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('42', '13', '不太满意', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('43', '13', '很不满意', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('44', '14', '非常愿意', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('45', '14', '愿意', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('46', '14', '不太愿意', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('47', '14', '很不愿意', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('48', '15', '硬件设施完善', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('49', '15', '老师认真负责', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('50', '15', '就业薪水高', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('51', '15', '学习资料完善', '0', '2018-09-04 16:37:53');
INSERT INTO `answer` VALUES ('52', '16', '#textarea#', '0', '2018-09-04 16:37:53');

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `qid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `source` varchar(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`qid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '1', '请问你对蜗牛学院的教学质量是否满意？', '单选题', '新增', '2018-09-04 16:05:19');
INSERT INTO `question` VALUES ('2', '1', '请问你是否愿意介绍朋友来蜗牛学院学习？', '单选题', '新增', '2018-09-04 16:05:55');
INSERT INTO `question` VALUES ('3', '1', '请问你最满意蜗牛学院的地方有哪些？', '多选题', '新增', '2018-09-04 16:06:44');
INSERT INTO `question` VALUES ('4', '1', '请在此输入你对蜗牛学院的各种建议和意见。', '简答题', '新增', '2018-09-04 16:20:46');
INSERT INTO `question` VALUES ('5', '2', '请问你对蜗牛学院的教学质量是否满意？', '单选题', '复制1', '2018-09-04 16:37:35');
INSERT INTO `question` VALUES ('6', '2', '请问你是否愿意介绍朋友来蜗牛学院学习？', '单选题', '复制1', '2018-09-04 16:37:35');
INSERT INTO `question` VALUES ('7', '2', '请问你最满意蜗牛学院的地方有哪些？', '多选题', '复制1', '2018-09-04 16:37:35');
INSERT INTO `question` VALUES ('8', '2', '请在此输入你对蜗牛学院的各种建议和意见。', '简答题', '复制1', '2018-09-04 16:37:35');
INSERT INTO `question` VALUES ('9', '3', '请问你对蜗牛学院的教学质量是否满意？', '单选题', '复制1', '2018-09-04 16:37:45');
INSERT INTO `question` VALUES ('10', '3', '请问你是否愿意介绍朋友来蜗牛学院学习？', '单选题', '复制1', '2018-09-04 16:37:45');
INSERT INTO `question` VALUES ('11', '3', '请问你最满意蜗牛学院的地方有哪些？', '多选题', '复制1', '2018-09-04 16:37:45');
INSERT INTO `question` VALUES ('12', '3', '请在此输入你对蜗牛学院的各种建议和意见。', '简答题', '复制1', '2018-09-04 16:37:45');
INSERT INTO `question` VALUES ('13', '4', '请问你对蜗牛学院的教学质量是否满意？', '单选题', '复制1', '2018-09-04 16:37:53');
INSERT INTO `question` VALUES ('14', '4', '请问你是否愿意介绍朋友来蜗牛学院学习？', '单选题', '复制1', '2018-09-04 16:37:53');
INSERT INTO `question` VALUES ('15', '4', '请问你最满意蜗牛学院的地方有哪些？', '多选题', '复制1', '2018-09-04 16:37:53');
INSERT INTO `question` VALUES ('16', '4', '请在此输入你对蜗牛学院的各种建议和意见。', '简答题', '复制1', '2018-09-04 16:37:53');

-- ----------------------------
-- Table structure for `result`
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  `answer` varchar(500) DEFAULT NULL,
  `sessionid` int(11) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of result
-- ----------------------------
INSERT INTO `result` VALUES ('1', '0', '0', 'reserved', '0', '0.0.0.0', '2018-09-03 16:28:52');
INSERT INTO `result` VALUES ('2', '4', '10', '没有意见，非常好。', '1', '192.168.2.29', '2018-09-04 16:33:48');
INSERT INTO `result` VALUES ('3', '3', '11', '1', '1', '192.168.2.29', '2018-09-04 16:33:48');
INSERT INTO `result` VALUES ('4', '3', '12', '1', '1', '192.168.2.29', '2018-09-04 16:33:48');
INSERT INTO `result` VALUES ('5', '1', '2', '1', '1', '192.168.2.29', '2018-09-04 16:33:48');
INSERT INTO `result` VALUES ('6', '2', '7', '1', '1', '192.168.2.29', '2018-09-04 16:33:48');

-- ----------------------------
-- Table structure for `survey`
-- ----------------------------
DROP TABLE IF EXISTS `survey`;
CREATE TABLE `survey` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `branch` varchar(20) DEFAULT NULL,
  `classid` varchar(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of survey
-- ----------------------------
INSERT INTO `survey` VALUES ('1', '成都', '36期班', '0', '2018-09-04 16:04:42');
INSERT INTO `survey` VALUES ('2', '重庆', '10期开发班', '0', '2018-09-04 16:37:35');
INSERT INTO `survey` VALUES ('3', '西安', '4期班', '0', '2018-09-04 16:37:45');
INSERT INTO `survey` VALUES ('4', '上海', '1期班', '0', '2018-09-04 16:37:53');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', 'admin');
INSERT INTO `user` VALUES ('2', 'chengdu', '123456', 'master');
INSERT INTO `user` VALUES ('3', 'chongqing', '123456', 'master');
INSERT INTO `user` VALUES ('4', 'xian', '123456', 'master');
INSERT INTO `user` VALUES ('5', 'shanghai', '123456', 'master');
