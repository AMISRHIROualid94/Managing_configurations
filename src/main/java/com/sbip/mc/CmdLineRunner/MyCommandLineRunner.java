package com.sbip.mc.CmdLineRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(MyCommandLineRunner.class);
    @Override
    public void run(String... args) throws Exception {
        logger.warn("CommandLine Runner executed from a component");
    }
}
