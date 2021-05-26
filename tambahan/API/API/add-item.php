<?php

require('db-controller.php');
header('Content-Type: application/json');

if(!empty($_POST)){
    $nama = $_POST['nama'];
    $jumlah = $_POST['jumlah'];
    $id_user = $_POST['id_user'];

    $query = $conn->prepare("INSERT INTO items(id_user,nama, jumlah) VALUES (?,?,?)");
    $query->bind_param("isi", $id_user,$nama,$jumlah);
    $result = $query->execute();

    if($result){
        $response['Message'] = "Data Created";
    }else{
        $response['Message'] = "Failed to save!";
    }
}else{
    $response['Message'] = "No POST Data!";
}

$query->close();
$conn->close();

echo json_encode($response);

?>