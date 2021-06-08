<?php
require('db-controller.php');
header('Content-Type: application/json');

if (!empty($_POST)) {
    $tipe_barang = $_POST['tipe_barang'];
    $jumlah = $_POST['jumlah'];
    $id_user = $_POST['id_user'];
    $watt_barang = $_POST['watt_barang'];
    $total_pemakaian = $_POST['total_pemakaian'];

    $query = $conn->prepare("INSERT INTO items(id, id_user ,tipe_barang,watt_barang,total_pemakaian, jumlah) VALUES (null,?,?,?,?,?)");
    $query->bind_param("isiii", $id_user,$tipe_barang,$watt_barang,$total_pemakaian, $jumlah);
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