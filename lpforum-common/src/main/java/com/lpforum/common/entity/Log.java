package com.lpforum.common.entity;

import com.lpforum.common.enums.EnumLogSenceType;
import com.lpforum.common.exception.LpforumException;
import com.lpforum.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {

    private static final long serialVersionUID = -633995113083682032L;

    private String Id;                      //日志主键
    private EnumLogSenceType type;                 //日志类型
    private String description;     //日志描述
    private String remoteAddr;   //请求地址
    private String requestUri;      //URI
    private String method;          //请求方式
    private String methodName; //方法名
    private String project;            //项目名
    private String requestParams;//请求参数
    private Throwable exception;      //异常
    private Date startDate;           //开始时间
    private Date endDate;           //结束时间
    private Long costTime;         //耗时
    private String userId;            //用户ID
    private String responseParams;//响应参数
    private Long maxMemory;   //最大内存
    private Long totalMemory;   //已分配内存
    private Long freeMemory;    //已分配内存剩余空间
    private Long maxEnableMemory;//最大可用内存

    @Override
    public String toString() {
        StringBuffer log = new StringBuffer();
//        if(StringUtils.isNotEmpty(project)){
//            log.append("项目："+project);
//        }
        if(StringUtils.isNotEmpty(description)){
            log.append(description);
        }

        if(StringUtils.isNotEmpty(remoteAddr)){
            log.append(",请求IP："+remoteAddr);
        }
        if(StringUtils.isNotEmpty(requestUri)){
            log.append(",请求URI："+requestUri);
        }
        if(StringUtils.isNotEmpty(method)){
            log.append(",请求方式："+method);
        }
        if(StringUtils.isNotEmpty(methodName)){
            log.append(",接口名称："+methodName);
        }

        if(type != null){
            switch (type){
                case BEFORE:

                    if(startDate != null){
                        log.append(",请求开始时间："+ DateUtil.dateStr(startDate,"yyyy-MM-dd HH:mm:ss.SSS"));
                    }
                    if(StringUtils.isNotEmpty(requestParams)){
                        log.append(",请求参数："+requestParams);
                    }
                    break;
                case AFTER:
                    break;
                case RETURN:
                    if(endDate != null){
                        log.append(",请求结束时间："+ DateUtil.dateStr(endDate,"yyyy-MM-dd HH:mm:ss.SSS"));
                    }
                    if(endDate != null && startDate != null){
                        log.append(",耗时："+ (endDate.getTime() - startDate.getTime()) + "ms");
                    }
                    if(StringUtils.isNotEmpty(responseParams)){
                        log.append(",响应参数："+responseParams);
                    }
                    if(maxMemory != null){
                        log.append(",最大内存："+maxMemory + "M");
                    }
                    if(totalMemory != null){
                        log.append(",已分配内存："+totalMemory + "M");
                    }
                    if(freeMemory != null){
                        log.append(",已分配内存中的剩余空间："+freeMemory + "M");
                    }
                    if(maxEnableMemory != null){
                        log.append(",最大可用内存："+maxEnableMemory + "M");
                    }
                    break;
                case THROWING:
                    if(endDate != null){
                        log.append(",请求结束时间："+ DateUtil.dateStr(endDate,"yyyy-MM-dd HH:mm:ss.SSS"));
                    }
                    if(endDate != null && startDate != null){
                        log.append(",耗时："+ (endDate.getTime() - startDate.getTime()) + "ms");
                    }
                    if(exception != null){
                        if(exception instanceof LpforumException){
                            LpforumException lpforumException = (LpforumException)exception;
                            log.append(",请求异常,异常码："+lpforumException.getCode()+"，异常描述："+lpforumException.getErrMsg());
                        }else {
                            log.append(",请求异常："+exception.toString());
                        }
                    }
                    break;
                    default: super.toString();
            }
        }

        return log.toString();
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public EnumLogSenceType getType() {
        return type;
    }

    public void setType(EnumLogSenceType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResponseParams() {
        return responseParams;
    }

    public void setResponseParams(String responseParams) {
        this.responseParams = responseParams;
    }

    public Long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(Long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public Long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(Long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public Long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(Long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public Long getMaxEnableMemory() {
        return maxEnableMemory;
    }

    public void setMaxEnableMemory(Long maxEnableMemory) {
        this.maxEnableMemory = maxEnableMemory;
    }
}