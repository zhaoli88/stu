package com.cy.pj.common.aspect;
import java.lang.reflect.Method;
import java.util.Date;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cy.pj.common.annotation.LogAnnotation;
import com.cy.pj.common.util.IPUtils;
import com.cy.pj.common.util.ShiroUtils;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 设置AOP切面控制的日子记录信息
 * @author 44734
 *
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("@annotation(com.cy.pj.common.annotation.LogAnnotation)")
	public void logPointCut() {}
	
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint jp) 
		throws Throwable{
			long start=System.currentTimeMillis();
			//执行目标方法(result为目标方法的执行结果)
	    	Object result=jp.proceed();
	    	long end=System.currentTimeMillis();
	    	//saveLog(joinPoint,(end-start));
	    	//将用户行为信息存储到数据库中的方法如下.
	    	saveSysLog(jp, (end-start));
			return result;
	}

	private void saveSysLog(ProceedingJoinPoint jp, long time) throws Exception {
		//1.获取用户行为日志(谁(ip+用户名)在什么时间访问了什么方法,传递什么参数,执行时长是多少等)
		 //2.1获取目标方法
		 //2.1.1获取方法签名(记录的是目标方法信息)
		 MethodSignature ms=(MethodSignature)jp.getSignature();
		 //2.1.2获取目标类型的字节码对象
		 Class<?> targetClass=jp.getTarget().getClass();
		 //2.1.3获取目标方法对象
		 Method targetMethod=
		 targetClass.getDeclaredMethod(ms.getName(),ms.getParameterTypes());
		 //2.1.4获取目标方法对象上的注解
		 LogAnnotation rLog=targetMethod.getAnnotation(LogAnnotation.class);
		 //2.1.5获取操作名
		 String operation=rLog.operation();
		 //2.1.5获取方法全名
		 String dType=targetClass.getName();
		 String methodName=ms.getName();
		 String targetClassMethod=dType+"."+methodName;
		 //2.2获取方法参数
		// String params=Arrays.toString(jp.getArgs());之前是获取tostring,现在获取Json数据
		 String params=//值为jason格式字符串
		 new ObjectMapper().writeValueAsString(jp.getArgs());
		 //2.对信息进行封装(SysLog)
		 SysLog log=new SysLog();
		 log.setIp(IPUtils.getIpAddr());
		 log.setName(ShiroUtils.getUsername());
		 log.setOperation(operation);
		 log.setMethod(targetClassMethod);//targetClass.targetMethod
		 log.setParams(params);
		 log.setTime(time);
		 log.setCreatedTime(new Date());
		 //3.将信息写入到数据库.
		 sysLogService.insertObject(log);
		
	}
	

}
