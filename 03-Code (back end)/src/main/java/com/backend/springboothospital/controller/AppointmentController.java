package com.backend.springboothospital.controller;

import com.backend.springboothospital.entity.Booking;
import com.backend.springboothospital.entity.Pet;
import com.backend.springboothospital.entity.User;
import com.backend.springboothospital.service.BookingService;
import com.backend.springboothospital.service.PetService;
import com.backend.springboothospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    BookingService bookingService;
    @Autowired
    PetService petService;
    @Autowired
    UserService userService;

    @GetMapping("/api/bookings")
    public List<Booking> list() throws Exception{
        return bookingService.list();
    }

    @GetMapping("/api/{id}/oneBooking")
    public Booking list(@PathVariable("id") int i) throws Exception{
        return bookingService.findById(i);
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

    @GetMapping("/api/{distribution}/calendar/bookings")
    public List<Booking> listByDistribution(@PathVariable("distribution") int distribution) throws Exception {
        if (0 == distribution || 1 == distribution ) {
            return bookingService.listByDistribution(distribution);
        } else {
            return list();
        }
    }

    @PostMapping("/api/{petId}/bookings")
    public Booking addOrUpdate(@RequestBody Booking booking,@PathVariable("petId") int petId) throws Exception {
        Pet pet = petService.get(petId);
        pet.setStatus(1);
        booking.setPet(pet);
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        booking.setTime(df.format(day));

        Calendar c = Calendar.getInstance();
        c.setTime(booking.getNeedtime());
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        Date d3 = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(d3.getTime());
        booking.setNeedtime(sqlDate);

        bookingService.addOrUpdate(booking);
        return booking;
    }

    @PutMapping("/api/update/{bookingId}/{needTime}/bookings")
    public Booking Update(@PathVariable("bookingId") int bookingId,@PathVariable("needTime") String needTime) throws Exception {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(needTime);
        Date d2 = sdf2.parse(needTime);
        Calendar c = Calendar.getInstance();
        c.setTime(d2);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        Date d3 = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(d3.getTime());
        Booking booking = bookingService.findById(bookingId);
        booking.setNeedtime(sqlDate);
        booking.setDistribution(1);
        bookingService.addOrUpdate(booking);
        return booking;
    }

    @PostMapping("/api/bookingDelete")
    public void delete(@RequestBody Booking booking) throws Exception {
        Booking booking1 = bookingService.findById(booking.getId());
        Pet pet = booking1.getPet();
        List<Booking> bookings = bookingService.listByPet(pet.getId());
        if(bookings.size()==1){
            pet.setStatus(0);
        }
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

    @GetMapping("/api/customerBookings/{username}")
    public List<Booking> listByUsername(@PathVariable("username") String username) throws Exception{
        User user = userService.getByName(username);
        List<Booking> bookings = bookingService.list();
        List<Booking> bookings2 = bookingService.list();
        List<Pet> pet = petService.listByUser(user);
        for(int i = 0; i < bookings.size(); i++){
            if(!pet.contains(bookings.get(i).getPet())){
                bookings2.remove(bookings.get(i));
            }
        }
        return bookings2;
    }

    @GetMapping("/api/customerBookings/{username}/{distribution}")
    public List<Booking> listByUserAndAssign(@PathVariable("username") String username,@PathVariable("distribution") int distribution) throws Exception{
        User user = userService.getByName(username);
        List<Booking> bookings = bookingService.list();
        List<Booking> bookings2 = bookingService.list();
        List<Booking> bookings3 = bookingService.list();
        List<Pet> pet = petService.listByUser(user);
        for(int i = 0; i < bookings.size(); i++){
            if(!pet.contains(bookings.get(i).getPet())){
                bookings2.remove(bookings.get(i));
                bookings3.remove(bookings.get(i));
            }
        }
        for(int i = 0 ; i < bookings2.size() ; i++){
            if(bookings2.get(i).getDistribution() != distribution){
                bookings3.remove(bookings2.get(i));
            }
        }
        return bookings3;
    }
}
