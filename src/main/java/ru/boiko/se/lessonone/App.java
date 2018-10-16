package ru.boiko.se.lessonone;

import ru.boiko.se.lessonone.ChangeArrayPlace;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        final Integer[] arrayOne = {1, 2, 3, 4, 5};
        final ChangeArrayPlace arrayPlaceInt = new ChangeArrayPlace(arrayOne);
        arrayPlaceInt.println();
        arrayPlaceInt.change(2, 4);
        arrayPlaceInt.println();
        arrayPlaceInt.convertToArrayList();
        System.out.println("\n");

        final String[] arrayTwo = {"A", "B", "C", "D", "E"};
        final ChangeArrayPlace arrayPlaceString = new ChangeArrayPlace(arrayTwo);
        arrayPlaceString.println();
        arrayPlaceString.change(1, 5);
        arrayPlaceString.println();
        arrayPlaceString.convertToArrayList();
        System.out.println("\n");


        final Double[] arrayThree = {0.01, 14.6, 2.3, 126.0, 15.1};
        final ChangeArrayPlace arrayPlaceDouble = new ChangeArrayPlace(arrayThree);
        arrayPlaceDouble.println();
        arrayPlaceDouble.change(4, 1);
        arrayPlaceDouble.println();
        System.out.println("\n");
        arrayPlaceDouble.convertToArrayList();
    }
}
