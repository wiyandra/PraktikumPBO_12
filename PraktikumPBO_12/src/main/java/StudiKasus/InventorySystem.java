package StudiKasus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventorySystem {
    private static final String TEXT_FILE = "produk.txt";
    private static final String SERIAL_FILE = "produk.ser";
    private static List<Produk> produkList = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Simpan ke File Teks");
            System.out.println("3. Simpan Objek ke File (Serialisasi)");
            System.out.println("4. Tampilkan Semua Produk");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");
            
            // Validasi input menu
            if (!scanner.hasNextInt()) {
                System.out.println("Pilihan harus berupa angka. Coba lagi.");
                scanner.next(); // Buang input salah
                continue;
            }
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Bersihkan newline setelah input numerik
            
            switch (pilihan) {
                case 1 -> tambahProduk(scanner);
                case 2 -> simpanKeFileTeks();
                case 3 -> simpanKeFileSerial();
                case 4 -> tampilkanProduk();
                case 5 -> {
                    System.out.println("Keluar dari sistem.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
    
    private static void tambahProduk(Scanner scanner) {
        System.out.print("Masukkan Nama Produk: ");
        String nama = scanner.nextLine();
        
        System.out.print("Masukkan Harga: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Harga harus berupa angka. Coba lagi.");
            scanner.next(); // Buang input salah
        }
        double harga = scanner.nextDouble();
        
        System.out.print("Masukkan Stok: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Stok harus berupa angka. Coba lagi.");
            scanner.next(); // Buang input salah
        }
        int stok = scanner.nextInt();
        scanner.nextLine(); // Bersihkan newline
        
        produkList.add(new Produk(nama, harga, stok));
        System.out.println("Produk berhasil ditambahkan.");
    }
    
    private static void simpanKeFileTeks() {
        try (FileWriter writer = new FileWriter(TEXT_FILE, true)) {
            for (Produk produk : produkList) {
                writer.write(produk.toString() + "\n");
            }
            System.out.println("Data produk berhasil disimpan ke file teks.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan pada saat menyimpan ke file teks.");
            e.printStackTrace();
        }
    }
    
    private static void simpanKeFileSerial() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIAL_FILE))) {
            oos.writeObject(produkList);
            System.out.println("Objek produk berhasil disimpan ke file serial.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan pada saat menyimpan ke file serial.");
            e.printStackTrace();
        }
    }
    
    private static void tampilkanProduk() {
        if (TEXT_FILE.isEmpty()) {
            System.out.println("Tidak ada produk dalam daftar.");
            return;
        }
        
        try (FileReader reader = new FileReader(TEXT_FILE)) {
            int character;
            System.out.println("\nDaftar Produk  : ");
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan pada saat membaca file.");
            e.printStackTrace();
        }
    }
}
