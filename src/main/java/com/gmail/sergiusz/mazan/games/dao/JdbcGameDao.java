package com.gmail.sergiusz.mazan.games.dao;

import com.gmail.sergiusz.mazan.games.model.Game;
import com.gmail.sergiusz.mazan.games.model.Publisher;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcGameDao extends AbstractJdbcDao implements GameDao {

    @Override
    public Game getById(int id) {
        return DataAccessUtils.singleResult(template.query("select g.game_id, g.title, g.date_of_publication, " +
                "p. publisher_id, p.publisher_name from game g join publisher p using (publisher_id) where game_id = ?",
                new Object[] { id }, new GameMapper()));
    }

    @Override
    public List<Game> getByPattern(String pattern) {
        return template.query("select g.game_id, g.title, g.date_of_publication, p.publisher_id, " +
                "p.publisher_name from game g join publisher p using (publisher_id) where g.title like ?",
                new Object[] { "%" + pattern + "%" }, new GameMapper());
    }

    @Override
    public List<Game> getGamesOfUser(int userId) {
        return template.query("select g.game_id, g.title, g.date_of_publication, p.publisher_id," +
                " p.publisher_name from game g join publisher p using(publisher_id) " +
                "join user_game ug using(game_id) where ug.user_id = ?", new Object[] { userId }, new GameMapper());
    }

    @Override
    public void insert(Game entity) {
        Date yearOfPublication = Date.valueOf(entity.getDateOfPublication());
        template.update("insert into game(title, date_of_publication, publisher_id) values(?, ?, ?)",
                entity.getTitle(), yearOfPublication, entity.getPublisher().getId());
    }

    private static class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet resultSet, int i) throws SQLException {
            Game game = new Game();
            game.setId(resultSet.getInt(1));
            game.setTitle(resultSet.getString(2));
            game.setDateOfPublication(resultSet.getDate(3).toLocalDate());

            Publisher publisher = new Publisher();
            publisher.setId(resultSet.getInt(4));
            publisher.setName(resultSet.getString(5));

            game.setPublisher(publisher);
            return game;
        }
    }
}
