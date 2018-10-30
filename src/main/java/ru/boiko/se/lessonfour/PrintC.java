package ru.boiko.se.lessonfour;

import lombok.SneakyThrows;

public class PrintC implements Runnable{
    @Override
    @SneakyThrows
    public synchronized void run() {
        System.out.print("C");
    }
}
