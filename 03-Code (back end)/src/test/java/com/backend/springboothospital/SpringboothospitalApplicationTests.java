package com.backend.springboothospital;

import com.backend.springboothospital.entity.Booking;
import com.backend.springboothospital.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
class SpringboothospitalApplicationTests {
    @Autowired
    BookingService bookingService;


    @Test
    void contextLoads() {
    }

    @Test
    void update(){
        Booking booking = bookingService.findById(1);
//        System.out.println(booking.getTime());
//        java.util.Date day = new Date();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
//        booking.setTime(df.format(day));
//        System.out.println(booking.getTime());
//        System.out.println(df.format(day));
        System.out.println(booking.getSymptom());
        String a = "nihao";
        booking.setSymptom(a);
//        Date date = new Date(2020,3,9);
        bookingService.Update(booking);
    }

}
