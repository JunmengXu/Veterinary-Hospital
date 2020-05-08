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

    @GetMapping("/api/{urgency}/{city}/bookings")
    public List<Booking> listByUrgencyCity(@PathVariable("urgency") int urgency, @PathVariable("city") int city) throws Exception {
        if (0 == urgency || 1 == urgency ) {
            List<Booking> list1 = bookingService.listByUrgency(urgency);
            List<Booking> list2 = bookingService.listByUrgency(urgency);
            for(int i=0; i<list1.size(); i++){
                if(list1.get(i).getHospital() != city){
                    list2.remove(list1.get(i));
                }
            }
            return list2;
        } else {
            return list();
        }
    }

    @GetMapping("/api/{distribution}/{city}/calendar/bookings")
    public List<Booking> listByDistribution(@PathVariable("distribution") int distribution,@PathVariable("city") int city) throws Exception {
        if (0 == distribution || 1 == distribution ) {
            List<Booking> list1 = bookingService.listByDistribution(distribution);
            List<Booking> list2 = bookingService.listByDistribution(distribution);
            for(int i=0; i<list1.size(); i++){
                if(list1.get(i).getHospital() != city){
                    list2.remove(list1.get(i));
                }
            }
            return list2;
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
        booking.getPet().setStatus(2);
        petService.addOrUpdate(booking.getPet());
        bookingService.addOrUpdate(booking);
        return booking;
    }

    @PutMapping("/api/updateRe/{bookingId}/{releaseTime}/bookings")
    public Booking UpdateRe(@PathVariable("bookingId") int bookingId,@PathVariable("releaseTime") String releaseTime) throws Exception {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(releaseTime);
        Date d2 = sdf2.parse(releaseTime);
        Calendar c = Calendar.getInstance();
        c.setTime(d2);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        Date d3 = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(d3.getTime());
        Booking booking = bookingService.findById(bookingId);
        booking.setReleasetime(sqlDate);
        booking.getPet().setStatus(3);
        petService.addOrUpdate(booking.getPet());
        bookingService.addOrUpdate(booking);
        return booking;
    }

    @PutMapping("/api/updateRate/{bookingId}/{rate}/{content}/bookings")
    public Booking UpdateRate(@PathVariable("content") String content,@PathVariable("bookingId") int bookingId,@PathVariable("rate") int rate) throws Exception {
        Booking booking1 = bookingService.findById(bookingId);
        booking1.setRatevalue(rate);
        booking1.setRatecontent(content);
        booking1.setRatedis(1);
        booking1.getPet().setStatus(0);
        bookingService.addOrUpdate(booking1);
        return booking1;
    }

    @PostMapping("/api/bookingDelete")
    public void delete(@RequestBody Booking booking) throws Exception {
        Booking booking1 = bookingService.findById(booking.getId());
        Pet pet = booking1.getPet();
        List<Booking> bookings = bookingService.listByPet(pet.getId());
        /*if(bookings.size()==1){
            pet.setStatus(0);
        }*/
        pet.setStatus(0);
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
