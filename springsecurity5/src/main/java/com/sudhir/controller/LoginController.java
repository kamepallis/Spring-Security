package com.sudhir.controller;

import com.sudhir.Repository.CustomerRepository;
import com.sudhir.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {

         try {
             String hashPassword = passwordEncoder.encode(customer.getPwd());
             customer.setPwd(hashPassword);
             customerRepository.save(customer);
             return ResponseEntity.status(HttpStatus.CREATED).body("User created");

         } catch (Exception exception) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create the user");
         }
    }
}
