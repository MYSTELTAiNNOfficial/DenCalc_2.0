<?php
require('db-controller.php');

if (!empty($_POST)) {
    $username = $_POST['username'];
    $password = $_POST['password'];
} else {
    $username = null;
    $password = null;
}
$query = $conn->prepare("SELECT id FROM user WHERE username = ?");
$query->bind_param('s', $username);
$query->execute();
$query->store_result();

if ($query->num_rows > 0) {
    $response['err'] = true;
    $response['Message'] = 'User already registered';
    $query->close();
} else {
    $query = $conn->prepare("INSERT INTO user (id, username, password) VALUES (id,?,?)");
    $query->bind_param("ss", $username, $password);
    if ($query->execute()) {
        $query = $conn->prepare("SELECT * FROM user WHERE username = ?");
        $query->bind_param("s", $username);
        $query->execute();
        $query->bind_result($id, $username, $password);
        $query->fetch();

        $user = array(
            'id' => $id,
            'username' => $username
        );
        $response['err'] = false;
        $response["user"] = $user;
        $response['Message'] = "Register Successful!";
        $response['Message2'] = "Now Login!";
        $query->close();
    }
}
$conn->close();
echo json_encode($response);
?>