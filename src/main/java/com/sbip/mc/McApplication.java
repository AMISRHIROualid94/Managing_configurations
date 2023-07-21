package com.sbip.mc;

import com.sbip.mc.ConfigProp.AppProperties;
import com.sbip.mc.PropertiesSrc.DbConfig;
import com.sbip.mc.Services.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class McApplication implements CommandLineRunner{
	public static final Logger logger = LoggerFactory.getLogger(McApplication.class);
	public static void main(String[] args) {



		SpringApplication application = new SpringApplication(McApplication.class);
		ConfigurableApplicationContext context = application.run(args);

//		Using SpringApplication for setting Properties
		Map<String,String> map = new HashMap<>();
		map.put("Spring.config.on-not-found","ignore");
		map.put("Spring.config.on-already-exist","ignore");

		Properties properties = new Properties();
		map.forEach(properties::setProperty);

		application.setDefaultProperties(properties);

		logger.warn("Spring.config.on-not-found="+properties.get("Spring.config.on-not-found"));
		logger.warn("Spring.config.on-already-exist="+properties.get("Spring.config.on-already-exist"));
// 		End Using SpringApplication

//		Using @PropertySource
		DbConfig dbConfig = context.getBean(DbConfig.class);
		logger.warn(dbConfig.toString());
//		End Using @PropertySource

//		Using an OS environment variable

		//		System.setProperty("APP_TIMEOUT","30");
		//		or
		//		add it manually : Run -> edit Config -> modify Options... -> environment variable
		//		example : APP_TIMEOUT=30

		Environment environment = context.getBean(Environment.class);
		logger.warn("APP_TIMEMOUT: {} ",environment.getProperty("app.timeout"));
		logger.warn("NOT_FOUND: {} ",environment.getProperty("app.not-found"));
//		End Using an OS environment variable

//		Using @ConfigurationProperties
		AppService appService = context.getBean(AppService.class);
		logger.warn(appService.getAppProperties().toString());
//		End Using @ConfigurationProperties
	}
	@Bean
	@Order(2)
	public CommandLineRunner commandLineRunner(){
		return args -> {
			logger.warn("CommandLineRunner executed as a Bean ....... ");
		};
	}

	@Override
	@Order(3)
	public void run(String... args) throws Exception {
		logger.warn("CommandLineRunner Executed as an implementation ......");
	}
}
