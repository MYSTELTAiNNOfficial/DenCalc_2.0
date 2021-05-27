<?php

require_once('db-controller.php');
header('Content-Type: application/json');

if (!empty($_POST)) {
    $id = $_POST['id'];
    $nama = $_POST['nama'];
    $jumlah = $_POST['jumlah'];
    $created_date = $time;
    $query = $conn->prepare("SELECT * FROM items WHERE id = ?");
    $query->bind_param('i', $id);
    $query->execute();
    $result = $query->get_result();

    if ($result->num_rows > 0) {

        $query = $conn->prepare("UPDATE items SET nama=?,jumlah=? WHERE id=?");
        $query->bind_param("sii", $nama, $jumlah, $id);
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