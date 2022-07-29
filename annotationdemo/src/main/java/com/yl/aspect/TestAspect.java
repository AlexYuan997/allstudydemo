package com.yl.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TestAspect {


    @Pointcut("@annotation(com.yl.annotationtest.AfterAciveTest)")
    public void afterPointAcive(){
    }

    @Pointcut(" execution(* com.yl.controller.*.*(..))")
    public void afterPointAciveTwo(){
    }

    @After("afterPointAcive()")
    public void testForAfter(){
        System.out.println("executed afterPointAcive().............");
    }

    @Before("afterPointAciveTwo()")
    public void testForBefore(){
        System.out.println("executed testForBefore().............");
    }

    @Around("afterPointAcive()")
    public Object testForArroud(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("testForArroud acive..........................");
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法参数类型数组
        Class[] paramTypeArray = methodSignature.getParameterTypes();
        // if (EntityManager.class.isAssignableFrom(paramTypeArray[paramTypeArray.length - 1])) {
        //     //如果方法的参数列表最后一个参数是entityManager类型，则给其赋值
        //     args[args.length - 1] = entityManager;
        // }
        //改变输入参数
        args[0]=123;
        args[1]="dsadsadsadsadsad";

        log.info("请求参数为{}",args);
        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        Object result = joinPoint.proceed(args);
        log.info("响应结果为{}",result);
        //如果这里不返回result，则目标对象实际返回值会被置为null
        return result;
    }

}
