package guru.springframework.msscbeerservice.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
            @Value("${inventory.username}") String inventoryUser,
            @Value("${inventory.password}") String inventoryPwd
    ){
        return new BasicAuthRequestInterceptor(inventoryUser,inventoryPwd);
    }
}
