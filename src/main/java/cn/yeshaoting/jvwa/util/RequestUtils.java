package cn.yeshaoting.jvwa.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public class RequestUtils {

  /**
   * 获取cookie值。多个key，只要取到一个即可返回。
   * 
   * @param cookies
   * @param keys 多个key组成的集合
   * @return
   */
  public static String getCookieVal(Cookie[] cookies, Set<String> keys) {

    if (cookies != null && keys != null) {
      for (Cookie c : cookies) {
        if (keys.contains(c.getName())) {
          return c.getValue();
        }
      }
    }

    return null;
  }

  /**
   * 获取cookie值
   * 
   * @param cookies
   * @param key
   * @return
   */
  public static String getCookieVal(Cookie[] cookies, String key) {

    if (cookies != null && key != null) {
      for (Cookie c : cookies) {
        if (key.equals(c.getName())) {
          return c.getValue();
        }
      }
    }

    return null;
  }

  public static String getCookieVal(HttpServletRequest request, String key) {

    Cookie[] cookies = request.getCookies();
    return getCookieVal(cookies, key);
  }

  /**
   * 获取cookie剩余失效时间，单位秒。
   * 
   * @param cookies
   * @param key
   * @return
   */
  public static int getMaxAge(Cookie[] cookies, String key) {

    if (cookies != null && key != null) {
      for (Cookie c : cookies) {
        if (key.equals(c.getName())) {
          return c.getMaxAge();
        }
      }
    }

    return -1;
  }

  /**
   * 添加cookie
   * 
   * @param key
   * @param value
   * @param maxAge 单位秒
   * @param response
   */
  public static void addCookie(String key, String value, int maxAge,
      final HttpServletResponse response) {
    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(maxAge);
    // 项目所有目录均有效，这句很关键，否则不敢保证删除
    cookie.setPath("/");
    cookie.setDomain(".security.com");
    response.addCookie(cookie);
  }

  /**
   * 清除cookie
   * 
   * @param key
   * @param response
   */
  public static void clearCookie(String key, HttpServletResponse response) {

    addCookie(key, null, 0, response);
  }

  public static void clearCookie(final Cookie cookie, HttpServletResponse response) {
    cookie.setMaxAge(0);
    // 项目所有目录均有效，这句很关键，否则不敢保证删除
    cookie.setPath("/");
    cookie.setDomain(".security.com");
    response.addCookie(cookie);
  }

  public static List<String> toKeyValuePairs(Map<String, String> map) {
    List<String> pairs = Lists.newArrayList();
    for (Entry<String, String> param : map.entrySet()) {
      pairs.add(param.getKey() + "=" + param.getValue());
    }

    return pairs;
  }

  public static List<String> toValuePairs(Map<String, String> map) {
    List<String> pairs = Lists.newArrayList();
    for (Entry<String, String> param : map.entrySet()) {
      pairs.add(param.getValue());
    }

    return pairs;
  }

  public static String assembleQueryString(Map<String, String> params) {
    return StringUtils.join(toKeyValuePairs(params), "&");
  }

}
