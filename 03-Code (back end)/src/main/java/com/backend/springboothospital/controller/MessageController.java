package com.backend.springboothospital.controller;

import com.backend.springboothospital.entity.Message;
import com.backend.springboothospital.entity.Pet;
import com.backend.springboothospital.entity.User;
import com.backend.springboothospital.service.MessageService;
import com.backend.springboothospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @GetMapping("/api/messagesHome/")
    public List<Message> listByUserNewMessage() throws Exception {
        Set<User> hs = new HashSet();
        List<Message> list1 = messageService.list();
        List<Message> list3 = messageService.list();
        for (int i=0; i<list1.size();i++){
            if(!hs.contains(list1.get(i).getUser())){
                hs.add(list1.get(i).getUser());
            }
            if(list1.get(i).getType() == 1){
                list3.remove(list1.get(i));
            }
        }
        Iterator<User> it = hs.iterator();
        Iterator<User> it2 = hs.iterator();
        for (int i=0; i<hs.size(); i++){
            List<Message> list2 = messageService.listByUser( it.next() );
            List<Message> list4 = messageService.listByUser( it2.next() );
            for (int k=0; k<list2.size(); k++){
                if(list2.get(k).getType() == 1){
                    list4.remove(list2.get(k));
                }
            }
            for (int j=0; j<list4.size()-1; j++){
                list3.remove(list4.get(j));
            }
        }
        return list3;
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
