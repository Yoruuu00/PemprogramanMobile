<?php
require_once("Model.php");
$peminjaman = getAllPeminjaman();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Data Peminjaman</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'Poppins', sans-serif; background: #f5f5f5; margin: 0; padding: 20px; }
        h1 { text-align: center; color: #333; }
        .top-link, .add-btn {
            display: block; width: max-content; margin: 10px auto;
            padding: 10px 20px; border-radius: 8px;
            text-decoration: none; font-weight: 600;
            color: white; background-color: #4caf50;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
        .top-link:hover, .add-btn:hover { background-color: #43a047; }
        table {
            width: 95%; margin: 20px auto;
            border-collapse: collapse; background-color: white;
            border-radius: 10px; overflow: hidden;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        th, td { padding: 12px 16px; border-bottom: 1px solid #ddd; }
        th { background-color: #4caf50; color: white; }
        tr:hover { background-color: #f1f1f1; }
        .action-links a {
            text-decoration: none; padding: 6px 12px; margin-right: 5px;
            border-radius: 6px; font-size: 14px; color: white;
        }
        .edit-btn { background-color: #ff9800; }
        .delete-btn { background-color: #f44336; }
    </style>
</head>
<body>
    <h1>Data Peminjaman</h1>
    <a href="Utama.php" class="top-link">← Kembali ke Halaman Awal</a>
    <a href="FormPeminjaman.php" class="add-btn">+ Tambah Peminjaman</a>

    <table>
        <thead>
            <tr>
                <th>No</th>
                <th>Nama Member</th>
                <th>Judul Buku</th>
                <th>Tanggal Pinjam</th>
                <th>Tanggal Kembali</th>
                <th>Aksi</th>
            </tr>
        </thead>
        <tbody>
            <?php $no = 1; if (!empty($peminjaman)) : foreach ($peminjaman as $p) : ?>
                <tr>
                    <td><?= $no++; ?></td>
                    <td><?= $p['member']; ?></td>
                    <td><?= $p['buku']; ?></td>
                    <td><?= $p['tanggal_pinjam']; ?></td>
                    <td><?= $p['tanggal_kembali']; ?></td>
                    <td class="action-links">
                        <a href="FormPeminjaman.php?id=<?= $p['id']; ?>" class="edit-btn">Edit</a>
                        <a href="HapusPeminjaman.php?id=<?= $p['id']; ?>" class="delete-btn" onclick="return confirm('Yakin ingin menghapus data ini?');">Hapus</a>
                    </td>
                </tr>
            <?php endforeach; else : ?>
                <tr><td colspan="6" style="text-align:center;">Tidak ada data.</td></tr>
            <?php endif; ?>
        </tbody>
    </table>
</body>
</html>
