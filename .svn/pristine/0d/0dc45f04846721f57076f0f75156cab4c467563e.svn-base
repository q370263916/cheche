package com.chechemotel.logs.service;




import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chechemotel.logs.common.MongoPageBean;



public interface MongoLogServiceI {

	/**
	 * 保存请求地址日志
	 * @param obj
	 * @return
	 */
	int insertUrlLog(Map<String, Object> obj) ;
	
	/**
	 * 保存类方法调用日志
	 * @param obj
	 * @return
	 */
	int insertClassMethodLog(Map<String, Object> obj);
	
	/**
	 * 保存渠道日志
	 * @param obj
	 * @return
	 */
	int insertChannelLog(Map<String, Object> obj);

	/**
	 * 保存订单日志
	 * @param obj
	 * @return
	 */
	int insertOrderCodeLog(Map<String, Object> obj);
	
	/**
	 * 用户消费流水表
	 * @param obj
	 * @return
	 */
	int insertUserPayLog(Map<String, Object> obj);

	/**
	 * 下订单请求日志表
	 * @param obj
	 * @return
	 */
	int insertOrderLogUrl(Map<String, Object> obj);

	/**
	 * 下订单请求日志表--更新操作
	 * @param id ： 主键
	 * @param obj
	 * @return
	 */
	int updateOrderLogUrl(Object id, Map<String, Object> obj);
	
	/**
	 * 下订单日志交易明细表
	 * @param obj
	 * @return
	 */
	int insertOrderMoneyLog(Map<String, Object> obj);
	
	/**
	 * 根据请求id查询--类方法调用日志列表
	 * @param time：时间 
	 * @param urlId
	 * @return
	 */
	List<Map<String, Object>> findClassMethodLog(String time, String urlId);

	/**
	 * 分页查询结果列表数据
	 * @param time
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	MongoPageBean<Map<String, Object>> findPageUrlLog(String time, Map<String, Object> vlaues, int startPage, int pageSize);

	/**
	 * 查询一个订单请求日志
	 * @param time
	 * @param id
	 * @return
	 */
	Map<String, Object> findOrderLog(String time, Object id);
	
	/**
	 * 查询一个渠道充值记录日志
	 * @param time
	 * @param id
	 * @return
	 */
	Map<String, Object> findpayChannelPayLog(String time, String id);
	
	/**
	 * 分页查询订单日志
	 * @param time
	 * @param vlaues
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	MongoPageBean<Map<String, Object>> findPageOrderLog(String time, Map<String, Object> vlaues, int startPage, int pageSize);
	
	/**
	 * 分页查询订单交易日志
	 * @param time
	 * @param vlaues
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	MongoPageBean<Map<String, Object>> findPageOrderMoneyLog(String time, Map<String, Object> vlaues, int startPage, int pageSize);
	
	/**
	 * 查询订单详情日志
	 * @param time
	 * @param vlaues
	 * @return
	 */
	List<Map<String, Object>> findOrderDetailLog(String time, Map<String, Object> vlaues);

	/**
	 * 保存更新订单日志表
	 * @param id
	 * @param obj
	 * @return
	 */
	int updateOrderCodeLog(String column,Object columnValue, Map<String, Object> obj);

	/**
	 * 分页查询渠道日志
	 * @param time
	 * @param vlaues
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	MongoPageBean<Map<String, Object>> findPageChannelLog(String time, Map<String, Object> vlaues, int startPage, int pageSize);
	
	/**
	 * 查询一条渠道日志
	 * @param time
	 * @param id
	 * @return
	 */
	Map<String, Object> findChannelLog(String time, Object id);

	/**
	 * 分页查询订单业务日志表
	 * @param time
	 * @param vlaues
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> findPageOrderCodeLog(String time, Map<String, Object> vlaues, int startPage, int pageSize);
	
	int findPageOrderCodeLogCount(String time, Map<String, Object> vlaues, int startPage, int pageSize);
	
	/**
	 * 查询一条订单业务日志表
	 * @param time
	 * @param id
	 * @return
	 */
	Map<String, Object> findOrderCodeLog(String time, Object id);

	/**
	 * 查询订单交易日志
	 * @param time
	 * @param vlaues
	 * @return
	 */
	List<Map<String, Object>> findOrderMoneyLog(String time, Map<String, Object> vlaues);

	/**
	 * 充值渠道充值记录表
	 * @param obj
	 * @return
	 */
	int insertPayChannelPayLog(Map<String, Object> obj);

	/**
	 * 根据开始时间和结束时间查询充值渠道充值记录
	 * @param vlaues
	 * @param sumName
	 * @param sumAlias
	 * @return
	 */
	List<Map<String, Object>> findPayCHannelPayLog(Map<String, Object> vlaues,String sumName);


	/**
	 * 根据时间段查询--多条件
	 * @param collectionName
	 * @param vlaues
	 * @param sortVlaues
	 * @param sumName
	 * @param sumAlias
	 * @return
	 */
	List<Map<String, Object>> findPayCHannelPayStatisLog(Map<String, Object> vlaues,String sumName);

	/**
	 * 分组统计--订单业务日志表
	 * @param time
	 * @param begin ：开始时间
	 * @param end ： 结束时间
	 * @param channelSucces ： 充值渠道成功 （true:成功， false:失败）
	 * @param channel : 下单渠道
	 * @param payChannel : 充值渠道
	 * @return
	 */
	List<Map<String, Object>> findOrderCodeLogGroupBy(String time, Date begin, Date end, Boolean channelSucces, String[] channel, String[] payChannel);

	/**
	 * 分组统计每个渠道的累计总数
	 * @param start : 开始时间
	 * @param end ：结束时间
	 * @return
	 */
	List<Map<String, Object>> findPayChannelGroupBySum(Integer start, Integer end, String... payChannel);

	/**
	 * 更新充值渠道充值记录
	 * @param column
	 * @param columnValue
	 * @param obj
	 * @return
	 */
	int updatePayChannelPayLog(String column, Object columnValue,Map<String, Object> obj);

	/**
	 * 根据条件查询数据
	 * @param time
	 * @param vlaues
	 * @return
	 */
	List<Map<String, Object>> findPayChannelPayLogExist(String time, Map<String, Object> vlaues);

	/**
	 * 查询折扣比例数据
	 * @param payChannel
	 * @return
	 */
	Map<String, Object> findOnePayChannelZheKou(String payChannel);
	Map<String, Object> findOrderCodeLogByOrder(String time, Object orderId);

	List<Map<String, Object>> findOrderUrlLogByReqId(String time, Object reqId);
	Map<String, Object> findOrderUrlLogByCode(String time, Object reqId,Object code);
	
	List<Map<String, Object>> findPageUserOrderLog(String time, Map<String, Object> vlaues, int startPage, int pageSize);
	int findPageUserOrderLogCount(String time, Map<String, Object> vlaues, int startPage, int pageSize);
}