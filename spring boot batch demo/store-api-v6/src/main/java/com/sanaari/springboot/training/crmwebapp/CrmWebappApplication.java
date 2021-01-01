package com.sanaari.springboot.training.crmwebapp;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class CrmWebappApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CrmWebappApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/store/**").allowedOrigins("http://localhost:9090");
			}
		};
	}
	
	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**"); } }; }
	 */

	/*
	 * @Bean public LocaleResolver localeResolver() { final SessionLocaleResolver
	 * localeResolver = new SessionLocaleResolver();
	 * localeResolver.setDefaultLocale(new Locale("en", "US")); return
	 * localeResolver; }
	 */

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public Docket productApi() {
		/*
		 * return new Docket(DocumentationType.SWAGGER_2).select()
		 * .apis(RequestHandlerSelectors.basePackage(
		 * "com.sanaari.springboot.training.crmwebapp.controllers")).build();
		 */

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.sanaari.springboot.training.crmwebapp.controllers"))
				.paths(PathSelectors.regex("/store/api.*")).build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().contact(new Contact("John Doe","https://sanaari.com","ysrao@gmail.com")).title("Spring boot Products API")
				.description("Products Rest API for online store").version("1.0").license("Apache License Version 2.0").licenseUrl("https://www.apache.org.licenses/LICENSE-2.0").build();
		/*ApiInfo apiInfo = new ApiInfo("Spring boot Products API", "", "1.0",
				"For training purpose use only. 2020 Sanaari. All rights reserved.",
				new Contact(),
				"", "");

		return apiInfo;*/
	}
	/*
	 * @Bean public LocaleResolver localeResolver() { SessionLocaleResolver slr =
	 * new SessionLocaleResolver(); slr.setDefaultLocale(Locale.US); return slr; }
	 */
}
