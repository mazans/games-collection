package com.gmail.sergiusz.mazan.games.dao;

import com.gmail.sergiusz.mazan.games.model.Game;

import java.util.List;

public interface GameDao {
    Game getById(int id);
    List<Game> getByPattern(String pattern);
    List<Game> getGamesOfUser(int userId);
    void insert(Game entity);
}
