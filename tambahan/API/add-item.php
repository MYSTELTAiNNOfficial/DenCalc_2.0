<?php
require('db-controller.php');
header('Content-Type: application/json');

if (!empty($_POST)) {
    $tipe_barang = $_POST['tipe_barang'];
    $watt_barang = $_POST['watt_barang'];
    $total_pemakaian = $_POST['total pemakaian'];
    $jumlah = $_POST['jumlah'];
    $id_user = 1;

    $query = $conn->prepare("INSERT INTO items(tipe_barang, watt_barang, total_pemakaian, jumlah) VALUES (?,?,?,?)");
    $query->bind_param("siii", $tipe_barang, $watt_barang, $total_pemakaian, $jumlah);
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