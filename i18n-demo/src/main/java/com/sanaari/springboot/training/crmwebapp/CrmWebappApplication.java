package com.sanaari.springboot.training.crmwebapp;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class CrmWebappApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CrmWebappApplication.class, args);
	}

	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**"); } }; }
	 */

	
	  @Bean public LocaleResolver localeResolver() { final SessionLocaleResolver
	  localeResolver = new SessionLocaleResolver();
	  localeResolver.setDefaultLocale(new Locale("en", "US")); return
	  localeResolver; }
	 

	/*
	 * @Bean public LocaleResolver localeResolver() { CookieLocaleResolver
	 * localeResolver = new CookieLocaleResolver();
	 * localeResolver.setDefaultLocale(Locale.US); return localeResolver; }
	 */

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

	/*
	 * @Bean public LocaleResolver localeResolver() { SessionLocaleResolver slr =
	 * new SessionLocaleResolver(); slr.setDefaultLocale(Locale.US); return slr; }
	 */
}
