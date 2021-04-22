package com.linyuang.www.annotation;

import java.lang.annotation.*;

/**
 * @author Lenovo
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id {
    String name();
    String type() default "String";
    int length() default 20;
}
