<?php
	$con = mysqli_connect("mysql12.000webhost.com","a6888240_abc","vaidya123","a6888240_abc");
	$email = $_POST['email'];
	
	if($email){
		if( (strstr($email, "@")) && (strstr($email, ".")) ){
				$query = mysqli_query($con, "SELECT * FROM user WHERE email = '$email'");
				$numrows = mysqli_num_rows($query);
				if($numrows == 1){
					$row = mysqli_fetch_assoc($query);
					$dbemail = $row['email'];
					
					if($email == $dbemail){
						$pass = rand();
						$pass = substr($pass, 0, 5);
						
						mysqli_query($con, "UPDATE user SET password = '$pass' WHERE email = '$email'");
						$query = mysqli_query($con, "SELECT * FROM user WHERE 	email = '$email' AND password = '$pass'");
						$numrows = mysqli_num_rows($query);
						if($numrows == 1){
							$webmaster = "nalinvaidya@gmail.com";
							$headers = "From: nalinvaidya<$webmaster>";
							$subject = "Your new password";
							$message = "Hello. Your Password has been reset. Your new password is below.\n";
							$message .= "Password: $pass\n";
							
							if(mail($email, $subject, $message, $headers)){
								echo "Your password has been reset and mail has been sent.";
							}
							else
								echo "Error has occured and email was not send";
						}
						else
							echo "An error occured and password is not reset.";
					}
					else
						echo "You enter wrong email";
				}
				else
					echo "User Not found.";
				
				mysqli_close();
		}
		else
			echo "Please enter valid email address.";
	}
	else
		echo "Please enter your email.";
?>