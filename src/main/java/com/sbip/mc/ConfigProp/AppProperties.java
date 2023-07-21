package com.sbip.mc.ConfigProp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@ConstructorBinding //--> Binding properties by constructors not setters
//once the properties bind to the POJO instance,
// there is no way to modify them.
@ConfigurationProperties(prefix = "app.sbip.ct")
public class AppProperties {

    private final String name;
    private final String ip;
    private final int port;
    private final Security security;

    @Getter
    @AllArgsConstructor
    @ToString
    public static class Security{
        private boolean enabled;
        private final String token;
        private final List<String> roles;
    }
}
