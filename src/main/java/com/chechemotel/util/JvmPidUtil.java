package com.chechemotel.util;

import java.lang.management.ManagementFactory;

/**
 * 获取tomcat的进程id
 * @author ljw
 *
 */
public class JvmPidUtil {

	public static String getPid() {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		return name.split("@")[0];
	}
}
