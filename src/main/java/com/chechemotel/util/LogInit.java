package com.chechemotel.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.Properties;

/**
 * 初始化配置文件
 * Created by 方乙百 on 2016/10/21.
 */
public class LogInit {

    private static Logger logger = null;

    public LogInit(){
        if(logger == null){
            logger = createLogger();
        }
    }


    public Logger getLogger(){
        if(logger == null){
            logger = createLogger();
        }
        return logger;
    }

    public Logger createLogger(){
        Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream("/log4j.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PropertyConfigurator.configure(props);
        Logger logger = Logger.getLogger(this.getClass());
        return logger;
    }

    public void debug(Object message) {
        Logger logger = getLogger();
        logger.debug(message);
    }

    public void info(Object message) {
        Logger logger = getLogger();
        logger.info(message);
    }

    public void error(Object message) {
        Logger logger = getLogger();
        logger.error(message);
    }
    public void warn(String msg){
        Logger logger = getLogger();
        logger.warn(msg);
    }

    public void fatal(String msg){
        Logger logger = getLogger();
        logger.fatal(msg);
    }
}
