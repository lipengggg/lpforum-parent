package com.lpforum.common.annotation;

import java.lang.annotation.*;

/**
 *
 * @author lipeng
 * @version Id: CheckData.java, v 0.1 2019/4/25 16:16 lipeng Exp $$
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckData {

    /**数据是否必须不为空**/
    boolean mustNotEmpty() default true;

    /**数据是否必须不为空**/
    boolean mustNotNull() default true;

    /**数据是否必须为数值**/
    boolean mustIsNumber() default true;

    /**校验字符串长度**/
    long length() default 0;

    /**校验类型是否匹配**/
    Class clazz() default Void.class;

    /**正则表达式匹配**/
    String regular() default "";
}
