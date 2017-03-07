<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
require_once 'dbConnect.php';

$name = $_POST['name'];
 
$sql = "SELECT name,email,image,description,user_name,dob,gender FROM user_accounts WHERE name LIKE '%".$name."%';";
$result = mysqli_query($con,$sql);
$response = array();

if(mysqli_num_rows($result)>0)
	{
		while($row = mysqli_fetch_row($result)){
		$name = $row[0];
		$email = $row[1];
		$image = $row[2];
		$description = $row[3];
		$user_name = $row[4];
		$dob = $row[5];
		$gender = $row[6];
		$code = "success";
		array_push($response,array("code"=>$code,"name"=>$name,"email"=>$email,"image"=>$image,"description"=>$description,"user_name"=>$user_name,"dob"=>$dob,"gender"=>$gender));
		}
		echo json_encode($response);
	}
	else
		{
		$code = "unsuccess";
		$message = "No friends found";
		array_push($response,array("code"=>$code,"message"=>$message));
		echo json_encode($response);
		}
	
	mysqli_close($con);
	
}
 else{
	echo "Error";
}

?>