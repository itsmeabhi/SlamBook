<?php

if($_SERVER['REQUEST_METHOD']=='POST')
{
	require_once('dbConnect.php');
	
	$to_user_name = $_POST['to_user_name'];
	$from_user_name = $_POST['from_user_name'];
	
	$sql = "SELECT * FROM $to_user_name WHERE from_user_name LIKE '".$from_user_name."';";
	$result = mysqli_query($con,$sql);
	$response = array();
	
	if(mysqli_num_rows($result)>0)
	{
		// data present
		$code = "success_present";
		
		$row = mysqli_fetch_row($result);
		$name = $row[1];
		$nick_name = $row[2];
		$dob = $row[3];
		$hobbies = $row[4];
		$on_famous_name_change_to = $row[5];
		$mood = $row[6];
		$aim = $row[7];
		$love_wearing = $row[8];
		$zodiac_sign = $row[9];
		$hangout_place = $row[10];
		$treat_for_birthday = $row[11];
		$weekend_activity = $row[12];
		$memorable_moment = $row[13];
		$embarrassing_moment = $row[14];
		$things_want_to_do_before_die = $row[15];
		$what_bores_me_most = $row[16];
		$m_crazy_about = $row[17];
		$my_biggest_strength = $row[18];
		$things_i_hate = $row[19];
		$when_m_happy = $row[20];
		$when_m_sad = $row[21];
		$when_m_mad = $row[22];
		$my_worst_habit = $row[23];
		$best_thing_abt_me = $row[24];
		$feel_powerful_when = $row[25];
		$biggest_achievement = $row[26];
		$my_teddy_knows = $row[27];
		$fb = $row[28];
		$address = $row[29];
		$phone_number = $row[30];
		$website = $row[31];
		$twitter = $row[32];
		$instagram = $row[33];
		$hpy_moment_wid_u = $row[34];
		$sad_moment_wid_u = $row[35];
		$good_things_about_u = $row[36];
		$bad_things_about_u = $row[37];
		$friendship_to_me_is = $row[38];
		$fav_color = $row[39];
		$fav_celebrities = $row[40];
		$fav_role_model = $row[41];
		$fav_tv_show = $row[42];
		$fav_music_band = $row[43];
		$fav_food = $row[44];
		$fav_sport = $row[45];
		
		array_push($response,array("code"=>$code, "name"=>$name, "nick_name"=>$nick_name, "dob"=>$dob, "hobbies"=>$hobbies, "on_famous_name_change_to"=>$on_famous_name_change_to, "mood"=>$mood, "aim"=>$aim,"love_wearing"=>$love_wearing, "zodiac_sign"=>$zodiac_sign, "hangout_place"=>$hangout_place,"treat_for_birthday"=>$treat_for_birthday, "weekend_activity"=>$weekend_activity, "memorable_moment"=>$memorable_moment, "embarrassing_moment"=>$embarrassing_moment, "things_want_to_do_before_die"=>$things_want_to_do_before_die, "what_bores_me_most"=>$what_bores_me_most, "m_crazy_about"=>$m_crazy_about, "my_biggest_strength"=>$my_biggest_strength, "things_i_hate"=>$things_i_hate, "when_m_happy"=>$when_m_happy, "when_m_sad"=>$when_m_sad, "when_m_mad"=>$when_m_mad, "my_worst_habit"=>$my_worst_habit, "best_thing_abt_me"=>$best_thing_abt_me, "feel_powerful_when"=>$feel_powerful_when, "biggest_achievement"=>$biggest_achievement, "my_teddy_knows"=>$my_teddy_knows, "fb"=>$fb, "address"=>$address, "phone_number"=>$phone_number, "website"=>$website, "twitter"=>$twitter, "instagram"=>$instagram, "hpy_moment_wid_u"=>$hpy_moment_wid_u, "sad_moment_wid_u"=>$sad_moment_wid_u, "good_things_about_u"=>$good_things_about_u, "bad_things_about_u"=>$bad_things_about_u, "friendship_to_me_is"=>$friendship_to_me_is, "fav_color"=>$fav_color, "fav_celebrities"=>$fav_celebrities, "fav_role_model"=>$fav_role_model, "fav_tv_show"=>$fav_tv_show, "fav_music_band"=>$fav_music_band, "fav_food"=>$fav_food, "fav_sport"=>$fav_sport));
		echo json_encode($response);
	}
	else {
		// data not present
		$code = "unsuccess_not_present";
		$message = "data not present";
		array_push($response,array("code"=>$code,"message"=>$message));
		echo json_encode($response);
	}
	
	mysqli_close($con);
}
else{
	echo "Error";
}
	
?>