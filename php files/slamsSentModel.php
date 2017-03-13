<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
require_once('dbConnect.php');

$user_name = $_POST['user_name'];

$user_name_sent_slams = $user_name."_sent_slams";
 
$sql = "SELECT * FROM $user_name_sent_slams";
$result = mysqli_query($con,$sql);
$response = array();
if(mysqli_num_rows($result)>0)
	{
		while($row = mysqli_fetch_row($result)){
		$to_user_name = $row[0];
		$sent_on = $row[1];
		$updated_on = $row[2];
		$query = "SELECT name,image,gender FROM user_accounts WHERE user_name LIKE '".$to_user_name."';";
		$query_result = mysqli_query($con,$query);
		
		if($query_result) {
			$row1 = mysqli_fetch_row($query_result);
			$name = $row1[0];
			$image = $row1[1];
			$gender = $row1[2];
			$code = "success";
			array_push($response,array("code"=>$code,"to_user_name"=>$to_user_name,"name"=>$name,"image"=>$image,"gender"=>$gender,"sent_on"=>$sent_on,"updated_on"=>$updated_on));
		} else{
			$code = "failed";
			array_push($response,array("code"=>$code));
		}
		
		}
		echo json_encode($response);
	}
	else
		{
		$code = "failed";
		array_push($response,array("code"=>$code));
		echo json_encode($response);
		}
	
	mysqli_close($con);
	
}
 else{
 echo "Error";
 }

?>