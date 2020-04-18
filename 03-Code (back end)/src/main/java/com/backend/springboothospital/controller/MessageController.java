package com.backend.springboothospital.controller;

import com.backend.springboothospital.entity.Message;
import com.backend.springboothospital.entity.Pet;
import com.backend.springboothospital.entity.User;
import com.backend.springboothospital.service.MessageService;
import com.backend.springboothospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    @GetMapping("/api/messages")
    public List<Message> list() throws Exception{
        return messageService.list();
    }

    @GetMapping("/api/messages/{username}")
    public List<Message> listByUser(@PathVariable("username") String username) throws Exception {
        User user = userService.getByName(username);
        return messageService.listByUser(user);
    }

    @PostMapping("/api/addMessage/{username}")
    public Message addOrUpdate(@RequestBody Message message, @PathVariable("username") String username) throws Exception {
        User user = userService.getByName(username);
        message.setUser(user);
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setTime(df.format(day));
        messageService.addOrUpdate(message);
        return message;
    }

}
