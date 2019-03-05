package com.gmail.sergiusz.mazan.games.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Publisher {

    private int id;

    @NotNull(message = "validation.publisher.title.empty")
    @NotEmpty(message = "validation.publisher.title.empty")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
