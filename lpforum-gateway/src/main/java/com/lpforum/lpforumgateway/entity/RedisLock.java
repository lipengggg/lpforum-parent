package com.lpforum.lpforumgateway.entity;

/**
 *
 * @author lipeng
 * @version Id: Lock.java, v 0.1 2019/4/26 17:40 lipeng Exp $$
 */
public class RedisLock {
    private String name;
    private String value;

    public RedisLock(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
