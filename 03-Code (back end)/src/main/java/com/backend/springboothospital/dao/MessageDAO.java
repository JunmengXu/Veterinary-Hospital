package com.backend.springboothospital.dao;

import com.backend.springboothospital.entity.Message;
import com.backend.springboothospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageDAO extends JpaRepository<Message,Integer> {
    List<Message> findAllByUser(User user);
}
