package com.chechemotel.util;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * 日志信息的优先级从高到低有ERROR、WARN、 INFO、DEBUG
 * 级别高的会把级别低的一起打印出来
 * 按照级别输出到不同文件配置文件
 * 定义自己的Appender类
 * 重写 isAsSevereAsThreshold(Priority priority)方法
 * 这样，进行唯一判断，只有当Threshold与priority一致时，才进行输出，就实现了真正Log4j按照级别输出日志文件。
 *
 * Created by fangyibai on 2016/10/21.
 */
public class LogAppender extends DailyRollingFileAppender {

    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        //只判断是否相等，而不判断优先级
        return this.getThreshold().equals(priority);
    }
}
