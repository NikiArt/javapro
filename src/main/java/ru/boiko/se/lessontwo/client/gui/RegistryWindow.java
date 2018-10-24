package ru.boiko.se.lessontwo.client.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.boiko.se.lessontwo.client.MessageSender;
import ru.boiko.se.lessontwo.packet.Packet;
import ru.boiko.se.lessontwo.packet.PacketType;

import javax.swing.*;

public class RegistryWindow extends JFrame {

    private JButton regButton;
    private JButton cancelButton;
    private JLabel mainText;
    private JLabel loginText;
    private JLabel passText;
    private JLabel passRepText;
    private JLabel nickText;
    private JLabel emailText;
    private JPasswordField passwordInput;
    private JPasswordField passwordRepeatInput;
    private JTextField loginInput;
    private JTextField nickInput;
    private JTextField emailInput;
    private MessageSender messageSender;
    private ObjectMapper objectMapper;
    
    public RegistryWindow(MessageSender messageSender) {
        objectMapper = new ObjectMapper();
        this.messageSender = messageSender;
        JPanel mainPanel = new JPanel();
        loginInput = new JTextField();
        mainText = new JLabel();
        regButton = new JButton();
        cancelButton = new JButton();
        loginText = new JLabel();
        passwordInput = new JPasswordField();
        passwordRepeatInput = new JPasswordField();
        nickInput = new JTextField();
        emailInput = new JTextField();
        passText = new JLabel();
        passRepText = new JLabel();
        nickText = new JLabel();
        emailText = new JLabel();

        setTitle("Сетевой чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainText.setText("Регистрация пользователя");
        mainText.setToolTipText("");

        regButton.setText("Ок");
        regButton.setToolTipText("");
        regButton.addActionListener(event -> registry(true));

        cancelButton.setText("Отмена");
        cancelButton.addActionListener(event -> registry(false));

        loginText.setText("Логин");

        loginInput.addActionListener(event -> passwordInput.requestFocus());
        passwordInput.addActionListener(event -> passwordRepeatInput.requestFocus());
        passwordRepeatInput.addActionListener(event -> nickInput.requestFocus());
        nickInput.addActionListener(event -> emailInput.requestFocus());
        emailInput.addActionListener(event -> registry(true));

        passText.setText("Пароль");

        passRepText.setText("Повторите пароль");

        nickText.setText("Никнейм");
        nickText.setToolTipText("");

        emailText.setText("e-mail");
        emailText.setToolTipText("");

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(loginText, GroupLayout.Alignment.TRAILING)
                                        .addComponent(passText, GroupLayout.Alignment.TRAILING)
                                        .addComponent(passRepText, GroupLayout.Alignment.TRAILING)
                                        .addComponent(nickText, GroupLayout.Alignment.TRAILING)
                                        .addComponent(emailText, GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(emailInput)
                                        .addComponent(nickInput)
                                        .addComponent(passwordRepeatInput)
                                        .addComponent(passwordInput)
                                        .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                                .addComponent(regButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(loginInput)
                                        .addComponent(mainText, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(126, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(mainText)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loginText))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passText))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordRepeatInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passRepText))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nickInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nickText))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(emailInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emailText))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(regButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(26, Short.MAX_VALUE))
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

    @SneakyThrows
    private void registry(boolean acceptRegistry) {
        if (acceptRegistry) {
            String password = new String(passwordInput.getPassword());
            String passwordRep = new String(passwordRepeatInput.getPassword());
            if (loginInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Поле логин обязательно для заполнения");
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Поле пароль обязательно для заполнения");
                return;
            }
            if (!password.equals(passwordRep)) {
                JOptionPane.showMessageDialog(this, "Введенные пароли не совпадают");
                return;
            }
            if (nickInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Поле никнейм обязательно для заполнения");
                return;
            }

            Packet packet = new Packet();
            packet.setType(PacketType.REGISTRY);
            packet.setMessage("Регистрация пользователя");
            packet.setLogin(loginInput.getText());
            packet.setPassword(password);
            packet.setNick(nickInput.getText());
            packet.setEmail(emailInput.getText());
            messageSender.send(objectMapper.writeValueAsString(packet));
        } else {
            WorkWindows.getInstance().getLoginWindow().setVisible(true);
            WorkWindows.getInstance().getRegistryWindow().setVisible(false);
        }
    }
}
