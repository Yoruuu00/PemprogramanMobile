<?php
require_once("Model.php");

if (isset($_GET['id'])) {
    deletePeminjaman($_GET['id']);
}

header("Location: Peminjaman.php");
exit;
