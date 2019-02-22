package com.gmail.sergiusz.mazan.games.dao;

import com.gmail.sergiusz.mazan.games.exception.InvalidPublisherException;
import com.gmail.sergiusz.mazan.games.model.Publisher;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcPublisherDao extends AbstractJdbcDao implements PublisherDao {

    @Override
    public Publisher getById(int id) {
        return DataAccessUtils.singleResult(template.query("select publisher_id, publisher_name from publisher " +
                "where publisher_id = ?", new Object[] { id }, new PublisherMapper()));
    }

    @Override
    public List<Publisher> getAll() {
        return template.query("select publisher_id, publisher_name from publisher", new PublisherMapper());
    }

    @Override
    public void insert(Publisher entity) throws InvalidPublisherException {
        try {
            template.update("insert into publisher set publisher_name = ?", entity.getName());
        }
        catch(DuplicateKeyException e) {
            throw new InvalidPublisherException("Publisher named: " + entity.getName() + " already exists in DB", e);
        }

    }

    private static class PublisherMapper implements RowMapper<Publisher> {

        @Override
        public Publisher mapRow(ResultSet resultSet, int i) throws SQLException {
            Publisher publisher = new Publisher();
            publisher.setId(resultSet.getInt(1));
            publisher.setName(resultSet.getString(2));
            return publisher;
        }
    }
}
