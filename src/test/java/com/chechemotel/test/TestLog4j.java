package com.chechemotel.test;


import com.chechemotel.util.LogUtil;
import org.apache.log4j.Logger;

/**
 *
 * Created by fangyibai on 2016/10/21.
 */
public class TestLog4j {

    //private static Logger logger = Logger.getLogger(TestLog4j.class);

    public static void main(String[] args) {
       /* // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");*/

        LogUtil.info("info msg");
        LogUtil.debug("debug msg");
        LogUtil.error("error msg");
    }
}
