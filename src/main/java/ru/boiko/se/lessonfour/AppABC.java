package ru.boiko.se.lessonfour;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppABC {

    @SneakyThrows
    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(3);
        final PrintA printA = new PrintA();
        final PrintB printB = new PrintB();
        final PrintC printC = new PrintC();
        for (int i = 0; i < 15; i++) {
            executorService.submit(printA);
            executorService.submit(printB).get();
            executorService.submit(printC).get();
        }
        System.out.println();
    }
}
