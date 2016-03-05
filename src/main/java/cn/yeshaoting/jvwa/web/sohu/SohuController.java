package cn.yeshaoting.jvwa.web.sohu;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yeshaoting.jvwa.vo.Response;

/**
 * @description 搜狐第一届擂台赛
 * @author yeshaoting
 * @date Mar 4, 2016 9:02:21 PM
 */
@Controller
@RequestMapping("sohu")
@SuppressWarnings("rawtypes")
public class SohuController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = { "", "index" })
    public String index() {
        return "sohu/index";
    }
    
    @RequestMapping(value = { "admin", "admin/index" })
    public String admin() {
        return "sohu/admin";
    }

    @ResponseBody
    @RequestMapping(value = "stage2", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Response stage2(@RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password) {
        logger.info("username: {}, password: {}", username, password);

        try {
            String LOGIN_SQL_FORMAT = "select count(id) from user where username = '%s' and password = '%s'";
            String sql = String.format(LOGIN_SQL_FORMAT, username, password);
            
            Integer rows = jdbcTemplate.queryForObject(sql, Integer.class);
            if (rows == null || rows == 0) {
                return Response.build(HttpStatus.BAD_REQUEST, "用户名密码验证失败！");
            }

            logger.info("bingo sql: {}", sql);
            return Response.build(HttpStatus.OK, "恭喜，闯关成功~");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.build(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}
