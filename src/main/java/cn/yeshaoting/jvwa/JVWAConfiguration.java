package cn.yeshaoting.jvwa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.annotation.Priority;
import java.io.InputStream;

/*
    description:
    author: yeshaoting
    time: 2020-06-20 08:28
*/
@Slf4j
@Configuration(value = "jvwaConfig}")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Priority(Ordered.HIGHEST_PRECEDENCE)
public class JVWAConfiguration {


//    @Bean(name = "apiBanking")
//    public ApiBanking createApiBanking() throws Exception {
//        log.info("start create api banking bean, env={}, jksPath={}", serviceEnv, jksPath);
//        ApiBankingEnvironment env = ApiBankingEnvironment.valueOf(serviceEnv);
//        InputStream keyStore = this.getClass().getResourceAsStream(jksPath);
//
//        JWT.applyIssueTimeOffset(offset);
//        JWT.applyJwtIssueTimeDuration(duration);
//        ApiBanking apiBanking = ApiBankingBuilder.$(env).withKeystore(keyStore, keystorePassword).withSignatureAlias(signatureAlias).encryptedResponses().build();
//        log.info("finish create api banking, env={}, jksPath={}", serviceEnv, jksPath);
//        return apiBanking;
//    }

}
