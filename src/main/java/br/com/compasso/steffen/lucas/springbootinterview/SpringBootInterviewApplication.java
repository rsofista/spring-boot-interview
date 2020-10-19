package br.com.compasso.steffen.lucas.springbootinterview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootInterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootInterviewApplication.class, args);

		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}

}
