package com.gmonetix.slambook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmonetix.slambook.helper.Const;
import com.gmonetix.slambook.helper.Utils;
import com.gmonetix.slambook.user_profile.UserHome;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Slams extends AppCompatActivity {

    private static final String INTENT_FROM_USERNAME = "from_username";
    private static final String INTENT_IMAGE = "image";

    private Toolbar toolbar;
    private ImageView profilePic;
    private ProgressBar progressBar;
    private TextView tvFromUsername,tvName,tvNickName,tvDob,tvHobbies,tvOnFamousNameChange,tvAim,tvLoveWearing,tvZodiacSign,
    tvHangoutPlace,tvTreatForBirthday,tvWeekendActivity,tvMemorableMoment,tvEmbarassingMoment,tvThingsToDoBeforeDie,tvWhatBoresMe,
    tvMcrazyAbout,tvMyBiggestStrength,tvThingsIHate,tvWhenMHappy,tvWhenMSad,tvWhenMMad,tvWorstHabit,tvBestThingAbtMe,tvFeelPowerfullWhen,
    tvBiggestAchievement,tvMyTeddyKnows,tvFb,tvAddress,tvPhoneNumber,tvWebsite,tvTwitter,tvInstagram,tvHpyMomentWidU,tvSadMomentWidU,
    tvGoodThingsAbtU,tvBadThingsAbtU,tvFriendshipToMe,tvFavColor,tvFavCelebrities,tvFavRoleModel,tvFavTvShow,tvFavMusicBand,tvFavFood,tvFavSport;
    private FloatingActionButton editSlam, home;

    private Utils utils;

    private String from_user_name,image,   name, nick_name, dob, hobbies, on_famous_name_change_to, mood, aim,love_wearing, zodiac_sign, hangout_place,
            treat_for_birthday, weekend_activity, memorable_moment, embarrassing_moment, things_want_to_do_before_die, what_bores_me_most,
            m_crazy_about, my_biggest_strength, things_i_hate, when_m_happy, when_m_sad, when_m_mad, my_worst_habit, best_thing_abt_me,
            feel_powerful_when, biggest_achievement, my_teddy_knows, fb, address, phone_number, website, twitter, instagram, hpy_moment_wid_u,
            sad_moment_wid_u, good_things_about_u, bad_things_about_u, friendship_to_me_is, fav_color, fav_celebrities, fav_role_model, fav_tv_show,
            fav_music_band, fav_food, fav_sport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slams);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Slams.this.setTitle("");

        if (getIntent().hasExtra(INTENT_FROM_USERNAME) && getIntent().hasExtra(INTENT_IMAGE))
        {
            from_user_name = getIntent().getExtras().getString(INTENT_FROM_USERNAME);
            image = getIntent().getExtras().getString(INTENT_IMAGE);
        }
        else {
            from_user_name = "";
            image = "";
        }

        init();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Slams.this,UserHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        progressBar.setVisibility(View.VISIBLE);
        ImageLoader.getInstance().displayImage(image, profilePic, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
                profilePic.setImageDrawable(getResources().getDrawable(R.drawable.profile));
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
        StringRequest rqst = new StringRequest(Request.Method.POST, Const.read_slam,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONArray jsonArray = new JSONArray(s);
                            JSONObject object = jsonArray.getJSONObject(0);

                            name = object.getString("name");
                            nick_name = object.getString("nick_name");
                            dob = object.getString("dob");
                            hobbies = object.getString("hobbies");
                            on_famous_name_change_to = object.getString("on_famous_name_change_to");
                            mood = object.getString("mood");
                            aim = object.getString("aim");
                            love_wearing = object.getString("love_wearing");
                            zodiac_sign = object.getString("zodiac_sign");
                            hangout_place = object.getString("hangout_place");
                            treat_for_birthday = object.getString("treat_for_birthday");
                            weekend_activity = object.getString("weekend_activity");
                            memorable_moment = object.getString("memorable_moment");
                            embarrassing_moment = object.getString("embarrassing_moment");
                            things_want_to_do_before_die = object.getString("things_want_to_do_before_die");
                            what_bores_me_most = object.getString("what_bores_me_most");
                            m_crazy_about = object.getString("m_crazy_about");
                            my_biggest_strength = object.getString("my_biggest_strength");
                            things_i_hate = object.getString("things_i_hate");
                            when_m_happy = object.getString("when_m_happy");
                            when_m_sad = object.getString("when_m_sad");
                            when_m_mad = object.getString("when_m_mad");
                            my_worst_habit = object.getString("my_worst_habit");
                            best_thing_abt_me = object.getString("best_thing_abt_me");
                            feel_powerful_when = object.getString("feel_powerful_when");
                            biggest_achievement = object.getString("biggest_achievement");
                            my_teddy_knows = object.getString("my_teddy_knows");
                            fb = object.getString("fb");
                            address = object.getString("address");
                            phone_number = object.getString("phone_number");
                            website = object.getString("website");
                            twitter = object.getString("twitter");
                            instagram = object.getString("instagram");
                            hpy_moment_wid_u = object.getString("hpy_moment_wid_u");
                            sad_moment_wid_u = object.getString("sad_moment_wid_u");
                            good_things_about_u = object.getString("good_things_about_u");
                            bad_things_about_u = object.getString("bad_things_about_u");
                            friendship_to_me_is = object.getString("friendship_to_me_is");
                            fav_color = object.getString("fav_color");
                            fav_celebrities = object.getString("fav_celebrities");
                            fav_role_model = object.getString("fav_role_model");
                            fav_tv_show = object.getString("fav_tv_show");
                            fav_music_band = object.getString("fav_music_band");
                            fav_food = object.getString("fav_food");
                            fav_sport = object.getString("fav_sport");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            tvFromUsername.setText(from_user_name);
                            tvName.setText(name);
                            tvNickName.setText(nick_name);
                            tvDob.setText(dob);
                            tvHobbies.setText(hobbies);
                            tvOnFamousNameChange.setText(on_famous_name_change_to);
                            tvAim.setText(aim);
                            tvLoveWearing.setText(love_wearing);
                            tvZodiacSign.setText(zodiac_sign);
                            tvHangoutPlace.setText(hangout_place);
                            tvTreatForBirthday.setText(treat_for_birthday);
                            tvWeekendActivity.setText(weekend_activity);
                            tvMemorableMoment.setText(memorable_moment);
                            tvEmbarassingMoment.setText(embarrassing_moment);
                            tvThingsToDoBeforeDie.setText(things_want_to_do_before_die);
                            tvWhatBoresMe.setText(what_bores_me_most);
                            tvMcrazyAbout.setText(m_crazy_about);
                            tvMyBiggestStrength.setText(my_biggest_strength);
                            tvThingsIHate.setText(things_i_hate);
                            tvWhenMHappy.setText(when_m_happy);
                            tvWhenMSad.setText(when_m_sad);
                            tvWhenMMad.setText(when_m_mad);
                            tvWorstHabit.setText(my_worst_habit);
                            tvBestThingAbtMe.setText(best_thing_abt_me);
                            tvFeelPowerfullWhen.setText(feel_powerful_when);
                            tvBiggestAchievement.setText(biggest_achievement);
                            tvMyTeddyKnows.setText(my_teddy_knows);
                            tvFb.setText(fb);
                            tvAddress.setText(address);
                            tvPhoneNumber.setText(phone_number);
                            tvWebsite.setText(website);
                            tvTwitter.setText(twitter);
                            tvInstagram.setText(instagram);
                            tvHpyMomentWidU.setText(hpy_moment_wid_u);
                            tvSadMomentWidU.setText(sad_moment_wid_u);
                            tvGoodThingsAbtU.setText(good_things_about_u);
                            tvBadThingsAbtU.setText(bad_things_about_u);
                            tvFriendshipToMe.setText(friendship_to_me_is);
                            tvFavColor.setText(fav_color);
                            tvFavCelebrities.setText(fav_celebrities);
                            tvFavRoleModel.setText(fav_role_model);
                            tvFavTvShow.setText(fav_tv_show);
                            tvFavMusicBand.setText(fav_music_band);
                            tvFavFood.setText(fav_food);
                            tvFavSport.setText(fav_sport);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();
                params.put(Const.USER_ACCOUNT_DATA_USER_NAME,utils.getUserName(Slams.this));
                params.put("from_user_name",from_user_name);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Slams.this);
        queue.add(rqst);
    }

    private void init() {
        utils = new Utils();
        utils.getUilInstance(Slams.this);

        profilePic = (ImageView) findViewById(R.id.iv_profile_image_read_slam_final);
        progressBar = (ProgressBar) findViewById(R.id.iv_profile_image_read_slam_progressBar);
        tvFromUsername = (TextView) findViewById(R.id.tvFromUsername);
        tvName = (TextView) findViewById(R.id.tvName);
        tvNickName = (TextView) findViewById(R.id.tvNickName);
        tvDob = (TextView) findViewById(R.id.tvDob);
        tvHobbies = (TextView) findViewById(R.id.tvHobbies);
        tvOnFamousNameChange = (TextView) findViewById(R.id.tvOnFamousNameChange);
        tvAim = (TextView) findViewById(R.id.tvAim);
        tvLoveWearing = (TextView) findViewById(R.id.tvLoveWearing);
        tvZodiacSign = (TextView) findViewById(R.id.tvZodiacSign);
        tvHangoutPlace = (TextView) findViewById(R.id.tvHangoutPlace);
        tvTreatForBirthday = (TextView) findViewById(R.id.tvTreatForBirthday);
        tvWeekendActivity = (TextView) findViewById(R.id.tvWeekendActivity);
        tvMemorableMoment = (TextView) findViewById(R.id.tvMemorableMoment);
        tvEmbarassingMoment = (TextView) findViewById(R.id.tvEmbarassingMoment);
        tvThingsToDoBeforeDie = (TextView) findViewById(R.id.tvThingsToDoBeforeDie);
        tvWhatBoresMe = (TextView) findViewById(R.id.tvWhatBoresMe);
        tvMcrazyAbout = (TextView) findViewById(R.id.tvMcrazyAbout);
        tvMyBiggestStrength = (TextView) findViewById(R.id.tvMyBiggestStrength);
        tvThingsIHate = (TextView) findViewById(R.id.tvThingsIHate);
        tvWhenMHappy = (TextView) findViewById(R.id.tvWhenMHappy);
        tvWhenMSad = (TextView) findViewById(R.id.tvWhenMSad);
        tvWhenMMad = (TextView) findViewById(R.id.tvWhenMMad);
        tvWorstHabit = (TextView) findViewById(R.id.tvWorstHabit);
        tvBestThingAbtMe = (TextView) findViewById(R.id.tvBestThingAbtMe);
        tvFeelPowerfullWhen = (TextView) findViewById(R.id.tvFeelPowerfullWhen);
        tvBiggestAchievement = (TextView) findViewById(R.id.tvBiggestAchievement);
        tvMyTeddyKnows = (TextView) findViewById(R.id.tvMyTeddyKnows);
        tvFb = (TextView) findViewById(R.id.tvFb);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvPhoneNumber = (TextView) findViewById(R.id.tvPhoneNumber);
        tvWebsite = (TextView) findViewById(R.id.tvWebsite);
        tvTwitter = (TextView) findViewById(R.id.tvTwitter);
        tvInstagram = (TextView) findViewById(R.id.tvInstagram);
        tvHpyMomentWidU = (TextView) findViewById(R.id.tvHpyMomentWidU);
        tvSadMomentWidU = (TextView) findViewById(R.id.tvSadMomentWidU);
        tvGoodThingsAbtU = (TextView) findViewById(R.id.tvGoodThingsAbtU);
        tvBadThingsAbtU = (TextView) findViewById(R.id.tvBadThingsAbtU);
        tvFriendshipToMe = (TextView) findViewById(R.id.tvFriendshipToMe);
        tvFavColor = (TextView) findViewById(R.id.tvFavColor);
        tvFavCelebrities = (TextView) findViewById(R.id.tvFavCelebrities);
        tvFavRoleModel = (TextView) findViewById(R.id.tvFavRoleModel);
        tvFavTvShow = (TextView) findViewById(R.id.tvFavTvShow);
        tvFavMusicBand = (TextView) findViewById(R.id.tvFavMusicBand);
        tvFavFood = (TextView) findViewById(R.id.tvFavFood);
        tvFavSport = (TextView) findViewById(R.id.tvFavSport);

        editSlam = (FloatingActionButton) findViewById(R.id.btn_edit_slam);
        home = (FloatingActionButton) findViewById(R.id.btn_home);
        editSlam.setVisibility(View.GONE);

        utils.setFont(Slams.this,tvFromUsername);
        utils.setFont(Slams.this,tvName);
        utils.setFont(Slams.this,tvNickName);
        utils.setFont(Slams.this,tvDob);
        utils.setFont(Slams.this,tvHobbies);
        utils.setFont(Slams.this,tvOnFamousNameChange);
        utils.setFont(Slams.this,tvAim);
        utils.setFont(Slams.this,tvLoveWearing);
        utils.setFont(Slams.this,tvZodiacSign);
        utils.setFont(Slams.this,tvHangoutPlace);
        utils.setFont(Slams.this,tvTreatForBirthday);
        utils.setFont(Slams.this,tvWeekendActivity);
        utils.setFont(Slams.this,tvMemorableMoment);
        utils.setFont(Slams.this,tvEmbarassingMoment);
        utils.setFont(Slams.this,tvThingsToDoBeforeDie);
        utils.setFont(Slams.this,tvWhatBoresMe);
        utils.setFont(Slams.this,tvMcrazyAbout);
        utils.setFont(Slams.this,tvMyBiggestStrength);
        utils.setFont(Slams.this,tvThingsIHate);
        utils.setFont(Slams.this,tvWhenMHappy);
        utils.setFont(Slams.this,tvWhenMSad);
        utils.setFont(Slams.this,tvWhenMMad);
        utils.setFont(Slams.this,tvWorstHabit);
        utils.setFont(Slams.this,tvBestThingAbtMe);
        utils.setFont(Slams.this,tvFeelPowerfullWhen);
        utils.setFont(Slams.this,tvBiggestAchievement);
        utils.setFont(Slams.this,tvMyTeddyKnows);
        utils.setFont(Slams.this,tvFb);
        utils.setFont(Slams.this,tvAddress);
        utils.setFont(Slams.this,tvPhoneNumber);
        utils.setFont(Slams.this,tvWebsite);
        utils.setFont(Slams.this,tvTwitter);
        utils.setFont(Slams.this,tvInstagram);
        utils.setFont(Slams.this,tvHpyMomentWidU);
        utils.setFont(Slams.this,tvSadMomentWidU);
        utils.setFont(Slams.this,tvGoodThingsAbtU);
        utils.setFont(Slams.this,tvBadThingsAbtU);
        utils.setFont(Slams.this,tvFriendshipToMe);
        utils.setFont(Slams.this,tvFavColor);
        utils.setFont(Slams.this,tvFavCelebrities);
        utils.setFont(Slams.this,tvFavRoleModel);
        utils.setFont(Slams.this,tvFavTvShow);
        utils.setFont(Slams.this,tvFavMusicBand);
        utils.setFont(Slams.this,tvFavFood);
        utils.setFont(Slams.this,tvFavSport);
    }
}
