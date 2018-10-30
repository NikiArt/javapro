package ru.boiko.se.lessonfour;

import lombok.SneakyThrows;

public class PrintA implements Runnable {
    @Override
    @SneakyThrows
    public synchronized void run() {
        System.out.print("A");
    }
}
