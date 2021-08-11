package de.gp.finance.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DataApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(DataApplicationStarter.class, args);
	}
}
