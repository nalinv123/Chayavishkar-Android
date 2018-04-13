<?php
	$con = mysqli_connect("mysql12.000webhost.com","a6888240_abc","vaidya123","a6888240_abc");
	
	$oldemail = $_POST['oldemail'];
	$newemail = $_POST['newemail'];
	
	$query = mysqli_query($con, "UPDATE user SET email = '$newemail' WHERE email = '$oldemail'");
	
	$response['success'] = true;
	
	echo json_encode($response);
	mysqli_close($con);
?>