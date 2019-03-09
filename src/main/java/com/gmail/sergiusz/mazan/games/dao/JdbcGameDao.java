package com.gmail.sergiusz.mazan.games.dao;

import com.gmail.sergiusz.mazan.games.model.Game;
import com.gmail.sergiusz.mazan.games.model.Publisher;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class JdbcGameDao extends AbstractJdbcDao implements GameDao {

    private static final String INSERT_QUERY = "insert into game(title, date_of_publication, datetime_of_add," +
            " publisher_id) values(?, ?, ?, ?)";

    @Override
    public Game getById(int id) {
        return DataAccessUtils.singleResult(template.query("select g.game_id, g.title, g.date_of_publication, " +
                        "g.datetime_of_add, p. publisher_id, p.publisher_name from game g" +
                        " join publisher p using (publisher_id) where game_id = ?",
                new Object[] { id }, new GameMapper()));
    }

    @Override
    public List<Game> getByPattern(String pattern) {
        return template.query("select g.game_id, g.title, g.date_of_publication, g.datetime_of_add, " +
                        "p.publisher_id, p.publisher_name from game g " +
                        "join publisher p using (publisher_id) where g.title like ?",
                new Object[] { "%" + pattern + "%" }, new GameMapper());
    }

    @Override
    public List<Game> getGamesOfUser(int userId) {
        return template.query("select g.game_id, g.title, g.date_of_publication, g.datetime_of_add, " +
                "p.publisher_id, p.publisher_name from game g join publisher p using(publisher_id) " +
                "join user_game ug using(game_id) where ug.user_id = ?", new Object[] { userId }, new GameMapper());
    }

    @Override
    public void insert(Game entity) {
        Date dateOfPublication = Date.valueOf(entity.getDateOfPublication());
        Timestamp dateTimeOfAdd = Timestamp.valueOf(entity.getDateTimeOfAdd());
        template.update(INSERT_QUERY, entity.getTitle(), dateOfPublication, dateTimeOfAdd,
                entity.getPublisher().getId());
    }

    @Override
    public int insertAndGetKey(Game entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update((Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, new String[] {"game_id"});
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setDate(2, Date.valueOf(entity.getDateOfPublication()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getDateTimeOfAdd()));
            preparedStatement.setInt(4, entity.getPublisher().getId());
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public List<Game> getLatestGames(int amount) {
        return template.query("select g.game_id, g.title, g.date_of_publication, g.datetime_of_add, " +
                "p.publisher_id, p.publisher_name from game g join publisher p using(publisher_id) " +
                "order by datetime_of_add desc limit ?", new Object[] { amount }, new GameMapper());
    }

    private static class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet resultSet, int i) throws SQLException {
            Game game = new Game();
            game.setId(resultSet.getInt(1));
            game.setTitle(resultSet.getString(2));
            game.setDateOfPublication(resultSet.getDate(3).toLocalDate());
            game.setDateTimeOfAdd(resultSet.getTimestamp(4).toLocalDateTime());

            Publisher publisher = new Publisher();
            publisher.setId(resultSet.getInt(5));
            publisher.setName(resultSet.getString(6));

            game.setPublisher(publisher);
            return game;
        }
    }
}
