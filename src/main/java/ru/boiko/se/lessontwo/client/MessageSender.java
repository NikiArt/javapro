package ru.boiko.se.lessontwo.client;

import lombok.SneakyThrows;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MessageSender implements Runnable{
    private final DataOutputStream outgoingMessage;
    private final Scanner consoleInput;

    @SneakyThrows
    public MessageSender(Socket socket) {
        outgoingMessage = new DataOutputStream(socket.getOutputStream());
        consoleInput = new Scanner(System.in);
    }

    @Override
    @SneakyThrows
    public void run() {
        if (consoleInput.hasNext()) {
            send(consoleInput.nextLine());
        }
        run();
    }

    @SneakyThrows
    public void send(String message) {
        outgoingMessage.writeUTF(message);
        outgoingMessage.flush();
    }
}
