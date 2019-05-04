package com.lpforum.common.enums;

public enum  EnumLogSenceType {

    /** 前置通知 **/
    BEFORE(1, "前置通知"),
    /** 后置通知 **/
    AFTER(2, "后置通知"),
    /** 返回通知**/
    RETURN(3, "返回通知"),
    /** 异常通知**/
    THROWING(4, "异常通知"),
    ;

    /** 状态码 **/
    private Integer code;
    /** 状态描述 **/
    private String description;

    private EnumLogSenceType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码查找枚举
     *
     * @param code
     *            编码
     * @return {@link EnumLogSenceType} 实例
     **/
    public static EnumLogSenceType find(Integer code) {
        for (EnumLogSenceType frs : EnumLogSenceType.values()) {
            if (frs.getCode().equals(code)) {
                return frs;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
