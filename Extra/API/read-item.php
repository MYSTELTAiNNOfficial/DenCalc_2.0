<?php

require_once('db-controller.php');
header('Content-Type: application/json');

if(!empty($_POST)){
    $id = $_POST["id"];
}else{
    $id = 0;
}

$query = $conn->prepare('SELECT * FROM items WHERE id_user = ?');
$query->bind_param('i', $id);
$query->execute();
$result = $query->get_result();
$response["items"] = array();

while ($data = $result->fetch_assoc()) {
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