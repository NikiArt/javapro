package ru.boiko.se.lessonfour;

import lombok.SneakyThrows;

public class PrintB implements Runnable {
    @Override
    @SneakyThrows
    public synchronized void run() {
        System.out.print("B");
    }
}
