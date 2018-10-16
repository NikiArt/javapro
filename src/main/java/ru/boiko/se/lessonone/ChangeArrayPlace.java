package ru.boiko.se.lessonone;

import lombok.Getter;

@Getter
public class ChangeArrayPlace<T> {
    private T[] array;

    public ChangeArrayPlace(T[] array) {
        this.array = array;
    }

    public T[] change(int valueFirst, int valueSecond) {
        T[] arrayTemp = array.clone();
        array[valueSecond-1] = arrayTemp[valueFirst-1];
        array[valueFirst-1] = arrayTemp[valueSecond-1];
        return array;
    }

    public void println() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }
}
