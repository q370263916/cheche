package com.chechemotel.logs.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 编码
// @DBMethod(value="XXX" encode=true)
// static Object encodeXXX(Object obj)

// 解码
// @DBMethod(value="XXX" encode=false)
// static Object decodeXXX(Object obj)

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MongoDBMethod {
	// 处理的字段名
	String value();

	// 编码或解码
	boolean encode();
}
