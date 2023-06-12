package com.sudhir.Repository;

import com.sudhir.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

    List<Customer> findByEmail(String username);
}
