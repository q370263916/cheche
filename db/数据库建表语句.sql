/*商户类别表*/
CREATE TABLE `ccm_shop_type` (                                           
              `id` varchar(32) NOT NULL COMMENT '主键ID',                         
              `type_code` varchar(5) NOT NULL  DEFAULT '' COMMENT  '类别编码',           
              `type_name` varchar(10) NOT NULL DEFAULT '' COMMENT '类别名称',  
              `create_time`  datetime  COMMENT '创建时间', 
              `update_time`  datetime  COMMENT '修改时间',  
              PRIMARY KEY (`id`)                                                  
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

/*商户信息表*/
CREATE TABLE `ccm_shop_info` (                                           
               `id` varchar(32) NOT NULL COMMENT '主键ID',                         
              `shop_code` varchar(6) NOT NULL DEFAULT '' COMMENT  '公司编码',           
              `shop_name` varchar(30) NOT NULL DEFAULT '' COMMENT '公司名称',  
              `shop_address` varchar(120) DEFAULT '' COMMENT '详细地址',
              `create_time`  datetime  COMMENT '创建时间', 
              `update_time`  datetime  COMMENT '修改时间',  
               `bussiness_licence` varchar(40) DEFAULT '' COMMENT '营业执照编号',
				`tax_registration_certificate` varchar(40) DEFAULT '' COMMENT '税务登记证号',
				`name` varchar(10) DEFAULT '' COMMENT '联系人姓名',
				`tel` varchar(16) DEFAULT '' COMMENT '联系人电话',
				`content` varchar(120) DEFAULT '' COMMENT '备注',
				`shop_type` varchar(5) DEFAULT '' COMMENT '类别编码',
				`money`  double(20,2) DEFAULT 0 COMMENT '商户余额',
				`bussiness_licence_pic` varchar(128) DEFAULT '' COMMENT '营业执照图片字段',
                PRIMARY KEY (`id`,`shop_code`)                                                  
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

/*商户用户表*/
CREATE TABLE `ccm_shop_role` (                                           
               `id` varchar(32) NOT NULL COMMENT '主键ID',                         
               `shop_code` varchar(6) NOT NULL DEFAULT '' COMMENT  '公司编码',           
               `shop_name` varchar(30) NOT NULL DEFAULT '' COMMENT '公司名称',  
               `username` varchar(32) NOT NULL DEFAULT '' COMMENT '第三方用户名',
			   `password` varchar(32) NOT NULL DEFAULT '' COMMENT '第三方用户密码',
			   `realname` varchar(30) NOT NULL DEFAULT '' COMMENT '真实姓名',
			   `tel` varchar(16) DEFAULT '' COMMENT '电话号',
			   `flag` smallint(1) NOT NULL COMMENT '状态', /*0 有效  1 无效*/
			   `type` smallint(1) NOT NULL COMMENT '状态',/*0 主管   1 员工*/
              `create_time`  datetime  COMMENT '创建时间', 
              `update_time`  datetime  COMMENT '修改时间',  
              `shop_type` varchar(5) DEFAULT '' COMMENT '类别编码',
              PRIMARY KEY (`id`)                                                  
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

/*商品类别表*/
CREATE TABLE `ccm_sp_type` (                                           
               `id` varchar(32) NOT NULL COMMENT '主键ID',                         
               `sp_type_name` varchar(10) NOT NULL DEFAULT '' COMMENT '商品类别名称',
			   `sp_type_code` varchar(10) NOT NULL DEFAULT '' COMMENT '商品类别编码',
			   `sp_type_level` int(1) NOT NULL COMMENT '商品类别级别',
              `sp_type_parent` int(1) DEFAULT '' COMMENT '上级类别代码',
              `create_time` datetime  COMMENT '创建时间',
              `update_time` datetime  COMMENT '修改时间',
               `mark` varchar(10) DEFAULT '' COMMENT '更改标志位',
              PRIMARY KEY (`id`)                                                  
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8
;


/*商品管理表*/
CREATE TABLE `ccm_sp` (                                           
               `id` varchar(32) NOT NULL COMMENT '主键ID',                         
               `sp_code` varchar(20) NOT NULL DEFAULT '' COMMENT '商品编码',
				`sp_name` varchar(30) NOT NULL DEFAULT '' COMMENT '商品名称',
				`sp_type_code` varchar(20) NOT NULL DEFAULT '' COMMENT '商品类别id',
				`zhekou` varchar(5) DEFAULT '' COMMENT '折扣',
				`ku_cun` int(11) NOT NULL COMMENT '库存数量',
				`price` varchar(20) NOT NULL DEFAULT '' COMMENT '价格',
				`shop_code` varchar(10) DEFAULT ''  COMMENT  '公司编码',
				`create_time` datetime  COMMENT '创建时间',
				`update_time` datetime  COMMENT '修改时间',
				`sp_img` varchar(100) DEFAULT '' COMMENT '商品的图标',
				`jifen` varchar(10) DEFAULT '' COMMENT '消费后的积分',
				`redbag_val` varchar(10) DEFAULT '' COMMENT '红包分值',
				`extend1` varchar(64) DEFAULT '' COMMENT '扩展字段1',
				`extend2` varchar(64) DEFAULT '' COMMENT '扩展字段2',
				`extend3` varchar(64) DEFAULT '' COMMENT '扩展字段3',
              PRIMARY KEY (`id`)                                                  
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

/*订单表*/
CREATE TABLE `ccm_order` (                                           
               `id` varchar(32) NOT NULL COMMENT '主键ID',                         
               `order_code` varchar(40) NOT NULL DEFAULT '' COMMENT '订单号',
				`order_time` datetime  COMMENT '下单时间',
				`sp_type_code` varchar(20) NOT NULL DEFAULT '' COMMENT '商品类别',
				`sp_code` varchar(100) NOT NULL DEFAULT '' COMMENT '订单包含商品编码',
				`so_name` varchar(300)NOT NULL DEFAULT '' COMMENT '订单包含商品名称',
				`dd_zj` double(8,2) DEFAULT 0 COMMENT '订单总价',
				`status` varchar(1) DEFAULT ''  COMMENT '状态',
				`update_time` datetime  COMMENT '修改时间',
				`shop_code` varchar(10) DEFAULT ''  COMMENT  '商户code',
				`renew_time` varchar(200) DEFAULT ''  COMMENT '更新时间记录',
				`extend1` varchar(64) DEFAULT ''  COMMENT '扩展字段1',
				`extend2` varchar(64)  DEFAULT ''  COMMENT '扩展字段2',
				`extend3` varchar(64)  DEFAULT ''  COMMENT '扩展字段3',
				`current_time_stamp` varchar(32) DEFAULT ''  COMMENT '录入时间戳',
              PRIMARY KEY (`id`)                                                  
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

/*商户流水表*/
CREATE TABLE `ccm_order_stream` (                                           
               `id` varchar(32) NOT NULL COMMENT '主键ID',                         
                `order_code` varchar(40) NOT NULL DEFAULT '' COMMENT '订单号',
				`order_time` datetime  COMMENT '下单时间',
				`sp_type_code` varchar(20) NOT NULL DEFAULT '' COMMENT '商品类别',
				`sp_code` varchar(20) NOT NULL DEFAULT '' COMMENT '商品编码',
				`so_name` varchar(20) NOT NULL DEFAULT '' COMMENT '商品名称',
				`dd_zj` double(8,2) DEFAULT 0 COMMENT '订单总价',
				`status` varchar(1) NOT NULL DEFAULT '' COMMENT '状态',
				`update_time` datetime  COMMENT '修改时间',
				`shop_code` varchar(10) DEFAULT ''  COMMENT  '商户code',
				 `current_time_stamp` varchar(32) DEFAULT '' COMMENT '录入时间戳',
              PRIMARY KEY (`id`)                                                  
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

/*用户基本信息表*/
CREATE TABLE `ccm_user` (                                           
                `id` varchar(32) NOT NULL COMMENT '主键ID',                         
                `user_code` varchar(36) NOT NULL DEFAULT '' COMMENT '会员编码',
				`account_name` varchar(50) NOT NULL DEFAULT '' COMMENT '帐户名',
				`phone` varchar(16)  DEFAULT '' COMMENT '手机号',
				`real_name` varchar(40) DEFAULT ''  COMMENT '真实姓名',
				`money` double(20,2) DEFAULT 0 COMMENT '账户余额',
				`qr_code` varchar(120) DEFAULT ''  COMMENT '用户二维码路径',
				`create_time` datetime COMMENT '创建时间',
				`update_time` datetime  COMMENT '修改时间',
				`card` varchar(20) DEFAULT ''  COMMENT '身份证号',
				`jifen` int(11) DEFAULT 0  COMMENT '积分',
				`zje` double(20,5) DEFAULT 0  COMMENT '消费总额',
				`sex` varchar(1) DEFAULT ''  COMMENT '性别',
				`head_img` varchar(100) DEFAULT ''  COMMENT '头像路径',
				`red_beg_zje` double(20,5) DEFAULT 0 COMMENT '红包总额',
				`can_tx_zje` double(20,5) DEFAULT 0 COMMENT '可提现红包总额',
				`reg_bag_count` int(11) DEFAULT 0  COMMENT '红包总数',
				`status` varchar(1) DEFAULT ''  COMMENT '会员状态',
				`tjm` varchar(10) DEFAULT ''  COMMENT '推荐码',
              PRIMARY KEY (`id`)                                                  
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

/*会员充值记录*/
CREATE TABLE `ccm_user_recharge` (                                           
                `id` varchar(32) NOT NULL COMMENT '主键ID',                         
                `user_code` varchar(36) NOT NULL DEFAULT '' COMMENT '会员编码',
				`in_money_time` datetime  COMMENT '资金收入时间',
				`in_money` double(20,5) DEFAULT 0 COMMENT '收入金额',
				`in_money_type` varchar(1) DEFAULT '' COMMENT '充值支付方式',
				`in_bank_ordernum` varchar(40) DEFAULT '' COMMENT '充值凭证号',
				`update_time` datetime  COMMENT '修改时间',
              PRIMARY KEY (`id`)                                                  
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

alter TABLE ccm_sp_type add COLUMN shop_code varchar(10) DEFAULT ''  COMMENT '商户编码';
alter TABLE ccm_sp_type add COLUMN shop_name varchar(30) DEFAULT ''  COMMENT '商户名称';
alter TABLE ccm_sp add COLUMN shop_name varchar(30) DEFAULT ''  COMMENT '商户名称';



