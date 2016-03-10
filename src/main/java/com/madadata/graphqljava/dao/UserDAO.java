package com.madadata.graphqljava.dao;

import com.madadata.graphqljava.api.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Created by jiayu on 3/10/16.
 */
@RegisterMapper(UserMapper.class)
public interface UserDAO {

    @SqlQuery("select * from Users where id = :id")
    User findById(@Bind("id") int id);
}
