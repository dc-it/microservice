package com.mapc.nacos.spring.boot.config.mysql.service;

import com.mapc.nacos.spring.boot.config.mysql.dao.UserRepository;
import com.mapc.nacos.spring.boot.config.mysql.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务处理
 *
 * @author duchao
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}
