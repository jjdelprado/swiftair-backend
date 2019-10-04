package com.swiftaireng.backend.apirest;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwiftairEngBackendApirestApplication {

	@PostConstruct
    void started() {
        // set JVM timezone as UTC
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Madrid"));
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SwiftairEngBackendApirestApplication.class, args);
	}

}
