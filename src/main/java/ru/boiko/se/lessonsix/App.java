package ru.boiko.se.lessonsix;

public class App {
    public static void main(String[] args) {
        final Integer[] array = {2, 1, 4, 3, 7, 5};
        final ArrayConvert arrayConvert = new ArrayConvert(array);
        final Integer[] convertedArray = arrayConvert.Convert();
        for (int i = 0; i < convertedArray.length; i++) {
            System.out.print(convertedArray[i]);
        }
        System.out.println();

        final int[] arrayOF = {1, 4, 1, 1, 1, 4};
        final OneFourArray oneFourArray = new OneFourArray(arrayOF);
        System.out.println("Array correct: " + oneFourArray.CheckCorrect());
    }
}
