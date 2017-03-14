package com.chechemotel.util;
import java.io.ByteArrayOutputStream; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL; 
public class DownLoadUtil {
	    public static void main(String[] args) throws IOException {
	    	String url="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFT8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3BuVlBSNHZraTJueTU0VmQ1MTA2AAIEm6NKVgMEAAAAAA==";
	    	String address="F:/picture/tupian.jpg";
	    	downLoad(url,address);
	    } 
	    public static void downLoad(String urlStr,String address) throws IOException{
	    	URL url = null;
			try {
				url = new URL(urlStr);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
	        InputStream inputStream = conn.getInputStream();
	        byte[] getData=null;
	        byte[] buffer = new byte[1024]; 
	        int len = 0; 
	        ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
	        while((len = inputStream.read(buffer)) != -1) { 
	            bos.write(buffer, 0, len); 
	        }    
	        getData= bos.toByteArray(); 
	        File imageFile = new File(address);   
	        FileOutputStream fos = new FileOutputStream(imageFile);    
	        fos.write(getData); 
	        bos.close();
	        fos.close(); 
	    	
	    }
	}

