package com.mapc.nacos.spring.boot.config.mysql.dao;

import com.mapc.nacos.spring.boot.config.mysql.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户模型处理
 *
 * @author duchao
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
