package com.chechemotel.logs.service.impl;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.chechemotel.logs.common.AbstractMongoDB;
import com.chechemotel.logs.common.MongoDao;
import com.chechemotel.logs.common.MongoDao.GroupType;
import com.chechemotel.logs.common.MongoDaoException;
import com.chechemotel.logs.common.MongoPageBean;
import com.chechemotel.logs.service.MongoLogServiceI;



/**
 * 记录到mongo数据库的service调用情况分析
 * @author ljw
 *
 */
@Component
public class MongoLogServiceImpl extends AbstractMongoDB implements MongoLogServiceI {
	
	/**
	 * 日志按月分表保存
	 */
	public static final DateFormat DF = new SimpleDateFormat("yyyyMM");
	
	public static final DateFormat DF_DAY = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 请求日志表
	 */
	public static final String REQ_URL_LOG_TN = "req_url_log_%s";
	
	/**
	 * 下订单请求日志表
	 */
	public static final String ORDER_LOG_TN = "order_url_log_%s";
	
	/**
	 * 下订单请求的明细日志表
	 */
	public static final String USER_PAY_LOG= "user_pay_log";
	
	/**
	 * 下订单请求的交易明细日志表（第三方渠道钱数变化明细表）
	 */
	public static final String ORDER_MONEY_LOG_TN = "order_money_log_%s";
	
	/**
	 * 类方法调用日志表
	 */
	public static final String REQ_CLASS_METHOD_LOG_TN = "req_class_method_log_%s";
	
	/**
	 * 订单业务日志表
	 */
	public static final String ORDER_CODE_LOG = "order_code_log";
	
	/**
	 * 渠道业务日志表
	 */
	public static final String CHANNEL_LOG = "channel_log";
	
	/**
	 * 管理后台用户操作日志表
	 */
	public static final String USER_OPERATE_LOG = "user_operate_log_%s";
	
	/**
	 * 充值渠道充值表
	 */
	public static final String PAYCHANNEL_PAY_LOG = "paychannel_pay_log";

