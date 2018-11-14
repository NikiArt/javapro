package ru.boiko.se;

import static org.junit.Assert.assertTrue;

import lombok.SneakyThrows;
import org.junit.Test;
import ru.boiko.se.lessonsix.ArrayConvert;
import ru.boiko.se.lessonsix.OneFourArray;
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

    @Test
    public void testArrayConvertEmpty() {
        Integer[] array = {};
        ArrayConvert arrayConvert = new ArrayConvert(array);
        Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();
    }

    @Test
    public void testArrayConvertOneFour() {
        Integer[] array = {4};
        ArrayConvert arrayConvert = new ArrayConvert(array);
        Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();
    }

    @Test
    public void testArrayConvertNormal() {
        Integer[] array = {1, 2, 4, 7, 2, 5};
        ArrayConvert arrayConvert = new ArrayConvert(array);
        Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();
    }

    @Test
    public void testArrayConvertFewFour() {
        Integer[] array = {1,4, 4, 7, 4, 5, 1};
        ArrayConvert arrayConvert = new ArrayConvert(array);
        Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();
    }

    @Test
    public void testArrayConvertRandom() {
        Integer[] array = new Integer[15];
        for (int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 10);
            System.out.print(array[i]);
        }
        System.out.println("\n");
        ArrayConvert arrayConvert = new ArrayConvert(array);
        Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();
    }

    @Test
    public void testOneFourArrayEmpty() {
        int[] arrayOF = {};
        OneFourArray oneFourArray = new OneFourArray(arrayOF);
        System.out.println("Array correct: " + oneFourArray.CheckCorrect());
    }

    @Test
    public void testOneFourArrayOnlyOne() {
        int[] arrayOF = {1, 1, 1, 1};
        OneFourArray oneFourArray = new OneFourArray(arrayOF);
        System.out.println("Array correct: " + oneFourArray.CheckCorrect());
    }

    @Test
    public void testOneFourArrayOnlyFour() {
        int[] arrayOF = {4, 4, 4};
        OneFourArray oneFourArray = new OneFourArray(arrayOF);
        System.out.println("Array correct: " + oneFourArray.CheckCorrect());
    }

    @Test
    public void testOneFourArrayNormal() {
        int[] arrayOF = {1, 4, 4, 1, 1, 1};
        OneFourArray oneFourArray = new OneFourArray(arrayOF);
        System.out.println("Array correct: " + oneFourArray.CheckCorrect());
    }
}
