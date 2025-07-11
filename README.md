# Pet Management

Aplikasi desktop sederhana untuk mengelola data hewan peliharaan (Pet) menggunakan JavaFX.
Fitur CRUD (Create, Read, Update, Delete) untuk menambah, mengubah, dan menghapus data pet.

## Fitur

* Menambahkan data pet baru
* Mengedit data pet yang sudah ada
* Menghapus data pet
* Melihat daftar semua pet
* Data yang disimpan meliputi:

  * Nama
  * Spesies
  * Umur (bulan)
  * Status (Sehat, Sakit, Diadopsi)

## Cara Menjalankan

1. Pastikan sudah terpasang **Java JDK 8** atau lebih baru.
2. Pastikan sudah terpasang **JavaFX** di komputer.
3. Clone atau download repository ini.
4. Jalankan file utama:

   ```
   Main.java
   ```

   atau gunakan perintah di terminal:

   ```
   java Main
   ```

   (pastikan sudah di direktori yang tepat dan JavaFX ada di classpath)

## Struktur Kode

* `Main.java`
  Tampilan utama aplikasi, berisi UI tabel dan form input.

* `Pet.java`
  Model untuk menyimpan data pet.

* `PetControllers.java`
  Logic untuk menyimpan, mengubah, dan menghapus data pet.

## Dependencies

* JavaFX SDK

## Catatan

Aplikasi ini menggunakan data sementara di memori (belum menggunakan database).
Jika aplikasi ditutup, data akan hilang.
