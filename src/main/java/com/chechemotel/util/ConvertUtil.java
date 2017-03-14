package com.chechemotel.util;

import java.util.HashMap;
import java.util.Map;

public class ConvertUtil {

	public static final Map<String,String> orderDetailMap=new HashMap<String,String>();
	static
	{
		/**
		 * 12345 ： 正常成功
		 * 888：异常----并不是返回程序异常
		 */
		/**
		 * 订单
		 */
		orderDetailMap.put("10001", "数据校验开始");
		orderDetailMap.put("10002", "数据校验成功");
		orderDetailMap.put("10003", "数据校验失败");
		orderDetailMap.put("10004", "渠道日志开始");
		orderDetailMap.put("10005", "渠道日志成功");
		orderDetailMap.put("10006", "保存用户信息开始");
		orderDetailMap.put("10007", "保存用户信息成功");
		orderDetailMap.put("10008", "生成订单开始");
		orderDetailMap.put("10009", "生成订单成功");
		orderDetailMap.put("10010", "余额计算开始");
		orderDetailMap.put("10011", "余额计算成功");
		orderDetailMap.put("10012", "资金收入开始");
		orderDetailMap.put("10013", "资金收入成功");
		orderDetailMap.put("10014", "会员加油服务开始");
		orderDetailMap.put("10015", "会员加油服务成功");
		orderDetailMap.put("10016", "生成油资开始");
		orderDetailMap.put("10017", "生成油资结束");
		orderDetailMap.put("10018", "加油卡保存开始");
		orderDetailMap.put("10019", "加油卡保存成功");
		orderDetailMap.put("10020", "插入消息丢列开始");
		orderDetailMap.put("10021", "插入消息丢列成功");
		
		/**
		 * 充值
		 */
		orderDetailMap.put("20001", "回调消息队列开始");
		orderDetailMap.put("20002", "回调消息队列传递参数");
		orderDetailMap.put("20003", "数据库中找不到参数");
		orderDetailMap.put("20004", "查到Map中的数据");
		orderDetailMap.put("20005", "限额校验开始");
		orderDetailMap.put("20006", "限额校验成功");
		orderDetailMap.put("20007", "充值开始");
		orderDetailMap.put("20008", "充值完成");
		orderDetailMap.put("20009", "充值异常停止消息队列");
		orderDetailMap.put("20010", "更改卡密状态开始");
		orderDetailMap.put("20011", "更改卡密状态成功");
		orderDetailMap.put("20012", "充值成功更新充值记录开始");
		orderDetailMap.put("20013", "充值成功更新充值记录成功");
		orderDetailMap.put("20014", "充值成功更新充值日志异常");
		orderDetailMap.put("20015", "调用支付接口返回失败");
		orderDetailMap.put("20016", "修改卡密支付失败开始");
		orderDetailMap.put("20017", "修改卡密支付失败成功");
		orderDetailMap.put("20018", "回调消息队列成功");
		
		orderDetailMap.put("20019", "手动固定充值开始");
		orderDetailMap.put("20020", "手动固定充值完成"); 
		orderDetailMap.put("20021", "手动发票充值开始");
		orderDetailMap.put("20022", "手动发票充值完成");
		orderDetailMap.put("20023", "卡密充值开始");
		orderDetailMap.put("20024", "卡密充值完成");
		orderDetailMap.put("20025", "重复充值预警");
		
		orderDetailMap.put("30001", "充值回调sucess");
		orderDetailMap.put("30002", "充值回调信息");
		orderDetailMap.put("30003", "充值回调失败");
		orderDetailMap.put("30004", "充值回调成功");
		orderDetailMap.put("30005", "手动充值和半自动充值不进行自动通知");
		orderDetailMap.put("30006", "通知内容");
		orderDetailMap.put("30007", "更新");
	
		
	}
	
}
