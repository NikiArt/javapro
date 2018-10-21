package ru.boiko.se.lessontwo.users;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import lombok.SneakyThrows;
import ru.boiko.se.lessontwo.packet.Packet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Users{
    private static Users instance;

    @NotNull
    private Map<String, User> users = new LinkedHashMap<>();


    private Users() throws IOException {
    }

    public static synchronized Users getInstance() throws IOException{
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    @SneakyThrows
    public User findByLogin(String login) {
        DbTasks dbTasks = new DbTasks();
        if (login == null || login.isEmpty()) return null;
        return dbTasks.getUser(login);
    }

    @SneakyThrows
    public boolean check(@Nullable String login, @Nullable String password) {
        DbTasks dbTasks = new DbTasks();
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        @Nullable final User user = dbTasks.getUser(login);
        dbTasks.disconnect();
        if (user == null) return false;
        return password.equals(user.getPassword());
    }

    @SneakyThrows
    public boolean changeNick(Packet packet) {
        DbTasks dbTasks = new DbTasks();
        if (packet.getNick() == null) return false;
        @Nullable final User userCheck = dbTasks.getUserByNick(packet.getNick());
        if (userCheck != null) return false;
        @Nullable final User user = dbTasks.getUser(packet.getLogin());
        dbTasks.update(user);
        dbTasks.commit();
        dbTasks.disconnect();
        return true;
    }

    @SneakyThrows
    public boolean regisrty(Packet packet) {
        DbTasks dbTasks = new DbTasks();
        if (dbTasks.getUser(packet.getLogin()) != null) {
            dbTasks.disconnect();
            return false;
        } else{
            @NotNull final User user = new User();
            user.setLogin(packet.getLogin());
            user.setPassword(packet.getPassword());
            user.setNick(packet.getNick());
            user.setEmail(packet.getEmail());
            users.put(packet.getLogin(), user);
            dbTasks.insert(user);
            dbTasks.commit();
            dbTasks.disconnect();
            System.out.println("Added new user with login " + packet.getLogin());
            return true;
        }

    }

    @SneakyThrows
    public boolean exists(String login) {
        DbTasks dbTasks = new DbTasks();
        if (login == null || login.isEmpty()) return false;
        if (dbTasks.getUser(login) == null) {
            dbTasks.disconnect();
            return false;
        } else {
            dbTasks.disconnect();
            return true;
        }
    }
}
