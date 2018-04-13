<?php
	$name = $_POST["name"];
	$image = $_POST["image"];
	
	$decodeImage = base64_decode("$image");
	if(file_put_contents("pictures/" . $name . ".JPG", $decodeImage) === FALSE)
	{
		$result = array('result' => "Unable to upload image");
	}
	else
	{
		$result = array('result' => "Image Uploaded Successfully");
	}

	echo json_encode($result);
?>	