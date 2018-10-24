package ru.boiko.se.lessontwo.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.boiko.se.lessontwo.packet.Packet;
import ru.boiko.se.lessontwo.packet.PacketType;
import ru.boiko.se.lessontwo.users.ActiveUsers;
import ru.boiko.se.lessontwo.users.User;
import ru.boiko.se.lessontwo.users.Users;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BroadcastSender implements Runnable{
    private DataInputStream incomingMessage;
    private DataOutputStream outgoingMessage;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Socket socket;
    private BufferedWriter fileWriter;
    private BufferedReader fileReader;


    @SneakyThrows
    public BroadcastSender(Socket socket) {
        this.socket = socket;
        incomingMessage = new DataInputStream(socket.getInputStream());
        outgoingMessage = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    //@SneakyThrows
    public void run() {
        String currentMessage = "";
        try {
            currentMessage = incomingMessage.readUTF();
        } catch (Exception e) {
            return;
        }
        System.out.println(currentMessage);
        if (!currentMessage.isEmpty()) {
            try {
                Packet packet = objectMapper.readValue(currentMessage, Packet.class);
                if (packet.getType() == PacketType.LOGIN) { login(packet); }
                if (packet.getType() == PacketType.REGISTRY) { registry(packet); }
                if (packet.getType() == PacketType.MESSAGE) { message(packet); }
                if (packet.getType() == PacketType.LOGOUT) { logout(packet); }
                if (packet.getType() == PacketType.CHANGE_NICK) { changeNick(packet); }
            } catch (Exception e) {
                send(currentMessage);
            }

        }
        run();
    }

    @SneakyThrows
    private void changeNick(Packet packet) {
        Packet packetRequest = new Packet();
        packetRequest.setType(PacketType.CHANGE_NICK);
        if (Users.getInstance().changeNick(packet)) {
            packetRequest.setSuccess(true);
            packetRequest.setMessage("Success");
        } else {
            packetRequest.setSuccess(false);
            packetRequest.setMessage("Такой никнейм уже используется");
        }
        send(objectMapper.writeValueAsString(packetRequest));
    }

    @SneakyThrows
    private void logout(Packet packet) {
        Packet packetRequest = new Packet();
        packetRequest.setType(PacketType.MESSAGE);
        packetRequest.setMessage(getUserNick(outgoingMessage) + " отключился от чата");
        message(packetRequest);
        System.out.println("Клиент " + ActiveUsers.getInstance().getActiveUsers().get(outgoingMessage) + " отключился");
        ActiveUsers.getInstance().getActiveUsers().remove(outgoingMessage);
        changeUserList();
        incomingMessage.close();
        outgoingMessage.close();
        socket.close();
    }

    @SneakyThrows
    private void registry(Packet packet) {
        Packet requestPacket = new Packet();
        if(!Users.getInstance().exists(packet.getLogin())) {
            if(Users.getInstance().regisrty(packet)) {
                requestPacket.setLogin(packet.getLogin());
                requestPacket.setType(PacketType.REGISTRY);
                requestPacket.setSuccess(true);
                requestPacket.setMessage("Пользователь " + packet.getLogin() + " успешно зарегистрирован");
            }
        } else {
            requestPacket = packet;
            requestPacket.setMessage("Пользователь " + packet.getLogin() + " уже зарегистрирован");
            requestPacket.setSuccess(false);
        }
        send(objectMapper.writeValueAsString(requestPacket));
    }

    @SneakyThrows
    private void login(Packet packet) {
        Packet requestPacket = new Packet();
        if (Users.getInstance().check(packet.getLogin(), packet.getPassword())) {
            for (HashMap.Entry<DataOutputStream, String> entry : ActiveUsers.getInstance().getActiveUsers().entrySet()) {
                if (entry.getValue().equals(packet.getLogin())) {
                    requestPacket.setId(packet.getId());
                    requestPacket.setMessage("Пользователь уже находится в чате");
                    requestPacket.setType(PacketType.LOGIN);
                    requestPacket.setSuccess(false);
                    send(objectMapper.writeValueAsString(requestPacket));
                    return;
                }
            }
            requestPacket.setId(packet.getId());
            requestPacket.setMessage("Авторизация пользователя " + packet.getLogin() + " прошла успешно");
            requestPacket.setType(PacketType.LOGIN);
            requestPacket.setSuccess(true);
            requestPacket.setLogin(packet.getLogin());
            requestPacket.setPassword(packet.getPassword());
            ActiveUsers.getInstance().getActiveUsers().put(outgoingMessage, packet.getLogin());
            changeUserList();
            sendLastMessages();

            Packet packetHello = new Packet();
            packetHello.setType(PacketType.MESSAGE);
            packetHello.setMessage(getUserNick(outgoingMessage) + " зашел в чат");
            message(packetHello);
        } else {
            requestPacket.setId(packet.getId());
            requestPacket.setMessage("Пользователь не найден или пароль введен не верно!");
            requestPacket.setType(PacketType.LOGIN);
            requestPacket.setSuccess(false);
        }
        send(objectMapper.writeValueAsString(requestPacket));
    }

    @SneakyThrows
    private void sendLastMessages() {
        File file = new File("chatlog.txt");
        if(file.exists() && !file.isDirectory()) {
            fileReader = new BufferedReader(new FileReader("chatlog.txt"));
            String string;
            List<String> allMessages = new ArrayList<>();
            while((string = fileReader.readLine()) != null)
                allMessages.add(string);
            fileReader.close();
            List<String> lastMessages = (allMessages.size() > 100) ? allMessages.subList(allMessages.size()-101,allMessages.size()-1) : allMessages;
            for (String currentString: lastMessages) {
                Packet packetHello = new Packet();
                packetHello.setType(PacketType.MESSAGE);
                packetHello.setMessage(currentString);
                outgoingMessage.writeUTF(objectMapper.writeValueAsString(packetHello));
            }
        }
    }

    @SneakyThrows
    private void changeUserList() {
        Packet requestPacket = new Packet();
        requestPacket.setType(PacketType.REFRESH_USER_LIST);
        String message = "";
        for (HashMap.Entry<DataOutputStream, String> entry : ActiveUsers.getInstance().getActiveUsers().entrySet()) {
            message += getUserNick(entry.getKey()) + ",,";
        }
        requestPacket.setMessage(message);
        for (HashMap.Entry<DataOutputStream, String> entry : ActiveUsers.getInstance().getActiveUsers().entrySet()) {
            DataOutputStream currentConnection = entry.getKey();
            currentConnection.writeUTF(objectMapper.writeValueAsString(requestPacket));
            System.out.println("Пользователю " + entry.getValue() + " отправлен список пользователей");
        }
    }

    //@SneakyThrows
    private void message(Packet packet) {
        try {
            for (HashMap.Entry<DataOutputStream, String> entry : ActiveUsers.getInstance().getActiveUsers().entrySet()) {
                DataOutputStream currentConnection = entry.getKey();
                currentConnection.writeUTF(objectMapper.writeValueAsString(packet));
            }
            System.out.println(packet.getMessage());
            fileWriter = new BufferedWriter(new FileWriter("chatlog.txt", true));
            fileWriter.write(packet.getMessage() + "\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private void send(String message) {
        outgoingMessage.writeUTF(message);
        System.out.println(message);
    }

    @SneakyThrows
    private String getUserNick(DataOutputStream dataOutputStream) {
        String login = ActiveUsers.getInstance().getActiveUsers().get(dataOutputStream);
        User user = Users.getInstance().findByLogin(login);
        return (user.getNick().isEmpty() ? user.getLogin() : user.getNick());
    }


}
