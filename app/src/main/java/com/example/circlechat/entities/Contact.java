package com.example.circlechat.entities;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @NonNull
    @PrimaryKey
    private String id;
    private String server;
    private String name;
    private String last;
    private String lastdate;

    @Ignore
    public Contact(@NonNull String id) {
        this.id = id;
        this.server = null;
        this.name = null;
        this.last = null;
        this.lastdate = null;
    }

    public Contact(@NonNull String id, String server, String name, String last, String lastdate) {
        this.id = id;
        this.server = server;
        this.name = name;
        this.last = last;
        this.lastdate = lastdate;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }
}
