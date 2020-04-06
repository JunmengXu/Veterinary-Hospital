package com.backend.springboothospital.controller;

import com.backend.springboothospital.entity.Booking;
import com.backend.springboothospital.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    BookingService bookingService;

    @GetMapping("/api/bookings")
    public List<Booking> list() throws Exception{
        return bookingService.list();
    }

//    紧急预约
    @GetMapping("/api/{urgency}/bookings")
    public List<Booking> listByUrgency(@PathVariable("urgency") int urgency) throws Exception {
        if (0 == urgency || 1 == urgency ) {
            return bookingService.listByUrgency(urgency);
        } else {
            return list();
        }
    }

    @PostMapping("/api/bookings")
    public Booking addOrUpdate(@RequestBody Booking booking) throws Exception {
        bookingService.addOrUpdate(booking);
        return booking;
    }

    @PostMapping("/api/delete")
    public void delete(@RequestBody Booking booking) throws Exception {
        bookingService.deleteById(booking.getId());
    }


    @GetMapping("/api/pets/{petId}/bookings")
    public List<Booking> listByCategory(@PathVariable("petId") int petId) throws Exception {
        if (0 != petId) {
            return bookingService.listByPet(petId);
        } else {
            return list();
        }
    }
}
