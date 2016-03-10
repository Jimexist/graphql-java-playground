package com.madadata.graphqljava.dao;

import com.madadata.graphqljava.api.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jiayu on 3/10/16.
 */
public class UserMapper implements ResultSetMapper<User> {
    @Override
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return null;
    }
}
