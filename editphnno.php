<?php
	$con = mysqli_connect("mysql12.000webhost.com","a6888240_abc","vaidya123","a6888240_abc");
	
	$oldphnno = $_POST['oldphnno'];
	$newphnno = $_POST['newphnno'];
	
	$query = mysqli_query($con, "UPDATE user SET phnno = '$newphnno' WHERE phnno = '$oldphnno'");
	
	$response['success'] = true;
	
	echo json_encode($response);
	mysqli_close($con);
?>