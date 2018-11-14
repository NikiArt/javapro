package ru.boiko.se.lessonsix;

import java.util.ArrayList;
import java.util.List;

public class ArrayConvert {
    final Integer[] incomingArray;

    public ArrayConvert(Integer[] incomingArray) {
        this.incomingArray = incomingArray;
    }

    public Integer[] Convert() {
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < incomingArray.length; i++) {
            if (incomingArray[i] == 4) {
                tempList.clear();
            } else {
                tempList.add(incomingArray[i]);
            }
        }
        return tempList.toArray(new Integer[tempList.size()]);
    }
}
