package com.sbip.mc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class McApplication {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(McApplication.class);
		
		Map<String,String> map = new HashMap<>();
		map.put("Spring.config.on-not-found","ignore");
		map.put("Spring.config.on-already-exist","ignore");

		Properties properties = new Properties();
		map.forEach(properties::setProperty);

		SpringApplication application = new SpringApplication(McApplication.class);
		application.setDefaultProperties(properties);

		logger.info("Spring.config.on-not-found="+properties.get("Spring.config.on-not-found"));
		logger.info("Spring.config.on-already-exist="+properties.get("Spring.config.on-already-exist"));

		application.run(args);
	}

}
