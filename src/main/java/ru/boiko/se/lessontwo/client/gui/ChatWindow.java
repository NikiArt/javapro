package ru.boiko.se.lessontwo.client.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import ru.boiko.se.lessontwo.client.MessageSender;
import ru.boiko.se.lessontwo.packet.Packet;
import ru.boiko.se.lessontwo.packet.PacketType;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

@Getter
@Setter
public class ChatWindow extends JFrame {

    private JButton sendButton;
    private JList userList;
    private JScrollPane userScroll;
    private JScrollPane chatScroll;
    private JTextArea chatArea;
    private JTextField inputText;
    private  DefaultListModel listModel;
    private MessageSender messageSender;
    private ObjectMapper objectMapper;
    private String user;
    
    public ChatWindow(MessageSender messageSender) {
        this.messageSender = messageSender;
        objectMapper = new ObjectMapper();
        userScroll = new JScrollPane();
        listModel = new DefaultListModel();
        userList = new JList(listModel);
        inputText = new JTextField();
        chatScroll = new JScrollPane();
        chatArea = new JTextArea();
        sendButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                logoff();
                e.getWindow().setVisible(false);
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                logoff();
                e.getWindow().setVisible(false);
                System.exit(0);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        userScroll.setViewportView(userList);

        chatArea.setColumns(20);
        chatArea.setRows(5);
        chatScroll.setViewportView(chatArea);

        sendButton.setText("Отправить");
        sendButton.addActionListener(event -> send());
        inputText.addActionListener(event -> send());
        userList.setLayoutOrientation(JList.VERTICAL);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(inputText, GroupLayout.Alignment.LEADING)
                                        .addComponent(chatScroll, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(userScroll)
                                        .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(chatScroll)
                                        .addComponent(userScroll, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(inputText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sendButton)))
        );

        pack();
        setLocationRelativeTo(null);
    }

    @SneakyThrows
    private void logoff() {
        Packet packet = new Packet();
        packet.setType(PacketType.LOGOUT);
        packet.setLogin(user);
        messageSender.send(objectMapper.writeValueAsString(packet));
        inputText.setText("");
    }

    @SneakyThrows
    private void send() {
        Packet packet = new Packet();
        packet.setType(PacketType.MESSAGE);
        packet.setLogin(user);
        packet.setMessage(user + ": " + inputText.getText());
        messageSender.send(objectMapper.writeValueAsString(packet));
        inputText.setText("");
    }

}

