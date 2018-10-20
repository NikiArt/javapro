package ru.boiko.se.lessontwo.packet;

public enum PacketType {

    NONE,
    PING,
    RESULT,

    REGISTRY,
    LOGOUT,
    LOGIN,

    MESSAGE,
    PRIVATE_MESSAGE,
    REFRESH_USER_LIST

}
