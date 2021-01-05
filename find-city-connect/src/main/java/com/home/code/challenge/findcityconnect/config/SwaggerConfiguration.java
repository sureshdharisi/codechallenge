package com.home.code.challenge.findcityconnect.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	public static final Contact DEFAULT_CONTACT = new Contact("Suresh Dharisi",
			"www.linkedin.com/in/suresh-dharisi", "suresh.dharisi@gmail.com");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Road Connectivity", "Verifies the road connectivity between the cities", "1.0",
			"urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/connected*")).build().apiInfo(DEFAULT_API_INFO);
	}

}
