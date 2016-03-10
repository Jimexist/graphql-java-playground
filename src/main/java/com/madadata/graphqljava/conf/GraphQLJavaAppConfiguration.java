package com.madadata.graphqljava.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by jiayu on 3/10/16.
 */
public class GraphQLJavaAppConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();


    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }
}
