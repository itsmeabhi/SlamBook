<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

require_once('dbConnect.php');

$password = $_POST['password'];
$user_name = $_POST['user_name'];
$new_password = $_POST['new_password'];
 
$sql = "SELECT * FROM user_accounts WHERE user_name LIKE '".$user_name."' AND password LIKE '".$password."';";
$result = mysqli_query($con,$sql);
$response = array();
if(mysqli_num_rows($result)>0)
	{
		$changeQuery = "UPDATE user_accounts SET password = '".$new_password."' WHERE user_name = '".$user_name."';";
		$changeQueryResult = mysqli_query($con,$changeQuery);
		if($changeQueryResult) {
			$code = "success";
			$message = "Password changed successfully";
			array_push($response,array("code"=>$code,"message"=>$message));
			echo json_encode($response);
		}
	}
	else
		{
		$code = "failed";
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

?>