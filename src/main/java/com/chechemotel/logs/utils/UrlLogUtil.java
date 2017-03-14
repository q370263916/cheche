package com.chechemotel.logs.utils;

/**
 * 日志请求id线程内存储
 * @author ljw
 *
 */
public class UrlLogUtil {

	/**
	 * 存放日志请求id
	 */
	public static final ThreadLocal<Object> urlLog = new ThreadLocal<Object>();
}
