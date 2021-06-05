package br.com.serratec.lojavirtual.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2) 
				.select() 
				.apis(RequestHandlerSelectors.any()) 
				.paths(regex("/api.*"))
				.build() 
				.apiInfo(info()); 
	}
	
	private ApiInfo info() {
		return new ApiInfo(
				"Dev-HQs", 
				"API REST de vendas de historias em quadrinhos", 
				"1.0", 
				"Termos de Serviços", 
				new Contact("Name Not-Found", 
						"https://www.linkedin.com/in/name-notfound/", 
						"devshqs@gmail.com"), 
				"Apache License Version 2.0",
				"https://www.apache.org/licesen.html", 
				new ArrayList<VendorExtension>());
	}

}
