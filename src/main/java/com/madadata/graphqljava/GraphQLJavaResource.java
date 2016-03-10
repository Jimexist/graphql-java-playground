package com.madadata.graphqljava;

import com.codahale.metrics.annotation.Timed;
import com.madadata.graphqljava.api.User;
import com.madadata.graphqljava.dao.UserDAO;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static graphql.Scalars.GraphQLID;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 * Created by jiayu on 3/10/16.
 */
@Path("/graphql/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GraphQLJavaResource {

    private static final Logger logger = LoggerFactory.getLogger(GraphQLJavaResource.class);

    private final UserDAO userDAO;

    private final GraphQLSchema schema;

    public GraphQLJavaResource(UserDAO userDAO) {
        this.userDAO = userDAO;
        GraphQLObjectType userType = newObject()
                .name("User")
                .description("user type")
                .field(newFieldDefinition()
                        .type(GraphQLID)
                        .name("userId")
                        .build())
                .field(newFieldDefinition()
                        .type(new GraphQLNonNull(GraphQLString))
                        .name("firstName")
                        .build())
                .field(newFieldDefinition()
                        .type(new GraphQLNonNull(GraphQLString))
                        .name("lastName")
                        .build())
                .field(newFieldDefinition()
                        .type(new GraphQLNonNull(GraphQLString))
                        .name("email")
                        .build())
                .field(newFieldDefinition()
                        .type(new GraphQLList(new GraphQLTypeReference("User")))
                        .name("friends")
                        .build())
                .build();
        GraphQLObjectType queryType = newObject()
                .name("UserQuery")
                .description("user query")
                .field(newFieldDefinition()
                        .name("user")
                        .argument(newArgument()
                                .name("id")
                                .description("user id")
                                .type(GraphQLString)
                                .build())
                        .type(userType)
                        .dataFetcher(dataFetchingEnvironment -> {
                            checkArgument(dataFetchingEnvironment.containsArgument("id"), "cannot find argument 'id'");
                            String id = dataFetchingEnvironment.getArgument("id");
                            return userDAO.findById(id);
                        })
                        .build())
                .build();
        schema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();
    }

    @GET
    @Timed
    public User findUser(@QueryParam("query") String query) {
        try {
            logger.info("query '{}'", query);
            ExecutionResult result = new GraphQL(schema).execute(query);
            checkState(result.getErrors().isEmpty(), "errors: %s", result.getErrors());
            Map<String, Object> data = (Map<String, Object>) result.getData();
            logger.info("data {}", data);
            return (User) data.get("user");
        } catch (Exception e) {
            logger.info("exception during graphql query", e);
            throw new WebApplicationException(e, BAD_REQUEST);
        }
    }
}
