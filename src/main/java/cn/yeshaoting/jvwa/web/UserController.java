package cn.yeshaoting.jvwa.web;

import cn.yeshaoting.jvwa.context.Constants;
import cn.yeshaoting.jvwa.entity.User;
import cn.yeshaoting.jvwa.mapper.UserMapper;
import cn.yeshaoting.jvwa.util.CookieUtils;
import cn.yeshaoting.jvwa.util.ThreadLocalUtil;
import cn.yeshaoting.jvwa.util.interceptor.LoginRequired;
import cn.yeshaoting.jvwa.vo.Response;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @description
 * @author yeshaoting
 * @date Mar 6, 2016 8:42:34 AM
 */
@Controller
@RequestMapping("user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server_url}")
    private String serverUrl;

    @Resource
    private UserMapper userMapper;

    @RequestMapping(value = "logout")
    public String logout(Model model, HttpServletResponse response) {
        CookieUtils.removeCookie(Constants.AUTH_COOKIE_KEY, response);
        ThreadLocalUtil.CACHE.set(null);
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("errorMsg", "请输入用户名登记，用于记录你的进度!");
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username", required = false) String username,
            Model model, HttpServletResponse response) {
        if (StringUtils.isEmpty(username)) {
            model.addAttribute("errorMsg", "用户名不允许为空!");
            return "login";
        }
        
        String usernameAfterEscape = StringEscapeUtils.escapeHtml4(username);
        if (!StringUtils.equals(username, usernameAfterEscape)) {
            model.addAttribute("errorMsg", "用户名不允许包含非法字符!");
            return "login";
        }

        User existedUser = userMapper.findUserByUsername(username);
        if (existedUser == null) {
            User user = new User();
            user.setUsername(username);
            userMapper.insert(user);
            logger.info("成功登记用户: {}", username);
        } else {
            logger.info("用户名 {} 已经登记，直接进入！", username);
        }

        CookieUtils.addCookie(Constants.AUTH_COOKIE_KEY, username, -1, response);
        return "redirect:" + serverUrl + "/security/index";
    }

    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "info")
    public Response<User> info(Model model) {
        User user = ThreadLocalUtil.CACHE.get();
        return Response.build("成功获取当前用户信息", user);
    }

}
