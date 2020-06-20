package cn.yeshaoting.jvwa;

import cn.yeshaoting.jvwa.util.interceptor.EnvironmentInterceptor;
import cn.yeshaoting.jvwa.util.interceptor.LoginInterceptor;
import cn.yeshaoting.jvwa.util.interceptor.StageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Priority;

/*
    description:
    author: yeshaoting
    time: 2020-06-20 08:28
*/
@Slf4j
@Configuration(value = "jvwaConfig}")
@Priority(Ordered.HIGHEST_PRECEDENCE)
public class JVWAConfiguration {
//    extends WebMvcConfigurationSupport {


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor());
//        registry.addInterceptor(new StageInterceptor());
//        registry.addWebRequestInterceptor(new EnvironmentInterceptor()).addPathPatterns("/**").order(0);
//    }

//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableWebServerFactory factory) {
//                factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/WEB-INF/views/errors/error.jsp"));
//                factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/WEB-INF/views/errors/error.jsp"));
//                factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/views/errors/404.jsp"));
//                factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/WEB-INF/views/errors/500.jsp"));
//                factory.addErrorPages(new ErrorPage(java.lang.Throwable.class, "/WEB-INF/views/errors/error.jsp"));
//            }
//        };
//
//    }
}
