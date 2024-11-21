package com.praktikumpbo_12;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOPractice {
    public static void main(String[] args) {
        String filePath = "data.txt";
        
        //Menulis data ke file
        try (FileWriter writer = new FileWriter (filePath)) {
            writer.write("Belajar File I/O di Java! \n");
            writer.write("Pemrograman Berorientasi Objek. \n");
            System.out.println("Data berhasil ditulis ke file : " + filePath);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan pada saat menulis file.");
            e.printStackTrace();
        }
        
        //Membaca data dari file
        try (FileReader reader = new FileReader(filePath)) {
            int character;
            System.out.println("\nIsi File : ");
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan pada saat membaca file.");
            e.printStackTrace();
        }
    }
}