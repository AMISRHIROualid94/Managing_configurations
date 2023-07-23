package com.sbip.mc;

import com.sbip.mc.ConfigProp.AppProperties;
import com.sbip.mc.PropertiesSrc.DbConfig;
import com.sbip.mc.Services.AppService;
import com.sbip.mc.models.Course;
import com.sbip.mc.models.User;
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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
//	Using Validation built-in
//		Course course = new Course();
//		course.setId(1L);
//		course.setRating(0);
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//		Set<ConstraintViolation<Course>> violations = validator.validate(course);
//		violations.forEach(v ->{
//			logger.warn("A constraint violation has occured. Violation details: [{}]",v);
//		});
//	End Using Validation

//	Using Validation Custom
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		User user1 = new User("sbip01", "sbip");
		Set<ConstraintViolation<User>> violations = validator.validate(user1);
		logger.error("Password for user1 do not adhere to the password policy");
		violations.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));

		User user2 = new User("sbip02", "Sbip01$4UDfg");
		violations = validator.validate(user2);
		if(violations.isEmpty()) {
			logger.info("Password for user2 adhere to the password policy");
		}

		User user3 = new User("sbip03", "Sbip01$4UDfgggg");
		violations = validator.validate(user3);
		logger.error("Password for user3 violates maximum repetitive rule");
		violations.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));

		User user4 = new User("sbip04", "Sbip014UDfgggg");
		violations = validator.validate(user4);
		logger.error("Password for user4 violates special character rule");
		violations.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));
//	End Using Validation Custom


	}
}
