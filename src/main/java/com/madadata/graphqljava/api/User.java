package com.madadata.graphqljava.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableCollection;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * Created by jiayu on 3/10/16.
 */
public class User {

    private int userId;
    @Length(min = 3)
    private String firstName;
    @Length(min = 3)
    private String lastName;
    @Email
    private String email;
    private ImmutableCollection<User> friends;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String email, ImmutableCollection<User> friends) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.friends = friends;
    }


    @JsonProperty
    public int getUserId() {
        return userId;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public ImmutableCollection<User> getFriends() {
        return friends;
    }
}
