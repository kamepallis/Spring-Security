package com.sudhir.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

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
        System.out.println("Custom Configuration");
        http.authorizeHttpRequests()
                .requestMatchers("/myAccount","/myBalance","/myLoan","/myCards").authenticated()
                .requestMatchers("/notices","/contact").permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }


   /**@Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        /**
         * Approach 1: we are usinf default Password Encoding

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin").password("admin123").authorities("admin").build();
        UserDetails reader = User.withDefaultPasswordEncoder()
                .username("reader").password("reader123").authorities("read").build();
        return new InMemoryUserDetailsManager(admin, reader);
         * /

        /**
         * Approach2 : Creating custom passord Encoder
         * Here we are using NoOpPasswordEncoder
         * /
        UserDetails user = User.withUsername("admin")
                .password("admin").authorities("admin").build();
        return new InMemoryUserDetailsManager(user);
    }*/

    /** Commented because we have created our own implementation for Userdetails Service
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
