package ru.boiko.se.lessonseven.annotations;

import java.lang.annotation.*;

/**
 * @author Nikita Boiko
 * {@link AfterSuite} - аннотация для выполнения метода в начале теста
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BeforeSuite {
}
