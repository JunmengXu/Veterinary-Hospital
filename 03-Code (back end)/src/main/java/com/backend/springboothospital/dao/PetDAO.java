package com.backend.springboothospital.dao;

import com.backend.springboothospital.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetDAO extends JpaRepository<Pet, Integer> {
}
