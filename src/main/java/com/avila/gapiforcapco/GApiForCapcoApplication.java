package com.avila.gapiforcapco;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableSwagger2
public class GApiForCapcoApplication {

	public static void main(String[] args) { run(GApiForCapcoApplication.class, args);
	}

	@Bean
	public Docket greetingApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.avila.gapiforcapco"))
				.build();

	}

}
