<?php

require_once('db-controller.php');
header('Content-Type: application/json');

if (!empty($_POST)) {
    $id = $_POST['id'];
    $tipe_barang = $_POST['tipe_barang'];
    $jumlah = $_POST['jumlah'];
    $watt_barang = $_POST['watt_barang'];
    $total_pemakaian = $_POST['total_pemakaian'];
    $query = $conn->prepare("SELECT * FROM items WHERE id = ?");
    $query->bind_param('i', $id);
    $query->execute();
    $result = $query->get_result();

    if ($result->num_rows > 0) {

        $query = $conn->prepare("UPDATE items SET tipe_barang=?,watt_barang=?,total_pemakaian=?, jumlah=? WHERE id=?");
        $query->bind_param("siiii", $tipe_barang,$watt_barang,$total_pemakaian, $jumlah, $id);
        $result = $query->execute();

        if ($result) {
            $response['Message'] = "Data Updated!";
        } else {
            $response['Message'] = "Failed to update!";
        }
    } else {
        $response['message'] = "Data not Found";
    }
} else {
    $response['Message'] = "No POST Data!";
}

$query->close();
$conn->close();

echo json_encode($response);
?>