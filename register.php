<?php
    $con = mysqli_connect("mysql12.000webhost.com","a6888240_abc","vaidya123","a6888240_abc");

    $name = $_POST["name"];
    $username = $_POST["username"];
    $password = $_POST["password"];
	$date = $_POST["date"];
	$email = $_POST["email"];
	$phnno = $_POST["phnno"];
    
    $statement = mysqli_prepare($con, "INSERT INTO user (name, username, password, date, email, phnno) VALUES (?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssssss", $name, $username, $password, $date, $email, $phnno);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode ($response);
	mysqli_close($con);
?>