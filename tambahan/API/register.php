<?php
require('db-controller.php');
header('Content-Type: application/json');

if (!empty($_POST)) {
    $username = $_POST['username'];
    $password = $_POST['password'];
    $query = $conn->prepare("SELECT id FROM user WHERE username = ?");
    $query->bind_param("s", $username);
    $query->execute();
    $query->store_result();

    if ($query->num_rows > 0) {
        $response['err'] = true;
        $response['message'] = 'User already registered';
        $query->close();
    } else {
        $query = $conn->prepare("INSERT INTO user(nama, password) VALUES (?,?)");
        $query->bind_param("ss", $username, $password);
        $query->execute();
        $query->store_result();
        $query->bind_result($id, $username, $password);
        $query->fetch();
        $user = array(
            'id' => $id,
            'username' => $username
        );
        $response['err'] = false;
        $response["user"] = $user;
        $response['Message'] = "Login Successful!";
    }
} else {
    $response['Message'] = "No POST Data!";
}

$query->close();
$conn->close();

echo json_encode($response);
