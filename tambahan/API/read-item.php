<?php

require_once('db-controller.php');
header('Content-Type: application/json');

$query = $conn->prepare('SELECT * FROM items WHERE id_user = ?');
$query->bind_param('i', $id);
$query->execute();
$result = $query->get_result();
$response["count"] = $query->num_rows;
$response["items"] = array();

while ($data = mysqli_fetch_assoc($result)) {
    $obj = array(
        'id' => $data['id'],
        'tipe_barang' => $data['tipe_barang'],
        'watt_barang' => $data['watt_barang'],
        'total_pemakaian' => $data['total_pemakaian'],
        'jumlah' => $data['jumlah']
    );
    array_push($response["items"],$obj);
}

$query->close();
$conn->close();

echo json_encode($response);

?>