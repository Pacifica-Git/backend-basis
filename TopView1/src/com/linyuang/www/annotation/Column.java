package com.linyuang.www.annotation;

import java.lang.annotation.*;

/**
 * @author Lenovo
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String name();
    String type() default "string";
    int length() default 20;
}