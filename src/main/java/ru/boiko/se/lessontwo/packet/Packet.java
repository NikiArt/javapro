package ru.boiko.se.lessontwo.packet;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Packet {
    private String id = UUID.randomUUID().toString();
    private String message = "";
    private String login = "";
    private String password = "";
    private PacketType type = PacketType.NONE;
    private Boolean success = false;
    private String nick = "";
    private String email = "";
}