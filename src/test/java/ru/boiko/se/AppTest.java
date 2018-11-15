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

    /**
     * Задание №2
    * Тест конвертации пустого  массива
     */
    @Test
    public void testArrayConvertEmpty() {
        final Integer[] array = {};
        final ArrayConvert arrayConvert = new ArrayConvert(array);
        final Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();
    }

    /**
     * Задание №2
     * Тест конвертации массива, где есть только эелемент со значением 4
     */
    @Test
    public void testArrayConvertOneFour() {
        final Integer[] array = {4};
        final ArrayConvert arrayConvert = new ArrayConvert(array);
        final Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();
    }

    /**
     * Задание №2
     * Тест конвертации массива, где есть только эелементы с разными значениями
     * ситуация, описанная в задаче
     */
    @Test
    public void testArrayConvertNormal() {
        final Integer[] array = {1, 2, 4, 7, 2, 5};
        final ArrayConvert arrayConvert = new ArrayConvert(array);
        final Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();
    }

    /**
     * Задание №2
     * Тест конвертации массива с несколькими эелементамы, равными 4
     */
    @Test
    public void testArrayConvertFewFour() {
        final Integer[] array = {1,4, 4, 7, 4, 5, 1};
        final ArrayConvert arrayConvert = new ArrayConvert(array);
        final Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();
    }

    /**
     * Задание №2
     * Тест конвертации массива со случайными числами
     */
    @Test
    public void testArrayConvertRandom() {
        final Integer[] array = new Integer[15];
        for (int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 10);
            System.out.print(array[i]);
        }
        System.out.println("\n");
        final ArrayConvert arrayConvert = new ArrayConvert(array);
        final Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();
    }

    /**
     * Задание №3
     * Тест проверки пустого массива
     */
    @Test
    public void testOneFourArrayEmpty() {
        final int[] arrayOF = {};
        final OneFourArray oneFourArray = new OneFourArray(arrayOF);
        System.out.println("Array correct: " + oneFourArray.CheckCorrect());
    }

    /**
     * Задание №3
     * Тест проверки массива с элементами, равными 1
     */
    @Test
    public void testOneFourArrayOnlyOne() {
        final int[] arrayOF = {1, 1, 1, 1};
        final OneFourArray oneFourArray = new OneFourArray(arrayOF);
        System.out.println("Array correct: " + oneFourArray.CheckCorrect());
    }

    /**
     * Задание №3
     * Тест проверки массива с элементами, равными 4
     */
    @Test
    public void testOneFourArrayOnlyFour() {
        final int[] arrayOF = {4, 4, 4};
        final OneFourArray oneFourArray = new OneFourArray(arrayOF);
        System.out.println("Array correct: " + oneFourArray.CheckCorrect());
    }

    /**
     * Задание №3
     * Тест проверки массива с элементами, равными 1 и 4
     * ситуация, описанная в задаче
     */
    @Test
    public void testOneFourArrayNormal() {
        final int[] arrayOF = {1, 4, 4, 1, 1, 1};
        final OneFourArray oneFourArray = new OneFourArray(arrayOF);
        System.out.println("Array correct: " + oneFourArray.CheckCorrect());
    }
}
