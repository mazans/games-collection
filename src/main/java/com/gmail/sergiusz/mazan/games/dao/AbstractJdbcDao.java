package com.gmail.sergiusz.mazan.games.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public abstract class AbstractJdbcDao {

    JdbcTemplate template;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }
}
