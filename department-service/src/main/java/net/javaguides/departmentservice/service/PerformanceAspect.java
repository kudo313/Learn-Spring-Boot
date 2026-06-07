package net.javaguides.departmentservice.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* net.javaguides.departmentservice.service.DepartmentService.*(..))")
    public Object measurePerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Method " + joinPoint.getSignature().getName() + " took " + (endTime - startTime) + " ms");
        return result;
    }

    @Around("@annotation(net.javaguides.departmentservice.entity.TrackTime)")
    public Object measureCustomPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("Start service: Department" );
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Custom method " + joinPoint.getSignature().getName() + " took " + (endTime - startTime) + " ms");
        return result;
    }
}
