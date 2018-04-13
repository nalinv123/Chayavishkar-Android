<?php
	$con = mysqli_connect("mysql12.000webhost.com","a6888240_abc","vaidya123","a6888240_abc");
	
	$oldpassword = $_POST["oldpassword"];
	$newpassword = $_POST["newpassword"];
	
	$statement = mysqli_query($con, "UPDATE user SET password = '$newpassword' WHERE password = '$oldpassword'");
	
	$response["success"] = true;
	
	echo json_encode($response);
	mysqli_close($con);
?>