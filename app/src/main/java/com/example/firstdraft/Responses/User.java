package com.example.firstdraft.Responses;

public class User {
    private int id;
    private String email, name, username;

    public User(int id, String email, String name, String username) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
