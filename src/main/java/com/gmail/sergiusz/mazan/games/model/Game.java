package com.gmail.sergiusz.mazan.games.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class Game {

    private int id;

    @NotNull(message = "validation.game.title")
    @NotEmpty(message = "validation.game.title")
    private String title;

    @NotNull(message = "validation.game.date")
    private LocalDate dateOfPublication;

    @NotNull(message = "validation.game.publisher")
    private Publisher publisher;

    public Game() {}

    public Game(String title, LocalDate dateOfPublication, Publisher publisher) {
        this.title = title;
        this.dateOfPublication = dateOfPublication;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
