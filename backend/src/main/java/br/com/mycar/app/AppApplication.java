package br.com.mycar.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@ComponentScan(basePackages = {"br.com.mycar.app.repositories"})
public class AppApplication {

	public static void main(String[] args) {SpringApplication.run(AppApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}

}
