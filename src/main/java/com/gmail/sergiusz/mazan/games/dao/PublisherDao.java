package com.gmail.sergiusz.mazan.games.dao;

import com.gmail.sergiusz.mazan.games.exception.InvalidPublisherException;
import com.gmail.sergiusz.mazan.games.model.Publisher;

import java.util.List;

public interface PublisherDao {
    Publisher getById(int id);
    List<Publisher> getAll();
    void insert(Publisher entity) throws InvalidPublisherException;
}
