package com.chechemotel.logs.aop;



import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chechemotel.logs.service.MongoLogServiceI;
import com.chechemotel.logs.utils.UrlLogUtil;


/**
 * 切面编程(设置切面)
 * @author ljw
 *
 */
@Component
@Aspect
public class AopLogInterceptor {

	/**
	 * 设置那些实体类进行过滤
	 */
//	  @Pointcut("execution(* com.bingjian.weixin.*.servcie.impl.*.*(..))")
	@Pointcut("execution(* xxx.com.bingjian.weixin.*.*.service.impl.*.*(..))")
	public void pointcut() {}
	
	@Autowired
	private MongoLogServiceI mls;
	
	/**
	 * 执行访求前(第一步)
	 */
	@Before("pointcut()")
	public void before(JoinPoint point) {
		/*System.out.println("id:" + mls);
		System.out.println("@Before：模拟权限检查...");
        System.out.println("@Before：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());*/
		
		Map<String, Object> reqClassMethodObj = new HashMap<String, Object>();
		
		reqClassMethodObj.put("urlId", UrlLogUtil.urlLog.get() + "");
		reqClassMethodObj.put("tm", System.currentTimeMillis());
		reqClassMethodObj.put("classMethod", point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
		reqClassMethodObj.put("param", Arrays.toString(point.getArgs()));
		reqClassMethodObj.put("content", "begin");
		
		mls.insertClassMethodLog(reqClassMethodObj);
	}
	
	/**
	 * 执行后(第二步)
	 * @param point
	 */
	@After("pointcut()")
    public void releaseResource(JoinPoint point) {
        /*System.out.println("@After：模拟释放资源...");
        System.out.println("@After：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@After：被织入的目标对象为：" + point.getTarget());*/
    }
	
	/**
	 * 执行返回结果(第三步)
	 * @param point
	 * @param returnObj
	 */
	@AfterReturning(pointcut="pointcut()", returning="returnObj")
	public void after(JoinPoint point, Object returnObj){
		/*System.out.println("@AfterReturning：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
		System.out.println("@AfterReturning：参数为：" + Arrays.toString(point.getArgs()));
		System.out.println("@AfterReturning：返回值为：" + returnObj);
		System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());*/
		
		Map<String, Object> reqClassMethodObj = new HashMap<String, Object>();
		
		reqClassMethodObj.put("urlId", UrlLogUtil.urlLog.get() + "");
		reqClassMethodObj.put("tm", System.currentTimeMillis());
		reqClassMethodObj.put("classMethod", point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
		reqClassMethodObj.put("param", Arrays.toString(point.getArgs()));
		reqClassMethodObj.put("content", "end");
		
		mls.insertClassMethodLog(reqClassMethodObj);
	}
	
	/**
	 * 异常信息
	 * @param point
	 * @param excep
	 */
	@AfterThrowing(pointcut="pointcut()",throwing="excep")
	public void throwing(JoinPoint point, Exception excep) {
		/*System.out.println("@AfterThrowing：模拟发现异常...");
        System.out.println("@AfterThrowing：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        System.out.println("@AfterThrowing：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@AfterThrowing：被织入的目标对象为：" + point.getTarget());
        System.out.println("@AfterThrowing：异常：");
        excep.printStackTrace();*/
	}
}
