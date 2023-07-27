package com.bit.springboard.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCutCommon {
	@Pointcut("execution(* com.bit.springboard.service..*Impl.*(..))")
	public void allPointcut() {};
}
