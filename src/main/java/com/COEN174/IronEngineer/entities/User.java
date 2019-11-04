package com.COEN174.IronEngineer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
//@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;

    public User(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }
}
