package com.lpforum.common.annotation.log;

import com.lpforum.common.enums.EnumLogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog{
    String value() default "";//描述，格式：XXX管理--执行XXX操作
    EnumLogType logType() default EnumLogType.CONTROLLER;//所属日志层面
}
