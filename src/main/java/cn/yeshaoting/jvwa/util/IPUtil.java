/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * 
 */
package cn.yeshaoting.jvwa.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author zhangdongxiao
 * @created Jul 4, 2012
 *
 * @version 1.0
 */
public class IPUtil {

    protected static final Logger LOGGER = LoggerFactory.getLogger(IPUtil.class);

    /**
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        return getRealIpAddr(request);
    }

    private static String getRealIpAddr(HttpServletRequest request) {
        String ip = head(request, "X-Real-IP");
        if (ip != null && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = head(request, "X-Forwarded-For");
        if (ip != null) {
            int index = ip.indexOf(',');
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            return (index != -1) ? ip.substring(0, index) : ip;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static String head(HttpServletRequest req, String s) {
        if (!StringUtils.equals(s, "X-Forwarded-For")) {
            return null;
        }
        
        return req.getHeader(s);
    }

}
