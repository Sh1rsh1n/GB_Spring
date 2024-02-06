package github.sh1rsh1n.seminar_8.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class TrackUserAspect {

    private Logger logger = Logger.getLogger(TrackUserAspect.class.getName());

    @Around("execution(* github.sh1rsh1n.seminar_8.controller.*.*(..))")
    public void loggingControllerMethodsWithArgs(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        System.out.println("Method " + methodName +
                " with parameters " + Arrays.asList(arguments) +
                " will execute");
    }

    @Around("@annotation(TrackUserAction)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature().getName() + " executed in " + executionTime + "ms");

        return proceed;
    }

    @AfterReturning(value = "@annotation(TrackUserAction)", returning = "returnedValue")
    public void loggingWhenCallTrackUserAction(Object returnedValue) {
        System.out.println("Method executed and returned " + returnedValue);
    }
}