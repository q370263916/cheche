package com.chechemotel.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CreateSceneid {
	/**
	 * 获取scneid（生成tickets所需参数）
	 * @param map
	 * @return
	 */
	
	public static String createScneid(Map<?, ?> map){
		StringBuffer buffer=new StringBuffer();
		for(Object key:map.keySet()){
			buffer.append(key);
			buffer.append(":");
			buffer.append(map.get(key));
			buffer.append("&");
		}
		return buffer.toString();
		
		
		
	
		
	}
	/**
	 * 获取scneid的参数（获取各参数对应值）
	 * @return
	 */
	public static Map<String,String> getScneid(String scneid){
		Map<String,String> map=new HashMap<String,String>();
		if(scneid.indexOf("&")!=-1){
			String [] s=scneid.split("&");
			for(int i=0;i<s.length;i++){
				String str=s[i];
				map.put(str.split(":")[0], str.split(":")[1]);
				
			}
		}
		
		return map;
		
	}
	
	public static void main(String args[]){
		Map map=new HashMap();
		map.put("name", "aa");
		map.put("password", "bb");
		
		System.out.println(getScneid("name:aa&"));
		System.out.println(createScneid(map));
		
	}

}
