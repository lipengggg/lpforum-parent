package com.lpforum.common.abstracts;


import com.google.common.base.Objects;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 *
 * @author lipeng
 * @version Id: AbstractThreadContext.java, v 0.1 2019/4/25 11:41 lipeng Exp $$
 */
abstract public class AbstractThreadContext {
    protected abstract ThreadLocal<Map<String,Object>> getThreadContext();

    /**
     * 设置线程上下文键值
     *
     * @param key
     * @param o
     */
    public void set(final String key,final Object o) {
        ThreadLocal<Map<String,Object>> threadContext = getThreadContext();
        Map<String,Object> map = threadContext.get();

//        LOG.info("BEFORE SET 当前线程号为:" + Thread.currentThread().getName() + "  当前key对应的线程上下文："+ GsonUtil.objectToJsonString(map));
        if (Objects.equal(null, map)) {
            map = Maps.newHashMap();
        }
        map.put(key, o);
        if (o instanceof String) {
            map.put((String) o, Thread.currentThread().getName());
        }
        threadContext.set(map);
//        LOG.info("AFTER SET 当前线程号为:" + Thread.currentThread().getName() + "  当前key对应的线程上下文："+ GsonUtil.objectToJsonString(map));
        return ;
    }

    /**
     * 设置线程上下文键值
     *
     * @param key
     */
    public void removeKey(String key) {
        ThreadLocal<Map<String,Object>> threadContext = getThreadContext();
        Map<String,Object> map = threadContext.get();
        if (Objects.equal(null, map)) {
            map = Maps.newHashMap();
        }
        map.remove(key);
        threadContext.set(map);
        return ;
    }
    /**
     * 获得线程上下文对应key的值
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        ThreadLocal<Map<String,Object>> threadContext = getThreadContext();
        Map<String,Object> map = threadContext.get();
//        LOG.info("当前线程号为:" + Thread.currentThread().getName() + "  当前key对应的线程上下文："+ GsonUtil.objectToJsonString(map));
        if (Objects.equal(null, map)) {
            return null;
        } else {
            return map.get(key);
        }
    }
    /**
     * 清楚线程上下文
     *
     * @return
     */
    public void clean() {
        ThreadLocal<Map<String,Object>> threadContext = getThreadContext();
        Map<String,Object> map = threadContext.get();
        if (!Objects.equal(null, map)) {
            map.clear();
            threadContext.set(map);
        }
    }
}
