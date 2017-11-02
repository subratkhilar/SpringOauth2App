package com.spring.SampleAuth2App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author A669825
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.spring.SampleAuth2App" })
public class SampleAuth2AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleAuth2AppApplication.class, args);
	}
}
