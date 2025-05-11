<?php
require_once("Model.php");

if (isset($_GET['id'])) {
    deleteBuku($_GET['id']);
}

header("Location: Buku.php");
exit;
