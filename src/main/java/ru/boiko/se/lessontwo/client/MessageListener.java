package ru.boiko.se.lessontwo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.boiko.se.lessontwo.client.gui.ChatWindow;
import ru.boiko.se.lessontwo.client.gui.WorkWindows;
import ru.boiko.se.lessontwo.packet.Packet;
import ru.boiko.se.lessontwo.packet.PacketType;

import javax.swing.*;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class MessageListener implements Runnable{
    private DataInputStream incomingMessage;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public MessageListener(Socket socket) {
        incomingMessage = new DataInputStream(socket.getInputStream());
    }

    @Override
    @SneakyThrows
    public void run() {
        String currentMessage = incomingMessage.readUTF();
        if (!currentMessage.isEmpty()) {
            System.out.println(currentMessage);
            Packet packet = objectMapper.readValue(currentMessage, Packet.class);
            if (packet.getType() == PacketType.LOGIN) { login(packet); }
            if (packet.getType() == PacketType.REGISTRY) { regisrty(packet); }
            if (packet.getType() == PacketType.REFRESH_USER_LIST) { refreshUserList(packet); }
            if (packet.getType() == PacketType.MESSAGE) { writeMessage(packet); }
        }
        run();
    }

    private void writeMessage(Packet packet) {
        ChatWindow chatWindow = WorkWindows.getInstance().getChatWindow();
        chatWindow.getChatArea().append(packet.getMessage() + "\n");
    }


    private void refreshUserList(Packet packet) {
        try {
            ChatWindow chatWindow = WorkWindows.getInstance().getChatWindow();
            DefaultListModel listModel = chatWindow.getListModel();
            listModel.removeAllElements();
            ArrayList aList = new ArrayList(Arrays.asList(packet.getMessage().split(",,")));
            for (int i = 0; i < aList.size(); i++) {
                listModel.addElement(aList.get(i));
                System.out.println(aList.get(i));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void regisrty(Packet packet) {
        if (packet.getSuccess()) {
            WorkWindows.getInstance().getLoginWindow().setVisible(true);
            WorkWindows.getInstance().getRegistryWindow().setVisible(false);
            JOptionPane.showMessageDialog(WorkWindows.getInstance().getRegistryWindow(),packet.getMessage());
        } else {
            JOptionPane.showMessageDialog(WorkWindows.getInstance().getLoginWindow(),packet.getMessage());
        }
    }

    private void login(Packet packet) {
        if (packet.getSuccess()) {
            WorkWindows.getInstance().getLoginWindow().setVisible(false);
            WorkWindows.getInstance().getChatWindow().setVisible(true);
            WorkWindows.getInstance().getChatWindow().setUser((packet.getNick().isEmpty()) ? packet.getLogin() : packet.getNick());
        } else {
            JOptionPane.showMessageDialog(WorkWindows.getInstance().getLoginWindow(),packet.getMessage());
        }
    }
}
