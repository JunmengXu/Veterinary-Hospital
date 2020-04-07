package com.backend.springboothospital.controller;

import com.backend.springboothospital.entity.Pet;
import com.backend.springboothospital.entity.User;
import com.backend.springboothospital.service.PetService;
import com.backend.springboothospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    PetService petService;
    @Autowired
    UserService userService;

    @GetMapping("/api/pets")
    public List<Pet> list() throws Exception{
        return petService.list();
    }

    @GetMapping("/api/pets/{username}")
    public List<Pet> listByUser(@PathVariable("username") String username) throws Exception {
        User user = userService.getByName(username);
        return petService.listByUser(user);
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
        petService.deleteById(pet.getId());
    }
}
