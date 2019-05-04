package com.lpforum.common.utils;

import java.util.UUID;

public class UuidUtil {
    public static String getUuid() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}
