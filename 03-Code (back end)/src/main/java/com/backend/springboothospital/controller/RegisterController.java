package com.backend.springboothospital.controller;

import com.backend.springboothospital.entity.User;
import com.backend.springboothospital.result.Result;
import com.backend.springboothospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

/**
 *
 *
 * @author: Zha Xiaoning
 * @create: 2020-04-05
 */

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @CrossOrigin
    //设置路径，前端需要通过此路径寻找到后端这里对应的方法
    @PostMapping(value = "api/register")
    @ResponseBody
    public Result register(@RequestBody User requestUser) {
// 对 html 标签进行转义，防止 XSS 攻击
        User user = new User();
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        boolean isExisted = userService.isExist(username);
        if (isExisted){
            return new Result(400);
        }
        else{
            String password = requestUser.getPassword();
            String email = requestUser.getEmail();
            String phoneNumber = requestUser.getPhoneNumber();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setAccount(username);
            user.setType(0);
            userService.add(user);
            return new Result(200);
        }

    }
}
