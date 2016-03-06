package cn.yeshaoting.jvwa.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yeshaoting.jvwa.util.interceptor.LoginRequired;

/**
 * @description 
 * @author yeshaoting
 * @date Mar 4, 2016 9:02:21 PM
 */
@Controller
@LoginRequired
public class IndexController {

    @Value("${server_url}")
    private String serverUrl;
    
    @RequestMapping(value = { "", "index" })
    public String index(Model model) {
        return "redirect:" + serverUrl + "/sohu/index";
    }

}
