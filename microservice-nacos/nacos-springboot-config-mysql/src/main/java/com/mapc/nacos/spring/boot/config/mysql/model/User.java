package com.mapc.nacos.spring.boot.config.mysql.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户模型
 *
 * @author duchao
 */
@Data
@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
}
