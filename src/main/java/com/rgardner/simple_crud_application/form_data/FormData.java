package com.rgardner.simple_crud_application.form_data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class FormData {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    public FormData(){}

    public FormData(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){return this.username;}

    public void setUsername(String username){this.username = username;}

    public String getPassword(){return this.password;}

    public void setPassword(String password){this.password = password;}
}
