package com.chechemotel.util;

/**
 * 日志工具类
 * Created by 方乙百 on 2016/10/24.
 */
public class LogUtil {

    private static LogInit logInit = null;

    public static LogInit getLogger() {
        if (logInit == null) {
            logInit = new LogInit();
        }
        return logInit;
    }

    public static void debug(String msg) {
        logInit = getLogger();
        logInit.debug(msg);
    }

    public static void info(String msg) {
        logInit = getLogger();
        logInit.info(msg);
    }

    public static void error(String msg) {
        logInit = getLogger();
        logInit.error(msg);
    }

    public static void warn(String msg) {
        logInit = getLogger();
        logInit.warn(msg);
    }

    public static void fatal(String msg) {
        logInit = getLogger();
        logInit.fatal(msg);
    }

}
