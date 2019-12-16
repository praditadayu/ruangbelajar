package com.example.kuispilihanganda;

public class DashboardSoal {
    private int id;
    private String kategori;
    private String soal;
    private String pilihan1;
    private String pilihan2;
    private String pilihan3;
    private String pilihan4;
    private String jawaban;

    public DashboardSoal() {
    }

    public DashboardSoal(String kategori, String soal, String pilihan1, String pilihan2,String pilihan3 ,String pilihan4,String jawaban) {
        this.kategori = kategori;
        this.soal = soal;
        this.pilihan1 = pilihan1;
        this.pilihan2 = pilihan2;
        this.pilihan3 = pilihan3;
        this.pilihan4 = pilihan4;
        this.jawaban = jawaban;
    }
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getPilihan1() {
        return pilihan1;
    }

    public void setPilihan1(String pilihan1) {
        this.pilihan1 = pilihan1;
    }

    public String getPilihan2() {
        return pilihan2;
    }

    public void setPilihan2(String pilihan2) {
        this.pilihan2 = pilihan2;
    }

    public String getPilihan3() {
        return pilihan3;
    }

    public void setPilihan3(String pilihan3) {
        this.pilihan3 = pilihan3;
    }
    public String getPilihan4() {
        return pilihan4;
    }

    public void setPilihan4(String pilihan4) {
        this.pilihan4 = pilihan4;
    }
    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
}
