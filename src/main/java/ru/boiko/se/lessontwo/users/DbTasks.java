package ru.boiko.se.lessontwo.users;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.IOException;
import java.util.List;

public class DbTasks  extends DbConnectionService{

    private final UserRepository userRepository;

    public DbTasks() throws IOException {
        userRepository = sqlSession.getMapper(UserRepository.class);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUser(String login) {
        return userRepository.getUser(login);
    }

    public List<String> nickList() {
        return userRepository.nickList();
    }

    public void insert(User user) {
        userRepository.insert(user);
    }

    public void commit() {
        sqlSession.commit();
    }

    public void disconnect() {
        sqlSession.close();
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public User getUserByNick(String nick) {
        return userRepository.getUserByNick(nick);
    }
}
