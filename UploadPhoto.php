<?php
$con = mysqli_connect("mysql12.000webhost.com","a6888240_abc","vaidya123","a6888240_abc");
	
	if($_SERVER['REQUEST_METHOD']=='POST'){
 
		$image = $_POST['image'];
        $name = $_POST['name'];
 
		$sql ="SELECT id FROM volleyupload ORDER BY id ASC";
 
		$res = mysqli_query($con,$sql);
 
		$id = 0;
 
		while($row = mysqli_fetch_array($res)){
			$id = $row['id'];
		}
 
		$path = "upload/$id.jpg";
 
		$actualpath = "http://nalinvaidya.16mb.com/$path";
 
		$sql = "INSERT INTO volleyupload (photo,name) VALUES ('$actualpath','$name')";
 
		if(mysqli_query($con,$sql)){
			file_put_contents($path,base64_decode($image));
			echo "Successfully Uploaded";
		}
 
		mysqli_close($con);
		}else{
		echo "Error";
	}
?>