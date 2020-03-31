package com.backend.springboothospital.repository;

import com.backend.springboothospital.dao.CustomerRepository;
import com.backend.springboothospital.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/findAll")
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
}