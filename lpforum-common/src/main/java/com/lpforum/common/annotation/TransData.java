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
public @interface TransData {


}
