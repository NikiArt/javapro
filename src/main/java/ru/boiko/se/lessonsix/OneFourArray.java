package ru.boiko.se.lessonsix;

public class OneFourArray {
    final int[] incomingArray;

    public OneFourArray(final int[] incomingArray) {
        this.incomingArray = incomingArray;
    }

    public Boolean CheckCorrect() {
        boolean valueA = false;
        boolean valueB = false;
        for (int i = 0; i < incomingArray.length; i++) {
            if (incomingArray[i] == 1) {
                valueA = true;
            }
            if (incomingArray[i] == 4) {
                valueB = true;
            }
            if (valueA && valueB) {
                return true;
            }
        }
        return false;
    }
}
