package com.backend.springboothospital.controller;

import com.backend.springboothospital.entity.Booking;
import com.backend.springboothospital.entity.Pet;
import com.backend.springboothospital.entity.User;
import com.backend.springboothospital.service.BookingService;
import com.backend.springboothospital.service.PetService;
import com.backend.springboothospital.service.UserService;
import com.backend.springboothospital.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class PetController {
    @Autowired
    PetService petService;
    @Autowired
    UserService userService;
    @Autowired
    BookingService bookingService;

    @GetMapping("/api/pets")
    public List<Pet> list() throws Exception{
        return petService.list();
    }

    @GetMapping("/api/pets/{username}")
    public List<Pet> listByUser(@PathVariable("username") String username) throws Exception {
        User user = userService.getByName(username);
        return petService.listByUser(user);
    }

    @GetMapping("/api/pets/{username}/{status}")
    public List<Pet> listByUser(@PathVariable("username") String username, @PathVariable("status") int status) throws Exception {
        User user = userService.getByName(username);
        List<Pet> list1 = petService.listByUser(user);
        List<Pet> list2 = petService.listByUser(user);
        for(int i=0 ; i < list1.size() ; i++){
            if (list1.get(i).getStatus() == 4){
                list1.get(i).setStatus(0);
            }
            if(list1.get(i).getStatus() != status){
                list2.remove(list1.get(i));
            }
        }
        return list2;
    }

    @PostMapping("/api/addPet/{username}")
    public Pet addOrUpdate(@RequestBody Pet pet,@PathVariable("username") String username) throws Exception {
        User user = userService.getByName(username);
        pet.setUser(user);
        petService.addOrUpdate(pet);
        return pet;
    }

    @PostMapping("/api/petDelete")
    public void delete(@RequestBody Pet pet) throws Exception {
        List<Booking> bookings = bookingService.listByPet(pet.getId());
        if (bookings.size()==0){
            petService.deleteById(pet.getId());
        }
    }

//    上传图片，处理文件名与存储位置
    @PostMapping("api/addPet/img/")
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
}
