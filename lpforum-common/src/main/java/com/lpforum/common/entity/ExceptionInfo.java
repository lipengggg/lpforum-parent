package com.lpforum.common.entity;

import com.lpforum.common.abstracts.AbstractBaseData;
import com.lpforum.common.annotation.CheckData;
import com.lpforum.common.enums.EnumErrorType;

/**
 *
 * @author lipeng
 * @version Id: ExceptionInfo.java, v 0.1 2019/4/25 16:11 lipeng Exp $$
 */
public class ExceptionInfo{
    /** 错误码 **/
    @CheckData(mustIsNumber = true,mustNotEmpty = true)
    private String errorCode;
    /** 错误类型 **/
    private EnumErrorType errorType;
    /** 租户号 **/
    @CheckData(mustNotEmpty = true)
    private String tenantNo;
    /** 模块名称 **/
    private String moduleName;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public EnumErrorType getEnumErrorType() {
        return errorType;
    }

    public void setEnumErrorType(EnumErrorType errorType) {
        this.errorType = errorType;
    }

    public void setEnumErrorType(String errorType) {
        this.errorType = EnumErrorType.valueOf(errorType);
    }

    public String getTenantNo() {
        return tenantNo;
    }

    public void setTenantNo(String tenantNo) {
        this.tenantNo = tenantNo;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
