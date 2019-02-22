package com.gmail.sergiusz.mazan.games.conversion;

import com.gmail.sergiusz.mazan.games.dao.PublisherDao;
import com.gmail.sergiusz.mazan.games.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class PublisherFormatter implements Formatter<Publisher> {

    private PublisherDao publisherDao;

    @Autowired
    private void setPublisherDao(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @Override
    public Publisher parse(String stringId, Locale locale) throws ParseException {
        int id = Integer.parseInt(stringId);
        return publisherDao.getById(id);
    }

    @Override
    public String print(Publisher publisher, Locale locale) {
        return publisher.getName();
    }
}
