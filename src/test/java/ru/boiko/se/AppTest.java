package ru.boiko.se;

import static org.junit.Assert.assertTrue;

import lombok.SneakyThrows;
import org.junit.Test;
import ru.boiko.se.lessontwo.users.DbTasks;
import ru.boiko.se.lessontwo.users.User;
import ru.boiko.se.lessontwo.users.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testConnectToSqlBaseInsert() {
        try {
            DbTasks dbTasks = new DbTasks();
            User user = new User();
            user.setLogin("987");
            user.setPassword("567");
            user.setNick("ttteeesssttt");
            dbTasks.insert(user);
            dbTasks.commit();
            dbTasks.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @SneakyThrows
    public void testConnectToSqlBaseSelect() {
        DbTasks dbTasks = new DbTasks();
        List<User> users = dbTasks.findAll();
        System.out.println(users);
        dbTasks.disconnect();
    }

    @Test
    @SneakyThrows
    public void testConnectToSqlBaseSelectOne() {
        DbTasks dbTasks = new DbTasks();
        User user = dbTasks.getUser("admin");
        System.out.println(user);
        dbTasks.disconnect();
    }

    @Test
    @SneakyThrows
    public void fileTest() {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("chatlog.txt", true));
        fileWriter.write("1\n");
        fileWriter.write("2\n");
        fileWriter.write("3\n");
        fileWriter.close();
    }
}
