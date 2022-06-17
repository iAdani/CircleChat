package com.example.circlechat.entities;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @NonNull
    @PrimaryKey
    private String username;
    private String server;
    private String nickname;
    private String lastMessage;
    private String lastTime;

    public Contact(String username, String server, String nickname, String lastMessage, String lastTime) {
        this.username = username;
        this.server = server;
        this.nickname = nickname;
        this.lastMessage = lastMessage;
        this.lastTime = lastTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLastMessage() { return lastMessage; }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
