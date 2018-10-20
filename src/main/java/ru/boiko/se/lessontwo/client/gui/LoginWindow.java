package ru.boiko.se.lessontwo.client.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.boiko.se.lessontwo.client.MessageSender;
import ru.boiko.se.lessontwo.packet.Packet;
import ru.boiko.se.lessontwo.packet.PacketType;

import javax.swing.*;

@Getter
@Setter
public class LoginWindow extends JFrame{
    
    private JButton loginButton;
    private JButton registryButton;
    private JPanel mainPanel;
    private JTextField loginInput;
    private JPasswordField passwordInput;
    private ObjectMapper objectMapper;
    private JLabel textLabel;
    private MessageSender messageSender;

    @SneakyThrows
    public LoginWindow(MessageSender messageSender) {
        this.messageSender = messageSender;
        objectMapper = new ObjectMapper();
        mainPanel = new JPanel();
        loginInput = new JTextField();
        loginInput.addActionListener(event -> passwordInput.requestFocus());
        textLabel = new JLabel();
        textLabel.setText("Вход в чат.");
        passwordInput = new JPasswordField();
        passwordInput.addActionListener(event -> send());
        loginButton = new JButton();
        loginButton.addActionListener(event -> send());
        registryButton = new JButton();
        registryButton.addActionListener(event -> openRegistryWindow());
        setTitle("Сетевой чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loginButton.setText("Вход");

        registryButton.setText("Регистрация");

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap(114, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(loginButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(registryButton))
                                        .addComponent(textLabel)
                                        .addComponent(loginInput)
                                        .addComponent(passwordInput))
                                .addContainerGap(140, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap(72, Short.MAX_VALUE)
                                .addComponent(textLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loginInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginButton)
                                        .addComponent(registryButton))
                                .addContainerGap(140, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void openRegistryWindow() {
        WorkWindows.getInstance().getLoginWindow().setVisible(false);
        loginInput.setText("");
        passwordInput.setText("");
        WorkWindows.getInstance().getRegistryWindow().setVisible(true);
    }

    @SneakyThrows
    private void send() {

        @NotNull final Packet packet = new Packet();
        packet.setType(PacketType.LOGIN);
        packet.setLogin(loginInput.getText());
        packet.setPassword(new String(passwordInput.getPassword()));
        packet.setMessage("Logging in user ...");
        messageSender.send(objectMapper.writeValueAsString(packet));
    }
}

