package com.chechemotel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Administrator
 */
public class SmsSend {

	 
    public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
    /*
     * */
    public static void main(String[] args) {
    	String PostData="";
		try {
			//PostData = "userid=&account=wcs8611&password=654953&mobile=13630839917&sendTime=&content="+java.net.URLEncoder.encode("您的验证码：111111，请立即输入，有任何问题，请联系我们。【建服易车】","utf-8");
            SmsSend.sendMsg("13522227535","验证码为：11111 【车倍爽】");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//out.println(PostData);
    	String ret =SmsSend.SMS(PostData, "http://sms.chanzor.com:8001/sms.aspx?action=send");
    	System.out.println(ret);
		
	}
    public static String sendMsg(String mobile,String content) throws UnsupportedEncodingException{
    	if(StringUtils.isNotEmpty(mobile)){
    		String PostData = "userid=&account=989fba&password=3yag8m56qb&mobile="+mobile+"&sendTime&content="+java.net.URLEncoder.encode(content,"utf-8");
        	//String ret =SmsSend.SMS(PostData, "http://sms.chanzor.com:8001/sms.aspx?action=send");
        	String ret =SmsSend.SMS(PostData, "http://api.chanzor.com/sms.aspx?action=send");
            System.out.println(PostData);
            return ret;
    	}else{
    		return null;
    	}
    	
    }
    
}
