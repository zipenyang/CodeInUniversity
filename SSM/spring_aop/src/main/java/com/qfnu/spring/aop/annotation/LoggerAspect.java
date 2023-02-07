package com.qfnu.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 1、在切面中，需要通过指定的注解将方法标识为通知方法
 * 、@Before:前置通知，在目标对象方法执行之前执行
 *、@After：后置通知，在目标对象方法的finally字句中执行
 * 、@AfterReturning：返回通知，在目标对象方法返回值之后执行
 *
 *
 *2、切入点表达式：设置在标识通知的注解的value属性中
 * execution(public int com.qfnu.spring.aop.annotation.CalculatorImpl.add(int,int))
 * execution(* com.qfnu.spring.aop.annotation.CalculatorImpl.*(..))
 * 第一个*表示设置在访问修饰符和返回值类型
 * 第二个*表示类中任意的方法
 * ..表示任意的参数列表
 *累的地方也可以使用*，表示包下所有的类
 *
 *
 * 3、重用切入点表达式
 * 、@Pointcut声明一个公共的切入点表达式
 * 使用方法：@Before("pointCut()")
 *
 *
 * 4、获取链接点的信息
 * 在通知方法的参数位置，设置JoinPoint类型的参数，就可以获取连接点所对应的连接点的信息
 *
 */
@Component
@Aspect //将当前组件标识为切面
public class LoggerAspect {

    @Pointcut("execution(* com.qfnu.spring.aop.annotation.CalculatorImpl.*(..))")
    public void pointCut(){}


    @Before("pointCut()")
    public void beforeAdviceMethod(JoinPoint joinPoint){
        //获取连接点所对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        //获取连接点所对应方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("LoggerAspect,方法："+signature.getName()+",参数："+ Arrays.toString(args));
    }

    @After("pointCut()")
    public void afterAdviceMethod(JoinPoint joinPoint){
        //获取连接点所对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect,方法："+signature.getName()+",执行完毕");
    }

    @AfterReturning(value="pointCut()",returning = "result")
    public void afterReturningAdviceMethod(JoinPoint joinPoint,Object result){
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect,方法："+signature.getName()+",结果："+result);
    }


    @AfterThrowing(value = "pointCut()",throwing = "ex")
    public void afterThrowingAdviceMethod(JoinPoint joinPoint,Throwable ex){
        //获取连接点所对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect,方法："+signature.getName()+",异常："+ex);
    }

    @Around("pointCut()")
    public void aroundAdviceMethod(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            System.out.println("前置通知");
            result = joinPoint.proceed();
            System.out.println("返回通知");
        } catch (Throwable e) {
            System.out.println("异常通知");
        } finally {
            System.out.println("后置通知");
        }
    }

}
