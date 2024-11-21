package Soal;

import java.io.Serializable;


public class Buku implements Serializable {
    private String nama;
    private String pengarang;
    private int tahunTerbit;

    public Buku(String nama, String pengarang, int tahunTerbit) {
        this.nama = nama;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
    }
    
    public String getNama() {
        return nama;
    }

    public String getPengarang() {
        return pengarang;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }
    
    @Override
    public String toString() {
        return "Judul Buku : " + nama + ", Nama Pengarang : " + pengarang + ", Tahun Terbit " + tahunTerbit;
    }
}
