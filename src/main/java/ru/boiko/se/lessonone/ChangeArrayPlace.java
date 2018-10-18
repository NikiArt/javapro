package ru.boiko.se.lessonone;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChangeArrayPlace<T> {
    private final T[] array;
    private final List<T> arrayList = new ArrayList<>();

    public ChangeArrayPlace(final T[] array) {
        this.array = array;
    }

    /**
     * @param valueFirst  - первое число
     * @param valueSecond - второе число
     *                    меняем их местами
     */
    public T[] change(final int valueFirst, final int valueSecond) {
        T[] arrayTemp = array.clone();
        array[valueSecond - 1] = arrayTemp[valueFirst - 1];
        array[valueFirst - 1] = arrayTemp[valueSecond - 1];
        return array;
    }

    public void println() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    /**
     * конвертируем массив в список
     */
    public void convertToArrayList() {
        arrayList.clear();
        for (T value : array) {
            arrayList.add(value);
        }
        System.out.println(arrayList);
    }
}
