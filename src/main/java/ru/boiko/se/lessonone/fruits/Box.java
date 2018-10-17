package ru.boiko.se.lessonone.fruits;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Box<T extends Fruit> {
    private final List<T> quantity = new ArrayList<>();

    public void add(T something) {
        quantity.add(something);
    }

    public void addSome(T something, int volume) {
        for (int i = 0; i < volume; i++) {
            add(something);
        }
    }

    public float getWeight() {
        float summary = 0f;
        for (T value: quantity) {
            summary += value.getWeight();
        }
        return summary;
    }

    public boolean compare(Box anotherBox) {
        return this.getWeight() == anotherBox.getWeight();
    }

    public boolean pass(Box anotherBox) {
        for (int i = 0; i < anotherBox.getQuantity().size(); i++) {
            quantity.add((T) anotherBox.getQuantity().get(i));
        }
        return true;
    }
}
