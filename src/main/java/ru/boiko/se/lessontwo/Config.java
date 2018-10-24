package ru.boiko.se.lessontwo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Config {
    private static Config instance;
    private final Integer port = 8179;
    private final String host = "localhost";

    public Config() {    }

    public static synchronized Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
}
