package com.chechemotel.front.log;

import java.util.Map;

public interface Log {

	void insertUrlLog(Map<String, Object> obj);
	

	void updateOrderLogUrl(String orderId,String channel,String channelOrderId);


	void insertPayLog(Map<String, Object> obj);


	void insertUserPayLogMap(Map<String,Object> args, Integer type);

	void insertOrderCodeLogMap(Map<String,Object> args, Integer type);

	void updateOrderCodeLog(Map<String,Object> args, final String column, final Object columnValue);
	
	/**
	 * 保存渠道日志
	 * @param obj
	 * @return
	 */
	int insertChannelLog(Map<String, Object> obj);

	void updateOrderLogUrlMap(Map<String,Object> obj);

	
}
