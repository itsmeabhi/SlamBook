<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
require_once('dbConnect.php');

$password = $_POST['password'];
$user_name = $_POST['user_name'];
$name = $_POST['name'];
$email = $_POST['email'];
$phone_number = $_POST['phone_number'];
$description = $_POST['description'];
$dob = $_POST['dob'];
$gender = $_POST['gender'];
$image = $_POST['image'];

$sql = "SELECT user_name,password FROM user_accounts WHERE user_name like '".$user_name."' and password like '".$password."';";
$result = mysqli_query($con,$sql);
$response = array();
if(mysqli_num_rows($result)>0)
	{
		$path = "profile/$user_name.png";
		$actualpath = "http://www.gmonetix.com/slambook/$path";
		
		$update_account = "UPDATE user_accounts SET name = '".$name."', email = '".$email."', phone_number = '".$phone_number."', description = '".$description."', dob = '".$dob."', gender = '".$gender."', image = '".$actualpath."' WHERE user_name = '".$user_name."';";
		$update_result = mysqli_query($con,$update_account);
		$image_update_result = file_put_contents($path,base64_decode($image));
		if($update_result && $image_update_result) {
			$code = "success";
			$message = "User account updated successfully";
			array_push($response,array("code"=>$code,"message"=>$message));
			echo json_encode($response);
		}
	}
else
	{
	$code = "unsuccess";
	$message = "could not find user";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);
	}
	
mysqli_close($con);
}
 else{
	echo "Error";
}
 
 ?>