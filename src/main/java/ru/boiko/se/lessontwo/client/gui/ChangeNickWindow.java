package ru.boiko.se.lessontwo.client.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.boiko.se.lessontwo.client.MessageSender;
import ru.boiko.se.lessontwo.packet.Packet;
import ru.boiko.se.lessontwo.packet.PacketType;

import javax.swing.*;

public class ChangeNickWindow extends JFrame{
    private JButton okButton;
    private JButton cancelButton;
    private JTextField inputNewNick;
    private MessageSender messageSender;
    private ObjectMapper objectMapper;
    private String user;

    public ChangeNickWindow(MessageSender messageSender) {
        this.messageSender = messageSender;
        objectMapper = new ObjectMapper();
        JLabel jLabel1 = new JLabel();
        inputNewNick = new JTextField();
        okButton = new JButton();
        cancelButton = new JButton();

        jLabel1.setText("Введите новое имя:");
        jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);

        inputNewNick.setText("inputNewNick");

        okButton.setText("ОК");
        okButton.addActionListener(event -> changeNick());
        cancelButton.setText("Отмена");
        cancelButton.addActionListener(event -> cancel());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(145, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inputNewNick)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(104, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputNewNick, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(151, Short.MAX_VALUE))
        );
    }

    public void setUser(String user) {
        this.user = user;
    }

    private void cancel() {
        WorkWindows.getInstance().getChatWindow().setVisible(true);
        WorkWindows.getInstance().getChangeNickWindow().setVisible(false);
    }

    @SneakyThrows
    private void changeNick() {
        @NotNull final Packet packet = new Packet();
        packet.setType(PacketType.CHANGE_NICK);
        packet.setLogin(user);
        packet.setNick(inputNewNick.getText());
        messageSender.send(objectMapper.writeValueAsString(packet));
    }

}
