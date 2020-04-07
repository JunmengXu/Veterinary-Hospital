package com.backend.springboothospital.service;

import com.backend.springboothospital.dao.PetDAO;
import com.backend.springboothospital.entity.Pet;
import com.backend.springboothospital.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    PetDAO petDAO;
    @Autowired
    UserService userService;

    public List<Pet> list(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return petDAO.findAll(sort);
    }

    public Pet get(int id){
        Pet p = petDAO.findById(id).orElse(null);
        return p;
    }

    public List<Pet> listByUser(User user){
        return petDAO.findAllByUser(user);
    }

    public void addOrUpdate(Pet pet){
        petDAO.save(pet);
    }

    public void deleteById(int id){
        petDAO.deleteById(id);
    }
}
