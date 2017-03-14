package com.chechemotel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Filename: DateUtil.java <br>
 * 
 * Description: 日期操作工具类 <br>
 * 
 * @author: LYQ <br>
 * @version: 1.0 <br>
 * @Createtime: Sep 14, 2012 <br>
 * 
 * @Copyright: Copyright (c)2012 by LYQ <br>
 * 
 */

public class DateUtil {

	/**
	 * 获取当前时间格式yyy-MM-dd HH:mm:ss时间字符串
	 * 
	 * @return
	 * @author: LYQ
	 * @Createtime: Sep 14, 2012
	 */
	public static String getNowDateStr() {
		return dateConvertStr(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	public static int getNowDateInt() {
		return Integer.parseInt(dateConvertStr(new Date(), "yyyyMMdd"));
	}
	/**
	   * 
	   * 
	   * @return 字符串 yyyyMMdd HHmmss
	   */
	public static String toDateString(String date1) {
		String newdate="";
		newdate=date1.replace("年", "").replace("月", "").replace("日", "");
	   return newdate;
	}
	/**
	 * 获取当前时间格式yyy-MM-dd时间字符串
	 * 
	 * @return
	 * @author: LYQ
	 * @Createtime: Sep 14, 2012
	 */
	public static String getNowDateDayStr() {
		return dateConvertStr(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 将Date类型转换成yyy-MM-dd HH:mm:ss 字符串类型
	 * 
	 * @param Date
	 *            date
	 * @return
	 * @author: LYQ
	 * @Createtime: Sep 14, 2012
	 */
	public static String dateConvertStr(Date date) {
		return dateConvertStr(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将Date类型转换成yyy-MM-dd字符串类型
	 * 
	 * @param Date
	 *            date
	 * @param String
	 *            format 如：yyyy-MM-dd HH:mm:ss
	 * @return
	 * @author: LYQ
	 * @Createtime: Sep 14, 2012
	 */
	public static String dateConvertDayStr(Date date) {
		return dateConvertStr(date, "yyyy-MM-dd");
	}

	/**
	 * 将Date类型转换成字符串类型
	 * 
	 * @param Date
	 *            date
	 * @param String
	 *            format 如：yyyy-MM-dd HH:mm:ss
	 * @return
	 * @author: LYQ
	 * @Createtime: Sep 14, 2012
	 */
	public static String dateConvertStr(Date date, String format) {
		return (date == null) ? null : new SimpleDateFormat(format)
				.format(date);
	}

	/**
	 * 时间格式字符串转换成Date类型
	 * 
	 * @param String
	 *            date
	 * @param String
	 *            format 如：yyyy-MM-dd HH:mm:ss
	 * @return
	 * @author: LYQ
	 * @Createtime: 7 9, 2013
	 */
	public static Date dateStrConvertDate(String date, String format) {
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
			Date newdate = simpledateformat.parse(date);
			return newdate;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 根据日期获取当前为星期几
	 * 
	 * @param date
	 * @return
	 * @author: LYQ
	 * @Createtime: Sep 14, 2012
	 */
	public static int getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int weekno = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (weekno == 0)
			weekno = 7;
		return weekno;

	}

	/**
	 * 计算两个时间间隔多少天
	 * 
	 * @param startday
	 * @param endday
	 * @return
	 * @author: LYQ
	 * @Createtime: Sep 14, 2012
	 */
	public static int getIntervalDays(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 * 指定时间对象，根据日历的规则，为给定的日历字段添加或减去指定的时间量,返回时间对象
	 * 
	 * @param date
	 *            Date类型
	 * @param field
	 *            int 日历字段,如：Calendar.DAY_OF_MONTH
	 * @param amount
	 *            int 为字段添加的日期或时间量。
	 * @return Date值
	 * @author: LYQ
	 * @Createtime: Sep 14, 2012
	 */
	public static Date getDate(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field,amount);
		return cal.getTime();
	}

	/**
	 * 指定时间对象，根据日历的规则，为给定的日历字段添加或减去指定的天数,返回时间对象
	 * 
	 * @param date
	 *            Date类型
	 * @param type
	 *            String类型  类别,可以是年、月、日
	 * @param day
	 *            天 int 为字段添加的日期或时间量。正负都可
	 * @return Date值
	 * @author: LYQ
	 * @Createtime: 7 5, 2013
	 */
	public static Date getDateMonth(Date date,String type,int day) {
		int field = 0;
		
		if(type.equals("YEAR"))
			field = Calendar.YEAR;
		else if(type.equals("MONTH")){
			field = Calendar.MONTH;
		}else if (type.equals("DATE"))
			field = Calendar.DATE;
		
		return getDate(date,field,day);
		
		
	}

	
	
	/**
	 * 获取指定时间在当年中的第几周
	 * 
	 * @param date
	 *            Date类型
	 * @return
	 * @author: LYQ
	 * @Createtime: Aug 13, 2012
	 */
	public static int getWeekNumber(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);

	}

	/**
	 * 获取当前时间在当年中的第几周
	 * 
	 * @return
	 * @author: LYQ
	 * @Createtime: Aug 13, 2012
	 */
	public static int getWeekNumber() {

		return getWeekNumber(new Date());

	}

	/**
		* @MethodName: dateCompare
		* @date 2013-7-9 下午03:12:58
		* @author: LuYuQiao 
		* @Description: TODO(比较两个时间先后的方法)
		* @param Date startday
		* @param Date endday
		* @param @return    设定文件
		* @return boolean    返回类型
		* @throws
	 */
	public static boolean timeCompare(Date startday, Date endday){
		boolean flag = false;
		flag = startday.before(endday);
		return flag;
	}
	
	public static boolean dateCompare(Date startday,Date endday){
		return false;
	}
	
	public static boolean dateCompare(Date day){
		boolean flag = false;
		try{
			java.util.Date nowdate=new java.util.Date(); 		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			String myString = sdf.format(day);
			Date d = sdf.parse(myString);
			flag = d.before(sdf.parse(sdf.format(nowdate)));	
			return flag;
		}catch(Exception e){
			return flag;
		}
	}
	
	public static int dateCompareInt(Date day){
		int flag = 0;
		try{
			java.util.Date nowdate=new java.util.Date(); 		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			String myString = sdf.format(day);
			String nowString = sdf.format(nowdate);
			
			Date d = sdf.parse(myString);
			if(myString.equals(nowString)){
				flag = 1;
			}else{
				if(d.before(sdf.parse(nowString)))
					flag=2;
				else
					flag=3;
			}
			return flag;
		}catch(Exception e){
			return flag;
		}
	}
	
	public static String IntCompareString(String str){
		String year = str.substring(0, 4);
		String month = str.substring(4, 6);
		String day = str.substring(6, 8);
		String hour = str.substring(8, 10);
		String minute = str.substring(10, 12);
		String second = str.substring(12, 14);
		String date = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		return date;
	}
	/**
	 * 转换时间20070816133511 为yyyy-MM-dd HH:mm:ss
	 * @param str
	 * @return
	 */
	public static Date IntCompareDate(String str){
		String year = str.substring(0, 4);
		String month = str.substring(4, 6);
		String day = str.substring(6, 8);
		String hour = str.substring(8, 10);
		String minute = str.substring(10, 12);
		String second = str.substring(12, 14);
		String date = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	/**
	 * 转换时间20070816133511 为yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static Date CompareDate(String str){
		Date d = null;
		if(str!=null && !"".equals(str)){
			String year = str.substring(0, 4);
			String month = str.substring(4, 6);
			String day = str.substring(6, 8);
			String date = year+"-"+month+"-"+day;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				d = df.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return d;
	}
	
	/**
	 * 转换时间20070816 为yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static String IntCompareDateStr8(String str){
		if (str != null && !str.equals("")) {
			String year = str.substring(0, 4);
			String month = str.substring(4, 6);
			String day = str.substring(6, 8);
			String date = year + "-" + month + "-" + day;
			return date;
		} else {
			return null;
		}
		
		
	}
	
	/**
	 * 
	 * @param date1 <Date>
	 * @param date2 <Date>
	 * @return int
	 * @throws ParseException
	 */
	public static int getMonthSpace(Date date1, Date date2) {

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(date1);
		c2.setTime(date2);

		int c =(c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12 + c2.get(Calendar.MONTH)
				- c1.get(Calendar.MONTH);
		return c;

	}
	
	/**
	 * 
	 * @param date1 <Date>
	 * @param date2 <Date>
	 * @return int
	 * @throws ParseException
	 */
	public static long getMiniSpace(Date bigdate1, Date smalldate2) {

		long cha = bigdate1.getTime() - smalldate2.getTime();
		long x = cha/1000/60;
		return x;

	}
	
	
	public static void main(String[] args) {
		//System.err.println(dateStrConvertDate("2013-02-27 14:20:32", "yyyyMMddHHmmss").getTime());
//		System.out.println(toDateString("2014年5月1日"));
		System.out.println(getNowDateInt());
	}
}
