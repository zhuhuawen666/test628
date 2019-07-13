package com.qf.Interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//@Component
//@Aspect
public class Interceptor {

    public void before(JoinPoint joinPoint){
        Logger logger = Logger.getLogger("abc");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String time = simpleDateFormat.format(date);
        String cname = joinPoint.getTarget().getClass().getName();
        String mname = joinPoint.getSignature().getName();
        logger.warn(time + cname + "的"+ mname +"方法执行，这是before通知");
        System.out.println(time + cname + "的"+ mname +"方法执行，这是before通知");
    }
    public void afterthrowing(JoinPoint joinPoint,Throwable throwable){
        Logger logger = Logger.getLogger("abc");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String time = simpleDateFormat.format(date);
        String cname = joinPoint.getTarget().getClass().getName();
        String mname = joinPoint.getSignature().getName();
        String message = throwable.getMessage();
        logger.error(time + cname + "的"+ mname +"方法执行，这是afterthrowing通知。错误："+ message);
        System.out.println(time + cname + "的"+ mname +"方法执行，这是afterthrowing通知。错误："+ message);

    }
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Logger logger = Logger.getLogger("abc");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String time = simpleDateFormat.format(date);
        String cname = point.getTarget().getClass().getName();
        String mname = point.getSignature().getName();
        logger.warn(time + cname + "的"+ mname +"方法执行，这是通知around1");
        System.out.println(time + cname + "的"+ mname +"方法执行，这是around通知1");
        Object proceed = point.proceed();
        Date date1 = new Date();
        String time1 =simpleDateFormat.format(date1);
        logger.warn(time1 + cname + "的"+ mname +"方法执行，这是around通知2");
        System.out.println(time + cname + "的"+ mname +"方法执行，这是around通知2");
        return proceed;
    }
    public void after(JoinPoint joinPoint){
        Logger logger = Logger.getLogger("abc");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String time = simpleDateFormat.format(date);
        String cname = joinPoint.getTarget().getClass().getName();
        String mname = joinPoint.getSignature().getName();
        logger.warn(time + cname + "的"+ mname +"方法执行，这是after通知");
        System.out.println(time + cname + "的"+ mname +"方法执行，这是afert通知");
    }

    public void afterReturning(JoinPoint joinPoint){
        Logger logger = Logger.getLogger("abc");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String time = simpleDateFormat.format(date);
        String cname = joinPoint.getTarget().getClass().getName();
        String mname = joinPoint.getSignature().getName();
        logger.warn(time + cname + "的"+ mname +"方法执行，这是afterReturning通知");
        System.out.println(time + cname + "的"+ mname +"方法执行，这是afertReturning通知");
    }
}
