package com.backend.springboothospital.controller;

import com.backend.springboothospital.entity.Booking;
import com.backend.springboothospital.entity.User;
import com.backend.springboothospital.service.UserService;
import com.backend.springboothospital.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/changeInf/{username}")
    public User addOrUpdate(@RequestBody User user, @PathVariable("username") String username) throws Exception {
        User user1 = userService.getByName(username);
        user1.setAccount(user.getAccount());
        user1.setEmail(user.getEmail());
        user1.setPhoneNumber(user.getPhoneNumber());
        userService.add(user1);
        return user1;
    }

    @PostMapping("/api/changePas/{username}")
    public User UpdatePas(@RequestBody User user, @PathVariable("username") String username) throws Exception {
        User user1 = userService.getByName(username);
        user1.setPassword(user.getPassword());
        userService.add(user1);
        return user1;
    }

    @PostMapping("/api/changeAva/{username}")
    public User UpdateAva(@RequestBody User user, @PathVariable("username") String username) throws Exception {
        User user1 = userService.getByName(username);
        user1.setUserimage(user.getUserimage());
        userService.add(user1);
        return user1;
    }

    @GetMapping("/api/personInfo/{username}")
    public User listByDistribution(@PathVariable("username") String username) throws Exception {
        return userService.getByName(username);
    }

    @PostMapping("api/changeAva/")
    public String coversUpload(MultipartFile file) throws Exception {
//        设置为自己本地的存储位置
        String folder = "D:/College subjects/keshe";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
//            构建url文件名
            file.transferTo(f);
            String imgURL = "http://localhost:8181/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @GetMapping("/api/messageHome/allUsers")
    public List<User> listAllUser() throws Exception {
        List<User> list1 = userService.list();
        List<User> list2 = userService.list();
        for(int i=0; i<list1.size(); i++){
            if(list1.get(i).getType() == 1){
                list2.remove(list1.get(i));
            }
        }
        return list2;
    }
}
