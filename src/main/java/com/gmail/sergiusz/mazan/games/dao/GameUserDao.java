package com.gmail.sergiusz.mazan.games.dao;

import com.gmail.sergiusz.mazan.games.exception.InvalidUserDataException;
import com.gmail.sergiusz.mazan.games.model.Game;
import com.gmail.sergiusz.mazan.games.model.GameUser;

public interface GameUserDao {
    GameUser getByUsername(String username);
    GameUser getByEmail(String email);
    void insert(GameUser entity) throws InvalidUserDataException;
    void addGameToUser(GameUser user, Game game) throws  InvalidUserDataException;
}
