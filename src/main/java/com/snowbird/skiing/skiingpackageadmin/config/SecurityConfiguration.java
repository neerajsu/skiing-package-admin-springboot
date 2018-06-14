package com.snowbird.skiing.skiingpackageadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter
     * #configure(org.springframework.security.config
     * .annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off
    	http.csrf().disable()
        .authorizeRequests()
            .anyRequest().permitAll()
            .and()
         .cors().and()
        .formLogin().disable().httpBasic().disable();
    }
}
