package com.rgardner.simple_crud_application.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private LocalDate releaseDate;

    public Game(){}

    public Game(String title, LocalDate releaseDate){
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public String getTitle(){return this.title;}

    public LocalDate getReleaseDate(){return this.releaseDate;}
}
