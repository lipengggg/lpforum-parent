package com.lpforum.common.utils;

import com.lpforum.common.abstracts.AbstractThreadContext;

import java.util.Map;

/**
 *
 * @author lipeng
 * @version Id: ThreadContextStoreUtil.java, v 0.1 2019/4/25 11:46 lipeng Exp $$
 */
public class ThreadContextStoreUtil extends AbstractThreadContext{
    private static ThreadLocal<Map<String,Object>> threadContext = new ThreadLocal<Map<String,Object>>();

    private volatile static ThreadContextStoreUtil app = null;

    private ThreadContextStoreUtil(){}

    public static synchronized ThreadContextStoreUtil getInstance() {
        if (app == null) {
            synchronized (ThreadContextStoreUtil.class) {
                if (app == null) {
                    app = new ThreadContextStoreUtil();
                }
            }
        }
        return app;
    }

    @Override
    protected ThreadLocal<Map<String, Object>> getThreadContext() {
        return threadContext;
    }
}
