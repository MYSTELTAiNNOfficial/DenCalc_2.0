<?php

require_once('db-controller.php');
header('Content-Type: application/json');

$query = $conn->query('SELECT * FROM items');
$response["count"] = $query->num_rows;
$response["items"] = array();

while ($data = mysqli_fetch_assoc($query)) {
    $obj = array(
        'id' => $data['id'],
        'nama' => $data['nama'],
        'jumlah' => $data['jumlah']
    );
    array_push($response["items"],$obj);
}

$query->close();
$conn->close();

echo json_encode($response);

?>