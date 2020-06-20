package cn.yeshaoting.jvwa.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import cn.yeshaoting.jvwa.context.Constants;
import cn.yeshaoting.jvwa.entity.User;
import cn.yeshaoting.jvwa.util.ThreadLocalUtil;
import cn.yeshaoting.jvwa.util.exception.AuthorizedException;

public class StageInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server_url}")
    private String serverUrl;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        if (handler == null || !(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        StageValidation stageAnnotation = handlerMethod.getMethodAnnotation(StageValidation.class);
        if (stageAnnotation == null) {
            return true;
        }

        User user = ThreadLocalUtil.CACHE.get();
        if (!Constants.isOpenStage && user.getStage() + 1 < stageAnnotation.current()) {
            logger.warn("user: {} try to access unauthoritied stage: {}", JSON.toJSONString(user),
                    stageAnnotation.current());
            throw new AuthorizedException("无权限访问的关卡！");
        }

        String url = request.getRequestURL().toString();
        logger.info("current username: {}, requesting url: {}", user.getUsername(), url);
        return true;
    }
}
