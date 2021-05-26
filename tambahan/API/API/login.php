<?php
require_once('db-controller.php');

if (!empty($_POST)) {
    $username = $_POST['username'];
    $password = $_POST['password'];
} else {
    $username = null;
    $password = null;
}

$query = $conn->prepare("SELECT * FROM user WHERE username = ? AND password = ?");
$query->bind_param('ss', $username, $password);
$query->execute();
$query->store_result();
if ($query->num_rows > 0) {
    $query->bind_result($id, $username,$password);
    $query->fetch();
    $user = array(
        'id' => $id,
        'username' => $username
    );
    $response['err'] = false;
    $response["user"] = $user;
} else {
    $response['err'] = true;
    $response['Message'] = "Username or Password incorrect!";
}

$query->close();
$conn->close();

echo json_encode($response);
?>