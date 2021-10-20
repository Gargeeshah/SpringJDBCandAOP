package com.spring.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;


import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@EnableAspectJAutoProxy
public class StudentAspect {

    Logger logger = Logger.getLogger(StudentAspect.class);

    @Pointcut(value = "execution(* com.spring.dao.StudentDaoImpl.*(..))")
    public void manipulatingData() {
    }

    @Pointcut(value = "execution(* com.spring.dao.StudentDaoImpl.*(..))")
    public void gettingException() {
    }

    @Pointcut("execution(* com.spring.dao.StudentDaoImpl.findAllStudentsUsingRowMapper(..)) ||" +
            " execution(* com.spring.dao.StudentDaoImpl.findAllStudentsUsingResultSetExtractor(..))")
    public void calculateTime() {}

    @AfterReturning(value = "manipulatingData()")
    public void afterReturning(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("method invoked " + method + "succesfully");
    }

    @AfterThrowing("gettingException()")
    public void afterThrowing(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getDeclaringType().toString();
        logger.info("Exception in " + method);
    }

    @Around("calculateTime()")
    public List<Object> timeTaken(ProceedingJoinPoint proceedingJoinPoint) {

        List<Object> list = new ArrayList<>();
        try {
            String method = proceedingJoinPoint.getSignature().toString();
            long start = System.currentTimeMillis();

           list = (List<Object>) proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();

            logger.info(method + " completed in " + (end - start) + " seconds");

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return list;

    }


}

