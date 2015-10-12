/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.51-community : Database - sp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `sm_sprots_evaluate` */

DROP TABLE IF EXISTS `sm_sprots_evaluate`;

CREATE TABLE `sm_sprots_evaluate` (
  `sm_sports_id` int(11) DEFAULT NULL COMMENT '比赛记录编号',
  `sp_user_id` int(11) DEFAULT NULL COMMENT '会员编号',
  `evaluate` int(11) DEFAULT NULL COMMENT '评价分数',
  `evaluate_content` varchar(200) DEFAULT NULL COMMENT '评价内容',
  `jifen` int(11) DEFAULT NULL COMMENT '贡献积分（会员赠送积分）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='比赛活动评价表';

/*Table structure for table `sp_admin` */

DROP TABLE IF EXISTS `sp_admin`;

CREATE TABLE `sp_admin` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT 'email',
  `memo` varchar(2000) DEFAULT NULL,
  `state` int(2) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='保存系统用户信息';

/*Table structure for table `sp_advertiser` */

DROP TABLE IF EXISTS `sp_advertiser`;

CREATE TABLE `sp_advertiser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `pic` varchar(200) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='广告商';

/*Table structure for table `sp_areas` */

DROP TABLE IF EXISTS `sp_areas`;

CREATE TABLE `sp_areas` (
  `id` int(6) DEFAULT NULL COMMENT '主键',
  `name` varchar(45) DEFAULT NULL COMMENT '名称',
  `parentid` int(6) DEFAULT NULL COMMENT '父节点id',
  `shortname` varchar(24) DEFAULT NULL COMMENT '简称',
  `leveltype` int(1) DEFAULT NULL COMMENT '行政区域级别',
  `citycode` int(5) DEFAULT NULL COMMENT '区号',
  `zipcode` int(6) DEFAULT NULL COMMENT '邮编',
  `mergername` varchar(84) DEFAULT NULL COMMENT '全称',
  `lng` decimal(10,7) DEFAULT NULL COMMENT '经度',
  `lat` decimal(8,6) DEFAULT NULL COMMENT '纬度',
  `pinyin` varchar(28) DEFAULT NULL COMMENT '拼音'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sp_insurance` */

DROP TABLE IF EXISTS `sp_insurance`;

CREATE TABLE `sp_insurance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `pic` varchar(200) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='运动保险表';

/*Table structure for table `sp_judges_coachs` */

DROP TABLE IF EXISTS `sp_judges_coachs`;

CREATE TABLE `sp_judges_coachs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jc_name` varchar(20) DEFAULT NULL,
  `jc_gender` varchar(2) DEFAULT NULL COMMENT '男” or “女”',
  `jc_age` int(11) DEFAULT NULL,
  `jc_phone` varchar(20) DEFAULT NULL,
  `jc_evaluate` int(11) DEFAULT NULL COMMENT '评价分数 满分5分',
  `jc_jifen` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0双重身份1裁判2教练',
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `district` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `resume` varchar(200) DEFAULT NULL COMMENT '简介',
  `sp_user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='裁判及教练表';

/*Table structure for table `sp_judges_coachs_evaluate` */

DROP TABLE IF EXISTS `sp_judges_coachs_evaluate`;

CREATE TABLE `sp_judges_coachs_evaluate` (
  `sp_judges_coachs_id` int(11) DEFAULT NULL COMMENT '教练或裁判编号',
  `sp_user_id` int(11) DEFAULT NULL COMMENT '会员编号',
  `evaluate` int(11) DEFAULT NULL COMMENT '评价分数',
  `evaluate_content` varchar(200) DEFAULT NULL COMMENT '评价内容',
  `jifen` int(11) DEFAULT NULL COMMENT '贡献积分 会员赠送给教练或裁判'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='裁判或教练评价表';

/*Table structure for table `sp_judges_coachs_sports_category` */

DROP TABLE IF EXISTS `sp_judges_coachs_sports_category`;

CREATE TABLE `sp_judges_coachs_sports_category` (
  `sp_judges_coachs_id` int(11) DEFAULT NULL COMMENT '教练或裁判编号',
  `sp_sports_category_id` int(11) DEFAULT NULL COMMENT '运动类别编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='裁判及教练运动项目表';

/*Table structure for table `sp_organization` */

DROP TABLE IF EXISTS `sp_organization`;

CREATE TABLE `sp_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `org_name` varchar(100) DEFAULT NULL COMMENT '名称',
  `org_password` varchar(20) DEFAULT NULL,
  `org_persons` int(11) DEFAULT NULL COMMENT '人数',
  `org_contacts` varchar(20) DEFAULT NULL COMMENT '联系人',
  `org_phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `org_jifen` int(11) DEFAULT NULL COMMENT '积分',
  `province` varchar(20) DEFAULT NULL COMMENT '省份',
  `city` varchar(20) DEFAULT NULL COMMENT '地市',
  `district` varchar(20) DEFAULT NULL COMMENT '县区',
  `status` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='协会及运动组织表';

/*Table structure for table `sp_organization_sports_category` */

DROP TABLE IF EXISTS `sp_organization_sports_category`;

CREATE TABLE `sp_organization_sports_category` (
  `sp_organization_id` int(11) DEFAULT NULL COMMENT '协会及运动组织编号',
  `sp_sports_category_id` int(11) DEFAULT NULL COMMENT '运动类别编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='协会及运动组织运动项目表';

/*Table structure for table `sp_people` */

DROP TABLE IF EXISTS `sp_people`;

CREATE TABLE `sp_people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pp_phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `status` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='群众表';

/*Table structure for table `sp_place` */

DROP TABLE IF EXISTS `sp_place`;

CREATE TABLE `sp_place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `pic` varchar(200) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `openTime` varchar(20) DEFAULT NULL,
  `closeTime` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `price` varchar(50) DEFAULT NULL,
  `sportsCategory` int(11) DEFAULT NULL COMMENT '场地类型',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `status` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='场地表';

/*Table structure for table `sp_sports` */

DROP TABLE IF EXISTS `sp_sports`;

CREATE TABLE `sp_sports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sp_name` varchar(100) DEFAULT NULL,
  `sp_picture` varchar(100) DEFAULT NULL,
  `sp_content` varchar(200) DEFAULT NULL COMMENT '活动详情',
  `sp_start_time` varchar(20) DEFAULT NULL COMMENT '活动开始时间',
  `sp_end_time` varchar(20) DEFAULT NULL COMMENT '活动结束时间',
  `sp_join_end_time` varchar(20) DEFAULT NULL COMMENT '报名截止时间',
  `sp_persons` int(11) DEFAULT NULL COMMENT '参加人数',
  `sp_cost` varchar(50) DEFAULT NULL COMMENT '活动费用 0代表免费',
  `sp_address` varchar(100) DEFAULT NULL COMMENT '活动地址',
  `sp_publisher` int(11) DEFAULT NULL COMMENT '发布者',
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `district` varchar(20) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='活动表';

/*Table structure for table `sp_sports_category` */

DROP TABLE IF EXISTS `sp_sports_category`;

CREATE TABLE `sp_sports_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sc_code` varchar(32) DEFAULT NULL COMMENT '类别代码',
  `sc_name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `sp_sports_match` */

DROP TABLE IF EXISTS `sp_sports_match`;

CREATE TABLE `sp_sports_match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sm_name` varchar(100) DEFAULT NULL,
  `sm_picture` varchar(100) DEFAULT NULL,
  `sm_content` varchar(200) DEFAULT NULL COMMENT '活动详情',
  `sm_start_time` varchar(20) DEFAULT NULL COMMENT '活动开始时间',
  `sm_end_time` varchar(20) DEFAULT NULL COMMENT '活动结束时间',
  `sm_join_end_time` varchar(20) DEFAULT NULL COMMENT '报名截止时间',
  `sm_persons` int(11) DEFAULT NULL COMMENT '参加人数',
  `sm_cost` varchar(50) DEFAULT NULL COMMENT '活动费用 0代表免费',
  `sm_site` int(11) DEFAULT NULL COMMENT '比赛场地',
  `sm_insurance` int(11) DEFAULT NULL COMMENT '比赛保险',
  `sm_address` varchar(100) DEFAULT NULL COMMENT '活动地址',
  `sm_publisher` int(11) DEFAULT NULL COMMENT '发布者',
  `sm_type` int(11) DEFAULT NULL COMMENT '类型 1比赛2活动',
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `district` varchar(20) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='比赛或活动表';

/*Table structure for table `sp_sprots_evaluate` */

DROP TABLE IF EXISTS `sp_sprots_evaluate`;

CREATE TABLE `sp_sprots_evaluate` (
  `sp_sports_id` int(11) DEFAULT NULL COMMENT '活动编号',
  `sp_user_id` int(11) DEFAULT NULL COMMENT '会员编号',
  `evaluate` int(11) DEFAULT NULL COMMENT '评分',
  `evaluate_content` varchar(200) DEFAULT NULL COMMENT '评价内容',
  `jifen` int(11) DEFAULT NULL COMMENT '贡献积分 会员赠送给活动发起者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='组织活动评价表';

/*Table structure for table `sp_sprots_judges` */

DROP TABLE IF EXISTS `sp_sprots_judges`;

CREATE TABLE `sp_sprots_judges` (
  `sp_sports_match_id` int(11) DEFAULT NULL COMMENT '比赛编号',
  `sp_judge_id` int(11) DEFAULT NULL COMMENT '裁判编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='比赛裁判表';

/*Table structure for table `sp_sprots_match_users` */

DROP TABLE IF EXISTS `sp_sprots_match_users`;

CREATE TABLE `sp_sprots_match_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sp_sports_match_id` int(11) DEFAULT NULL COMMENT '比赛编号',
  `sp_user_id` int(11) DEFAULT NULL COMMENT '会员编号',
  `status` int(11) DEFAULT NULL COMMENT '0未通过1通过',
  `remark` varchar(200) DEFAULT NULL COMMENT '未通过原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='比赛或活动人员申请表';

/*Table structure for table `sp_sprots_users` */

DROP TABLE IF EXISTS `sp_sprots_users`;

CREATE TABLE `sp_sprots_users` (
  `sp_sports_id` int(11) DEFAULT NULL COMMENT '活动编号',
  `sp_user_id` int(11) DEFAULT NULL COMMENT '会员编号',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '参加人员电话',
  `user_name` varchar(20) DEFAULT NULL COMMENT '参加人员名称',
  `status` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL COMMENT '0未通过，1，通过'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='活动人员申请表';

/*Table structure for table `sp_user` */

DROP TABLE IF EXISTS `sp_user`;

CREATE TABLE `sp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `us_name` varchar(20) DEFAULT NULL COMMENT '会员名称',
  `us_nickname` varchar(40) DEFAULT NULL COMMENT '会员昵称',
  `us_picture` varchar(100) DEFAULT NULL COMMENT '会员头像',
  `us_phone` varchar(20) DEFAULT NULL COMMENT '会员手机号（登录名）',
  `us_password` varchar(32) DEFAULT NULL COMMENT '密码',
  `us_age` int(11) DEFAULT NULL COMMENT '年龄',
  `us_gender` varchar(2) DEFAULT NULL COMMENT '性别 男” or “女”',
  `us_email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `us_qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `us_weixin` varchar(50) DEFAULT NULL COMMENT '微信',
  `us_interest` varchar(100) DEFAULT NULL COMMENT '兴趣爱好',
  `us_introduction` varchar(200) DEFAULT NULL COMMENT '自我介绍',
  `province` varchar(20) DEFAULT NULL COMMENT '省份',
  `city` varchar(20) DEFAULT NULL COMMENT '地市',
  `district` varchar(20) DEFAULT NULL COMMENT '县区',
  `us_job` int(11) DEFAULT NULL COMMENT '职业 1学生2已就业3未就业4退休5其他',
  `us_jifen` int(11) DEFAULT NULL COMMENT '积分',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `jcid` int(11) DEFAULT NULL COMMENT '教练裁判编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `sp_user_sports_category` */

DROP TABLE IF EXISTS `sp_user_sports_category`;

CREATE TABLE `sp_user_sports_category` (
  `sp_user_id` int(11) DEFAULT NULL COMMENT '会员编号',
  `sp_sports_category_id` int(11) DEFAULT NULL COMMENT '运动项目类别编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
