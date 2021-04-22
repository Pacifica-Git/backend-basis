package com.linyuang.www.annotation;

import java.lang.annotation.*;

/**
 * @author 数据库表中除主键Id外的其他列
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String name();
    String type() default "string";
    int length() default 20;
}