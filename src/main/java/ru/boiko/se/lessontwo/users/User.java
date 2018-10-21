package ru.boiko.se.lessontwo.users;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    private String id = "";
    private String login = "";
    private String password = "";
    private String nick = "";
    private String email = "";

    public User() {
        this.id = UUID.randomUUID().toString();
    }

}
