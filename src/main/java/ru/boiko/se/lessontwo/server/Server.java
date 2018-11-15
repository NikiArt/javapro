package ru.boiko.se.lessontwo.server;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService executor;
    private final Connections connections;

    @SneakyThrows
    public Server() {
        executor = Executors.newCachedThreadPool();
        connections = new Connections(executor);
        String message = "Сервер запущен, ожидаем подключения...";
        System.out.println(message);
        final BufferedWriter fileWriter = new BufferedWriter(new FileWriter("chatlog.txt", true));
        fileWriter.write( LocalDateTime.now() + ": " + message + "\n");
        fileWriter.close();
    }

    @SneakyThrows
    public final void run() {
        executor.submit(connections);
    }
}
