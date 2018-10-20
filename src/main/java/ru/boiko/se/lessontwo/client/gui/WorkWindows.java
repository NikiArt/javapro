package ru.boiko.se.lessontwo.client.gui;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkWindows {
    private static WorkWindows instance;
    private LoginWindow loginWindow;
    private ChatWindow chatWindow;
    private RegistryWindow registryWindow;



    private WorkWindows() {
    }

    public static synchronized WorkWindows getInstance() {
        if (instance == null) {
            instance = new WorkWindows();
        }
        return instance;
    }
}
