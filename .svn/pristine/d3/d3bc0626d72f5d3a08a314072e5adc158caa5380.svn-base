package com.chechemotel.util;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class StringUtil {

	private static final char QUOTE_ENCODE[] = "&quot;".toCharArray();

	private static final char AMP_ENCODE[] = "&amp;".toCharArray();

	private static final char LT_ENCODE[] = "&lt;".toCharArray();

	private static final char GT_ENCODE[] = "&gt;".toCharArray();

	private static MessageDigest digest = null;

	private static Random randGen = new Random();
	
	private static String encodingCharset = "UTF-8";

	private static char numbersAndLetters[] = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();

	/**
	 * 给生成唯一字符串使用 
	 */
	private static String[] strArray = new String[] { "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4",
			"5", "6", "7", "8", "9", "!", "@", "#", "$", "%", "^", "&", "(",
			")" };

	public StringUtil() {
	}

	/**
	 * 搜索text中的repl, 从第m位开始，返回repl确切位置
	 * @param text
	 * @param repl
	 * @return -1不包含 其他包含
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */

	public static int ishave(String text, String repl, int m) {
		if (text == null || repl == null || "".equals(repl)) {
			return -1;
		}
		return text.indexOf(repl, m);
	}

	/**
	 * 搜索text中的repl,返回其第一次索引
	 * @param text
	 * @param repl
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static int ishavefirst(String text, String repl) {
		if (text == null || repl == null || "".equals(repl)) {
			return -1;
		}
		return text.indexOf(repl);
	}

	public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		if (line == null)
			return null;
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			return buf.toString();
		} else {
			return line;
		}
	}

	public static final String replaceIgnoreCase(String line, String oldString,
			String newString, int count[]) {
		if (line == null)
			return null;
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			int counter = 0;
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		} else {
			return line;
		}
	}

	/**
	 * 将特殊字符 转义成html格式
	 * @param in
	 * @return String
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static final String escapeHTMLTags(String in) {
		if (in == null)
			return null;
		int i = 0;
		int last = 0;
		char input[] = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>')
				if (ch == '<') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '>') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(GT_ENCODE);
				}
		}

		if (last == 0)
			return in;
		if (i > last)
			out.append(input, last, i - last);
		return out.toString();
	}

	/**
	 * 过滤html 标签
	 * @param s
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static String clearHTMLTags(String s) {
		boolean flag = true;
		StringBuffer sb = new StringBuffer();
		if (s == null || s.length() == 0)
			return "";
		String srcStr = replaceIgnoreCase(s, "&nbsp;", "");
		srcStr = replaceIgnoreCase(srcStr, "\n", "");
		srcStr = replaceIgnoreCase(srcStr, "\t", "");
		for (int i = 0; i < srcStr.length(); i++) {
			char c = srcStr.charAt(i);
			if (c == '>') {
				flag = true;
			} else {
				if (c == '<')
					flag = false;
				if (c != ' ' && c != '\u3000' && i < srcStr.length() && flag)
					sb.append(c);
			}
		}

		String desStr = sb.toString();
		return desStr;
	}
 
	/**
	 * MD5 加密
	 * @param data
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static final synchronized String hash(String data) {
		if (digest == null)
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err
						.println("Failed to load the MD5 MessageDigest. Jive will be unable to function normally.");
				nsae.printStackTrace();
			}
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

	/**
	 * byte数字转换16进制字符串
	 * @param bytes
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static final String encodeHex(byte bytes[]) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 16)
				buf.append("0");
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}

		return buf.toString();
	}

	/**
	 * 16进制字符串转换byte数组
	 * @param hex
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static final byte[] decodeHex(String hex) {
		char chars[] = hex.toCharArray();
		byte bytes[] = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}

		return bytes;
	}

	/**
	 * char类型转换byte字节
	 * @param ch
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case 48: // '0'
			return 0;

		case 49: // '1'
			return 1;

		case 50: // '2'
			return 2;

		case 51: // '3'
			return 3;

		case 52: // '4'
			return 4;

		case 53: // '5'
			return 5;

		case 54: // '6'
			return 6;

		case 55: // '7'
			return 7;

		case 56: // '8'
			return 8;

		case 57: // '9'
			return 9;

		case 97: // 'a'
			return 10;

		case 98: // 'b'
			return 11;

		case 99: // 'c'
			return 12;

		case 100: // 'd'
			return 13;

		case 101: // 'e'
			return 14;

		case 102: // 'f'
			return 15;
		}
		return 0;
	}

	/**
	 * 字符串Base64加密
	 * @param data
	 * @return String
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static String encodeBase64(String data) {
		return encodeBase64(data.getBytes());
	}

	/**
	 * 字节数组Base64加密
	 * @param data
	 * @return String
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static String encodeBase64(byte data[]) {
		int len = data.length;
		StringBuffer ret = new StringBuffer((len / 3 + 1) * 4);
		for (int i = 0; i < len; i++) {
			int c = data[i] >> 2 & 0x3f;
			ret
					.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
							.charAt(c));
			c = data[i] << 4 & 0x3f;
			if (++i < len)
				c |= data[i] >> 4 & 0xf;
			ret
					.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
							.charAt(c));
			if (i < len) {
				c = data[i] << 2 & 0x3f;
				if (++i < len)
					c |= data[i] >> 6 & 0x3;
				ret
						.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
								.charAt(c));
			} else {
				i++;
				ret.append('=');
			}
			if (i < len) {
				c = data[i] & 0x3f;
				ret
						.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
								.charAt(c));
			} else {
				ret.append('=');
			}
		}

		return ret.toString();
	}

	/**
	 * 字符串Base64解密
	 * @param data
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static String decodeBase64(String data) {
		return decodeBase64(data.getBytes());
	}

	/**
	 * 字节数组Base64解密
	 * @param data
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static String decodeBase64(byte data[]) {
		int len = data.length;
		StringBuffer ret = new StringBuffer((len * 3) / 4);
		for (int i = 0; i < len; i++) {
			int c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
					.indexOf(data[i]);
			i++;
			int c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
					.indexOf(data[i]);
			c = c << 2 | c1 >> 4 & 0x3;
			ret.append((char) c);
			if (++i < len) {
				c = data[i];
				if (61 == c)
					break;
				c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
						.indexOf((char) c);
				c1 = c1 << 4 & 0xf0 | c >> 2 & 0xf;
				ret.append((char) c1);
			}
			if (++i >= len)
				continue;
			c1 = data[i];
			if (61 == c1)
				break;
			c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
					.indexOf((char) c1);
			c = c << 6 & 0xc0 | c1;
			ret.append((char) c);
		}

		return ret.toString();
	}

	private static final String replace(String line, String oldString,
			String newString) {
		if (line == null)
			return null;
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			return buf.toString();
		} else {
			return line;
		}
	}

	/**
	 * 字符串中 特殊符号 替换为空
	 * @param text
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static final String[] toLowerCaseWordArray(String text) {
		if (text == null || text.length() == 0)
			return new String[0];
		ArrayList wordList = new ArrayList();
		BreakIterator boundary = BreakIterator.getWordInstance();
		boundary.setText(text);
		int start = 0;
		for (int end = boundary.next(); end != -1; end = boundary.next()) {
			String tmp = text.substring(start, end).trim();
			tmp = replace(tmp, "+", "");
			tmp = replace(tmp, "/", "");
			tmp = replace(tmp, "\\", "");
			tmp = replace(tmp, "#", "");
			tmp = replace(tmp, "*", "");
			tmp = replace(tmp, ")", "");
			tmp = replace(tmp, "(", "");
			tmp = replace(tmp, "&", "");
			if (tmp.length() > 0)
				wordList.add(tmp);
			start = end;
		}

		return (String[]) wordList.toArray(new String[wordList.size()]);
	}

	/**
	 * 生成固定长度 数字字母 的随机数
	 * @param length
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static final String randomString(int length) {
		if (length < 1)
			return null;
		char randBuffer[] = new char[length];
		for (int i = 0; i < randBuffer.length; i++)
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];

		return new String(randBuffer);
	}
	/**
	 * 
	* @Title: getRandomPwd
	* @Description: TODO(获取6位数字密码)
	* @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	 public static String getRandom6Pwd(){
	        return String.valueOf((1 + Math.random()) * 1000000).substring(1, 7);
	 }

	/**
	 * 将字符串中特殊字符转义成xml格式
	 * @param string
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static final String escapeForXML(String string) {
		if (string == null)
			return null;
		int i = 0;
		int last = 0;
		char input[] = string.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>')
				if (ch == '<') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '&') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(AMP_ENCODE);
				} else if (ch == '"') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(QUOTE_ENCODE);
				}
		}

		if (last == 0)
			return string;
		if (i > last)
			out.append(input, last, i - last);
		return out.toString();
	}

	/**
	 * 将xml转义字符 转换为正常字符串
	 * @param string
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static final String unescapeFromXML(String string) {
		string = replace(string, "&lt;", "<");
		string = replace(string, "&gt;", ">");
		string = replace(string, "&quot;", "\"");
		return replace(string, "&amp;", "&");
	}

	/**
	 * 字符串格式转换 ISO8859_1 转为 GBK
	 * @param strvalue
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static String toChinese(String strvalue) {
		try {
			if (strvalue == null) {
				return null;
			} else {
				strvalue = new String(strvalue.getBytes("GBK"), "ISO8859_1");
				return strvalue;
			}
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 过滤字符串中包含SQL注入的关键字
	 * @param content
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static String inspect(String content) {
		//String content=str;
		if (content == null || content.trim().equals("")) {
			return "";
		}
		String filter[] = new String[] { "'", " and", "and ", " exec", "exec ",
				" insert", "insert ", " select", "select ", " delete",
				"delete ", " update", "update ", " count", "count ", "*", "%",
				" chr", "chr ", " mid", "mid ", " master", "master ",
				" truncate", "truncate ", " char", "char ", " declare",
				"declare ", ";", " or", "or ", "-", "+", "," };
		for (int i = 0; i < filter.length; i++) {
			if (filter[i].trim().equals("")) {
				continue;
			}
			content = content.replace(filter[i], "");
		}
		return content;
	}

	/**
	 * 取得xml某节点下参数的值
	 * @param xpath 节点
	 * @param xml 字符串
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static String getValue(String xpath, String xml) {
		try {
			Document doc = DocumentHelper.parseText(xml);
			List list = doc.selectNodes(xpath);
			Element ele = (Element) list.get(0);
			return ele.getStringValue();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 验证手机号码
	 * @param mobiles 手机号码(单个)
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(17[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	/**
	 * 根据url返回字符串
	 * @param url 网络url
	 * @return
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public String getStu(String url){
		URL urlmy = null;
		try {
			urlmy = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}   
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) urlmy.openConnection();
			con.setFollowRedirects(true);   
			con.setInstanceFollowRedirects(false);   
			con.connect();   
		} catch (IOException e) {
			e.printStackTrace();
		}   
		BufferedReader br;
		StringBuffer sb = new StringBuffer("");   
		try {
			br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			String s = "";   
			while ((s = br.readLine()) != null) {   
				sb.append(s.toString()+"\r\n");   
			}  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		
		return sb.toString();
	}
	/**
	 * 图片压缩
	 * @param inpath 待压缩图片路径
	 * @param outpath 压缩后图片路径
	 * @param height 压缩高度
	 * @param width 压缩宽度
	 * @return 压缩后图片路径
	 * @throws Exception
	 * @author: LJ  
	 * @Createtime: Sep 24, 2012
	 */
	public static Icon getFixedBoundIcon(String inpath,String outpath, int height, int width)throws Exception {
		double Ratio = 0.0;
		//缩放比例 
		File F = new File(inpath);
		if (!F.isFile())
			throw new Exception(F+ " is not image file error in getFixedBoundIcon!");
		Icon ret = new ImageIcon(inpath);
		BufferedImage Bi = ImageIO.read(F);
		if ((Bi.getHeight() > height) || (Bi.getWidth() > width)) {
			if (Bi.getHeight() > Bi.getWidth()) {
				Ratio = (new Integer(height)).doubleValue() / Bi.getHeight();
			} else {
				Ratio = (new Integer(width)).doubleValue() / Bi.getWidth();
			}
			int lastLength = inpath.lastIndexOf(".");
			String subFilePath = outpath.substring(0, lastLength);
			String fileType = inpath.substring(lastLength);
			File zoomFile = new File(subFilePath+fileType);
			Image Itemp = Bi.getScaledInstance(width, height, Bi.SCALE_SMOOTH);
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(Ratio, Ratio), null);
			Itemp = op.filter(Bi, null);
			try {
				ImageIO.write((BufferedImage) Itemp, "jpg", zoomFile);
				ret = new ImageIcon(zoomFile.getPath());
				System.err.println("图片压缩后路径为 "+outpath);
			} catch (Exception ex) {
				System.out.println("######## here error : " + ex);
			}
		}
		return ret;
	}
	public static void main(String args[]) throws UnsupportedEncodingException {

		String sql = "#dev,111111111111,111,111,1!";
		byte[] bs = sql.getBytes();
		//用新的字符编码生成字符串
		//  new String(bs, newCharset);
		try {
			System.out.println(getFixedBoundIcon("e:/3.jpg","e:/1.jpg",100,500));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String toSqlParam(String value){
		String rv="";
		if(value!=null){
			String[] vs=value.split(",");
			if(vs.length>0){
				for(String v:vs){
					rv+="'"+v+"',";
				}
				if(rv.length()>0){
					rv=rv.substring(0, rv.length()-1);
				}
			}
		}
		return rv;
		
	}
	/**
	 * @MethodName: isEmpty
	 * @Description: 判断一个值是否为无效值 null及字符串""会被对待为无效值
	 * @author: tengfeiyu
	 * @date 2014-12-10 上午09:22:55
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Object value) {
		if ("".equals(checkObjForStr(value))) {
			return true;
		}
		return false;
	}
	/**
	 * 检查对象转换成数字
	 * @param value
	 * @return
	 */
	public static String checkObjForNum(Object value) {
		if (value == null || value.toString().trim().equals("")) {
			value = "0";
		}
		return value.toString().trim();
	}
	/**
	 * 检查对象转换成字符
	 * @param value
	 * @return
	 */
	public static String checkObjForStr(Object value) {
		if (value == null || value.toString().trim().equals("")) {
			value = "";
		}
		return value.toString().trim();
	}
	/**
	 * 去除字符串中的横线
	 * @param str
	 * @return
	 */
	public static String deleteHorizontal(String str){
		String s = str.replaceAll("-", "");
		return s;
	}
	/**
	 * 给字符串左边补0
	 * @param str 要进行补0操作的字符串
	 * @param length 要补到的位数
	 * @return 补充完的字符串
	 * 如字符串st="789",length=10,则方法返回为"0000000789"
	 */
	public static String supplementZero(String str,int length){
		int len = length - str.length();
		StringBuffer zero = new StringBuffer("");
		if( len > 0 ){
			for(int i=0;i<len;i++){
				zero.append("0");
			}
		}
		return zero.append(str).toString();
	}
	
	/**
	 * 给字符串左边补0
	 * @param obj 要进行补0操作的对象
	 * @param length 要补到的位数
	 * @return 补充完的字符串
	 * 如字符串st="789",length=10,则方法返回为"0000000789"
	 */
	public static String supplementZero(Object obj,int length){
		String str = checkObjForStr(obj);
		int len = length - str.length();
		StringBuffer zero = new StringBuffer("");
		if( len > 0 ){
			for(int i=0;i<len;i++){
				zero.append("0");
			}
		}
		return zero.append(str).toString();
	}
	
	/**
	 * @MethodName: replaceDateStr
	 * @Description: 快速替换日期字符串中的"-"和"："
	 * @author: tengfeiyu
	 * @date 2015-1-14 下午04:16:15
	 * @param dateStr
	 * @return
	 */
	public static String replaceDateStr(String dateStr) {
		if (dateStr != null) {
			return dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		}
		return null;
	}
	/**
	 * 
	* @Title: separatorChar
	* @Description: TODO(linux和windows文件路径处理)
	* @param resource
	* @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String separatorChar(String resource) {// 对\\ 和 / 处理 window
		// 和liunx 不同
		if (File.separatorChar == '/') {
			resource = StringUtil.replace(resource, "\\", "/");
		} else {
			resource = StringUtil.replace(resource, "/", "\\");
		}
		return resource;
	}
	/**
	 * 
	* @Title: createOrderNo
	* @Description: 生成订单号
	* @author zhangwei
	* @date 2015-5-27 下午03:16:56
	* @return String    返回类型
	* @throws
	 */
	public static String createOrderNo(){
		String orderno = UUID.randomUUID().toString();
//		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.CHINA);
// 		String orderno="11"+df.format(new Date());
		return orderno;
	}
	
	/**
	 * 
	* @Title: createYQM
	* @Description: 生成推荐码
	* @author zhangwei
	* @date 2015-5-27 下午03:16:56
	* @return String    返回类型
	* @throws
	 */
	public static String createYQM(){
		String yqm="C";
		for (int i=0; i < 4; i++) {
			yqm += numbersAndLetters[randGen.nextInt(71)];
		}
		return yqm;
	}
	
	/**
	 * 
	* @Title: getYzm
	* @Description: 生成6位验证码
	* @author zhangwei
	* @date 2015-5-27 下午03:16:56
	* @return String    返回类型
	* @throws
	 */
	public static String getYzm(){
		Random random = new Random(System. currentTimeMillis());
		return String.valueOf(Math.abs(random.nextLong())).substring(0,6) ;
	}
	
	/**
	 * "######0.00" 两位小数
	 * @param format
	 * @return
	 */
	public static Double doubleFormat(Double num,String format){
		DecimalFormat    df   = new DecimalFormat(format); 
		return new Double(df.format(num));
	}
	
	
	/**
	 * 0出现的概率为%50
	 */
	public static double rate0 = 0.50;
	/**
	 * 1出现的概率为%20
	 */
	public static double rate1 = 0.20;
	/**
	 * 2出现的概率为%15
	 */
	public static double rate2 = 0.15;
	/**
	 * 3出现的概率为%10
	 */
	public static double rate3 = 0.10;
	/**
	 * 4出现的概率为%4
	 */
	public static double rate4 = 0.04;
	/**
	 * 5出现的概率为%1
	 */
	public static double rate5 = 0.01;

	/**
	 * Math.random()产生一个double型的随机数，判断一下 例如0出现的概率为%50，则介于0到0.50中间的返回0
	 * 
	 * @return int
	 * 
	 */
	public static int percentageRandom() {
		double randomNumber;
		randomNumber = Math.random();
		if (randomNumber >= 0 && randomNumber <= rate0) {
			return 0;
		} else if (randomNumber >= rate0 / 100 && randomNumber <= rate0 + rate1) {
			return 1;
		} else if (randomNumber >= rate0 + rate1
				&& randomNumber <= rate0 + rate1 + rate2) {
			return 2;
		} else if (randomNumber >= rate0 + rate1 + rate2
				&& randomNumber <= rate0 + rate1 + rate2 + rate3) {
			return 3;
		} else if (randomNumber >= rate0 + rate1 + rate2 + rate3
				&& randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4) {
			return 4;
		} else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4
				&& randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4
						+ rate5) {
			return 5;
		}
		return -1;
	}
	/**
	 * 要保存任意的utf8字符，数据必须用utf8mb4字符集，有些情况下，不能变更已选定的字符集，只好不得以而为之，把输入中的4个字节的utf8字符全部过滤掉，好在，utf8字符集中，汉字是3个字节的。
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	static public String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
		if(StringUtil.isEmpty(text))
		{
			return "";
		}
        byte[] bytes = text.getBytes("utf-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        int i = 0;
        while (i < bytes.length) {
            short b = bytes[i];
            if (b > 0) {
                buffer.put(bytes[i++]);
                continue;
            }
            b += 256;
            if ((b ^ 0xC0) >> 4 == 0) {
                buffer.put(bytes, i, 2);
                i += 2;
            }
            else if ((b ^ 0xE0) >> 4 == 0) {
                buffer.put(bytes, i, 3);
                i += 3;
            }
            else if ((b ^ 0xF0) >> 4 == 0) {
                i += 4;
            }
            else
            {
            	buffer.put(bytes[i]);//zj2015-10-20修改
            	i++;
            }
        }
        buffer.flip();
        return new String(buffer.array(), "utf-8");
    }
	
	public static String fourRandom(){
		int max=9999;
        int min=1000;
        Random random = new Random();
        return String.valueOf((random.nextInt(max)%(max-min+1) + min));
	}
	
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
        "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
        "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
        "W", "X", "Y", "Z" };

	//生成短8位UUID
	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
		    String str = uuid.substring(i * 4, i * 4 + 4);
		    int x = Integer.parseInt(str, 16);
		    shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}
	
	//形成hamc值，就是将map和密钥放入此函数即可
	public static String hmacSign(String aValue, String aKey) {
		byte k_ipad[] = new byte[64];
		byte k_opad[] = new byte[64];
		byte keyb[];
		byte value[];
		try {
			keyb = aKey.getBytes(encodingCharset);
			value = aValue.getBytes(encodingCharset);
		} catch (UnsupportedEncodingException e) {
			keyb = aKey.getBytes();
			value = aValue.getBytes();
		}
		Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
		Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
		for (int i = 0; i < keyb.length; i++) {
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);
			k_opad[i] = (byte) (keyb[i] ^ 0x5c);
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		md.update(k_ipad);
		md.update(value);
		byte dg[] = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 16);
		dg = md.digest();
		return toHex(dg);
	}
	
	public static String toHex(byte input[]) {
		if (input == null)
			return null;
		StringBuffer output = new StringBuffer(input.length * 2);
		for (int i = 0; i < input.length; i++) {
			int current = input[i] & 0xff;
			if (current < 16)
				output.append("0");
			output.append(Integer.toString(current, 16));
		}
		return output.toString();
	}
	
}
