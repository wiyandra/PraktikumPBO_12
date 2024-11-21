package StudiKasus;

import java.io.Serializable;


public class Produk implements Serializable {
    private String nama;
    private double harga;
    private int stok;

    public Produk(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }
    
    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Harga: " + harga + ", Stok: " + stok;
    }
}
