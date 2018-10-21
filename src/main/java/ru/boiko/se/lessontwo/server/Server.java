package ru.boiko.se.lessontwo.server;

import lombok.SneakyThrows;
import ru.boiko.se.lessontwo.packet.Packet;
import ru.boiko.se.lessontwo.users.Users;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService executor;
    private final Connections connections;

    @SneakyThrows
    public Server() {
        executor = Executors.newCachedThreadPool();
        connections = new Connections(executor);
        System.out.println("Сервер запущен, ожидаем подключения...");
    }

    @SneakyThrows
    public final void run() {
        executor.submit(connections);
    }
}
