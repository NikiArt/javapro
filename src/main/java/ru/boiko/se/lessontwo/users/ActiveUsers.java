package ru.boiko.se.lessontwo.users;

import lombok.Getter;
import lombok.Setter;

import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ActiveUsers {
    private static ActiveUsers instance;
    private final Map<DataOutputStream, String> activeUsers = new HashMap<>();

    private ActiveUsers() {
    }

    public static synchronized ActiveUsers getInstance() {
        if (instance == null) {
            instance = new ActiveUsers();
        }
        return instance;
    }
}
