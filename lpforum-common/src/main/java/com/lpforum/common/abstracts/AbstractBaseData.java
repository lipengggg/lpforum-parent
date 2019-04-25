package com.lpforum.common.abstracts;

import com.google.common.collect.Collections2;
import com.lpforum.common.annotation.CheckData;
import com.lpforum.common.enums.EnumErrorCode;
import com.lpforum.common.enums.EnumErrorType;
import com.lpforum.common.exception.LpforumException;
import com.lpforum.common.utils.ExceptionHandlerUtil;
import com.lpforum.common.utils.StringUtil;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

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
        this.toString();
    }

    /**
     * 当创建对象时调用toString方法校验数据
     * @return
     */
    @Override
    public String toString() {
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

                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return super.toString();
    }

    private Object getFieldValue(Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(clazz != null){
            String fieldName = field.getName();
            Method method = clazz.getMethod(getMethod(fieldName));
            return method.invoke(this);
        }
    }

    private String getMethod(String fieldName) {
        if(StringUtil.isNotEmpty(fieldName)){
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
                    if(){
                        return;
                    }
                }
                //数据不为null，如果为map

            }

        }

        throw new LpforumException(EnumErrorCode.DATA_IS_EMPTY.getCode(),
                fieldName+EnumErrorCode.DATA_IS_EMPTY.getDescription(),
                EnumErrorCode.DATA_IS_EMPTY.getEnumErrorType());
    }



}
