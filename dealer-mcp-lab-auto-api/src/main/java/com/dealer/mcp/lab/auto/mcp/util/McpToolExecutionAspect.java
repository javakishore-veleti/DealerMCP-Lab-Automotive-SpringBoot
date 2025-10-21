package com.dealer.mcp.lab.auto.mcp.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class McpToolExecutionAspect {

    // 👇 this intercepts any method in classes under com.dealer.mcp.lab.auto.mcp.tools
    @Around("execution(* com.dealer.mcp.lab.auto.mcp.tools..*(..))")
    public Object profileToolExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("Tool {} executed in {} ms",
                    joinPoint.getSignature().toShortString(),
                    stopWatch.getTotalTimeMillis());
        }
    }
}