package com.snowbird.skiing.skiingpackageadmin;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.snowbird.skiing.skiingpackageadmin.service.SkiingPackageService;

@SpringBootApplication
public class SkiingPackageAdminApplication implements CommandLineRunner{
	
	@Autowired
	private SkiingPackageService skiingPackageService;

	public static void main(String[] args) {
		SpringApplication.run(SkiingPackageAdminApplication.class, args);
	}
	
	@Override
	public void run(String... strings) throws Exception {
		skiingPackageService.createSkiingPackage("AD-AM", "All mountain - stable", "Full", false, 100);
		skiingPackageService.createSkiingPackage("BGL", "Beginner", "Partial", true, 80);
		skiingPackageService.createSkiingPackage("AD-PD", "Powder", "Full & hike", true, 120);
		skiingPackageService.createSkiingPackage("IN-L", "All mountain - lightweight", "Full", true, 120);
	}
	
	
	@Bean
	@SuppressWarnings("unchecked")
	public FilterRegistrationBean simpleCorsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	    config.setAllowedMethods(Collections.singletonList("*"));
	    config.setAllowedHeaders(Collections.singletonList("*"));
	    source.registerCorsConfiguration("/**", config);
	    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return bean;
	}
}
