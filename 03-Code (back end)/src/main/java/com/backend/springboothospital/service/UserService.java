package com.backend.springboothospital.service;

import com.backend.springboothospital.dao.UserDAO;
import com.backend.springboothospital.entity.Booking;
import com.backend.springboothospital.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

//登录功能 负责业务逻辑，跟功能相关的代码一般写在这里，编写、调用各种方法对
// DAO 取得的数据进行操作

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }

    public List<User> list(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return userDAO.findAll(sort);
    }
}