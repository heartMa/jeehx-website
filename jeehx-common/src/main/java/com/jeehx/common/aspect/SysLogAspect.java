/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.jeehx.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.jeehx.common.web.BaseController;

/**
 * 系统日志，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
public class SysLogAspect {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 定义切入点，切入点为com.example.aop下的所有函数
	 */
	@Pointcut("execution(public * com.jeehx.modules.*.controller.*.*(..))")
	public void logPointCut() { 
		
	}
	
	/**
	 * 前置通知：在连接点之前执行的通知
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
		logger.debug("###############前置通知##############");
	}
	
	/**
	 * 
	 * 环绕通知： 
	 *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。 
	 *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型 
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		logger.debug("#############日志切面###############");
		return result;
	}
	
	/**
	 * 后置返回通知
	 * @param ret
	 * @throws Throwable
	 */
    @AfterReturning(returning = "ret",pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
    	logger.debug("RESPONSE : " + ret);
        logger.debug("###############后置返回通知##############");
    }
    
    /** 
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法） 
     * @param joinPoint 
     */  
    @After(value = "logPointCut()")  
    public void doAfterAdvice(JoinPoint joinPoint){
    	
    	//判断参数中是否有ModelMap类型，有则添加对应模板信息
		Object[] args = joinPoint.getArgs();//2.传参
		for (Object object : args) {
            if(object instanceof ModelMap) {
            	ModelMap map = (ModelMap)object;
            	map.put("template", BaseController.THEME);
            }
        }
		logger.debug("##################后置最终通知执行了#######################");  
    } 
    
    /**
     * 异常通知
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(pointcut = "logPointCut()",throwing = "exception")  
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
    	logger.debug("################发生异常了#############");
        //目标方法名：  
    	System.out.println(joinPoint.getSignature().getName());  
        if(exception instanceof NullPointerException){  
        	logger.debug("##############发生了空指针异常################"); 
        }  
    } 
}
