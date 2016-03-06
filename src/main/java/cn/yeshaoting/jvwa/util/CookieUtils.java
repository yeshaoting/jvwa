package cn.yeshaoting.jvwa.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import cn.yeshaoting.jvwa.context.Constants;

/**
 * cookie的一些操作的工具类
 *
 * @author XiongHui
 */
public class CookieUtils {

    /**
     * <pre>
     * 获取cookie的值
     * may be return null
     * </pre>
     */
    public static String getCookieValue(String cookieName, HttpServletRequest request) {
        if (cookieName == null) {
            return null;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    String value = c.getValue();
                    return new String(Base64.decodeBase64(value));
                }
            }
        }

        return null;
    }

    /**
     * 获取cookie的失效日期
     */
    public static int getMaxAge(String cookieName, HttpServletRequest request) {
        if (cookieName == null) {
            return -1;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    return c.getMaxAge();
                }
            }
        }

        return -1;
    }

    /**
     * 添加cookie
     */
    public static void addCookie(String name, String value, HttpServletResponse response) {
        addCookie(name, value, Constants.DEFAULT_AUTH_EXPIRY, response);
    }

    /**
     * 添加cookie
     */
    public static void addRememberCookie(String name, String value, HttpServletResponse response) {
        addCookie(name, value, Constants.REMEMBER_AUTH_EXPIRY, response);
    }

    /**
     * 删除cookie
     */
    public static void removeCookie(String name, HttpServletResponse response) {
        addCookie(name, null, 0, response);
    }

    /**
     * 添加cookie
     */
    public static void addCookie(String name, String value, int time,
            HttpServletResponse response) {
        if (StringUtils.isNotEmpty(value)) {
            value = Base64.encodeBase64String(value.getBytes());
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(time);
        // 项目所有目录均有效，这句很关键，否则不敢保证删除
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
