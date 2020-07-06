package com.example.farming.model;

public class Angajat {
    private String username, parola, status;
    public Angajat(String username, String parola, String status) {
        this.parola = parola;
        this.username = username;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public String getParola() {
        return parola;
    }

    public String getStatus() {
        return status;
    }
}
