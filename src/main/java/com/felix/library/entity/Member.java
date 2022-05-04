package com.felix.library.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String mail;

    private String phone;
}
