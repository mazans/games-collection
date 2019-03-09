package com.gmail.sergiusz.mazan.games.model;

import com.gmail.sergiusz.mazan.games.validation.ContentType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AddedGame {

    @NotNull(message = "Game title is required")
    @NotEmpty(message = "Game title is required")
    private String title;

    @NotNull(message = "Date o publication is required")
    private LocalDate dateOfPublication;

    @NotNull(message = "Publisher is required")
    private Publisher publisher;

    @ContentType(contentType = "image/png", message = "Game cover must be .png")
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
