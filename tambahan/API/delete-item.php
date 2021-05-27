<?php

require_once('db-controller.php');
header('Content-Type: application/json');

if (!empty($_POST)) {
    $id = $_POST['id'];

    $query = $conn->prepare("SELECT * FROM items WHERE id = ?");
    $query->bind_param('i', $id);
    $query->execute();
    $result = $query->get_result();

    if ($result->num_rows > 0) {
        $query = $conn->prepare("DELETE FROM items WHERE id=?");
        $query->bind_param('i', $id);
        $result = $query->execute();

        if ($result) {
            $response['Message'] = "Data Deleted";
        } else {
            $response['Message'] = "Failed to delete";
        }
    } else {
        $response['Message'] = "Data not found";
    }
} else {
    $response['Message'] = "No Post Data";
}

$query->close();
$conn->close();

echo json_encode($response);
?>