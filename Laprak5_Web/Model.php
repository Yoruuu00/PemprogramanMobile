<?php
require_once("Koneksi.php");

function getAllMember() {
    $conn = koneksiDatabase();
    $result = mysqli_query($conn, "SELECT * FROM member");
    return mysqli_fetch_all($result, MYSQLI_ASSOC);
}

function getMemberById($id) {
    $conn = koneksiDatabase();
    $id = mysqli_real_escape_string($conn, $id);
    $result = mysqli_query($conn, "SELECT * FROM member WHERE id = '$id'");
    return mysqli_fetch_assoc($result);
}

function insertMember($nama, $alamat, $telepon, $tanggal_mendaftar, $tanggal_terakhir_bayar) {
    $conn = koneksiDatabase();
    $nama = mysqli_real_escape_string($conn, $nama);
    $alamat = mysqli_real_escape_string($conn, $alamat);
    $telepon = mysqli_real_escape_string($conn, $telepon);
    $tanggal_mendaftar = mysqli_real_escape_string($conn, $tanggal_mendaftar);
    $tanggal_terakhir_bayar = mysqli_real_escape_string($conn, $tanggal_terakhir_bayar);

    $query = "INSERT INTO member (nama, alamat, telepon, tanggal_mendaftar, tanggal_terakhir_bayar) 
              VALUES ('$nama', '$alamat', '$telepon', '$tanggal_mendaftar', '$tanggal_terakhir_bayar')";
    return mysqli_query($conn, $query);
}

function updateMember($id, $nama, $alamat, $telepon, $tanggal_mendaftar, $tanggal_terakhir_bayar) {
    $conn = koneksiDatabase();
    $id = mysqli_real_escape_string($conn, $id);
    $nama = mysqli_real_escape_string($conn, $nama);
    $alamat = mysqli_real_escape_string($conn, $alamat);
    $telepon = mysqli_real_escape_string($conn, $telepon);
    $tanggal_mendaftar = mysqli_real_escape_string($conn, $tanggal_mendaftar);
    $tanggal_terakhir_bayar = mysqli_real_escape_string($conn, $tanggal_terakhir_bayar);

    $query = "UPDATE member SET 
                nama='$nama', 
                alamat='$alamat', 
                telepon='$telepon', 
                tanggal_mendaftar='$tanggal_mendaftar', 
                tanggal_terakhir_bayar='$tanggal_terakhir_bayar' 
              WHERE id='$id'";
    return mysqli_query($conn, $query);
}

function deleteMember($id) {
    $conn = koneksiDatabase();
    $id = mysqli_real_escape_string($conn, $id);
    $query = "DELETE FROM member WHERE id='$id'";
    return mysqli_query($conn, $query);
}


function getAllBuku() {
    $conn = koneksiDatabase();
    $result = $conn->query("SELECT * FROM buku ORDER BY id ASC");
    return $result->fetch_all(MYSQLI_ASSOC);
}

function getBukuById($id) {
    $conn = koneksiDatabase();
    $stmt = $conn->prepare("SELECT * FROM buku WHERE id=?");
    $stmt->bind_param("i", $id);
    $stmt->execute();
    return $stmt->get_result()->fetch_assoc();
}

function insertBuku($judul, $pengarang, $penerbit, $tahun_terbit) {
    $conn = koneksiDatabase();
    $stmt = $conn->prepare("INSERT INTO buku (judul, pengarang, penerbit, tahun_terbit) VALUES (?, ?, ?, ?)");
    $stmt->bind_param("sssi", $judul, $pengarang, $penerbit, $tahun_terbit);
    $stmt->execute();
}

function updateBuku($id, $judul, $pengarang, $penerbit, $tahun_terbit) {
    $conn = koneksiDatabase();
    $stmt = $conn->prepare("UPDATE buku SET judul=?, pengarang=?, penerbit=?, tahun_terbit=? WHERE id=?");
    $stmt->bind_param("sssii", $judul, $pengarang, $penerbit, $tahun_terbit, $id);
    $stmt->execute();
}

function deleteBuku($id) {
    $conn = koneksiDatabase();
    $query = "DELETE FROM buku WHERE id = $id";
    return mysqli_query($conn, $query);
}


function getAllPeminjaman() {
    $conn = koneksiDatabase();
    $result = mysqli_query($conn, 
        "SELECT p.id, m.nama AS member, b.judul AS buku, p.tanggal_pinjam, p.tanggal_kembali
         FROM peminjaman p 
         JOIN member m ON p.member_id = m.id 
         JOIN buku b ON p.buku_id = b.id"
    );
    return mysqli_fetch_all($result, MYSQLI_ASSOC);
}

function getPeminjamanById($id) {
    $conn = koneksiDatabase();
    $result = mysqli_query($conn, "SELECT * FROM peminjaman WHERE id = $id");
    return mysqli_fetch_assoc($result);
}

function insertPeminjaman($member_id, $buku_id, $tanggal_pinjam, $tanggal_kembali) {
    $conn = koneksiDatabase();
    $query = "INSERT INTO peminjaman (member_id, buku_id, tanggal_pinjam, tanggal_kembali) 
              VALUES ('$member_id', '$buku_id', '$tanggal_pinjam', '$tanggal_kembali')";
    return mysqli_query($conn, $query);
}

function updatePeminjaman($id, $member_id, $buku_id, $tanggal_pinjam, $tanggal_kembali) {
    $conn = koneksiDatabase();
    $query = "UPDATE peminjaman 
              SET member_id='$member_id', buku_id='$buku_id', tanggal_pinjam='$tanggal_pinjam', tanggal_kembali='$tanggal_kembali' 
              WHERE id = $id";
    return mysqli_query($conn, $query);
}

function deletePeminjaman($id) {
    $conn = koneksiDatabase();
    $query = "DELETE FROM peminjaman WHERE id = $id";
    return mysqli_query($conn, $query);
}
?>