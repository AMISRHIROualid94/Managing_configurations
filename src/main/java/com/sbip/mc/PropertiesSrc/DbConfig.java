package com.sbip.mc.PropertiesSrc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:dbConfig.properties")
public class DbConfig {
    @Autowired Environment environment;

    @Override
    public String toString() {
        return "Username: "+environment.getProperty("spring.datasource.username")+", Password: "+environment.getProperty("spring.datasource.password");
    }
}
