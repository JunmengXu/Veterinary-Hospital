package com.backend.springboothospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

//登录功能 后端链接数据库 并获得数据
@Entity
//对于user数据库
@Table(name = "pet")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne
    @JoinColumn(name="ownerid")
    private User user;

    int gender;
    String name;
    String type;

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}