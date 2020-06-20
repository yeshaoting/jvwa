package cn.yeshaoting.jvwa;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
    description:
    author: yeshaoting
    time: 2020-06-20 08:14
*/
@Slf4j
@EnableRetry
@SpringBootApplication(scanBasePackages = "cn.yeshaoting.jvwa")
@MapperScan("cn.yeshaoting.jvwa.mapper")
public class JVWAApplication {

    public static void main(final String[] args) {
        log.info("start jvwa application");
        SpringApplication.run(JVWAApplication.class, args);
        log.info("finish jvwa application");
    }
}
