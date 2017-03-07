<?php
	 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 require_once('dbConnect.php');
 
 $image = $_POST['image'];
 $name = $_POST['name'];
 $description = $_POST['description'];
 $dob = $_POST['dob'];
 $email = $_POST['email'];
 $phone_number = $_POST['phone_number'];
 $password = $_POST['password'];
 $user_name = $_POST['user_name'];
 $gender = $_POST['gender'];
 $registered_at = $_POST['registered_at'];
 
 
 
 $sql = "SELECT * FROM user_accounts WHERE email LIKE '".$email."';";
	
	$result = mysqli_query($con,$sql);
	$response = array();
	
	if(mysqli_num_rows($result)>0)
	{
		$code = "registraion_unsuccessfull";
		$message = "User already exist";
		array_push($response,array("code"=>$code,"message"=>$message));
		echo json_encode($response);
		
	}
	else{
		$path = "profile/$user_name.png";
		$actualpath = "http://www.gmonetix.com/slambook/$path";
 
		$sql = "INSERT INTO user_accounts (image,name,user_name,dob,phone_number,description,email,password,gender,registered_at) VALUES ('$actualpath','$name','$user_name','$dob','$phone_number','$description','$email','$password','$gender','$registered_at')";
        
		$create_table="CREATE TABLE $user_name (from_user_name CHAR(30) UNIQUE,name CHAR(50),nick_name CHAR(50),dob CHAR(10),hobbies LONGTEXT,on_famous_name_change_to LONGTEXT,mood LONGTEXT,aim LONGTEXT,love_wearing LONGTEXT, zodiac_sign LONGTEXT, hangout_place LONGTEXT,treat_for_birthday LONGTEXT,weekend_activity LONGTEXT, memorable_moment LONGTEXT, embarrassing_moment LONGTEXT, things_want_to_do_before_die LONGTEXT, what_bores_me_most LONGTEXT, m_crazy_about LONGTEXT, my_biggest_strength LONGTEXT,things_i_hate LONGTEXT,when_m_happy LONGTEXT,when_m_sad LONGTEXT,when_m_mad LONGTEXT,my_worst_habit LONGTEXT,best_thing_abt_me LONGTEXT,feel_powerful_when LONGTEXT,biggest_achievement LONGTEXT,my_teddy_knows LONGTEXT,fb LONGTEXT,address LONGTEXT,phone_number LONGTEXT,website LONGTEXT,twitter LONGTEXT,instagram LONGTEXT,hpy_moment_wid_u LONGTEXT,sad_moment_wid_u LONGTEXT,good_things_about_u LONGTEXT,bad_things_about_u LONGTEXT,friendship_to_me_is LONGTEXT,fav_color LONGTEXT,fav_celebrities LONGTEXT,fav_role_model LONGTEXT,fav_tv_show LONGTEXT,fav_music_band LONGTEXT,fav_food LONGTEXT,fav_sport LONGTEXT)";
		
		$user_name_sent_slams = $user_name."_sent_slams";
		$create_table1 = "CREATE TABLE $user_name_sent_slams (to_user_name CHAR(50) UNIQUE, sent_on CHAR(40), updated_on CHAR(40))";
		
		if(mysqli_query($con,$sql) && mysqli_query($con,$create_table) && mysqli_query($con,$create_table1) && file_put_contents($path,base64_decode($image)) ){
			$code = "success";
			$message = "Successfully registered";
			array_push($response,array("code"=>$code,"message"=>$message));
			echo json_encode($response);
		} else{
			$code = "failed";
			$message = "some error occured";
			array_push($response,array("code"=>$code,"message"=>$message));
			echo json_encode($response);
		}
	}
	
	mysqli_close($con);
 }
 else{
	echo "Error";
}
 
 ?>