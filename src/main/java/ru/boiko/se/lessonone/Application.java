package ru.boiko.se.lessonone;

import ru.boiko.se.lessonone.fruits.Apple;
import ru.boiko.se.lessonone.fruits.Box;
import ru.boiko.se.lessonone.fruits.Orange;

/**
 * Hello world!
 */
public class Application {
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
        System.out.println("\n");

        Box boxAppleFirst = new Box();
        boxAppleFirst.addSome(new Apple(), 7);

        Box boxAppleSecond = new Box();
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());

        Box boxAppleThird = new Box();
        boxAppleThird.addSome(new Apple(), 3);

        Box boxAppleFourth = new Box();
        boxAppleFourth.addSome(new Orange(), 7);

        System.out.println("Вес коробки boxAppleFirst (" +  boxAppleFirst.getWeight() + ") и вес коробки boxAppleSecond (" +  boxAppleSecond.getWeight() + ") " + (boxAppleFirst.compare(boxAppleSecond) ? "совпадает" : "не совпадает"));
        System.out.println("Вес коробки boxAppleFirst (" +  boxAppleFirst.getWeight() + ") и вес коробки boxAppleThird (" +  boxAppleThird.getWeight() + ") " + (boxAppleFirst.compare(boxAppleThird) ? "совпадает" : "не совпадает"));
        System.out.println("Вес коробки boxAppleFirst (" +  boxAppleFirst.getWeight() + ") и вес коробки boxAppleFourth (" +  boxAppleFourth.getWeight() + ") " + (boxAppleFirst.compare(boxAppleFourth) ? "совпадает" : "не совпадает"));

        boxAppleFirst.pass(boxAppleFourth);
    }
}
