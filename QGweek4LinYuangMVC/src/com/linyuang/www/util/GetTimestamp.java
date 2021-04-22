package com.linyuang.www.util;

import java.sql.Timestamp;

/**
 * @author 获取当前时间
 */
public class GetTimestamp {
    public static Timestamp getTimeStamp(){
        return new Timestamp(System.currentTimeMillis());
    }
}
