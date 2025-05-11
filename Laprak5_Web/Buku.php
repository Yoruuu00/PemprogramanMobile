<?php
require_once("Model.php");
$daftarBuku = getAllBuku();
?>

<!DOCTYPE html>
<html>
<head>
    <title>Data Buku</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'Poppins', sans-serif; background-color: #f5f5f5; padding: 40px; }
        .container { max-width: 900px; margin: auto; background: white; padding: 30px; border-radius: 16px; box-shadow: 0 6px 16px rgba(0,0,0,0.1); }
        h1 { text-align: center; color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #4caf50; color: white; }
        a { text-decoration: none; color: #4caf50; font-weight: 600; }
        .tambah-btn { display: inline-block; margin-bottom: 15px; padding: 10px 16px; background-color: #4caf50; color: white; border-radius: 8px; }
        .aksi a { margin-right: 10px; }

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

    </style>
</head>
<body>
    <div class="container">
        <h1>Data Buku</h1>
        <a href="Utama.php" class="top-link">← Kembali ke Halaman Awal</a>
        <a href="FormBuku.php" class="add-btn">+ Tambah Buku</a>
        <table>
    <thead>
        <tr>
            <th>No</th> 
            <th>Judul</th>
            <th>Pengarang</th>
            <th>Penerbit</th>
            <th>Tahun Terbit</th>
            <th>Aksi</th>
        </tr>
    </thead>
    <tbody>
        <?php $no = 1; foreach ($daftarBuku as $buku): ?>
            <tr>
                <td><?= $no++; ?></td> 
                <td><?= $buku['judul']; ?></td>
                <td><?= $buku['pengarang']; ?></td>
                <td><?= $buku['penerbit']; ?></td>
                <td><?= $buku['tahun_terbit']; ?></td>
                <td class="aksi">
                    <a href="FormBuku.php?id=<?= $buku['id']; ?>">Edit</a>
                    <a href="HapusBuku.php?id=<?= $buku['id']; ?>" onclick="return confirm('Yakin ingin menghapus buku ini?')">Hapus</a>
                </td>
            </tr>
        <?php endforeach; ?>
                <?php if (count($daftarBuku) === 0): ?>
                    <tr>
                        <td colspan="6" style="text-align:center;">Tidak ada data buku</td>
                    </tr>
                <?php endif; ?>
            </tbody>
        </table>
    </div>
</body>
</html>
