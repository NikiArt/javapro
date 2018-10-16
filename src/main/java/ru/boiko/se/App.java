package ru.boiko.se;

import ru.boiko.se.lessonone.ChangeArrayPlace;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Integer[] arrayOne = {1, 2, 3, 4, 5};
        ChangeArrayPlace arrayPlaceInt = new ChangeArrayPlace(arrayOne);
        arrayPlaceInt.println();
        arrayPlaceInt.change(2,4);
        arrayPlaceInt.println();
        System.out.println("\n");

        String[] arrayTwo = {"A", "B", "C", "D", "E"};
        ChangeArrayPlace arrayPlaceString = new ChangeArrayPlace(arrayTwo);
        arrayPlaceString.println();
        arrayPlaceString.change(1,5);
        arrayPlaceString.println();
        System.out.println("\n");

        Double[] arrayThree = {0.01, 14.6, 2.3, 126.0, 15.1};
        ChangeArrayPlace arrayPlaceDouble = new ChangeArrayPlace(arrayThree);
        arrayPlaceDouble.println();
        arrayPlaceDouble.change(4,1);
        arrayPlaceDouble.println();
    }
}
