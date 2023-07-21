package com.sbip.mc.ConfigProp;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

@Data
@ToString
@ConstructorBinding
@ConfigurationProperties(prefix = "app.sbip.ct")
public class AppProperties {

    private final String name;
    private final String ip;
    private final int port;
    private final Security security;

    @Data
    @ToString
    public static class Security{
        private boolean enabled;
        private final String token;
        private final List<String> roles;
    }
}
