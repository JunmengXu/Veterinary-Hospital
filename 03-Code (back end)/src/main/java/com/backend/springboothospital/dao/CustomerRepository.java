package com.backend.springboothospital.dao;

import com.backend.springboothospital.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
