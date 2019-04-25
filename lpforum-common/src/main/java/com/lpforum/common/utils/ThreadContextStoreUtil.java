package com.lpforum.common.utils;

import com.lpforum.common.abstracts.AbstractThreadContext;

import java.util.Map;

/**
 *
 * @author lipeng
 * @version Id: ThreadContextStoreUtil.java, v 0.1 2019/4/25 11:46 lipeng Exp $$
 */
public class ThreadContextStoreUtil extends AbstractThreadContext{
    private static ThreadLocal<Map<String, Object>> threadContext = new ThreadLocal();
    private static volatile ThreadContextStoreUtil app = null;

    private ThreadContextStoreUtil() {
    }

    public static synchronized ThreadContextStoreUtil getInstance() {
        if (app == null) {
            Class var0 = ThreadContextStoreUtil.class;
            synchronized(ThreadContextStoreUtil.class) {
                if (app == null) {
                    app = new ThreadContextStoreUtil();
                }
            }
        }

        return app;
    }

    protected ThreadLocal<Map<String, Object>> getThreadContext() {
        return threadContext;
    }
}
