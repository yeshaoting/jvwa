package com.sohu.pvrs.school.context;

import org.apache.commons.io.Charsets;

/**
 * @description 公共常量
 * 
 * @author yeshaoting
 * @date 2015-07-24 10:34:02
 */
public class Constants {
  
  public final static int DEFAULT_RANK = 100;
  
  public final static int DEFAULT_PAGE_SIZE = 20;
  
  public static final String DEFAULT_ENCODING = Charsets.UTF_8.name();
  
  // 默认HttpClient 超时时间，单位毫秒
  public static final int DEFAULT_HTTP_TIMEOUT = 5000;
  
  // 分页最大内容数据
  public final static int MAX_CONTENT_SIZE = 2000;
  
  public final static String AUTH_COOKIE_KEY = "auth-info";
  
  public final static String AUTH_INFO_FIELD_SEP = "|";
  
  // 默认的认证过期时间，默认1天
  public final static int DEFAULT_AUTH_EXPIRY = 24 * 60 * 60;
  
  // 默认的认证过期时间，默认7天
  public final static int REMEMBER_AUTH_EXPIRY = 7 * 24 * 60 * 60;
  
}
