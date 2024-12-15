package com.example.myapplication.model;

public class Kosakata {

    private int id;
    private String kata;
    private String terjemahan;
    private String carabaca;
    private String keterangan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getTerjemahan() {
        return terjemahan;
    }

    public void setTerjemahan(String terjemahan) {
        this.terjemahan = terjemahan;
    }

    public String getCarabaca() {
        return carabaca;
    }

    public void setCarabaca(String carabaca) {
        this.carabaca = carabaca;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
