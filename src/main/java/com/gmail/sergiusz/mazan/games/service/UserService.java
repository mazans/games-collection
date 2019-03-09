package com.gmail.sergiusz.mazan.games.service;

import com.gmail.sergiusz.mazan.games.dao.GameDao;
import com.gmail.sergiusz.mazan.games.dao.GameUserDao;
import com.gmail.sergiusz.mazan.games.exception.InvalidUserDataException;
import com.gmail.sergiusz.mazan.games.model.Game;
import com.gmail.sergiusz.mazan.games.model.GameUser;
import com.gmail.sergiusz.mazan.games.model.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private GameDao gameDao;
    private GameUserDao gameUserDao;

    private PasswordEncoder encoder;

    @Autowired
    private void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Autowired
    private void setGameUserDao(GameUserDao gameUserDao) {
        this.gameUserDao = gameUserDao;
    }

    @Autowired
    private void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public Set<RegistrationResult> registerUser(UserRegistration newUser) {
        GameUser user = new GameUser(newUser.getUsername(), newUser.getEmail(), encoder.encode(newUser.getPassword()));
        try {
            gameUserDao.insert(user);
            return EnumSet.of(RegistrationResult.OK);
        }
        catch(InvalidUserDataException e) {
            Set<RegistrationResult> result = EnumSet.noneOf(RegistrationResult.class);
            if(gameUserDao.getByUsername(newUser.getUsername()) != null)
                result.add(RegistrationResult.DUPLICATE_USERNAME);
            if(gameUserDao.getByEmail(newUser.getEmail()) != null)
                result.add(RegistrationResult.DUPLICATE_EMAIL);
            return result;
        }
    }

    public GameUser getUser(String username) {
        GameUser gameUser = gameUserDao.getByUsername(username);
        List<Game> games = gameDao.getGamesOfUser(gameUser.getId());
        gameUser.setGames(games);
        return gameUser;
    }

    public AddGameToUserResult addGameToUser(GameUser user, int gameId) {
        try {
            Game game = gameDao.getById(gameId);
            gameUserDao.addGameToUser(user, game);
            user.getGames().add(game);
            return AddGameToUserResult.OK;
        } catch (InvalidUserDataException e) {
            return AddGameToUserResult.USER_HAS_GAME;
        }

    }
}
