package cn.yeshaoting.jvwa.util;

import cn.yeshaoting.jvwa.entity.User;

/**
 * @description
 * @author yeshaoting
 * @date Mar 6, 2016 10:03:01 AM
 */
public class ThreadLocalUtil<T> extends ThreadLocal<T> {

    public static final ThreadLocal<User> CACHE = new ThreadLocal<User>();
    
}
