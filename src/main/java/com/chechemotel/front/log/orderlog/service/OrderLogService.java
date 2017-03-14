package com.chechemotel.front.log.orderlog.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.chechemotel.front.log.Log;
import com.chechemotel.logs.service.MongoLogServiceI;
import com.chechemotel.logs.utils.UrlLogUtil;
import com.chechemotel.util.DateUtil;


@Service("orderLogService")
public class OrderLogService implements Log {

	@Autowired
	private MongoLogServiceI mongoLogService;
	/**
	 * 线程池
	 */
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	/**
	 * 插入请求信息
	 */
	@Override
	public void insertUrlLog(Map<String, Object> obj) {
		final Map<String, Object> urlMap = new HashMap<String, Object>();
		urlMap.put("reqUUID", (UUID.randomUUID() + "").replaceAll("-", ""));
		urlMap.put("tomcatId", obj.get("tomcatId"));
		urlMap.put("appcontextId", obj.get("appcontextId"));
		urlMap.put("threadId", obj.get("threadId"));
		urlMap.put("remoteAddr", obj.get("remoteAddr"));
		urlMap.put("reqUrl", obj.get("reqUrl"));
		urlMap.put("channel", obj.get("channel"));
		//urlMap.put("businessType", ConstantClass.BUSINESS_ORDER_TYPE);//订单类型
		urlMap.put("tm", System.currentTimeMillis());
		urlMap.put("pid", obj.get("pid"));
		if (0 != mongoLogService.insertOrderLogUrl(urlMap)) {
			UrlLogUtil.urlLog.set(urlMap.get("_id"));
		}
	}
	/**
	 * 充值请求信息
	 * @param obj
	 */
	@Override
	public void insertPayLog(Map<String, Object> obj) {
		final Map<String, Object> urlMap = new HashMap<String, Object>();
		urlMap.put("reqUUID", (UUID.randomUUID() + "").replaceAll("-", ""));
		urlMap.put("tomcatId", obj.get("tomcatId"));
		urlMap.put("appcontextId", obj.get("appcontextId"));
		urlMap.put("threadId", obj.get("threadId"));
		urlMap.put("remoteAddr", obj.get("remoteAddr"));
		urlMap.put("reqUrl", obj.get("reqUrl"));
		//urlMap.put("businessType", ConstantClass.BUSINESS_PAY_TYPE);//充值类型
		urlMap.put("tm", System.currentTimeMillis());
		urlMap.put("pid", obj.get("pid"));
		if (0 != mongoLogService.insertOrderLogUrl(urlMap)) {
			UrlLogUtil.urlLog.set(urlMap.get("_id"));
		}
	}
	/**
	 * 插入充值记录
	 */
	
	@Override
	public void insertOrderCodeLogMap(Map<String,Object> args,Integer type){
		final Map<String,Object> map = new HashMap<String,Object>();
		map.put("reqId", UrlLogUtil.urlLog.get().toString());
		map.put("type", type);
		//map.put("businessType", ConstantClass.BUSINESS_PAY_TYPE);//充值类型
		map.put("tm", System.currentTimeMillis());
		map.put("day_tm", DateUtil.getNowDateInt());
		map.putAll(args);
		taskExecutor.execute(new Runnable() {
			public void run() {
				mongoLogService.insertOrderCodeLog(map);
			}
		});
	}
	
	/**
	 * 更新充值日志
	 */
	@Override
	public void updateOrderCodeLog(Map<String,Object> args,final String column, final Object columnValue) {
		final Map<String,Object> map = new HashMap<String,Object>();
		//map.put("businessType", ConstantClass.BUSINESS_PAY_TYPE);//充值类型
		map.putAll(args);
		taskExecutor.execute(new Runnable() {
			public void run() {
				mongoLogService.updateOrderCodeLog(column,columnValue, map);
			}
		});
		
	}
	/**
	 * 充值详情日志Map
	 */
	@Override
	public void insertUserPayLogMap(Map<String,Object> args,Integer type) {
		final Map<String,Object> map = new HashMap<String,Object>();
		map.put("reqId", UrlLogUtil.urlLog.get().toString());
		map.put("type", type);
		//map.put("businessType", ConstantClass.BUSINESS_PAY_TYPE);//充值类型
		map.put("tm", System.currentTimeMillis());
		map.putAll(args);
		taskExecutor.execute(new Runnable() {
			public void run() {
				mongoLogService.insertUserPayLog(map);
			}
		});
		
	}
	/**
	 * 订单日志更新
	 */
	@Override
	public void updateOrderLogUrl(String orderId, String channel,String channelOrderId) {
		final Map<String,Object> map = new HashMap<String,Object>();
		map.put("channel", channel);
		map.put("orderId", orderId);
		//map.put("businessType", ConstantClass.BUSINESS_ORDER_TYPE);//充值类型
		map.put("uptm", System.currentTimeMillis());
		map.put("channelOrderId", channelOrderId);
		final String id = UrlLogUtil.urlLog.get().toString();
		taskExecutor.execute(new Runnable() {
			public void run() {
				mongoLogService.updateOrderLogUrl(new ObjectId(id), map);
			}
		});
	}
	
	@Override
	public void updateOrderLogUrlMap(Map<String,Object> obj) {
		final Map<String,Object> map = new HashMap<String,Object>();
		map.putAll(obj);
		final String id = UrlLogUtil.urlLog.get().toString();
		taskExecutor.execute(new Runnable() {
			public void run() {
				mongoLogService.updateOrderLogUrl(new ObjectId(id), map);
			}
		});
	}
	
	@Override
	public int insertChannelLog(Map<String, Object> obj) {
		final Map<String,Object> map = new HashMap<String,Object>();
		map.put("reqId", UrlLogUtil.urlLog.get().toString());
		map.put("tm", System.currentTimeMillis());
		map.putAll(obj);
		taskExecutor.execute(new Runnable() {
			public void run() {
				mongoLogService.insertChannelLog(map);
			}
		});
		return 0;
	}
	
	

}
