package ru.boiko.se.lessonsix;

public class App {
    public static void main(String[] args) {
        Integer[] array = {2, 1, 4, 3, 7, 5};
        ArrayConvert arrayConvert = new ArrayConvert(array);
        Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();
    }
}
