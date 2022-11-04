package cn.tedu.jsd2203.csmall.passport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDateTime;
import java.util.Date;

@Configuration
public class BeanConfig {

    @Lazy
    @Bean
    public static Date date(){
        System.out.println("BeanConfig.date()");
        return new Date();
    }

    @Bean
    public static LocalDateTime localDateTime(){
        System.out.println("BeanConfig.localDateTime()");
        return LocalDateTime.now();
    }


}
