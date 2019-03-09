package com.gmail.sergiusz.mazan.games.service;

import com.gmail.sergiusz.mazan.games.dao.GameDao;
import com.gmail.sergiusz.mazan.games.dao.PublisherDao;
import com.gmail.sergiusz.mazan.games.exception.InvalidPublisherException;
import com.gmail.sergiusz.mazan.games.model.Game;
import com.gmail.sergiusz.mazan.games.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameService {

    private GameDao gameDao;
    private PublisherDao publisherDao;

    @Autowired
    private void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Autowired
    private void setPublisherDao(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    public List<Game> findGames(String pattern) {
        return gameDao.getByPattern(pattern);
    }

    public int addGame(Game newGame) {
        newGame.setDateTimeOfAdd(LocalDateTime.now());
        return gameDao.insertAndGetKey(newGame);
    }

    public List<Publisher> getAllPublishers() {
        return publisherDao.getAll();
    }

    public AddPublisherResult addPublisher(Publisher newPublisher) {
        try {
            publisherDao.insert(newPublisher);
            return AddPublisherResult.OK;
        } catch(InvalidPublisherException e) {
            return AddPublisherResult.PUBLISHER_ALREADY_EXISTS;
        }
    }

    public List<Game> getLatestGames(int amount) {
        return gameDao.getLatestGames(amount);
    }
}
