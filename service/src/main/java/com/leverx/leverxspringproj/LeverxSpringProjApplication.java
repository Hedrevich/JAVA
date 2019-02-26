package com.leverx.leverxspringproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.sap.cloud.sdk")
public class LeverxSpringProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeverxSpringProjApplication.class, args);
	}

}

