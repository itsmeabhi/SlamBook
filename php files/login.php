<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
require_once('dbConnect.php');

$password = $_POST['password'];
$user_name = $_POST['user_name'];
 
$sql = "SELECT * FROM user_accounts WHERE user_name LIKE '".$user_name."' AND password LIKE '".$password."';";
$result = mysqli_query($con,$sql);
$response = array();
if(mysqli_num_rows($result)>0)
	{
		$row = mysqli_fetch_row($result);
		$name = $row[0];
		$email = $row[1];
		$image = $row[4];
		$description = $row[5];
		$phone_number = $row[6];
		$dob = $row[7];
		$gender = $row[8];
		$registered_at = $row[9];
		$id = $row[10];
		$code = "login_success";
		
		array_push($response,array("code"=>$code,"id"=>$id,"user_name"=>$user_name,"password"=>$password,"name"=>$name,"email"=>$email,"image"=>$image,"description"=>$description,"phone_number"=>$phone_number,"dob"=>$dob,"gender"=>$gender,"registered_at"=>$registered_at));
		echo json_encode($response);
	}
	else
		{
		$code = "login_failed";
		$message = "user not found";
		array_push($response,array("code"=>$code,"message"=>$message));
		echo json_encode($response);
		}
	
	mysqli_close($con);
	
}
 else{
 echo "Error";
 }

?>