package com.animecompanion;

import database.JDBC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootWebApplication {
    public static void main(String[] args) throws Exception {
       // jdbc.setUrl("F:\\ACP-rep\\Repository\\HW2020.db");
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

}