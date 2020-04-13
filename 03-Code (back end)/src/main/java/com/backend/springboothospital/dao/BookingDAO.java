package com.backend.springboothospital.dao;

import com.backend.springboothospital.entity.Booking;
import com.backend.springboothospital.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDAO extends JpaRepository<Booking,Integer> {
    List<Booking> findAllByPet(Pet pet);
    List<Booking> findByUrgency(int urgency);
    List<Booking> findByDistribution(int distribution);
}
