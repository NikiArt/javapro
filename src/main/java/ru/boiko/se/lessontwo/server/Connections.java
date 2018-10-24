package ru.boiko.se.lessontwo.server;

import lombok.SneakyThrows;
import ru.boiko.se.lessontwo.Config;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Connections implements Runnable{
    private final ExecutorService executor;
    private final ServerSocket serverSocket;

    @SneakyThrows
    public Connections(ExecutorService executor) {
        this.executor = executor;
        serverSocket = new ServerSocket(Config.getInstance().getPort());
    }

    @Override
    @SneakyThrows
    public void run() {
        final Socket socket = serverSocket.accept();
        System.out.println("Клиент подключился");
        BroadcastSender broadcastSender = new BroadcastSender(socket);
        executor.submit(broadcastSender);
        run();
    }
}
