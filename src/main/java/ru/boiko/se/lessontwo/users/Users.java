package ru.boiko.se.lessontwo.users;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import ru.boiko.se.lessontwo.packet.Packet;

import java.util.LinkedHashMap;
import java.util.Map;

public class Users {
    private static Users instance;

    @NotNull
    private Map<String, User> users = new LinkedHashMap<>();


    private Users() {
    }

    public static synchronized Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public User findByLogin(String login) {
        if (login == null || login.isEmpty()) return null;
        return users.get(login);
    }

    public boolean check(@Nullable String login, @Nullable String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        @Nullable final User user = findByLogin(login);
        if (user == null) return false;
        return password.equals(user.getPassword());
    }

    public boolean regisrty(Packet packet) {
        @NotNull final User user = new User();
        user.setLogin(packet.getLogin());
        user.setPassword(packet.getPassword());
        user.setNick(packet.getNick());
        user.setEmail(packet.getEmail());
        users.put(packet.getLogin(), user);
        System.out.println("Added new user with login " + packet.getLogin());
        return true;

    }

    public boolean exists(String login) {
        if (login == null || login.isEmpty()) return false;
        return users.containsKey(login);
    }
}
