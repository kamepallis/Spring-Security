package com.sudhir.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class PojectSecurityConfiguration {


    /**
     *  requestMatchers() we can pass uri or redex
     *  permitAll() skips the authenctication
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Request Matchers - permitALl()");
        http.authorizeHttpRequests()
                .requestMatchers("/myAccount","/myBalance","/myLoan","/myCards").authenticated()
                .requestMatchers("/notices","/contact").permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }

    /**
     * This confiurtion protects all the request
     *   .anyRequest() ---> Every Request
     *   .authenticated() --> needs to be authenticated
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Default");
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.formLogin();
        http.httpBasic();
        return http.build();
    } */

}
