package br.com.mycar.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {SpringApplication.run(AppApplication.class, args);
		//TODO To implement refresh in JWT token
		//TODO To create simple html for interface test with authentication and authorization
	}

}
