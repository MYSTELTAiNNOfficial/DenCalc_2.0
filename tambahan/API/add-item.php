<?php
require('db-controller.php');
header('Content-Type: application/json');

if (!empty($_POST)) {
    $nama_barang = $_POST['nama_barang'];
    $jumlah = $_POST['jumlah'];
    $id_user = 1;

    $query = $conn->prepare("INSERT INTO items(nama_barang, jumlah) VALUES (?,?)");
    $query->bind_param("si", $nama_barang, $jumlah);
    $result = $query->execute();

    if ($result) {
        $response['Message'] = "Data Created";
    } else {
        $response['Message'] = "Failed to save!";
    }
} else {
    $response['Message'] = "No POST Data!";
}

$query->close();
$conn->close();

echo json_encode($response);
?>