package ru.boiko.se.lessonone.fruits;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Box<T extends Fruit> {
    private List<T> quantity = new ArrayList<>();

    /**
     * @param something - добавляем какой-то фрукт в коробку
     */
    public void add(T something) {
        this.quantity.add(something);
    }

    /**
     * @param something - добавляем какой-то фрукт в коробку
     * @param volume    - в каком-то количестве
     */
    public void addSome(T something, int volume) {
        for (int i = 0; i < volume; i++) {
            add(something);
        }
    }

    /**
     * получаем общий вес всех фруктов
     */
    public float getWeight() {
        float summary = 0f;
        for (T value : quantity) {
            summary += value.getWeight();
        }
        return summary;
    }

    /**
     * @param anotherBox - сравниваем коробку с фруктами с текущей
     */
    public boolean compare(Box<? extends Fruit> anotherBox) {
        return this.getWeight() == anotherBox.getWeight();
    }

    /**
     * @param anotherBox - пересыпаем из какой-то коробки фрукты в текущую
     */
    public boolean pass(Box<T> anotherBox) {
        quantity.addAll(anotherBox.getQuantity());
        anotherBox.setQuantity(new ArrayList<>());
        return true;
    }
}
