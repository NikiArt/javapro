package ru.boiko.se.lessonseven.annotations;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Method;

/**
 * @author Nikita Boiko
 * @see MethodTestAnno - класс для хранения связки метода и его приоритета
 * используется для корректной сортировки списка и дальнейшего запуска кождого метода
 * @see MethodTestAnno#compareTo(MethodTestAnno) - функция для указания параметра отбора
 */

@Getter
public class MethodTestAnno implements Comparable <MethodTestAnno>{
    final Integer priority;
    final Method method;

    public MethodTestAnno(int priority, Method method) {
        this.priority = priority;
        this.method = method;
    }

    @Override
    public int compareTo(@NotNull MethodTestAnno o) {
        return this.priority.compareTo(o.priority);
    }
}
