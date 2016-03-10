package cn.yeshaoting.jvwa.util.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PreSetEnvironmentVariableInterceptor implements HandlerInterceptor {
  
  @Value("${base_url}")
  private String BASE_URL;
  
  @Value("${server_url}")
  private String SERVER_URL;
  
  @Value("${index_url}")
  private String INDEX_URL;
  
  @Value("${resources_path}")
  private String RESOURCES_PATH;
  
  @Value("${scripts_path}")
  private String SCRIPTS_PATH;
  
  @Value("${styles_path}")
  private String STYLES_PATH;
  
  @Value("${images_path}")
  private String IMAGES_PATH;
  
  @Value("${resources_version}")
  private String RESOURCES_VERSION;
  
  @Value("${max_stage}")
  private int MAX_STAGE;
  
  @Value("${open_stage}")
  private boolean isOpenStage;
  
  private void init(HttpServletRequest request) {
    request.setAttribute("base_url", BASE_URL);
    request.setAttribute("server_url", SERVER_URL);
    request.setAttribute("index_url", INDEX_URL);
    
    request.setAttribute("resources_path", RESOURCES_PATH);
    request.setAttribute("scripts_path", SCRIPTS_PATH);
    request.setAttribute("styles_path", STYLES_PATH);
    request.setAttribute("images_path", IMAGES_PATH);
    request.setAttribute("resources_version", RESOURCES_VERSION);
    
    request.setAttribute("max_stage", MAX_STAGE);
    request.setAttribute("is_open_stage", isOpenStage);
  }
  
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    init(request);
    return true;
  }
  
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {}
  
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {}
  
}
