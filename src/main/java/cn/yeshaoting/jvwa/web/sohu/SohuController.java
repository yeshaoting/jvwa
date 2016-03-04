package cn.yeshaoting.jvwa.web.sohu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description 搜狐第一届擂台赛
 * @author yeshaoting
 * @date Mar 4, 2016 9:02:21 PM
 */
@Controller
@RequestMapping("sohu")
public class SohuController {

    @RequestMapping(value = {"", "index"})
    public String index() {
        return "sohu/index";
    }
    
}
