package com.animecompanion.model;

import org.hibernate.validator.constraints.NotBlank;

public class SearchCriteria {

    @NotBlank(message = "Search string can't empty!")
    String name;

    public String getName() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }
}