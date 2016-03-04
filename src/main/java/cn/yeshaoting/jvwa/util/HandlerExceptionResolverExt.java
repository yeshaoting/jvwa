package cn.yeshaoting.jvwa.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.yeshaoting.jvwa.vo.Response;

/**
 * @description 异常处理
 * 
 * @author yeshaoting
 * @date 2015-08-20 10:28:45
 */
public class HandlerExceptionResolverExt implements HandlerExceptionResolver {
  
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  private final String DEFAULT_VIEW_NAME = "errors/500";
  
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    
    logger.error(ex.getMessage(), ex);
    
    try {
      if (handler == null) {
        throw new IllegalStateException("handler is null!");
      }
      
      HandlerMethod handlerMethod = null;
      if (handler instanceof HandlerMethod) {
        handlerMethod = (HandlerMethod) handler;
      }
      
      if (handlerMethod == null) {
        throw new IllegalStateException("handler is not a handler method!");
      }
      
      Method method = handlerMethod.getMethod();
      ResponseBody annotation = method.getAnnotation(ResponseBody.class);
      
      // 页面请求异常处理
      if (annotation == null) {
        return new ModelAndView("errors/500");
      }
      
      // 接口请求异常处理
      Response<Object> vo = Response.build(HttpStatus.INTERNAL_SERVER_ERROR, "系统内部错误！");
      request.setAttribute("response", JSON.toJSONString(vo));
      return new ModelAndView("errors/json");
      
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    
    return new ModelAndView(DEFAULT_VIEW_NAME);
  }
}
