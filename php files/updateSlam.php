<?php

if($_SERVER['REQUEST_METHOD']=='POST')
{
	require_once('dbConnect.php');
	
	$to_user_name = $_POST['to_user_name'];
	$from_user_name = $_POST['from_user_name'];
    $updated_on = $_POST['updated_on'];
	
	$name = $_POST['name'];
	$nick_name = $_POST['nick_name'];
	//$dob = $_POST['dob'];
	$hobbies = $_POST['hobbies'];
	$on_famous_name_change_to = $_POST['on_famous_name_change_to'];
	//$mood = $_POST['mood'];
	$aim = $_POST['aim'];
	$love_wearing = $_POST['love_wearing'];
	$zodiac_sign = $_POST['zodiac_sign'];
	$hangout_place = $_POST['hangout_place'];
	$treat_for_birthday = $_POST['treat_for_birthday'];
	$weekend_activity = $_POST['weekend_activity'];
	$memorable_moment = $_POST['memorable_moment'];
	$embarrassing_moment = $_POST['embarrassing_moment'];
	$things_want_to_do_before_die = $_POST['things_want_to_do_before_die'];
	$what_bores_me_most = $_POST['what_bores_me_most'];
	$m_crazy_about = $_POST['m_crazy_about'];
	$my_biggest_strength = $_POST['my_biggest_strength'];
	$things_i_hate = $_POST['things_i_hate'];
	$when_m_happy = $_POST['when_m_happy'];
	$when_m_sad = $_POST['when_m_sad'];
	$when_m_mad = $_POST['when_m_mad'];
	$my_worst_habit = $_POST['my_worst_habit'];
	$best_thing_abt_me = $_POST['best_thing_abt_me'];
	$feel_powerful_when = $_POST['feel_powerful_when'];
	$biggest_achievement = $_POST['biggest_achievement'];
	$my_teddy_knows = $_POST['my_teddy_knows'];
	$fb = $_POST['fb'];
	$address = $_POST['address'];
	$phone_number = $_POST['phone_number'];
	$website = $_POST['website'];
	$twitter = $_POST['twitter'];
	$instagram = $_POST['instagram'];
	$hpy_moment_wid_u = $_POST['hpy_moment_wid_u'];
	$sad_moment_wid_u = $_POST['sad_moment_wid_u'];
	$good_things_about_u = $_POST['good_things_about_u'];
	$bad_things_about_u = $_POST['bad_things_about_u'];
	$friendship_to_me_is = $_POST['friendship_to_me_is'];
	$fav_color = $_POST['fav_color'];
	$fav_celebrities = $_POST['fav_celebrities'];
	$fav_role_model = $_POST['fav_role_model'];
	$fav_tv_show = $_POST['fav_tv_show'];
	$fav_music_band = $_POST['fav_music_band'];
	$fav_food = $_POST['fav_food'];
	$fav_sport = $_POST['fav_sport'];
	
	
	$sql = "SELECT from_user_name FROM $to_user_name WHERE from_user_name LIKE '".$from_user_name."';";
	$result = mysqli_query($con,$sql);
	$response = array();
	
	if(mysqli_num_rows($result)>0)
	{
		// slam is present, update it
		
		$update_slam = "UPDATE $to_user_name SET name = '".$name."', nick_name = '".$nick_name."', hobbies = '".$hobbies."', on_famous_name_change_to = '".$on_famous_name_change_to."', aim = '".$aim."',love_wearing = '".$love_wearing."', zodiac_sign = '".$zodiac_sign."', hangout_place = '".$hangout_place."', treat_for_birthday = '".$treat_for_birthday."', weekend_activity = '".$weekend_activity."', memorable_moment = '".$memorable_moment."', embarrassing_moment = '".$embarrassing_moment."', things_want_to_do_before_die = '".$things_want_to_do_before_die."', what_bores_me_most = '".$what_bores_me_most."', m_crazy_about = '".$m_crazy_about."', my_biggest_strength = '".$my_biggest_strength."', things_i_hate = '".$things_i_hate."', when_m_happy = '".$when_m_happy."', when_m_sad = '".$when_m_sad."', when_m_mad = '".$when_m_mad."', my_worst_habit = '".$my_worst_habit."', best_thing_abt_me = '".$best_thing_abt_me."', feel_powerful_when = '".$feel_powerful_when."', biggest_achievement = '".$biggest_achievement."', my_teddy_knows = '".$my_teddy_knows."', fb = '".$fb."', address = '".$address."', phone_number = '".$phone_number."', website = '".$website."', twitter = '".$twitter."', instagram = '".$instagram."', hpy_moment_wid_u = '".$hpy_moment_wid_u."', sad_moment_wid_u = '".$sad_moment_wid_u."', good_things_about_u = '".$good_things_about_u."', bad_things_about_u = '".$bad_things_about_u."', friendship_to_me_is = '".$friendship_to_me_is."', fav_color = '".$fav_color."', fav_celebrities = '".$fav_celebrities."', fav_role_model = '".$fav_role_model."', fav_tv_show = '".$fav_tv_show."', fav_music_band = '".$fav_music_band."' , fav_food = '".$fav_food."', fav_sport = '".$fav_sport."' WHERE from_user_name = '".$from_user_name."';";
        
        $user_name_sent_slams = $from_user_name."_sent_slams"; 
        $update_slam2 = "UPDATE $user_name_sent_slams SET updated_on = '".$updated_on."';";
        
		$update_slam_result = mysqli_query($con,$update_slam);
        $update_slam_result2 = mysqli_query($con,$update_slam2);
        
		if($update_slam_result && $update_slam_result2) {
			$code = "success_present";
			$message = "slambook updated";
			array_push($response,array("code"=>$code,"message"=>$message));
			echo json_encode($response);
		}
		
	}
	else {
		// Slam not present
		$code = "unsuccess";
		$message = "slambook not updated";
		array_push($response,array("code"=>$code,"message"=>$message));
		echo json_encode($response);
	}
	
	mysqli_close($con);
}
else{
	echo "Error";
}
	
?>