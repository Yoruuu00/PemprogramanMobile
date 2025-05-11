<?php
require_once("Model.php");

$edit = false;
$data = ['id' => '', 'member_id' => '', 'buku_id' => '', 'tanggal_pinjam' => '', 'tanggal_kembali' => ''];

if (isset($_GET['id'])) {
    $edit = true;
    $data = getPeminjamanById($_GET['id']);
}

$members = getAllMember();
$buku = getAllBuku();

if (isset($_POST['submit'])) {
    $member_id = $_POST['member_id'];
    $buku_id = $_POST['buku_id'];
    $tgl_pinjam = $_POST['tanggal_pinjam'];
    $tgl_kembali = $_POST['tanggal_kembali'];

    if ($edit) {
        updatePeminjaman($data['id'], $member_id, $buku_id, $tgl_pinjam, $tgl_kembali);
    } else {
        insertPeminjaman($member_id, $buku_id, $tgl_pinjam, $tgl_kembali);
    }

    header("Location: Peminjaman.php");
    exit;
}
?>

<!DOCTYPE html>
<html>
<head>
    <title><?= $edit ? "Edit" : "Tambah"; ?> Peminjaman</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'Poppins', sans-serif; background-color: #f5f5f5; padding: 40px; }
        .form-container { background: white; max-width: 550px; margin: auto; padding: 30px; border-radius: 16px; box-shadow: 0 6px 16px rgba(0,0,0,0.1); }
        h1 { text-align: center; color: #333; }
        label { display: block; margin-top: 15px; font-weight: 600; }
        select, input[type="date"] { width: 100%; padding: 10px; margin-top: 6px; border: 1px solid #ccc; border-radius: 8px; }
        input[type="submit"] { background-color: #4caf50; color: white; border: none; padding: 12px 20px; font-weight: 600; border-radius: 8px; margin-top: 20px; width: 100%; cursor: pointer; }
        input[type="submit"]:hover { background-color: #388e3c; }
        .back-link { display: block; margin-top: 20px; text-align: center; text-decoration: none; color: #4caf50; font-weight: 600; }
    </style>
</head>
<body>
    <div class="form-container">
        <h1><?= $edit ? "Edit" : "Tambah"; ?> Peminjaman</h1>
        <form method="post">
            <label>Nama Member:</label>
            <select name="member_id" required>
                <option value="">-- Pilih Member --</option>
                <?php foreach ($members as $m) : ?>
                    <option value="<?= $m['id']; ?>" <?= $data['member_id'] == $m['id'] ? 'selected' : ''; ?>>
                        <?= $m['nama']; ?>
                    </option>
                <?php endforeach; ?>
            </select>

            <label>Judul Buku:</label>
            <select name="buku_id" required>
                <option value="">-- Pilih Buku --</option>
                <?php foreach ($buku as $b) : ?>
                    <option value="<?= $b['id']; ?>" <?= $data['buku_id'] == $b['id'] ? 'selected' : ''; ?>>
                        <?= $b['judul']; ?>
                    </option>
                <?php endforeach; ?>
            </select>

            <label>Tanggal Pinjam:</label>
            <input type="date" name="tanggal_pinjam" value="<?= $data['tanggal_pinjam']; ?>" required>

            <label>Tanggal Kembali:</label>
            <input type="date" name="tanggal_kembali" value="<?= $data['tanggal_kembali']; ?>" required>

            <input type="submit" name="submit" value="<?= $edit ? "Update" : "Simpan"; ?>">
        </form>
        <a href="Peminjaman.php" class="back-link">← Kembali ke Data Peminjaman</a>
    </div>
</body>
</html>
