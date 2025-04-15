package com.multisorteios.cambista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@ComponentScan(basePackages = {
	    "com.multisorteios.cambista", 
	    "com.multisorteios.common"
	})
	@EntityScan(basePackages = {
	    "com.multisorteios.common.model",
	    "com.multisorteios.cambista.model"
	})
	@EnableJpaRepositories(basePackages = {
	    "com.multisorteios.common.repository",
	    "com.multisorteios.cambista.repository"
	})
public class MsCambistaApplication extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(MsCambistaApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MsCambistaApplication.class);
    }
}
