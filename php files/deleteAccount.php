<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
	
require_once('dbConnect.php');
	
$password = $_POST['password'];
$user_name = $_POST['user_name'];
	
$sql = "SELECT user_name FROM user_accounts WHERE user_name LIKE '".$user_name."' AND password LIKE '".$password."';";
$result = mysqli_query($con,$sql);
$response = array();
if(mysqli_num_rows($result)>0)
	{
		$delete_account = "DELETE FROM user_accounts WHERE user_name = '".$user_name."';";
		$delete_table = "DROP TABLE $user_name";
        $user_name_sent_slams = $user_name."_sent_slams";
        $delete_table2 = "DROP TABLE $user_name_sent_slams";
		$delete_account_result = mysqli_query($con,$delete_account);
		$delete_table_result = mysqli_query($con,$delete_table);
        $delete_table_result2 = mysqli_query($con,$delete_table2);
		if($delete_account_result && $delete_account_result && $delete_table_result2) {
			$code = "success";
			$message = "User deleted successfully";
			array_push($response,array("code"=>$code,"message"=>$message));
			echo json_encode($response);
		}		
	}
else
	{
	$code = "failed";
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
	
?>