package com.backend.springboothospital.dao;

import com.backend.springboothospital.entity.Pet;
import com.backend.springboothospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetDAO extends JpaRepository<Pet, Integer> {
    List<Pet> findAllByUser(User user);
}
