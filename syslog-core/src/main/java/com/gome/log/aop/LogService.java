package com.gome.log.aop;


import java.lang.annotation.*;

/**
 * Created by zhangliewei on 2016/6/8.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogService {
    String biz() default "";


    ///
}
