package Soal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Soal.Buku;

public class SistemManajemenBuku {
    private static final String TEXT_FILE = "buku.txt";
    private static final String SERIAL_FILE = "buku.ser";
    private static List<Buku> bukuList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Simpan ke File Teks");
            System.out.println("3. Simpan Objek ke File (Serialisasi)");
            System.out.println("4. Tampilkan Semua Buku File TXT");
            System.out.println("5. Tampilkan Semua Buku File SER");
            System.out.println("6. Keluar");
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
                case 1 -> tambahBuku(scanner);
                case 2 -> simpanKeFileTeks();
                case 3 -> simpanKeFileSerial();
                case 4 -> tampilkanBukuTXT();
                case 5 -> tampilkanBukuSER();
                case 6 -> {
                    System.out.println("Keluar dari sistem.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void tambahBuku(Scanner scanner) {
        System.out.print("Masukkan Nama Buku: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Nama Pengarang: ");
        String pengarang = scanner.nextLine();

        System.out.print("Masukkan Tahun Terbit: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Tahun terbit harus berupa angka. Coba lagi.");
            scanner.next(); // Buang input salah
        }
        int tahunTerbit = scanner.nextInt();
        scanner.nextLine(); // Bersihkan newline

        bukuList.add(new Buku(nama, pengarang, tahunTerbit));
        System.out.println("Buku berhasil ditambahkan.");
    }

    private static void simpanKeFileTeks() {
        try (FileWriter writer = new FileWriter(TEXT_FILE, true)) { // Mode append untuk menambahkan data
            for (Buku buku : bukuList) {
                writer.write(buku.getNama() + "," + buku.getPengarang() + "," + buku.getTahunTerbit() + "\n");
            }
            bukuList.clear(); // Kosongkan daftar setelah menyimpan
            System.out.println("Data buku berhasil disimpan ke file teks.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan pada saat menyimpan ke file teks.");
            e.printStackTrace();
        }
    }

    private static void simpanKeFileSerial() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIAL_FILE))) {
            oos.writeObject(bukuList);
            System.out.println("Objek buku berhasil disimpan ke file serial.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan pada saat menyimpan ke file serial.");
            e.printStackTrace();
        }
    }

    private static void tampilkanBukuTXT() {
        File file = new File(TEXT_FILE);
        if (!file.exists() || file.length() == 0) {
            System.out.println("Tidak ada buku dalam daftar.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(TEXT_FILE))) {
            String line;
            System.out.println("\nDaftar Buku:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan pada saat membaca file.");
            e.printStackTrace();
        }
    }
    
    private static void tampilkanBukuSER() {
        File file = new File(SERIAL_FILE);
        if (!file.exists() || file.length() == 0) {
            System.out.println("File serial belum ada atau kosong.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIAL_FILE))) {
            List<Buku> bukuDariFile = (List<Buku>) ois.readObject(); // Membaca bukuList dari file serial
            if (bukuDariFile.isEmpty()) {
                System.out.println("Tidak ada buku yang tersimpan di file serial.");
            } else {
                System.out.println("\nDaftar Buku dari File Serial:");
                for (Buku buku : bukuDariFile) {
                    System.out.println(buku);
                }
            }
            } catch (IOException e) {
                System.out.println("Terjadi kesalahan saat membaca file serial.");
        e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class Buku tidak ditemukan saat membaca file serial.");
            e.printStackTrace();
        }
    }
}
