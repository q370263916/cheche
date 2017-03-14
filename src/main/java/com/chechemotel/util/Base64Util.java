package com.chechemotel.util;

import org.apache.commons.codec.binary.Base64;



  public class Base64Util {
	public static String base64Encode(String str) throws Exception {
        String retStr = "";
//        if(StringUtils.isBlank(str)) {
//            return "";
//        }
        try{
            //BASE64加密算法
//            BASE64Encoder base64 = new BASE64Encoder();
//            byte[] xmlStr = str.getBytes();
//            retStr = base64.encode(xmlStr);
//        	Base64 base64 = new Base64();
        	retStr =new String(Base64.encodeBase64(str.getBytes()));
        }catch(Exception e){
            throw new RuntimeException("Base64编码 加密 失败！");
        }
        return retStr;
    }
   
    public static String base64Decode(String str) throws Exception{
//        if(StringUtils.isBlank(str)) {
//            return "";
//        }
        byte[] bt = null;
        String retStr = "";
        try{
//            BASE64Decoder decoder = new BASE64Decoder();
//            bt = decoder.decodeBuffer(str);
//            base64Decode(str)
            retStr = new String(Base64.decodeBase64(str.getBytes()),"gbk");
        }catch(Exception e){
            throw new RuntimeException("Base64解码失败");
        }
        return retStr;
    }
}
