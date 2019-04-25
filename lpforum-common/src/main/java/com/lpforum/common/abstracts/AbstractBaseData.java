package com.lpforum.common.abstracts;

import com.lpforum.common.annotation.CheckData;
import com.lpforum.common.enums.EnumErrorCode;
import com.lpforum.common.exception.LpforumException;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author lipeng
 * @version Id: AbstractBaseData.java, v 0.1 2019/4/25 17:52 lipeng Exp $$
 */
abstract public class AbstractBaseData implements Serializable{
    private static final long serialVersionUID = 180144651425340063L;

    private final Class<? extends AbstractBaseData> clazz;

    public AbstractBaseData(){
        clazz = this.getClass();
        this.checkData();
    }

    /**
     * @return
     */
    public void checkData() {
        try {
            //获取子类的所有域
            Field[] fields = clazz.getDeclaredFields();
            if(fields != null && fields.length > 0){
                for (Field field:fields) {
                    //获取域上的注解
                    CheckData checkData = field.getAnnotation(CheckData.class);
                    if(checkData != null){
                        //获取域的值
                        Object fieldValue = getFieldValue(field);
                        //校验是否非空
                        checkIsNotEmpty(checkData,fieldValue,field.getName());
                        //校验是否Null
                        checkIsNotNull(checkData,fieldValue,field.getName());
                        //校验是否为数值
                        checkIsNumber(checkData,fieldValue,field.getName());
                        //校验长度是否符合
                        checkDataLength(checkData,fieldValue,field.getName());
                        //校验类型
                        checkDataType(checkData,fieldValue,field.getName());
                        //校验正则表达式
                        checkDataRegular(checkData,fieldValue,field.getName());
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (LpforumException e){
            e.printStackTrace();
        }
    }

    private Object getFieldValue(Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(clazz != null){
            String fieldName = field.getName();
            Method method = clazz.getMethod(getMethod(fieldName));
            Object invoke = method.invoke(this);
            return invoke;
        }
        return null;
    }

    private String getMethod(String fieldName) {
        if(StringUtils.isNotEmpty(fieldName)){
            StringBuilder methodName = new StringBuilder();
            methodName.append("get");
            if(Character.isUpperCase(fieldName.charAt(0))){
                return methodName.append(fieldName).toString();
            } else{
                return methodName.append(Character.toUpperCase(fieldName.charAt(0))).append(fieldName.substring(1)).toString();
            }
        }
        return null;
    }

    private  void checkIsNotEmpty(CheckData checkData,Object value,String fieldName){
        boolean mustNotEmpty = checkData.mustNotEmpty();
        if(mustNotEmpty){
            //数据不为null
            if (value != null){
                //数据不为null，如果为字符串
                if(value instanceof String){
                    if(StringUtils.isNotEmpty((String)value)){
                        return;
                    }
                }
                //数据不为null，如果为集合
                if(value instanceof Collection){
                    if(!((Collection)value).isEmpty()){
                        return;
                    }
                }
                //数据不为null，如果为map
                if(value instanceof Map){
                    if(!((Map)value).isEmpty()){
                        return;
                    }
                }
            }
        }else {
            return;
        }

        throw new LpforumException(EnumErrorCode.DATA_IS_EMPTY.getCode(),
                fieldName+EnumErrorCode.DATA_IS_EMPTY.getDescription(),
                EnumErrorCode.DATA_IS_EMPTY.getEnumErrorType());
    }

    private  void checkIsNotNull(CheckData checkData,Object value,String fieldName){
        boolean mustNotNull = checkData.mustNotNull();
        if(mustNotNull){
            if(value != null){
                return;
            }
        }else {
            return;
        }

        throw new LpforumException(EnumErrorCode.DATA_IS_NULL.getCode(),
                fieldName+EnumErrorCode.DATA_IS_NULL.getDescription(),
                EnumErrorCode.DATA_IS_NULL.getEnumErrorType());
    }

    private  void checkIsNumber(CheckData checkData,Object value,String fieldName){
        boolean mustIsNumber = checkData.mustIsNumber();
        if(mustIsNumber){
            if(value != null){
                if(value instanceof String){
                    if(((String)value).matches("[0-9]*")){
                        return;
                    }
                }
                throw new LpforumException(EnumErrorCode.DATA_IS_NOT_STRING.getCode(),
                        fieldName+EnumErrorCode.DATA_IS_NOT_STRING.getDescription(),
                        EnumErrorCode.DATA_IS_NOT_STRING.getEnumErrorType());
            }
        }else {
            return;
        }

        throw new LpforumException(EnumErrorCode.DATA_IS_NOT_NUMBER.getCode(),
                fieldName+EnumErrorCode.DATA_IS_NOT_NUMBER.getDescription(),
                EnumErrorCode.DATA_IS_NOT_NUMBER.getEnumErrorType());
    }

    private  void checkDataLength(CheckData checkData,Object value,String fieldName){
        long length = checkData.length();
        if(length > 0){
            if(value != null){
                if(value instanceof String){
                    if(((String)value).length() == length){
                        return;
                    }
                }
                throw new LpforumException(EnumErrorCode.DATA_IS_NOT_STRING.getCode(),
                        fieldName+EnumErrorCode.DATA_IS_NOT_STRING.getDescription(),
                        EnumErrorCode.DATA_IS_NOT_STRING.getEnumErrorType());
            }
        }else {
            return;
        }

        throw new LpforumException(EnumErrorCode.DATA_IS_NOT_MACTH_LENGTH.getCode(),
                fieldName+EnumErrorCode.DATA_IS_NOT_MACTH_LENGTH.getDescription(),
                EnumErrorCode.DATA_IS_NOT_MACTH_LENGTH.getEnumErrorType());
    }

    private  void checkDataType(CheckData checkData,Object value,String fieldName){
        Class clazz = checkData.clazz();
        if(!clazz.getName().equals(Void.class.getName())){
            if(value != null){
                if(clazz.getName().equals(value.getClass().getName())){
                    return;
                }
            }
        }

        throw new LpforumException(EnumErrorCode.DATA_IS_NOT_MACTH_TYPE.getCode(),
                fieldName+EnumErrorCode.DATA_IS_NOT_MACTH_TYPE.getDescription(),
                EnumErrorCode.DATA_IS_NOT_MACTH_TYPE.getEnumErrorType());
    }

    private  void checkDataRegular(CheckData checkData,Object value,String fieldName){
        String regular = checkData.regular();
        if(value != null){
            if(value instanceof String){
                if(((String)value).matches(regular)){
                    return;
                }
            }
            throw new LpforumException(EnumErrorCode.DATA_IS_NOT_STRING.getCode(),
                    fieldName+EnumErrorCode.DATA_IS_NOT_STRING.getDescription(),
                    EnumErrorCode.DATA_IS_NOT_STRING.getEnumErrorType());
        }
        throw new LpforumException(EnumErrorCode.DATA_IS_NOT_MACTH_REGULAR.getCode(),
                fieldName+EnumErrorCode.DATA_IS_NOT_MACTH_REGULAR.getDescription(),
                EnumErrorCode.DATA_IS_NOT_MACTH_REGULAR.getEnumErrorType());
    }

}
