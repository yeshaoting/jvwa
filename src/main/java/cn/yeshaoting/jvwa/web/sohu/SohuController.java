package cn.yeshaoting.jvwa.web.sohu;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import cn.yeshaoting.jvwa.entity.User;
import cn.yeshaoting.jvwa.mapper.UserMapper;
import cn.yeshaoting.jvwa.params.Stage8Params;
import cn.yeshaoting.jvwa.util.IPUtil;
import cn.yeshaoting.jvwa.util.RequestUtils;
import cn.yeshaoting.jvwa.util.ThreadLocalUtil;
import cn.yeshaoting.jvwa.util.interceptor.LoginRequired;
import cn.yeshaoting.jvwa.util.interceptor.StageValidation;
import cn.yeshaoting.jvwa.vo.Response;

/**
 * @description 搜狐第一届擂台赛
 * @author yeshaoting
 * @date Mar 4, 2016 9:02:21 PM
 */
@Controller
@RequestMapping("sohu")
@SuppressWarnings("rawtypes")
@LoginRequired
public class SohuController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String LOGIN_SQL_FORMAT = "select count(id) from user2 where username = '%s' and password = '%s'";

    /**
     * 禁止登录的用户名
     */
    private static final String USERNAME = "laogong-wangsicong@163.com";

    /**
     * 正则表达式：验证手机号
     */
    private static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 初始短信验证码
     */
    private static AtomicInteger SMS_CODE = new AtomicInteger(RandomUtils.nextInt(100, 999));

    private static final String STAGE6_CODE = "sATa3HGe6";

    private static final Map<String, Integer> stage4MoneyMap = Maps.newConcurrentMap();
    private static final Map<Integer, Integer> stage4GoodsMap = Maps.newHashMap();
    private static final int stage4DefaultMoney = 5000000;

    private static final Map<String, String> stage6FileMap = Maps.newHashMap();
    
    private static final String STAGE7_IP = "127.0.0.1";
    
    private static final int STAGE8_TIME_LIMIT = 120;

    @Value("${max_stage}")
    private int MAX_STAGE;
    
    @Value("${open_stage}")
    private boolean isOpenStage;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private UserMapper userMapper;
    
    @Resource
    private HttpServletRequest request;
    
    @PostConstruct
    public void init() {
        stage4GoodsMap.put(1, 199);
        stage4GoodsMap.put(2, 1999);
        stage4GoodsMap.put(3, 7999999);

        stage6FileMap.put("../image.jpg", "classpath:file/image.jpg");
        stage6FileMap.put("stage6.php", "classpath:file/stage6.jsp");
    }

    @RequestMapping(value = { "", "index" })
    public String index(Model model) {
        return "sohu/index";
    }
    
    @RequestMapping(value = "switchOpenStage")
    public String switchOpenStage(Model model) {
        isOpenStage = !isOpenStage;
        return "sohu/index";
    }

    @RequestMapping(value = "stage/{id}")
    public String index(@PathVariable(value = "id") int id, Model model) {
        if (id <= 0 || id > MAX_STAGE) {
            return "sohu/dashboard";
        }

        User user = ThreadLocalUtil.CACHE.get();
        if (!isOpenStage && user.getStage() + 1 < id) {
            logger.warn("user: {} try to access unauthoritied stage: {}", JSON.toJSONString(user),
                    id);
            return "sohu/unauthoritied";
        }

        model.addAttribute("id", id);
        return "sohu/stage" + id;
    }

    @StageValidation(current = 1)
    @ResponseBody
    @RequestMapping(value = "stage1/pass", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Response passStage1() {
        upgrade(1);
        return Response.build(HttpStatus.OK);
    }

    @StageValidation(current = 2)
    @ResponseBody
    @RequestMapping(value = "stage2", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Response stage2(@RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password) {
        logger.info("username: {}, password: {}", username, password);

        try {
            String sql = String.format(LOGIN_SQL_FORMAT, username, password);

            logger.info("debug sql: {}", sql);
            if (StringUtils.equals(username, USERNAME)) {
                logger.warn("forbid username: {}", USERNAME);
                return Response.build(HttpStatus.BAD_REQUEST, "禁止登录的用户！");
            }

            Integer rows = jdbcTemplate.queryForObject(sql, Integer.class);
            if (rows == null || rows == 0) {
                return Response.build(HttpStatus.BAD_REQUEST, "用户名密码验证失败！");
            }

            upgrade(2);
            logger.info("bingo sql: {}", sql);
            return Response.build(HttpStatus.OK, "恭喜，闯关成功~");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.build(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    private void upgrade(int current) {
        if (isOpenStage) {
            logger.debug("开放所有关卡，不再记录用户闯关情况！");
            return;
        }
        
        User user = ThreadLocalUtil.CACHE.get();
        if (user.getStage() < current) {
            user.setStage(user.getStage() + 1);
            userMapper.replace(user);
        }

    }

    @StageValidation(current = 3)
    @RequestMapping(value = { "admin", "admin/index" })
    public String admin() {
        return "sohu/admin";
    }

    @StageValidation(current = 3)
    @ResponseBody
    @RequestMapping(value = "stage3/pass", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Response<Object> passStage3() {
        upgrade(3);
        return Response.build(HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "stage4/money", produces = "application/json;charset=UTF-8")
    public Response<Integer> stage4Money() {
        User user = ThreadLocalUtil.CACHE.get();
        Integer money = MapUtils.getIntValue(stage4MoneyMap, user.getUsername(),
                stage4DefaultMoney);
        return Response.build("成功查询所拥有的虚拟货币", money);
    }

    @StageValidation(current = 4)
    @ResponseBody
    @RequestMapping(value = "stage4/pass", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Response<Object> buyStage4(@RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "price", required = true) int price) {
        if (!stage4GoodsMap.containsKey(id)) {
            return Response.build(HttpStatus.BAD_REQUEST, "不存在的商品");
        }

        User user = ThreadLocalUtil.CACHE.get();
        int money = MapUtils.getIntValue(stage4MoneyMap, user.getUsername(), stage4DefaultMoney);
        if (money < stage4GoodsMap.get(id)) {
            return Response.build(HttpStatus.BAD_REQUEST, "很抱歉，你的虚拟货币不足以购买此件商品！");
        }

        stage4MoneyMap.put(user.getUsername(), money - price);

        if (id != 3) {
            return Response.build(HttpStatus.BAD_REQUEST, "购买商品，但是未购买价值最高的物品");
        }

        upgrade(4);
        return Response.build(HttpStatus.OK);
    }

    @StageValidation(current = 5)
    @ResponseBody
    @RequestMapping(value = "sms/code/verfiy", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Response verfiySmsCode(@RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "code", required = true) int code) {
        logger.debug("requesting phone: {}, code: {}, current code: {}", phone, code, SMS_CODE);

        if (StringUtils.isEmpty(phone) || !isMobile(phone)) {
            return Response.build(HttpStatus.BAD_REQUEST, "无效的手机号！");
        }

        if (code < 100 || code > 999) {
            return Response.build(HttpStatus.BAD_REQUEST, "无效的短信验证码！");
        }

        if (code != SMS_CODE.get()) {
            return Response.build(HttpStatus.BAD_REQUEST, "短信验证码不正确！");
        }

        logger.debug("requesting phone: {}, bingo code: {}", phone, SMS_CODE);
        upgrade(5);
        return Response.build(HttpStatus.OK);
    }

    @StageValidation(current = 5)
    @ResponseBody
    @RequestMapping(value = "sms/code/send", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Response sendSmsCode(@RequestParam(value = "phone", required = true) String phone) {
        logger.info("requesting phone: {}", phone);

        if (StringUtils.isEmpty(phone) || !isMobile(phone)) {
            return Response.build(HttpStatus.BAD_REQUEST, "无效的手机号！");
        }

        try {
            long millis = RandomUtils.nextLong(100, 800);
            Thread.sleep(millis);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        changeSmsCode();
        return Response.build(HttpStatus.OK);
    }

    /**
     * 随机切换3位短信验证码
     */
    private void changeSmsCode() {
        int originSmsCode = SMS_CODE.get();
        SMS_CODE = new AtomicInteger(RandomUtils.nextInt(100, 999));
        logger.warn("origin sms code: {}, current sms code: {}", originSmsCode, SMS_CODE);
    }

    /**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    private static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    @StageValidation(current = 6)
    @RequestMapping(value = "stage6.php")
    public void stage6Image(@RequestParam(value = "file", required = true) String file,
            HttpServletResponse response) throws IOException {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            if (!stage6FileMap.containsKey(file)) {
                logger.info("找不到对应的文件：{}", file);
                response.setContentType("text/plain");
                IOUtils.write("请求的文件不存在！".getBytes(), out);
                return;
            }

            if (StringUtils.endsWith(file, "php") || StringUtils.endsWith(stage6FileMap.get(file), "jsp")) {
                response.setContentType("text/plain");
            } else {
                response.setContentType("image/jpeg");
            }

            String filepath = stage6FileMap.get(file);
            File localFile = ResourceUtils.getFile(filepath);
            logger.debug("file: {}", localFile.getAbsolutePath());
            byte[] bytes = FileUtils.readFileToByteArray(localFile);
            if (ArrayUtils.isEmpty(bytes)) {
                return;
            }

            IOUtils.write(bytes, out);
        } finally {
            if (out != null) {
                out.flush();
                IOUtils.closeQuietly(out);
            }
        }

    }

    @StageValidation(current = 6)
    @ResponseBody
    @RequestMapping(value = "checkSecureCode", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Response checkSecureCode(@RequestParam(value = "code", required = true) String code) {
        logger.info("requesting code: {}", code);

        if (!StringUtils.equals(code, STAGE6_CODE)) {
            return Response.build(HttpStatus.BAD_REQUEST, "密码不正确！");
        }

        upgrade(6);
        return Response.build(HttpStatus.OK);
    }
    
    @StageValidation(current = 7)
    @ResponseBody
    @RequestMapping(value = "checkStage7Right", produces = "application/json;charset=UTF-8")
    public Response checkStage7Right() {
        String ip = IPUtil.getIp(request);
        
        logger.info("requesting ip: {}", ip);
        if (!StringUtils.equals(ip, STAGE7_IP)) {
            return Response.build(HttpStatus.BAD_REQUEST, "无权访问！");
        }

        upgrade(7);
        return Response.build(HttpStatus.OK);
    }
    
    private String getStage8Verfiy(int level, int unixTime) {
        User user = ThreadLocalUtil.CACHE.get();
        
        Map<String, String> params = Maps.newTreeMap();
        params.put("level", String.valueOf(level));
        params.put("username", user.getUsername());
        params.put("time", String.valueOf(unixTime));
        
        String text = RequestUtils.assembleQueryString(params);
        String stage8Verfiy = DigestUtils.md5Hex(text);
        
        logger.info("md5 text: {}, real md5: {}", text, stage8Verfiy);
        return stage8Verfiy;
    }
    
    /**
     * 返回当前时间的秒数
     *
     * @return
     */
    private static int unixTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }
    
    @StageValidation(current = 8)
    @ResponseBody
    @RequestMapping(value = "stage8", produces = "application/json;charset=UTF-8")
    public String passStage8(Stage8Params params) {
        int currentUnixTime = unixTime();
        
        logger.info("stage8 current time: {}, params: {}", currentUnixTime, JSON.toJSONString(params));
        if (currentUnixTime - params.getTime() >= STAGE8_TIME_LIMIT) {
            return "这是一次已经过期的提交，请在2分钟内完成请求提交。";
        }
        
        if (params.getLevel() != 8) {
            return "小样，试图通过本关突破其他关卡是没用的。";
        }
        
        User user = ThreadLocalUtil.CACHE.get();
        if (!StringUtils.equals(params.getUsername(), user.getUsername())) {
            return "别傻了，你在帮别人过关。";
        }
        
        String stage8Verfiy = getStage8Verfiy(params.getLevel(), params.getTime());
        if (!StringUtils.equals(params.getVerify(), stage8Verfiy)) {
            return "请求在服务器端验证失败。";
        }
        
        upgrade(8);
        return "恭喜通关，请通过dashboard页进入下一关。";
    }

}
