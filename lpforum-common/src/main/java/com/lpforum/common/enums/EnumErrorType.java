package com.lpforum.common.enums;

/**
 *
 * @author lipeng
 * @version Id: EnumErrorType.java, v 0.1 2019/4/25 16:13 lipeng Exp $$
 */
public enum EnumErrorType {
    /** 流程错误 **/
    FLOW_ERR("HSJRY_FLOW_ERR", "流程错误"),
    /** 验证错误 **/
    VALIDATE_ERR("HSJRY_VALIDATE_ERR", "验证错误"),
    /** 系统错误**/
    SYS_ERR("HSJRY_SYS_ERR", "系统错误"),
    /** 通讯异常**/
    RPC_ERR("HSJRY_RPC_ERR", "通讯异常"),
    /** 数据校验异常**/
    CHECK_DATA_ERR("CHECK_DATA_ERR", "数据校验异常")
    ;

    /** 状态码 **/
    private String code;
    /** 状态描述 **/
    private String description;

    private EnumErrorType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码查找枚举
     *
     * @param code
     *            编码
     * @return {@link EnumErrorType} 实例
     **/
    public static EnumErrorType find(String code) {
        for (EnumErrorType frs : EnumErrorType.values()) {
            if (frs.getCode().equals(code)) {
                return frs;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
