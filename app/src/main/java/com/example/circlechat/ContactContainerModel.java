package com.example.circlechat;

public class ContactContainerModel {
    int image;
    String nickname;
    String lastMessage;
    String time;

    public ContactContainerModel(int image, String nickname, String lastMessage, String time) {
        this.image = image;
        this.nickname = nickname;
        this.lastMessage = lastMessage;
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTime() {
        return time;
    }
}
