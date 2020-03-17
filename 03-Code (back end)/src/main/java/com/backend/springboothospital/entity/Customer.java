package com.backend.springboothospital.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Customer {
    @Id
    private Integer id;
    private String name;
    private String pet;
}
