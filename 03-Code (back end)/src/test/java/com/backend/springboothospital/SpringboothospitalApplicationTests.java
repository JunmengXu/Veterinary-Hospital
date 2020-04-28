package com.backend.springboothospital;

import com.backend.springboothospital.entity.Booking;
import com.backend.springboothospital.entity.Message;
import com.backend.springboothospital.entity.User;
import com.backend.springboothospital.service.BookingService;
import com.backend.springboothospital.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;


@SpringBootTest
class SpringboothospitalApplicationTests {
    @Autowired
    BookingService bookingService;
    @Autowired
    MessageService messageService;


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

    @Test
    void listByUserNewMessage() throws Exception {
        Set<User> hs = new HashSet();
        List<Message> list1 = messageService.list();
        for (int i=0; i<list1.size();i++){
            if(!hs.contains(list1.get(i).getUser())){
                hs.add(list1.get(i).getUser());
            }
        }
        Iterator<User> it = hs.iterator();
        for (int i=0; i<hs.size(); i++){
            List<Message> list2 = messageService.listByUser( it.next() );
            for (int j=0; j<list2.size()-1; j++){
                list1.remove(list2.get(j));
            }
        }
    }

}
