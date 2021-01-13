package com.mapc.nacos.spring.boot.config.mysql.controller;

import com.mapc.nacos.spring.boot.config.mysql.model.User;
import com.mapc.nacos.spring.boot.config.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户前端控制器
 *
 * @author duchao
 */
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public User get(@PathVariable Integer id) {
        return userService.findById(id);
    }
}
