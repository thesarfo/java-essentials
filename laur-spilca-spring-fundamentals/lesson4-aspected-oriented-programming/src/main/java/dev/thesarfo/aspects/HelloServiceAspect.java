package dev.thesarfo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloServiceAspect {

    /* The annotations used on aspects are called join points */
    /* The place to which the aspect is applied, i.e the "excecution" of the method in the joinpoint's brackets are called point cuts*/

    /*intercept the hello method in the service and do something before/after/during it */

    @Before("execution(* dev.thesarfo.service.HelloService.hello(..))")
    public void before(){
        System.out.println("Before This method has been called");
    }

    /* after annotation executes whether an exception is thrown or not */
    @After("execution(* dev.thesarfo.service.HelloService.hello(..))")
    public void after(){
        System.out.println("After this method has been called");
    }

    /* afterReturning is only executed when an exception is not thrown */
    @AfterReturning("execution(* dev.thesarfo.service.HelloService.hello(..))")
    public void afterReturning(){
        System.out.println("After this method has been called without an exception");
    }

    /* afterThrowing is executed only when the method throws an exception */
    @AfterThrowing("execution(* dev.thesarfo.service.HelloService.hello(..))")
    public void afterThrowing(){
        System.out.println("After this method has been called with an exception");
    }

    /*Around makes you completely specify what you wanna do before, after, and even during the execution of the method, regardless of anything that happens */

    /* we use a return type of object because since aspects are decoupled from your app, you usually do not know the return type of the method that is being intercepted */
    /* the proceeding joinpoint is the instance of the method being intercepted */
    @Around("execution(* dev.thesarfo.service.HelloService.hello(..))")
    public Object  around(ProceedingJoinPoint joinPoint){
        System.out.println("A");
        Object result = null;
        try{
            result = joinPoint.proceed();
            System.out.println("B");
        } catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return result;
    }

}
