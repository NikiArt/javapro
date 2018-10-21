package ru.boiko.se.lessontwo.users;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserRepository {

    @Select("SELECT * FROM `users`")
    List<User> findAll();

    @Select("SELECT nick FROM `users`")
    List<String> nickList();

    @Select("SELECT * FROM `users` WHERE login = #{login}")
    User getUser(String login);

    @Insert("INSERT INTO `users`(`id`, `login`, `password`, `nick`, `email`) VALUES (#{id}, #{login}, #{password}, #{nick}, #{email})")
    void insert(User user);
}
