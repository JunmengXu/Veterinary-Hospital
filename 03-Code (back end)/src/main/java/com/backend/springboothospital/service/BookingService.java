package com.backend.springboothospital.service;

import com.backend.springboothospital.dao.BookingDAO;
import com.backend.springboothospital.entity.Booking;
import com.backend.springboothospital.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void addOrUpdate(Booking booking){
        bookingDAO.save(booking);
    }

    public void deleteById(int id){
        bookingDAO.deleteById(id);
    }

    public List<Booking> listByPet(int petId){
        Pet pet = petService.get(petId);
        return bookingDAO.findAllByPet(pet);
    }
}
