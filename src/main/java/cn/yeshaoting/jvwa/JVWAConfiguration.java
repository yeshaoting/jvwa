package cn.yeshaoting.jvwa;

import cn.yeshaoting.jvwa.util.interceptor.EnvironmentInterceptor;
import cn.yeshaoting.jvwa.util.interceptor.LoginInterceptor;
import cn.yeshaoting.jvwa.util.interceptor.StageInterceptor;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Priority;
import java.util.List;

/*
    description:
    author: yeshaoting
    time: 2020-06-20 08:28
*/
@Slf4j
@Configuration(value = "jvwaConfig}")
@Priority(Ordered.HIGHEST_PRECEDENCE)
public class JVWAConfiguration implements WebMvcConfigurer {

    @Autowired
    private EnvironmentInterceptor environmentInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private StageInterceptor stageInterceptor;

    @Bean
    public EnvironmentInterceptor initEnvironmentInterceptor() {
        return new EnvironmentInterceptor();
    }

    @Bean
    public LoginInterceptor initLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public StageInterceptor initStageInterceptor() {
        return new StageInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> staticPaths = Lists.newArrayList("/resources/**", "/js/**", "/css/**", "/img/**");
        registry.addInterceptor(environmentInterceptor).excludePathPatterns(staticPaths);
        registry.addInterceptor(loginInterceptor).excludePathPatterns(staticPaths);
        registry.addInterceptor(stageInterceptor).excludePathPatterns(staticPaths);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/WEB-INF/views/errors/error.jsp"));
                factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/WEB-INF/views/errors/error.jsp"));
                factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/views/errors/404.jsp"));
                factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/WEB-INF/views/errors/500.jsp"));
                factory.addErrorPages(new ErrorPage(java.lang.Throwable.class, "/WEB-INF/views/errors/error.jsp"));
            }
        };

    }
}