	public int insertUrlLog(Map<String, Object> obj) {
		try {
			return super.insert(getCollectionName(REQ_URL_LOG_TN), obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int insertClassMethodLog(Map<String, Object> obj) {
		try {
			return super.insert(getCollectionName(REQ_CLASS_METHOD_LOG_TN), obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int insertOrderCodeLog(Map<String, Object> obj) {
		try {
			return super.insert(getCollectionName(ORDER_CODE_LOG), obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertChannelLog(Map<String, Object> obj) {
		try {
			return super.insert(getCollectionName(CHANNEL_LOG), obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int insertPayChannelPayLog(Map<String, Object> obj) {
		try {
			return super.insert(getCollectionName(PAYCHANNEL_PAY_LOG), obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertOrderLogUrl(Map<String, Object> obj) {
		try {
			return super.insert(getCollectionName(ORDER_LOG_TN), obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateOrderLogUrl(Object id, Map<String, Object> obj) {
		try {
			return super.update(getCollectionName(ORDER_LOG_TN), id, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateOrderCodeLog(String column,Object columnValue, Map<String, Object> obj) {
		try {
			return super.updateValueByColumn(getCollectionName(ORDER_CODE_LOG), column,columnValue, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updatePayChannelPayLog(String column,Object columnValue, Map<String, Object> obj) {
		try {
			return super.updateValueByColumn(getCollectionName(PAYCHANNEL_PAY_LOG), column,columnValue, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertUserPayLog(Map<String, Object> obj) {
		try {
			return super.insert(getCollectionName(USER_PAY_LOG), obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertOrderMoneyLog(Map<String, Object> obj) {
		try {
			return super.insert(getCollectionName(ORDER_MONEY_LOG_TN), obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int insertUserOperateLog(Map<String, Object> obj) {
		try {
			return super.insert(getCollectionName(USER_OPERATE_LOG), obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public MongoPageBean<Map<String, Object>> findPageUrlLog(String time, Map<String, Object> vlaues, int startPage, int pageSize) {
		String collectionName = String.format(REQ_URL_LOG_TN, time);
		
		Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		
		sortVlaues.put("tm", AbstractMongoDB.ORDER_DESC);
		
		MongoPageBean<Map<String, Object>> rs = new MongoPageBean<Map<String,Object>>();
		
		rs.setStartPage(startPage);
		rs.setPageSize(pageSize);
		rs.setTotals(super.findPageByValCount(collectionName, vlaues));
		rs.setResult(super.findPageByVal(collectionName, vlaues, sortVlaues, startPage, pageSize));
		
		return rs;
	}
	
	public List<Map<String, Object>> findClassMethodLog(String time, String urlId)  {
		
		String collectionName = String.format(REQ_CLASS_METHOD_LOG_TN, time);
		
		Map<String, Object> vlaues = new HashMap<String, Object>();
		
		vlaues.put("urlId", urlId);
		
		Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		
		sortVlaues.put("tm", AbstractMongoDB.ORDER_ASC);
		
		return findEqByVal(collectionName, vlaues, sortVlaues);
	}
	
	public List<Map<String, Object>> findPageOrderCodeLog(String time, Map<String, Object> vlaues, int startPage, int pageSize) {
		String collectionName = null == time ? getCollectionName(ORDER_CODE_LOG) : String.format(ORDER_CODE_LOG, time);

		Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		
		//sortVlaues.put("tm", AbstractMongoDB.ORDER_DESC);
		
		//MongoPageBean<Map<String, Object>> rs = new MongoPageBean<Map<String,Object>>();
		List<Map<String, Object>> rs = new ArrayList<Map<String,Object>>();
		//rs.setStartPage(startPage);
		//rs.setPageSize(pageSize);
		//rs.setTotals(super.findPageByValCount(collectionName, vlaues));
		//rs.setResult(super.findPageByVal(collectionName, vlaues, sortVlaues, startPage, pageSize));
		
		return super.findPageByVal(collectionName, vlaues, sortVlaues, startPage, pageSize);
	}
	
	public int findPageOrderCodeLogCount(String time, Map<String, Object> vlaues, int startPage, int pageSize) {
		String collectionName = null == time ? getCollectionName(ORDER_CODE_LOG) : String.format(ORDER_CODE_LOG, time);

		Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		
		//sortVlaues.put("tm", AbstractMongoDB.ORDER_DESC);
		
		//MongoPageBean<Map<String, Object>> rs = new MongoPageBean<Map<String,Object>>();
		List<Map<String, Object>> rs = new ArrayList<Map<String,Object>>();
		return super.findPageByValCount(collectionName, vlaues);
	}
	public Map<String, Object> findOrderCodeLog(String time, Object id)  {
		
		String collectionName = null == time ? getCollectionName(ORDER_CODE_LOG) : String.format(ORDER_CODE_LOG, time);
		
		Map<String, Object> vlaues = new HashMap<String, Object>();
		
		vlaues.put("_id", id);
		
		List<Map<String, Object>> rsList = findEqByVal(collectionName, vlaues, null);
		
		if (rsList != null && !rsList.isEmpty()) {
			return rsList.get(0);
		}
		
		return null;
	}
	
	public Map<String, Object> findpayChannelPayLog(String time, String id)  {
		
		String collectionName = null == time ? getCollectionName(PAYCHANNEL_PAY_LOG) : String.format(PAYCHANNEL_PAY_LOG, time);
		
		Map<String, Object> vlaues = new HashMap<String, Object>();
		
		vlaues.put("_id", new ObjectId(id));
		
		List<Map<String, Object>> rsList = findEqByVal(collectionName, vlaues, null);
		
		if (rsList != null && !rsList.isEmpty()) {
			return rsList.get(0);
		}
		
		return null;
	}
	
	
	public List<Map<String, Object>> findOrderCodeLogGroupBy(String time, Date begin, Date end, Boolean channelSucces, String[] channel, String[] payChannel)  {
		
		String collectionName = null == time ? getCollectionName(ORDER_CODE_LOG) : String.format(ORDER_CODE_LOG, time);
		
		MongoDao dao = new MongoDao(getSource());
		
		List<Map<String, Object>> rs = null;
		
		Integer size = -1;
		
		try {
			dao.newFilter();
			
			if (null != begin) {
				dao.gte("day_tm", Integer.parseInt(DF_DAY.format(begin)));
			}
			
			if (null != end) {
				dao.lte("day_tm", Integer.parseInt(DF_DAY.format(end)));
			}
			
			if (null != channelSucces) {
				if (channelSucces) {
					dao.eq("succStatus", 1);
				} else {
					dao.eq("failStatus", 1);
				}
			}
			
			if (null != channel && channel.length > 0) {
				List<String> paraList = new ArrayList<String>();
				for (int i = 0; i < channel.length; i++) {
					paraList.add(channel[i]);
				}
				if (!paraList.isEmpty()) {
					dao.in("channelId", paraList);
				}
			}
			
			if (null != payChannel && payChannel.length > 0) {
				List<String> paraList = new ArrayList<String>();
				for (int i = 0; i < payChannel.length; i++) {
					paraList.add(payChannel[i]);
				}
				if (!paraList.isEmpty()) {
					dao.in("payChannel", paraList);
				}
			}
			
			dao.groupBy("amount", "payType");
			// 成功个数
			dao.groupAs(GroupType.SUM, "succStatus", "succStatusSum");
			// 失败个数
			dao.groupAs(GroupType.SUM, "failStatus", "failStatusSum");
			// 折后金额总数
			dao.groupAs(GroupType.SUM, "discountAmt", "discountAmtSum");
			// 成功金额总数
			dao.groupAs(GroupType.SUM, "succAmt", "succAmtSum");
			// 失败金额总数
			dao.groupAs(GroupType.SUM, "failAmt", "failAmtSum");
			
			dao.find(collectionName);
			
			rs = dao.getRowMapList(size);
		} catch (MongoDaoException e) {
			e.printStackTrace();
		} finally {
			try {
				dao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dao = null;
		}
		
		return rs;
	}

	public MongoPageBean<Map<String, Object>> findPageChannelLog(String time, Map<String, Object> vlaues, int startPage, int pageSize) {
		String collectionName = null == time ? getCollectionName(CHANNEL_LOG) : String.format(CHANNEL_LOG, time);
		
		Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		
		//sortVlaues.put("tm", AbstractMongoDB.ORDER_DESC);
		
		MongoPageBean<Map<String, Object>> rs = new MongoPageBean<Map<String,Object>>();
		
		rs.setStartPage(startPage);
		rs.setPageSize(pageSize);
		rs.setTotals(super.findPageByValCount(collectionName, vlaues));
		rs.setResult(super.findPageByVal(collectionName, vlaues, sortVlaues, startPage, pageSize));
		
		return rs;
	}
	
	public Map<String, Object> findChannelLog(String time, Object id) {
		
		String collectionName = null == time ? getCollectionName(CHANNEL_LOG) : String.format(CHANNEL_LOG, time);
		
		Map<String, Object> vlaues = new HashMap<String, Object>();
		
		vlaues.put("_id", id);
		
		List<Map<String, Object>> rsList = findEqByVal(collectionName, vlaues, null);
		
		if (rsList != null && !rsList.isEmpty()) {
			return rsList.get(0);
		}
		
		return null;
	}

	public MongoPageBean<Map<String, Object>> findPageOrderLog(String time, Map<String, Object> vlaues, int startPage, int pageSize) {
		String collectionName = null == time ? getCollectionName(ORDER_LOG_TN) : String.format(ORDER_LOG_TN, time);
		
		Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		
		sortVlaues.put("tm", AbstractMongoDB.ORDER_DESC);
		
		MongoPageBean<Map<String, Object>> rs = new MongoPageBean<Map<String,Object>>();
		
		rs.setStartPage(startPage);
		rs.setPageSize(pageSize);
		rs.setTotals(super.findPageByValCount(collectionName, vlaues));
		rs.setResult(super.findPageByVal(collectionName, vlaues, sortVlaues, startPage, pageSize));
		
		return rs;
	}
	
	public MongoPageBean<Map<String, Object>> findPageOrderMoneyLog(String time, Map<String, Object> vlaues, int startPage, int pageSize) {
		String collectionName = null == time ? getCollectionName(ORDER_MONEY_LOG_TN) : String.format(ORDER_MONEY_LOG_TN, time);
		
		Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		
		sortVlaues.put("tm", AbstractMongoDB.ORDER_DESC);
		
		MongoPageBean<Map<String, Object>> rs = new MongoPageBean<Map<String,Object>>();
		
		rs.setStartPage(startPage);
		rs.setPageSize(pageSize);
		rs.setTotals(super.findPageByValCount(collectionName, vlaues));
		rs.setResult(super.findPageByVal(collectionName, vlaues, sortVlaues, startPage, pageSize));
		
		return rs;
	}
	
	public Map<String, Object> findOrderLog(String time, Object id)  {
		
		String collectionName = null == time ? getCollectionName(ORDER_LOG_TN) : String.format(ORDER_LOG_TN, time);
		
		Map<String, Object> vlaues = new HashMap<String, Object>();
		
		vlaues.put("_id", id);
		
		List<Map<String, Object>> rsList = findEqByVal(collectionName, vlaues, null);
		
		if (rsList != null && !rsList.isEmpty()) {
			return rsList.get(0);
		}
		
		return null;
	}
	
	public List<Map<String, Object>> findOrderDetailLog(String time, Map<String, Object> vlaues)  {
		
		String collectionName = null == time ? getCollectionName(USER_PAY_LOG) : String.format(USER_PAY_LOG, time);
		
		Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		
		sortVlaues.put("tm", AbstractMongoDB.ORDER_ASC);
		
		return findEqByVal(collectionName, vlaues, sortVlaues);
	}
	
	@Override
	public List<Map<String, Object>> findPayChannelPayLogExist(String time, Map<String, Object> vlaues){
		
		String collectionName = null == time ? getCollectionName(PAYCHANNEL_PAY_LOG) : String.format(PAYCHANNEL_PAY_LOG, time);
		
		Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		
		sortVlaues.put("tm", AbstractMongoDB.ORDER_DESC);
		
		return findEqByVal(collectionName, vlaues, sortVlaues);
	}
	
	public List<Map<String, Object>> findOrderMoneyLog(String time, Map<String, Object> vlaues)  {
		
		String collectionName = null == time ? getCollectionName(ORDER_MONEY_LOG_TN) : String.format(ORDER_MONEY_LOG_TN, time);
		
		Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		
		sortVlaues.put("tm", AbstractMongoDB.ORDER_ASC);
		
		return findEqByVal(collectionName, vlaues, sortVlaues);
	}

	public List<Map<String, Object>> findPayCHannelPayLog(Map<String, Object> vlaues,String sumName) {
		MongoDao dao = new MongoDao(getSource());
		List<Map<String, Object>> rs = null;
		try {
			dao.newFilter();
			if (null != vlaues && vlaues.size() > 0) {
				Set<String> set = vlaues.keySet();
				for (String key : set) {
					if (null != vlaues.get(key) && !"".equals(vlaues.get(key))) {
						if("beginDate".equals(key)){
							dao.gte("tm", vlaues.get(key));
							continue;
						}else if("endDate".equals(key)){
							dao.lte("tm", vlaues.get(key));
							continue;
						}
						dao.eq(key, vlaues.get(key));
					}
				}
			}
			dao.sortDesc("tm");
			if(StringUtils.isNotBlank(sumName)){
				dao.groupBy(sumName).groupAs(GroupType.SUM, "transfer", "transfer").
				groupAs(GroupType.SUM, "wasteBook", "wasteBook");
			}
			
			dao.find(PAYCHANNEL_PAY_LOG);
			
			rs = dao.getRowMapList();
			
		} catch (MongoDaoException e) {
			e.printStackTrace();
		} finally {
			try {
				dao.close();
			} catch (Exception e) {
			}
			dao = null;
		}
		return rs;
	}
	
	public List<Map<String, Object>> findPayChannelGroupBySum(Integer start, Integer end, String... payChannel) {
		/*
		zhekou100   	//折扣100
		zhekou200   	//折扣200
		zhekou500	//折扣500
		zhekou1000	//折扣1000
		zhekouEvery	//任意的折扣

		zhekou100FixAmt		//100折扣后的金额
		zhekou200FixAmt		//200折扣后的金额
		zhekou500FixAmt		//500折扣后的金额
		zhekou1000FixAmt	//1000折扣后的金额

		zhekou100BillAmt	//100任意折扣后的金额
		zhekou200BillAmt	//200任意折扣后的金额
		zhekou500BillAmt	//500任意折扣后的金额
		zhekou1000BillAmt	//1000任意折扣后的金额

		profit100FixAmt		//固定100的盈亏
		profit200FixAmt		//固定200的盈亏
		profit500FixAmt		//固定500的盈亏
		profit1000FixAmt	//固定1000的盈亏

		profit100BillAmt	//任意100的盈亏
		profit200BillAmt	//任意200的盈亏
		profit500BillAmt	//任意500的盈亏
		profit1000BillAmt	//任意1000的盈亏
		*/
		
		MongoDao dao = new MongoDao(getSource());
		List<Map<String, Object>> rs = null;
		
		try {
			dao.newFilter();
				
			if (null != start) {
				dao.gte("tm", start);
			}
			
			if (null != end) {
				dao.lte("tm", end);
			}
			
			if (null != payChannel && payChannel.length > 0) {
				List<String> payChannelList = new ArrayList<String>();
				for (int i = 0, size = payChannel.length; i < size; i++) {
					payChannelList.add(payChannel[i]);
		        }
				dao.in("payChannel", payChannelList);
			}
			
			dao.groupBy();
			
			int[] nums = {100, 200, 500, 1000};
			
			for (int i = 0; i < nums.length; i++) {
				dao.groupAs(GroupType.SUM, "zhekou" + nums[i] + "FixAmt", "zhekou" + nums[i] + "FixAmtSum");
				dao.groupAs(GroupType.SUM, "zhekou" + nums[i] + "BillAmt", "zhekou" + nums[i] + "BillAmtSum");
				dao.groupAs(GroupType.SUM, "profit" + nums[i] + "FixAmt", "profit" + nums[i] + "FixAmtSum");
				dao.groupAs(GroupType.SUM, "profit" + nums[i] + "BillAmt", "profit" + nums[i] + "BillAmtSum");
			}
			
			dao.find(PAYCHANNEL_PAY_LOG);
			rs = dao.getRowMapList();
		} catch (MongoDaoException e) {
			e.printStackTrace();
		} finally {
			try {
				dao.close();
			} catch (Exception e) {
			}
			dao = null;
		}
		return rs;
	}
	
	public List<Map<String, Object>> findPayCHannelPayStatisLog(Map<String, Object> vlaues,String sumName) {
		MongoDao dao = new MongoDao(getSource());
		List<Map<String, Object>> rs = null;
		
		try {
			dao.newFilter();
			
			if (null != vlaues && vlaues.size() > 0) {
					
				Set<String> set = vlaues.keySet();
				
				for (String key : set) {
					if (null != vlaues.get(key) && !"".equals(vlaues.get(key))) {
						if("beginDate".equals(key)){
							dao.gte("tm", vlaues.get(key));
							continue;
						}else if("endDate".equals(key)){
							dao.lte("tm", vlaues.get(key));
							continue;
						}else if("payChannel".equals(key)){
							//做判断模糊查询
							List<String> l = new ArrayList<String>();
							dao.in("", l);
							continue;
						}else if("channel".equals(key)){
							//做判断模糊查询
							List<String> l = new ArrayList<String>();
							dao.in("", l);
							continue;
						}
						dao.eq(key, vlaues.get(key));
					}
				}
			}
			
			dao.sortDesc("tm");
			dao.groupBy("amount");
			dao.groupAs(GroupType.SUM, "payChannel", "payChannelSum");
			dao.groupAs(GroupType.SUM, "channel", "channel");
			dao.find(PAYCHANNEL_PAY_LOG);
			rs = dao.getRowMapList();
			
		} catch (MongoDaoException e) {
			e.printStackTrace();
		} finally {
			try {
				dao.close();
			} catch (Exception e) {
			}
			dao = null;
		}
		return rs;
	}
	
	@Override
	public Map<String, Object> findOnePayChannelZheKou(String payChannel) {
		MongoDao dao = new MongoDao(getSource());
		List<Map<String, Object>> rs = null;
		
		try {
			dao.newFilter();
			dao.project("zhekou100", "zhekou200", "zhekou500", "zhekou1000", "zhekouEvery");
			dao.eq("payChannel", payChannel);
			dao.sortDesc("tm");
			dao.limit(1);
			
			dao.find(PAYCHANNEL_PAY_LOG);
			rs = dao.getRowMapList();
		} catch (MongoDaoException e) {
			e.printStackTrace();
		} finally {
			try {
				dao.close();
			} catch (Exception e) {
			}
			dao = null;
		}
		
		if (rs != null && rs.size() > 0) {
			return rs.get(0);
		}
		
		return null;
	}
	
	private String getCollectionName(String key) {
		return String.format(key, DF.format(new Date(System.currentTimeMillis())));
	}

	@Override
	public Map<String, Object> findOrderCodeLogByOrder(String time,
			Object orderId) {
		// TODO Auto-generated method stub
       String collectionName = null == time ? getCollectionName(ORDER_CODE_LOG) : String.format(ORDER_CODE_LOG, time);
		Map<String, Object> vlaues = new HashMap<String, Object>();
		vlaues.put("orderId", orderId);
        Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		sortVlaues.put("tm", AbstractMongoDB.ORDER_DESC);
		List<Map<String, Object>> rsList = findEqByVal(collectionName, vlaues, sortVlaues);
		
		if (rsList != null && !rsList.isEmpty()) {
			return rsList.get(0);
		}
		
		return null;
	}

	@Override
	public List<Map<String, Object>> findOrderUrlLogByReqId(String time,
			Object reqId) {
		// TODO Auto-generated method stub
		 String collectionName = null == time ? getCollectionName(USER_PAY_LOG) : String.format(USER_PAY_LOG, time);
			Map<String, Object> vlaues = new HashMap<String, Object>();
			vlaues.put("reqId", reqId);
	        Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
			sortVlaues.put("tm", AbstractMongoDB.ORDER_DESC);
			List<Map<String, Object>> rsList = findEqByVal(collectionName, vlaues, sortVlaues);
			
			if (rsList != null && !rsList.isEmpty()) {
				return rsList;
			}
		return null;
	}

	@Override
	public Map<String, Object> findOrderUrlLogByCode(String time, Object reqId,
			Object code) {
		// TODO Auto-generated method stub
		 String collectionName = null == time ? getCollectionName(USER_PAY_LOG) : String.format(USER_PAY_LOG, time);
			Map<String, Object> vlaues = new HashMap<String, Object>();
			vlaues.put("reqId",reqId);
			vlaues.put("type", code);
	        Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
			sortVlaues.put("tm", AbstractMongoDB.ORDER_DESC);
			List<Map<String, Object>> rsList = findEqByVal(collectionName, vlaues, sortVlaues);
			
			if (rsList != null && !rsList.isEmpty()) {
				return rsList.get(0);
			}
		return null;
	}
	
	
	public List<Map<String, Object>> findPageUserOrderLog(String time, Map<String, Object> vlaues, int startPage, int pageSize) {
		String collectionName = null == time ? getCollectionName(USER_PAY_LOG) : String.format(USER_PAY_LOG, time);

		Map<String, Integer> sortVlaues = new HashMap<String, Integer>();
		
		//sortVlaues.put("tm", AbstractMongoDB.ORDER_DESC);
		
		//MongoPageBean<Map<String, Object>> rs = new MongoPageBean<Map<String,Object>>();
		List<Map<String, Object>> rs = new ArrayList<Map<String,Object>>();
		//rs.setStartPage(startPage);
		//rs.setPageSize(pageSize);
		//rs.setTotals(super.findPageByValCount(collectionName, vlaues));
		//rs.setResult(super.findPageByVal(collectionName, vlaues, sortVlaues, startPage, pageSize));
		
		return super.findPageByVal(collectionName, vlaues, sortVlaues, startPage, pageSize);
	}
	
	public int findPageUserOrderLogCount(String time, Map<String, Object> vlaues, int startPage, int pageSize) {
		String collectionName = null == time ? getCollectionName(USER_PAY_LOG) : String.format(USER_PAY_LOG, time);
		return super.findPageByValCount(collectionName, vlaues);
	}
}
