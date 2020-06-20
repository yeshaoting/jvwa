package cn.yeshaoting.jvwa.util.interceptor;

import cn.yeshaoting.jvwa.context.Constants;
import cn.yeshaoting.jvwa.entity.User;
import cn.yeshaoting.jvwa.mapper.UserMapper;
import cn.yeshaoting.jvwa.util.CookieUtils;
import cn.yeshaoting.jvwa.util.ThreadLocalUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server_url}")
    private String serverUrl;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler == null || !(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired clazzAnnotation = handlerMethod.getBean().getClass().getAnnotation(LoginRequired.class);
        LoginRequired methodAnnotation = handlerMethod.getMethodAnnotation(LoginRequired.class);
        if (clazzAnnotation == null && methodAnnotation == null) {
            return true;
        }

        String authorizeUrl = serverUrl + "/user/login";
        try {
            String username = CookieUtils.getCookieValue(Constants.AUTH_COOKIE_KEY, request);
            if (StringUtils.isBlank(username)) {
                throw new IllegalStateException("cookie不包含任何必须的登录信息！");
            }

            User user = userMapper.findUserByUsername(username);
            if (user == null) {
                throw new IllegalStateException("用户" + username + "不存在，请先登记！");
            }

            ThreadLocalUtil.CACHE.set(user);
            request.setAttribute("user", user);
            request.setAttribute("username", user.getUsername());
            return true;
        } catch (IllegalStateException e) {
            logger.debug(e.getMessage(), e);
            response.sendRedirect(authorizeUrl);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendRedirect(authorizeUrl);
        }

        return false;

    }

}
