package ru.boiko.se.lessonone;

import ru.boiko.se.lessonone.fruits.Apple;
import ru.boiko.se.lessonone.fruits.Box;
import ru.boiko.se.lessonone.fruits.Fruit;
import ru.boiko.se.lessonone.fruits.Orange;

/**
 * Домашнее задание к первому уроку - "Обобщения"
 */
public class Application {
    public static void main(String[] args) {
        /**
         * создаем массивы с разными типами элментов
         * @see ChangeArrayPlace#change(int, int) - меняем символы местами
         * @see ChangeArrayPlace#convertToArrayList() - конвертируем массив в список
         */
        final Integer[] arrayOne = {1, 2, 3, 4, 5};
        final ChangeArrayPlace<Integer> arrayPlaceInt = new ChangeArrayPlace(arrayOne);
        arrayPlaceInt.println();
        arrayPlaceInt.change(2, 4);
        arrayPlaceInt.println();
        arrayPlaceInt.convertToArrayList();
        System.out.println("\n");

        final String[] arrayTwo = {"A", "B", "C", "D", "E"};
        final ChangeArrayPlace<String> arrayPlaceString = new ChangeArrayPlace(arrayTwo);
        arrayPlaceString.println();
        arrayPlaceString.change(1, 5);
        arrayPlaceString.println();
        arrayPlaceString.convertToArrayList();
        System.out.println("\n");


        final Double[] arrayThree = {0.01, 14.6, 2.3, 126.0, 15.1};
        final ChangeArrayPlace<Double> arrayPlaceDouble = new ChangeArrayPlace(arrayThree);
        arrayPlaceDouble.println();
        arrayPlaceDouble.change(4, 1);
        arrayPlaceDouble.println();
        System.out.println("\n");
        arrayPlaceDouble.convertToArrayList();
        System.out.println("\n");


        /**
         * создаем массивы с разными типами элментов
         * @see Box#addSome(Fruit, int) - добавляем в коробку фрукты Fruit, в количестве volume
         * @see Box#add(Fruit)  - добавляем в коробку фрукт Fruit
         * @see Box#getWeight() (Fruit)  - получаем общий вес фруктов в коробке
         * @see Box#getQuantity() (Fruit)  - получаем список (ArrayList) фруктов
         * @see Box#compare(Box) (Box)  (Fruit)  - сравниваем вес фруктов в коробках
         * @see Box#pass(Box)  (Fruit)  - перекладываем фрукты в коробку. В качестве аругмента указана коробка, из которой пересыпаем
         */
        Box<Apple> boxAppleFirst = new Box();
        boxAppleFirst.addSome(new Apple(), 7);

        Box<Apple> boxAppleSecond = new Box();
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());
        boxAppleSecond.add(new Apple());

        Box<Apple> boxAppleThird = new Box();
        boxAppleThird.addSome(new Apple(), 3);

        Box<Orange> boxOrangeFirst = new Box();
        boxOrangeFirst.addSome(new Orange(), 7);

        System.out.println("Вес коробки boxAppleFirst (" + boxAppleFirst.getWeight() + ") и вес коробки boxAppleSecond (" + boxAppleSecond.getWeight() + ") " + (boxAppleFirst.compare(boxAppleSecond) ? "совпадает" : "не совпадает"));
        System.out.println("Вес коробки boxAppleFirst (" + boxAppleFirst.getWeight() + ") и вес коробки boxAppleThird (" + boxAppleThird.getWeight() + ") " + (boxAppleFirst.compare(boxAppleThird) ? "совпадает" : "не совпадает"));
        System.out.println("Вес коробки boxAppleFirst (" + boxAppleFirst.getWeight() + ") и вес коробки boxAppleFourth (" + boxOrangeFirst.getWeight() + ") " + (boxAppleFirst.compare(boxOrangeFirst) ? "совпадает" : "не совпадает"));

        boxAppleFirst.pass(boxAppleSecond);
        System.out.println("Вес первой коробки " + boxAppleFirst.getWeight());
        System.out.println("Вес второй коробки " + boxAppleSecond.getWeight());
    }
}
