package com.linyuang.www.annotation;

import java.lang.annotation.*;

/**
 * @author
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    String name();
}
