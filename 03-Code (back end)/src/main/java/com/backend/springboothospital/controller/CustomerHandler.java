package com.backend.springboothospital.controller;

import com.backend.springboothospital.entity.Customer;
import com.backend.springboothospital.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//接受前端的请求，返回customer的数据
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
