<?php
if($_SERVER['REQUEST_METHOD']=='POST')
{
	
require_once 'dbConnect.php';

$user_name = $_POST['user_name'];
$from_user_name = "";
 
$sql = "SELECT from_user_name, name FROM $user_name ";
$result = mysqli_query($con,$sql);
$response = array();

if($result != null)
	{
		while($row = mysqli_fetch_row($result)){
		$from_user_name = $row[0];
		$name = $row[1];
		$code = "success";
		$image_query = "SELECT image FROM user_accounts WHERE user_name LIKE '".$from_user_name."';";
		$image_query_result = mysqli_query($con,$image_query);
		if($image_query_result) {
			$row1 = mysqli_fetch_row($image_query_result);
			$image = $row1[0];
			array_push($response,array("code"=>$code,"name"=>$name,"image"=>$image,"from_user_name"=>$from_user_name));
		} else {
			$code = "unsuccess";
			$message = "No slam found";
			array_push($response,array("code"=>$code,"message"=>$message));
			echo json_encode($response);
		}
	  }
	  
		if(strlen($from_user_name) ==0)
		{
		$code = "unsuccess";
		$message = "No slams found";
		array_push($response,array("code"=>$code,"message"=>$message));
		}
	echo json_encode($response);
	
	}
	else
		{
		$code = "unsuccess";
		$message = "No user found";
		array_push($response,array("code"=>$code,"message"=>$message));
		echo json_encode($response);
		}
	
	mysqli_close($con);

}
else{
	echo "Error";
}

?>