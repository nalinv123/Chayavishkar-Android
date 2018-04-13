<?php
	$con = mysqli_connect("mysql12.000webhost.com","a6888240_abc","vaidya123","a6888240_abc");
	
	$username = $_POST["username"];
	$password = $_POST["password"];
	
	$statement = mysqli_prepare($con, "Select * from user where username = ? AND password = ?");
	mysqli_stmt_bind_param($statement, "ss", $username, $password);
	mysqli_stmt_execute($statement);
	
	mysqli_stmt_store_result($statement);
   
	mysqli_stmt_bind_result($statement, $userID, $name, $username, $password, $date, $email, $phnno);

	$response = array();
	$response["success"] = false;
	
	while(mysqli_stmt_fetch($statement)){
		$response["success"] = true;
		$response["name"] = $name;
		$response["username"] = $username;
		$response["password"] = $password;
		$response["date"] = $date;
		$response["email"] = $email;
		$response["phnno"] = $phnno;
	}
	
	echo json_encode ($response);
	mysqli_close($con);
?>