package com.backend.springboothospital.controller;

import com.backend.springboothospital.entity.Customer;
import com.backend.springboothospital.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerHandler {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/findAll")
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
}
