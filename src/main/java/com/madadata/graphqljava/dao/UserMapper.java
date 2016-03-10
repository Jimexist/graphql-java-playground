package com.madadata.graphqljava.dao;

import com.google.common.collect.ImmutableList;
import com.madadata.graphqljava.api.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jiayu on 3/10/16.
 */
public class UserMapper implements ResultSetMapper<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);

    @Override
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new User(r.getInt("id"),
                r.getString("firstName"),
                r.getString("lastName"),
                r.getString("email"),
                ImmutableList.of());
    }
}
