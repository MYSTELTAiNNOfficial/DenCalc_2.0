<?php

require_once('db-controller.php');
header('Content-Type: application/json');

if(!empty($_POST)){
    $id = $_POST["id"];
}else{
    $id = -1;
}

$query = $conn->prepare('SELECT * FROM items WHERE id = ?');
$query->bind_param('i', $id);
$query->execute();

$result = $query->get_result();
$data = $result->fetch_assoc();

if(!empty($data)){
    $obj = array(
        'tipe_barang' => $data['tipe_barang'],
        'watt_barang' => $data['watt_barang'],
        'total_pemakaian' => $data['total_pemakaian'],
        'jumlah' => $data['jumlah']
    );
    $response["items"] = $obj;
}else{
    $response['Message'] = "Data not found!";
}

$query->close();
$conn->close();

echo json_encode($response);


?>