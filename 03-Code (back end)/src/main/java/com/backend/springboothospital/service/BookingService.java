package com.backend.springboothospital.service;

import com.backend.springboothospital.dao.BookingDAO;
import com.backend.springboothospital.entity.Booking;
import com.backend.springboothospital.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    BookingDAO bookingDAO;
    @Autowired
    PetService petService;

    public List<Booking> list(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return bookingDAO.findAll(sort);
    }

    public List<Booking> listByUrgency(int i){
        return bookingDAO.findByUrgency(i);
    }

    public List<Booking> listByDistribution(int i){
        return bookingDAO.findByDistribution(i);
    }

    public void addOrUpdate(Booking booking){
        bookingDAO.save(booking);
    }

    public void Update(Booking booking){
        bookingDAO.save(booking);
    }

    public Booking findById(int i){
        Optional<Booking> findResult = bookingDAO.findById(i);
        return findResult.get();
    }

    public void deleteById(int id){
        bookingDAO.deleteById(id);
    }

    public List<Booking> listByPet(int petId){
        Pet pet = petService.get(petId);
        return bookingDAO.findAllByPet(pet);
    }
}
