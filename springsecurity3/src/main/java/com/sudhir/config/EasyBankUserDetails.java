package com.sudhir.config;

import com.sudhir.Repository.CustomerRepository;
import com.sudhir.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EasyBankUserDetails implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = customerRepository.findByEmail(username);
        if (customers.isEmpty()) {
           throw new UsernameNotFoundException("USer not found"+username);
        } else {
            List<GrantedAuthority> autorities = new ArrayList<>();
            autorities.add(new SimpleGrantedAuthority(customers.get(0).getRoles()));
            return new User(customers.get(0).getEmail(),
                    customers.get(0).getPwd(),
                    autorities);
        }

    }
}
