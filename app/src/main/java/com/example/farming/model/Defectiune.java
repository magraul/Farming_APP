package com.example.farming.model;

public class Defectiune {
    private String utilaj, gravitate;

    public Defectiune(String utilaj, String gravitate) {
        this.utilaj = utilaj;
        this.gravitate = gravitate;
    }

    public String getUtilaj() {
        return utilaj;
    }

    public String getGravitate() {
        return gravitate;
    }
}
