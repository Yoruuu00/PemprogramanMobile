<?php
require_once("Model.php");

$edit = false;
$member = ['id' => '', 'nama' => '', 'alamat' => '', 'telepon' => '', 'tanggal_mendaftar' => '', 'tanggal_terakhir_bayar' => ''];

if (isset($_GET['id'])) {
    $edit = true;
    $member = getMemberById($_GET['id']);
}

if (isset($_POST['submit'])) {
    $nama = $_POST['nama'];
    $alamat = $_POST['alamat'];
    $telepon = $_POST['telepon'];
    $tanggal_mendaftar = $_POST['tanggal_mendaftar'];
    $tanggal_terakhir_bayar = $_POST['tanggal_terakhir_bayar'];

    if ($edit) {
        updateMember($member['id'], $nama, $alamat, $telepon, $tanggal_mendaftar, $tanggal_terakhir_bayar);
    } else {
        insertMember($nama, $alamat, $telepon, $tanggal_mendaftar, $tanggal_terakhir_bayar);
    }

    header("Location: Member.php");
    exit;
}
?>

<!DOCTYPE html>
<html>
<head>
    <title><?= $edit ? "Edit" : "Tambah"; ?> Member</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f5f5f5;
            padding: 40px;
        }

        .form-container {
            background: white;
            max-width: 500px;
            margin: auto;
            padding: 30px;
            border-radius: 16px;
            box-shadow: 0 6px 16px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: 600;
        }

        input[type="text"], input[type="date"], textarea {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-family: 'Poppins', sans-serif;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            border: none;
            padding: 12px 20px;
            font-weight: 600;
            border-radius: 8px;
            margin-top: 20px;
            width: 100%;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #388e3c;
        }

        .back-link {
            display: block;
            margin-top: 20px;
            text-align: center;
            text-decoration: none;
            color: #4caf50;
            font-weight: 600;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1><?= $edit ? "Edit" : "Tambah"; ?> Member</h1>
        <form method="post">
            <label>Nama:</label>
            <input type="text" name="nama" value="<?= $member['nama']; ?>" required>

            <label>Alamat:</label>
            <textarea name="alamat" required><?= $member['alamat']; ?></textarea>

            <label>Telepon:</label>
            <input type="text" name="telepon" value="<?= $member['telepon']; ?>" required>

            <label>Tanggal Mendaftar:</label>
            <input type="date" name="tanggal_mendaftar" value="<?= $member['tanggal_mendaftar'] ?? ''; ?>" required>

            <label>Tanggal Terakhir Bayar:</label>
            <input type="date" name="tanggal_terakhir_bayar" value="<?= $member['tanggal_terakhir_bayar'] ?? ''; ?>" required>

            <input type="submit" name="submit" value="<?= $edit ? "Update" : "Simpan"; ?>">
        </form>
        <a href="Member.php" class="back-link">← Kembali ke Data Member</a>
    </div>
</body>
</html>
