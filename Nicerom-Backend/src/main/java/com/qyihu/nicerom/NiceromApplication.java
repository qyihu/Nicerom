package com.qyihu.nicerom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NiceromApplication {

	public static void main(String[] args) {
		SpringApplication.run(NiceromApplication.class, args);
	}
}
