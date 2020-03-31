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

import java.util.Objects;

//登录功能 负责数据交互，即接收前端发送的数据，
// 通过调用 Service 获得处理后的数据并返回
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    //设置路径，前端需要通过此路径寻找到后端这里对应的方法
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            return new Result(200);
        }
    }
}