package org.springframework.fn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcSourceApplication.class, args);
	}
}
