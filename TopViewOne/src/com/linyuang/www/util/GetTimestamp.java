package com.linyuang.www.util;

import java.sql.Timestamp;

/**
 * @author Lenovo
 */
public class GetTimestamp {
    public static Timestamp getTimeStamp(){
        return new Timestamp(System.currentTimeMillis());
    }
}
