package ru.boiko.se.lessonseven.annotations;

import java.lang.annotation.*;

/**
 * @author Nikita Boiko
 * {@link Test} - аннотация для выполнения метода в тесте
 * @see Test#priority() - указывается очередность запуска теста: 1 - самый высокий приоритет,
 * 10 - самый низкий приоритет, 5 - приоритет по умолчанию
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    int priority() default 5;
}
