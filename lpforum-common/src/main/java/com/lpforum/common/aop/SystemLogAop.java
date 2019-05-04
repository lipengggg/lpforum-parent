package com.lpforum.common.aop;

import com.alibaba.fastjson.JSON;
import com.lpforum.common.annotation.log.SystemLog;
import com.lpforum.common.entity.Log;
import com.lpforum.common.enums.EnumLogSenceType;
import com.lpforum.common.enums.EnumLogType;
import com.lpforum.common.utils.ThreadContextStoreUtil;
import com.lpforum.common.utils.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SystemLogAop {

    private static Logger logger = LoggerFactory.getLogger(SystemLogAop.class);

    @Pointcut("@annotation(com.lpforum.common.annotation.log.SystemLog)")
    public void systemAspect(){}


    @Autowired(required=false)
    private HttpServletRequest request;

    //@Autowired
    //private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 处理之前的日志
     * @param joinPoint
     */
    @Before("systemAspect()")
    public void doBefore(JoinPoint joinPoint){
        Log log = new Log();

        //获取参数
        Object[] args = joinPoint.getArgs();
        //获取注解
        SystemLog systemLog = getAnnotion(joinPoint);
        //获取注解描述
        String value = systemLog.value();
        //获取注解的层面
        EnumLogType logType = systemLog.logType();

        log.setStartDate(new Date());
        log.setDescription(value);
        log.setId(UuidUtil.getUuid());
        log.setMethodName(getMethodName(joinPoint));
        log.setRequestParams(JSON.toJSONString(args));
        log.setType(EnumLogSenceType.BEFORE);

        if(logType == EnumLogType.CONTROLLER){
            log.setRemoteAddr(request.getRemoteAddr());
            log.setRequestUri(request.getRequestURI());
            log.setProject(request.getLocalName());
            log.setMethod(request.getMethod());
        }else if (logType == EnumLogType.SERVICE){

        }else if (logType == EnumLogType.DAO){

        }
        ThreadContextStoreUtil.getInstance().set("log",log);
        logger.info(log.toString());
    }


    /**
     * 处理完成后的日志
     * @param joinPoint
     */
    @After("systemAspect()")
    public void doAfter(JoinPoint joinPoint){
    }

    /**
     * 返回时的日志处理
     * @param res
     */
    @AfterReturning(returning = "res",pointcut = "systemAspect()")
    public void doAfterReturn(JoinPoint point,Object res){
        Log log = (Log) ThreadContextStoreUtil.getInstance().get("log");
        log.setType(EnumLogSenceType.RETURN);
        log.setEndDate(new Date());
        log.setResponseParams(JSON.toJSONString(res));
        log.setMaxMemory(Runtime.getRuntime().maxMemory()/1024/1024);
        log.setFreeMemory(Runtime.getRuntime().freeMemory()/1024/1024);
        log.setMaxEnableMemory((Runtime.getRuntime().maxMemory()-
                Runtime.getRuntime().totalMemory()+
                Runtime.getRuntime().freeMemory())/1024/1024);
        log.setTotalMemory(Runtime.getRuntime().totalMemory()/1024/1024);
        logger.info(log.toString());
    }

    /**
     * 异常后的日志处理
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(throwing = "e",pointcut = "systemAspect()")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e){
        Log log = (Log) ThreadContextStoreUtil.getInstance().get("log");
        log.setException(e);
        log.setType(EnumLogSenceType.THROWING);
        log.setEndDate(new Date());
        logger.info(log.toString());
    }

    /**
     * 获取注解
     * @param joinPoint
     * @return
     */
    private SystemLog getAnnotion(JoinPoint joinPoint){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(SystemLog.class);
    }

    /**
     * 获取方法全名
     * @param joinPoint
     * @return
     */
    private String getMethodName(JoinPoint joinPoint){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getName();
    }

}
