package com.util;

import java.lang.annotation.*;

/**
 * @Descrption该注解是标签型注解，被此注解标注的方法需要进行权限校验
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface PageCode {
    /**
     * @Description功能Id的参数索引位置  默认为0，表示功能id在第一个参数的位置上，-1则表示未提供，无法进行校验
     */
    int idx() default 0;
}