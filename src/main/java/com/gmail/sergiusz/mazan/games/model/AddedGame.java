package com.gmail.sergiusz.mazan.games.model;

import com.gmail.sergiusz.mazan.games.validation.ContentType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AddedGame {

    @NotNull(message = "validation.game.title")
    @NotEmpty(message = "validation.game.title")
    private String title;

    @NotNull(message = "validation.game.date")
    private LocalDate dateOfPublication;

    @NotNull(message = "validation.game.publisher")
    private Publisher publisher;

    @ContentType(contentType = "image/png", message = "validation.game.cover")
    private MultipartFile cover;

    public AddedGame() {}

    public AddedGame(String title, LocalDate dateOfPublication, Publisher publisher, MultipartFile cover) {
        this.title = title;
        this.dateOfPublication = dateOfPublication;
        this.publisher = publisher;
        this.cover = cover;
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

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public MultipartFile getCover() {
        return cover;
    }

    public void setCover(MultipartFile cover) {
        this.cover = cover;
    }
}
