package org.springframework.fn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitSinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitSinkApplication.class, args);
	}
}
