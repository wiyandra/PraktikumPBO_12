package com.praktikumpbo_12;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationPractice {
    public static void main(String[] args) {
        String filePath = "produk.ser";
        
        //Membuat objek Produk
        Produk produk = new Produk("Laptop", 15000000, 10);
        
        //Menyimpan objek ke file (Serialisasi)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(produk);
            System.out.println("Objek Produk berhasil disimpan ke file : " + filePath);
        } catch (IOException e) {
        System.out.println("Terjadi kesalahan pada saat menyimpan objek.");
        e.printStackTrace();
        }
        
        //Membaca objek dari file (Deserialisasi)
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Produk deserializedProduk = (Produk) ois.readObject();
            System.out.println("\n Objek Produk berhasil dibaca dari file : ");
            deserializedProduk.tampilkanInfo();
        } catch (IOException | ClassNotFoundException e) {
        System.out.println("Terjadi kesalahan pada saat membaca objek.");
        e.printStackTrace();
        }
    }
}