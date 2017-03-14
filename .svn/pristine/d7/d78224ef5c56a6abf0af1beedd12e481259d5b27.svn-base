package com.chechemotel.front.log;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.util.ResourceUtil;

import com.chechemotel.util.JvmPidUtil;





public class LogUtils {

	public static Map<String,Object> urlLog(HttpServletRequest request,String channel){
		
		Map<String, Object> urlMap = new HashMap<String, Object>();
		urlMap.put("reqUUID", (UUID.randomUUID()+ "").replaceAll("-", ""));
		urlMap.put("tomcatId", request.getServerName() + ":" + request.getServerPort());
		urlMap.put("appcontextId", request.getContextPath());
		urlMap.put("threadId", Thread.currentThread().getId() + "");
		urlMap.put("remoteAddr", request.getRemoteAddr());
		urlMap.put("reqUrl", ResourceUtil.getRequestPath(request));
		urlMap.put("pid", JvmPidUtil.getPid());
		urlMap.put("channel", channel);
		return urlMap;
	}
	public static Map<String,Object> urlLog(HttpServletRequest request){
		
		Map<String, Object> urlMap = new HashMap<String, Object>();
		urlMap.put("reqUUID", (UUID.randomUUID()+ "").replaceAll("-", ""));
		urlMap.put("tomcatId", request.getServerName() + ":" + request.getServerPort());
		urlMap.put("appcontextId", request.getContextPath());
		urlMap.put("threadId", Thread.currentThread().getId() + "");
		urlMap.put("remoteAddr", request.getRemoteAddr());
		urlMap.put("reqUrl", ResourceUtil.getRequestPath(request));
		urlMap.put("pid", JvmPidUtil.getPid());
		return urlMap;
	}
	
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> Bean2Map(Object javaBean) {  
        Map<K, V> ret = new HashMap<K, V>();  
        try {  
            Method[] methods = javaBean.getClass().getDeclaredMethods();  
            for (Method method : methods) {  
                if (method.getName().startsWith("get")) {  
                    String field = method.getName();  
                    field = field.substring(field.indexOf("get") + 3);  
                    field = field.toLowerCase().charAt(0) + field.substring(1);  
                    Object value = method.invoke(javaBean, (Object[]) null);  
                    if(value instanceof Date){
                    	value = ((Date)value).getTime();
                    }
                    ret.put((K) field, (V) (null == value ? "" : value));  
                }  
            }  
        } catch (Exception e) {  
        }  
        return ret;  
    }  
}
