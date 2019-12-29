package com.cy.pj.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//运行时有效
@Retention(RetentionPolicy.RUNTIME)
//目标对象
@Target(ElementType.METHOD)
public @interface LogAnnotation {

	String operation() default "";
}
