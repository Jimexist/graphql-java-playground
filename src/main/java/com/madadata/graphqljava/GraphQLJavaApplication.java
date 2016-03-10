package com.madadata.graphqljava;

import com.madadata.graphqljava.conf.GraphQLJavaAppConfiguration;
import com.madadata.graphqljava.dao.UserDAO;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * Created by jiayu on 3/10/16.
 */
public class GraphQLJavaApplication extends Application<GraphQLJavaAppConfiguration> {

    public static void main(String[] args) throws Exception {
        new GraphQLJavaApplication().run(args);
    }

    @Override
    public void run(GraphQLJavaAppConfiguration graphQLJavaAppConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, graphQLJavaAppConfiguration.getDataSourceFactory(), "sqlite");
        final UserDAO dao = jdbi.onDemand(UserDAO.class);
        environment.jersey().register(new GraphQLJavaResource(dao));
    }
}
