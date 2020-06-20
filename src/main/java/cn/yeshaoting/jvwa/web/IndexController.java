package cn.yeshaoting.jvwa.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author yeshaoting
 * @description
 * @date Mar 4,2016 9:02:21 PM
 */
@Controller
@Slf4j
public class IndexController {

    @Value("${server_url}")
    private String serverUrl;

    @RequestMapping(value = {"", "index"})
    public String index() {
        return "redirect:" + serverUrl + "/security/index";
    }

    @ResponseBody
    @RequestMapping(path = {"/ping"})
    public String ping() {
        log.info("server is avaliable");
        return "ACK";
    }

}
