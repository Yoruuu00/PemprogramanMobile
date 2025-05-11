<?php
require_once("Model.php");
$members = getAllMember();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Data Member</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: #f5f5f5;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .top-link, .add-btn {
            display: block;
            width: max-content;
            margin: 10px auto;
            padding: 10px 20px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: 600;
            color: white;
            background-color: #4caf50;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
        .top-link:hover, .add-btn:hover {
            background-color: #43a047;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px 16px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4caf50;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .action-links a {
            text-decoration: none;
            padding: 6px 12px;
            margin-right: 5px;
            border-radius: 6px;
            font-size: 14px;
            color: white;
        }
        .edit-btn {
            background-color: #ff9800;
        }
        .delete-btn {
            background-color: #f44336;
        }
        .action-links {
            text-align: right;
        }
    </style>
</head>
<body>
    <h1>Data Member</h1>
    <a href="Utama.php" class="top-link">← Kembali ke Halaman Awal</a>
    <a href="FormMember.php" class="add-btn">+ Tambah Member</a>

    <table>
        <thead>
            <tr>
                <th>No</th>
                <th>Nama</th>
                <th>Alamat</th>
                <th>Telepon</th>
                <th>Tgl Mendaftar</th>
                <th>Tgl Terakhir Bayar</th>
                <th>Aksi</th> <!-- Aksi column moved to the end -->
            </tr>
        </thead>
        <tbody>
            <?php $no = 1; if (!empty($members)) : foreach ($members as $m) : ?>
                <tr>
                    <td><?= $no++; ?></td>
                    <td><?= $m['nama']; ?></td>
                    <td><?= $m['alamat']; ?></td>
                    <td><?= $m['telepon']; ?></td>
                    <td><?= $m['tanggal_mendaftar']; ?></td>
                    <td><?= $m['tanggal_terakhir_bayar']; ?></td>
                    <td class="action-links">
                        <a href="FormMember.php?id=<?= $m['id']; ?>" class="edit-btn">Edit</a>
                        <a href="HapusMember.php?id=<?= $m['id']; ?>" class="delete-btn" onclick="return confirm('Yakin ingin menghapus data ini?');">Hapus</a>
                    </td>
                </tr>
            <?php endforeach; else : ?>
                <tr><td colspan="7" style="text-align:center;">Tidak ada data.</td></tr>
            <?php endif; ?>
        </tbody>
    </table>
</body>
</html>
