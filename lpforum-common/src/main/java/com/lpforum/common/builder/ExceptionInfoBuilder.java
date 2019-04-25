package com.lpforum.common.builder;

import com.google.common.base.Objects;
import com.lpforum.common.entity.ExceptionInfo;
import com.lpforum.common.enums.EnumErrorType;

/**
 *异常信息建造类
 * @author lipeng
 * @version Id: ExceptionInfoBuilder.java, v 0.1 2019/4/25 16:20 lipeng Exp $$
 */
public class ExceptionInfoBuilder {
    private final ExceptionInfo exceptionInfo = new ExceptionInfo();

    private ExceptionInfoBuilder() {
    }

    public static ExceptionInfoBuilder getInstance() {
        return new ExceptionInfoBuilder();
    }

    public ExceptionInfoBuilder buildErrorCode(String errorCode) {
        this.exceptionInfo.setErrorCode(errorCode);
        return this;
    }

    public ExceptionInfoBuilder buildErrorType(String errorType) {
        this.exceptionInfo.setEnumErrorType(errorType);
        return this;
    }

    public ExceptionInfoBuilder buildErrorType(EnumErrorType errorType) {
        this.exceptionInfo.setEnumErrorType(errorType);
        return this;
    }

    public ExceptionInfoBuilder buildTenantNo(String tenantNo) {
        this.exceptionInfo.setTenantNo(tenantNo);
        return this;
    }

    public ExceptionInfoBuilder buildModuleName(String moduleName) {
        this.exceptionInfo.setModuleName(moduleName);
        return this;
    }

    public ExceptionInfo builder() {
        List<ConstraintViolation> list = CheckUtil.getInstance().check(this.exceptionInfo).checkOver();
        String info = CheckUtil.getInstance().check(this.exceptionInfo).checkOverReturnStr();
        if (!Objects.equal((Object)null, list) && !Objects.equal(Integer.valueOf(0), list.size())) {
            throw new NullPointerException(info);
        } else {
            return this.exceptionInfo;
        }
    }
}
