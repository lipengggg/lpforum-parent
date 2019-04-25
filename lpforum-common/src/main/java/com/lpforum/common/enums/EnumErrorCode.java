package com.lpforum.common.enums;

/**
 *
 * @author lipeng
 * @version Id: EnumErrorCode.java, v 0.1 2019/4/25 19:19 lipeng Exp $$
 */
public enum EnumErrorCode {

    CHECK_DATA_ERR("1000","数据校验错误",EnumErrorType.CHECK_DATA_ERR),
    DATA_IS_EMPTY("1001","数据为空",EnumErrorType.CHECK_DATA_ERR),
    DATA_IS_NULL("1002","数据为NULL",EnumErrorType.CHECK_DATA_ERR),
    DATA_IS_NOT_NUMBER("1003","数据为非数值",EnumErrorType.CHECK_DATA_ERR),
    DATA_IS_NOT_MACTH_LENGTH("1004","数据长度不匹配",EnumErrorType.CHECK_DATA_ERR),
    DATA_IS_NOT_MACTH_TYPE("1005","数据类型不匹配",EnumErrorType.CHECK_DATA_ERR),
    DATA_IS_NOT_MACTH_REGULAR("1006","数据与正则表达式不匹配",EnumErrorType.CHECK_DATA_ERR),
    ;

    /** 状态码 **/
    private String code;
    /** 状态描述 **/
    private String description;
    /**异常类型**/
    private EnumErrorType errorType;

    private EnumErrorCode(String code, String description,EnumErrorType errorType) {
        this.code = code;
        this.description = description;
        this.errorType = errorType;
    }

    public static EnumErrorCode find(String code) {
        for (EnumErrorCode frs : EnumErrorCode.values()) {
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

    public EnumErrorType getEnumErrorType() {
        return errorType;
    }
}
