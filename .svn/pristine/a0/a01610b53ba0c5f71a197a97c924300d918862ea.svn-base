package com.chechemotel.logs.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MongoDBValueMethod {
	// 处理的值类型
	MongoDBValueType[] type();

	// 编码或解码
	boolean encode();
}
