package ru.boiko.se.lessontwo;

import ru.boiko.se.lessontwo.client.Client;
import ru.boiko.se.lessontwo.server.Server;

public class App {
    public static void main(String[] args) {
        getApp(args);
    }

    private static void getApp(final String[] args){
        if(args == null || args.length == 0) {
            try {
                final Client clientRun = new Client();
                clientRun.run();
            } catch(Exception e) {
                System.out.println("Не удалось установить соединение с сервером");
            }
        }
        if(args.length == 1 && "server".equals(args[0])) {
            final Server serverRun = new Server();
            serverRun.run();
        }
    }
}
