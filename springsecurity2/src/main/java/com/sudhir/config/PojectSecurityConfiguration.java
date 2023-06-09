package com.sudhir.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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

        /**
         * This denys all requests
         * First prompts to authencticate then deny all (Authorisation)
         * Return 403 error
         *
        System.out.println("Deny all");
        http.authorizeHttpRequests().anyRequest().denyAll()
                .and().formLogin()
                .and().httpBasic();
         */

        /**
         *  Permits all requests
        System.out.println("Permit all");
        http.authorizeHttpRequests().anyRequest().permitAll()
                .and().formLogin()
                .and().httpBasic();
         */

        /**
         * This confiurtion protects all the request
         *      *   .anyRequest() ---> Every Request
         *      *   .authenticated() --> needs to be authenticated
         System.out.println("Default");
         http.authorizeHttpRequests().anyRequest().authenticated();
         http.formLogin();
         http.httpBasic();
        */
        /**
         *  From SpringSecurity 6.1 , spring boot 3.1.0
         *  recommeds to use Lambda DSL
         *  Existing convetion was deprectaed
         *  From seccurity 7 it will be removed
         *  no needto use and()
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/myAccount","/myBalance","/myLoan","/myCards").authenticated()
                .requestMatchers("/notices","/contact").permitAll()
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
        );
         */
        return http.build();
    }
}
