<?php
require_once("Model.php");

$edit = false;
$buku = ['id' => '', 'judul' => '', 'pengarang' => '', 'penerbit' => '', 'tahun_terbit' => ''];

if (isset($_GET['id'])) {
    $edit = true;
    $buku = getBukuById($_GET['id']);
}

if (isset($_POST['submit'])) {
    $judul = $_POST['judul'];
    $pengarang = $_POST['pengarang'];
    $penerbit = $_POST['penerbit'];
    $tahun_terbit = $_POST['tahun_terbit'];

    if ($edit) {
        updateBuku($buku['id'], $judul, $pengarang, $penerbit, $tahun_terbit);
    } else {
        insertBuku($judul, $pengarang, $penerbit, $tahun_terbit);
    }

    header("Location: Buku.php");
    exit;
}
?>

<!DOCTYPE html>
<html>
<head>
    <title><?= $edit ? "Edit" : "Tambah"; ?> Buku</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'Poppins', sans-serif; background-color: #f5f5f5; padding: 40px; }
        .form-container { background: white; max-width: 500px; margin: auto; padding: 30px; border-radius: 16px; box-shadow: 0 6px 16px rgba(0,0,0,0.1); }
        h1 { text-align: center; color: #333; }
        label { display: block; margin-top: 15px; font-weight: 600; }
        input[type="text"], input[type="number"] { width: 100%; padding: 10px; margin-top: 6px; border: 1px solid #ccc; border-radius: 8px; }
        input[type="submit"] { background-color: #4caf50; color: white; border: none; padding: 12px 20px; font-weight: 600; border-radius: 8px; margin-top: 20px; width: 100%; cursor: pointer; }
        input[type="submit"]:hover { background-color: #388e3c; }
        .back-link { display: block; margin-top: 20px; text-align: center; text-decoration: none; color: #4caf50; font-weight: 600; }
    </style>
</head>
<body>
    <div class="form-container">
        <h1><?= $edit ? "Edit" : "Tambah"; ?> Buku</h1>
        <form method="post">
            <label>Judul:</label>
            <input type="text" name="judul" value="<?= $buku['judul']; ?>" required>

            <label>Pengarang:</label>
            <input type="text" name="pengarang" value="<?= $buku['pengarang']; ?>" required>

            <label>Penerbit:</label>
            <input type="text" name="penerbit" value="<?= $buku['penerbit']; ?>" required>

            <label>Tahun Terbit:</label>
            <input type="number" name="tahun_terbit" value="<?= $buku['tahun_terbit']; ?>" required>

            <input type="submit" name="submit" value="<?= $edit ? "Update" : "Simpan"; ?>">
        </form>
        <a href="Buku.php" class="back-link">← Kembali ke Data Buku</a>
    </div>
</body>
</html>
