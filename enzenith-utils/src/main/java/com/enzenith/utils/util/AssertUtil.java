package com.enzenith.utils.util;



/**
 * AssertUtil
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:32
 **/
public class AssertUtil {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
