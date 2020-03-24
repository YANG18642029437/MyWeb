package com.czxy.utils;

import java.util.UUID;

public class StringUtils {
    public static String UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
