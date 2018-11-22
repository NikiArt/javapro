package ru.boiko.se.lessonseven;

import ru.boiko.se.lessonseven.annotations.*;

/**
 * @author Nikita Boiko
 * @see AppTest - класс для написания тестов
 * Аннотации BeforeSuite и AfterSuite - должны присутствовать в единичном экземпляре
 * К аннотации Test можно указать приоритет запуска: 1 - самый высокий, 10 - самый низкий,
 * 5 - значение по умолчанию
 */

public class AppTest {

    @BeforeSuite
    public void testone() {
        System.out.println("making BeforeSuite");
    }

    @AfterSuite
    public void testtwo() {
        System.out.println("making AfterSuite");
    }

    @Test(priority = 7)
    public void testthree() {
        System.out.println("making test. Priority 7");
    }

    @Test(priority = 1)
    public void testfour() {
        System.out.println("making test. Priority 1");
    }

    @Test(priority = 10)
    public void testfive() {
        System.out.println("making test. Priority 10");
    }

    @Test()
    public void testsix() {
        System.out.println("making test. Priority default (5)");
    }

}
