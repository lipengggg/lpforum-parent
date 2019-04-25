package com.lpforum.common.entity;

import com.lpforum.common.abstracts.AbstractBaseData;
import com.lpforum.common.annotation.CheckData;

public class Demo extends AbstractBaseData {

    @CheckData(mustIsNumber = false,mustNotEmpty = true)
    private String name;

    @CheckData(mustIsNumber = true)
    private String idCard;

    @CheckData(mustNotNull = true)
    private ExceptionInfo exceptionInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(ExceptionInfo exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }
}
