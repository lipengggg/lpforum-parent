package com.lpforum.common.utils;

import java.util.Map;

/**
 *
 * @author lipeng
 * @version Id: ChannelMsgUtil.java, v 0.1 2019/1/10 10:33 lipeng Exp $$
 */
public class ChannelMsgUtil {
    private static volatile ChannelMsgUtil channelMsg = null;

    private ChannelMsgUtil() {
    }

    public static synchronized ChannelMsgUtil getInstance() {
        if (channelMsg == null) {
            Class var0 = ChannelMsgUtil.class;
            synchronized(ChannelMsgUtil.class) {
                if (channelMsg == null) {
                    channelMsg = new ChannelMsgUtil();
                }
            }
        }
        return channelMsg;
    }

    public static void setChannelMsg(Map apiPlatformInfo) {
        ThreadContextStoreUtil.getInstance().set("channelNo", apiPlatformInfo.get("channelNo"));
        ThreadContextStoreUtil.getInstance().set("appId", apiPlatformInfo.get("appId"));
        ThreadContextStoreUtil.getInstance().set("publicKey", apiPlatformInfo.get("publicKey"));
        ThreadContextStoreUtil.getInstance().set("privateKey", apiPlatformInfo.get("privateKey"));
        ThreadContextStoreUtil.getInstance().set("productId", apiPlatformInfo.get("productId"));
        ThreadContextStoreUtil.getInstance().set("productNo", apiPlatformInfo.get("productNo"));
        ThreadContextStoreUtil.getInstance().set("riskChannel", apiPlatformInfo.get("riskChannel"));
        ThreadContextStoreUtil.getInstance().set("repaymentNotifyUrl", apiPlatformInfo.get("repaymentNotifyUrl"));
        ThreadContextStoreUtil.getInstance().set("mqConsumerCallbackAdapter", apiPlatformInfo.get("mqConsumerCallbackAdapter"));
    }

    public static void removeChannelMsg() {
        ThreadContextStoreUtil.getInstance().removeKey("channelNo");
        ThreadContextStoreUtil.getInstance().removeKey("appId");
        ThreadContextStoreUtil.getInstance().removeKey("publicKey");
        ThreadContextStoreUtil.getInstance().removeKey("privateKey");
        ThreadContextStoreUtil.getInstance().removeKey("productId");
        ThreadContextStoreUtil.getInstance().removeKey("productNo");
        ThreadContextStoreUtil.getInstance().removeKey("riskChannel");
        ThreadContextStoreUtil.getInstance().removeKey("repaymentNotifyUrl");
        ThreadContextStoreUtil.getInstance().removeKey("mqConsumerCallbackAdapter");
    }

    public static String getMqConsumerCallbackAdapter() {
        return (String)ThreadContextStoreUtil.getInstance().get("mqConsumerCallbackAdapter");
    }

    public static String getChannelNo() {
        return (String)ThreadContextStoreUtil.getInstance().get("channelNo");
    }

    public static void setChannelNo(String channelNo) {
        ThreadContextStoreUtil.getInstance().set("channelNo",channelNo);
    }

    public static String getAppId() {
        return (String)ThreadContextStoreUtil.getInstance().get("appId");
    }

    public static String getPublicKey() {
        return (String)ThreadContextStoreUtil.getInstance().get("publicKey");
    }

    public static String getProductId() {
        return (String)ThreadContextStoreUtil.getInstance().get("productId");
    }

    public static String getPrivateKey() {
        return (String)ThreadContextStoreUtil.getInstance().get("privateKey");
    }

    public static String getProductNo() {
        return (String)ThreadContextStoreUtil.getInstance().get("productNo");
    }

    public static String getRiskChannel() {
        return (String)ThreadContextStoreUtil.getInstance().get("riskChannel");
    }

    public static String getCallbackUrl() {
        return (String)ThreadContextStoreUtil.getInstance().get("repaymentNotifyUrl");
    }
}
