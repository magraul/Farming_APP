package com.example.farming.model;

public class Produs {
    private String denumire;
    private Integer cantitate;

    public Produs(String denumire, Integer cantitate) {
        this.denumire = denumire;
        this.cantitate = cantitate;
    }

    public String getDenumire() {
        return denumire;
    }

    public Integer getCantitate() {
        return cantitate;
    }
}
