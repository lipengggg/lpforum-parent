package com.lpforum.common.enums;

public enum  EnumLogType {

    /** controller层日志 **/
    CONTROLLER(1, "控制层"),
    /** service **/
    SERVICE(2, "业务层"),
    /** DAO**/
    DAO(3, "数据层"),
    ;

    /** 状态码 **/
    private Integer code;
    /** 状态描述 **/
    private String description;

    private EnumLogType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码查找枚举
     *
     * @param code
     *            编码
     * @return {@link EnumLogType} 实例
     **/
    public static EnumLogType find(Integer code) {
        for (EnumLogType frs : EnumLogType.values()) {
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
