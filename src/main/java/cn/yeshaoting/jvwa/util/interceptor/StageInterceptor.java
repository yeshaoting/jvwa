package cn.yeshaoting.jvwa.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import cn.yeshaoting.jvwa.entity.User;
import cn.yeshaoting.jvwa.util.ThreadLocalUtil;

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
        if (user.getStage() + 1 < stageAnnotation.current()) {
            logger.warn("user: {} try to access unauthoried stage: {}", JSON.toJSONString(user),
                    stageAnnotation.current());
            throw new IllegalStateException("无权限访问的关卡！");
        }

        return true;
    }
}
