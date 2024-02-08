package github.sh1rsh1n.seminar_8.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Аспект, отслеживания действий пользователя
 * с методами контроллера и аннотриованых @TrackUserAction
 */
@Aspect
@Component
public class TrackUserAspect {

    private Logger logger = Logger.getLogger(TrackUserAspect.class.getName());

    /**
     * Метод отслеживает действия пользователя во всех методах контроллера
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* github.sh1rsh1n.seminar_8.controller.*.*(..))")
    public Object loggingControllerMethodsWithArgs(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info(String.format("Execute controller method. Call method: %s", methodName));
        return joinPoint.proceed();
    }

    /**
     * Метод, замеряет время выполнения метода, аннотриованого @TrackUserAction
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(TrackUserAction)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info(joinPoint.getSignature().getName() + " executed in " + executionTime + "ms");
        return proceed;
    }

    /**
     * Метод срабатывает, только если метод аннотриованый @TrackUserAction
     * вернет какое-то значение
     * @param returnedValue
     */
    @AfterReturning(value = "@annotation(TrackUserAction)", returning = "returnedValue")
    public void loggingWhenCallTrackUserAction(Object returnedValue) {
        logger.info("Method executed and returned " + returnedValue);
    }
}