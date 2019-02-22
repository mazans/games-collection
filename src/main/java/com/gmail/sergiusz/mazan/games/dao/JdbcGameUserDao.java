package com.gmail.sergiusz.mazan.games.dao;

import com.gmail.sergiusz.mazan.games.exception.InvalidUserDataException;
import com.gmail.sergiusz.mazan.games.model.Game;
import com.gmail.sergiusz.mazan.games.model.GameUser;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcGameUserDao extends AbstractJdbcDao implements GameUserDao {

    @Override
    public GameUser getByUsername(String username) {
        return DataAccessUtils.singleResult(template.query("select user_id, username, email, user_password" +
                " from app_user where username like ?", new Object[] { username }, new UserMapper()));
    }

    @Override
    public GameUser getByEmail(String email) {
        return DataAccessUtils.singleResult(template.query("select user_id, username, email, user_password" +
                " from app_user where email like ?", new Object[] { email }, new UserMapper()));
    }

    @Override
    public void insert(GameUser entity) throws InvalidUserDataException {
        try {
            template.update("insert into app_user(username, email, user_password) values(?, ?, ?)",
                    entity.getUsername(), entity.getEmail(), entity.getPassword());
        }
        catch(DuplicateKeyException e) {
            throw new InvalidUserDataException("User with username " + entity.getUsername() + " or email " +
                    entity.getEmail() + " already exists", e);
        }
    }

    @Override
    public void addGameToUser(GameUser user, Game game) throws InvalidUserDataException {
        try {
            template.update("insert into user_game(user_id, game_id) values(?, ?)", user.getId(), game.getId());
        }
        catch(DuplicateKeyException e) {
            throw new InvalidUserDataException("User with username " + user.getUsername() + " has game titled " +
                    game.getTitle(), e);
        }
    }

    private static class UserMapper implements RowMapper<GameUser> {

        @Override
        public GameUser mapRow(ResultSet resultSet, int i) throws SQLException {
            GameUser gameUser = new GameUser();
            gameUser.setId(resultSet.getInt(1));
            gameUser.setUsername(resultSet.getString(2));
            gameUser.setEmail(resultSet.getString(3));
            gameUser.setPassword(resultSet.getString(4));
            return gameUser;
        }
    }
}
