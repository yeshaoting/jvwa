package cn.yeshaoting.jvwa.vo;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

/**
 * @description 请求响应内容
 * @datetime 2014-01-14 19:32:41
 * @author yeshaoting
 */
public final class Response<T> {
  
  /** 状态码 */
  private final HttpStatus status;
  
  /** 响应消息 */
  private final String statusText;
  
  /** 返回数据 */
  private final T data;
  
  private Response(HttpStatus status, String statusText, T data) {
    this.status = status;
    this.statusText = statusText;
    this.data = data;
  }
  
  /**
   * default status 200
   */
  public static <T> Response<T> build() {
    HttpStatus status = HttpStatus.OK;
    return new Response<T>(status, status.getReasonPhrase(), null);
  }
  
  /**
   * 只有状态码
   * 
   * @param status - not null
   */
  public static <T> Response<T> build(HttpStatus status) {
    return new Response<T>(status, status.getReasonPhrase(), null);
  }
  
  /**
   * default status 200<br/>
   * 返回数据
   * 
   * @param data - not null and not be HttpStatus instance
   */
  public static <T> Response<T> build(T data) {
    Assert.state(!(data instanceof HttpStatus));
    HttpStatus status = HttpStatus.OK;
    return new Response<T>(status, status.getReasonPhrase(), data);
  }
  
  /**
   * 状态码和响应消息
   * 
   * @param status - not null
   * @param statusText
   */
  public static <T> Response<T> build(HttpStatus status, String statusText) {
    Assert.notNull(status);
    return new Response<T>(status, statusText, null);
  }
  
  /**
   * default status 200<br/>
   * 响应消息和返回数据
   * 
   * @param statusText - not null
   * @param data
   */
  public static <T> Response<T> build(String statusText, T data) {
    Assert.notNull(statusText);
    return new Response<T>(HttpStatus.OK, statusText, data);
  }
  
  /**
   * 状态码, 响应消息和返回数据
   * 
   * @param status
   * @param statusText
   * @param data
   */
  public static <T> Response<T> build(HttpStatus status, String statusText, T data) {
    return new Response<T>(status, statusText, data);
  }
  
  public int getStatus() {
    return status.value();
  }
  
  public String getStatusText() {
    return statusText;
  }
  
  public T getData() {
    return data;
  }
  
}
