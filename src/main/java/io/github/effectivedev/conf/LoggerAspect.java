package io.github.effectivedev.conf;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@EnableAspectJAutoProxy
public class LoggerAspect {
    @Around("execution(* io.github.effectivedev.controller.*.*(..)) || execution(* io.github.effectivedev.service.*.*(..)) || execution(* io.github.effectivedev.repository.*.*(..))")
    public Object calculatePerformanceTime(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        String signature = proceedingJoinPoint.getSignature().toShortString();
        ANNOTATION_TYPE type = ANNOTATION_TYPE.ETC;
        if (signature.indexOf(ANNOTATION_TYPE.CONTROLLER.getName()) > -1) {
            type = ANNOTATION_TYPE.CONTROLLER;
        } else if (signature.indexOf(ANNOTATION_TYPE.SERVICE.getName()) > -1) {
            type = ANNOTATION_TYPE.SERVICE;
        } else if (signature.indexOf(ANNOTATION_TYPE.REPOSITORY.getName()) > -1) {
            type = ANNOTATION_TYPE.REPOSITORY;
        }
        String signatureShort = proceedingJoinPoint.getSignature().toShortString();
        log.info("{} [{}] {}", type.getStart(), type.getName(), signatureShort);
        try {
            long start = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();

            log.info("Execution Time : {}", (end - start));
        } catch (Throwable throwable) {
            log.error("exception! ");
        }
        log.info("{} [{}] {}", type.getEnd(), type.getName(), signatureShort);
        return result;
    }

    private enum ANNOTATION_TYPE {
        CONTROLLER("Controller", ">  ", "<  "),
        SERVICE("Service", ">> ", "<< "),
        REPOSITORY("Repository", ">>>", "<<<"),
        ETC("Etc", "", "");
        final private String name;
        final private String start;
        final private String end;

        ANNOTATION_TYPE(String name, String start, String end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }

        public String getName() {
            return name;
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }
    }
}

