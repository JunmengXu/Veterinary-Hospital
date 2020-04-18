package com.backend.springboothospital.service;

import com.backend.springboothospital.dao.BookingDAO;
import com.backend.springboothospital.dao.MessageDAO;
import com.backend.springboothospital.entity.Booking;
import com.backend.springboothospital.entity.Message;
import com.backend.springboothospital.entity.Pet;
import com.backend.springboothospital.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    MessageDAO messageDAO;
    @Autowired
    UserService userService;

    public List<Message> list(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return messageDAO.findAll(sort);
    }

    public void addOrUpdate(Message message){
        messageDAO.save(message);
    }

    public void Update(Message message){
        messageDAO.save(message);
    }

    public List<Message> listByUser(User user){
        return messageDAO.findAllByUser(user);
    }
}
