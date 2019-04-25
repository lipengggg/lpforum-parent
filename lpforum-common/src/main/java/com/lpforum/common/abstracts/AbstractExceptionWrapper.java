package com.lpforum.common.abstracts;

import com.lpforum.common.builder.ExceptionInfoBuilder;
import com.lpforum.common.entity.ExceptionInfo;
import com.lpforum.common.enums.EnumErrorType;

/**
 *
 * @author lipeng
 * @version Id: AbstractExceptionWrapper.java, v 0.1 2019/4/25 16:10 lipeng Exp $$
 */
abstract public class AbstractExceptionWrapper extends RuntimeException{
    private static final long serialVersionUID = -7337538045214606656L;

    public AbstractExceptionWrapper(ExceptionInfo info) {
        super("This rent id is '" + info.getTenantNo() + "' & module's name is '" + info.getModuleName() + "' && " +
                " make error code is '" + info.getErrorCode() + "' And errorType is " + info.getEnumErrorType().getCode() + ", " + info.getEnumErrorType().getDescription());
        this.exceptionInfo = info;
        this.msg = "This rent id is '" + info.getTenantNo() + "' & module's name is '" + info.getModuleName() + "' && " +
                " make error code is '" + info.getErrorCode() + "' And errorType is " + info.getEnumErrorType().getCode() + ", " + info.getEnumErrorType().getDescription();
        this.errorCode = info.getErrorCode();
        this.type = info.getEnumErrorType();
        this.tenantNo = info.getTenantNo();
        this.moduleName = info.getModuleName();
    }

    public AbstractExceptionWrapper(ExceptionInfo info, Throwable throwable) {
        super("This rent id is '" + info.getTenantNo() + "' & module's name is '" + info.getModuleName() + "' && " +
                " make error code is '" + info.getErrorCode() + "' And errorType is " + info.getEnumErrorType().getCode() + ", " + info.getEnumErrorType().getDescription(), throwable);
        this.exceptionInfo = info;
        this.msg = "This rent id is '" + info.getTenantNo() + "' & module's name is '" + info.getModuleName() + "' && " +
                " make error code is '" + info.getErrorCode() + "' And errorType is " + info.getEnumErrorType().getCode() + ", " + info.getEnumErrorType().getDescription();
        this.errorCode = info.getErrorCode();
        this.type = info.getEnumErrorType();
        this.tenantNo = info.getTenantNo();
        this.moduleName = info.getModuleName();
    }

    public AbstractExceptionWrapper(String msg, String errorCode,String tenantNo,String moduleName, EnumErrorType type) {
        super(msg);
        this.msg = msg;
        this.errorCode = errorCode;
        this.type = type;
        this.tenantNo = tenantNo;
        this.moduleName = moduleName;
        this.exceptionInfo = ExceptionInfoBuilder.getInstance().buildErrorCode(this.errorCode).buildErrorType(type).buildModuleName(moduleName).buildTenantNo(tenantNo).builder();
    }

    public AbstractExceptionWrapper(String msg, String errorCode,String tenantNo,String moduleName, EnumErrorType type, Throwable throwable) {
        super(msg, throwable);
        this.msg = msg;
        this.errorCode = errorCode;
        this.type = type;
        this.tenantNo = tenantNo;
        this.moduleName = moduleName;
        this.exceptionInfo = ExceptionInfoBuilder.getInstance().buildErrorCode(this.errorCode).buildErrorType(type).buildModuleName(moduleName).buildTenantNo(tenantNo).builder();
    }

    public String getMsg() {
        return msg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    // 消息
    private final String msg;
    // 错误码
    private final String errorCode;
    // 错误类型
    private final EnumErrorType type;
    // 异常信息
    private final ExceptionInfo exceptionInfo;
    // 租户
    private final String tenantNo;

    // 模块名
    private final String moduleName;

    public EnumErrorType getType() {
        return type;
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }

    public String getTenantNo() {
        return tenantNo;
    }

    @Override
    public String toString() {
        StringBuffer message = new StringBuffer();
        message.append("error message has " + this.msg);
        message.append(" & error's code is " + this.errorCode);
        message.append(" & error's type is " + type.getCode() + "," + type.getDescription());
        return message.toString();
    }
}
