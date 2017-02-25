package com.gmonetix.slambook;

import android.graphics.Bitmap;
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
    tvGoodThingsAbtU,tvBadThingsAbtU,tvFriendshipToMe,tvFavColor,tvFavCelebrities,tvFavRoleModel,tvFavTvShow,tvFavMusicBand,tvFavFood,tvFavSport,

    tvWhenMHappy1,tvWhenMSad1,tvFb1,tvAddress1,tvPhoneNumber1,tvWebsite1,tvTwitter1,tvInstagram1,tvFavColor1,tvFavCelebrities1,tvFavRoleModel1,tvFavTvShow1,tvFavMusicBand1,tvFavFood1,tvFavSport1;

    private Utils utils;

    private String from_user_name,image,   name, nick_name, dob, hobbies, on_famous_name_change_to, mood, aim,love_wearing, zodiac_sign, hangout_place,
            treat_for_birthday, autograph, weekend_activity, memorable_moment, embarrassing_moment, things_want_to_do_before_die, what_bores_me_most,
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
        progressBar.setVisibility(View.VISIBLE);
        ImageLoader.getInstance().displayImage(image, profilePic, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
                profilePic.setImageDrawable(getResources().getDrawable(R.drawable.profile
                ));
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
                            autograph = object.getString("autograph");
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
                            tvFromUsername.setText("Username -->  "+from_user_name);
                            tvName.setText("Name -->  "+name);
                            tvNickName.setText("Nickname -->  "+nick_name);
                            tvDob.setText("Date of birth -->  "+dob);
                            tvHobbies.setText("Hobbies -->  "+hobbies);
                            tvOnFamousNameChange.setText("When I become famous I will change my name to -->  "+on_famous_name_change_to);
                            tvAim.setText("Aim in life -->  "+aim);
                            tvLoveWearing.setText("I love wearing -->  "+love_wearing);
                            tvZodiacSign.setText("Zodiac sign -->  "+zodiac_sign);
                            tvHangoutPlace.setText("Hangout place -->  "+hangout_place);
                            tvTreatForBirthday.setText("Treat for my birthday -->  "+treat_for_birthday);
                            tvWeekendActivity.setText("Weekend activity -->  "+weekend_activity);
                            tvMemorableMoment.setText("My most memorable moment -->  "+memorable_moment);
                            tvEmbarassingMoment.setText("My most embarassing moment -->  "+embarrassing_moment);
                            tvThingsToDoBeforeDie.setText("Things I want to do before I die -->  "+things_want_to_do_before_die);
                            tvWhatBoresMe.setText("What bores me most -->  "+what_bores_me_most);
                            tvMcrazyAbout.setText("I'm crazy about -->  "+m_crazy_about);
                            tvMyBiggestStrength.setText("My biggest Strength -->  "+my_biggest_strength);
                            tvThingsIHate.setText("Things I hate -->  "+things_i_hate);
                            tvWhenMHappy.setText("When I'm happy -->  "+when_m_happy);
                            tvWhenMSad.setText("When I'm sad -->  "+when_m_sad);
                            tvWhenMMad.setText("When I'm mad -->  "+when_m_mad);
                            tvWorstHabit.setText("My worst habit -->  "+my_worst_habit);
                            tvBestThingAbtMe.setText("Best thing about me is -->  "+best_thing_abt_me);
                            tvFeelPowerfullWhen.setText("I feel powerful when -->  "+feel_powerful_when);
                            tvBiggestAchievement.setText("My biggest achievement -->  "+biggest_achievement);
                            tvMyTeddyKnows.setText("My teddy knows -->  "+my_teddy_knows);
                            tvFb.setText("Facebook id -->  "+fb);
                            tvAddress.setText("Address -->  "+address);
                            tvPhoneNumber.setText("Phone number -->  "+phone_number);
                            tvWebsite.setText("My website -->  "+website);
                            tvTwitter.setText("Twitter id -->  "+twitter);
                            tvInstagram.setText("instagram id -->  "+instagram);
                            tvHpyMomentWidU.setText("Happy moments with you -->  "+hpy_moment_wid_u);
                            tvSadMomentWidU.setText("Sad moments with you -->  "+sad_moment_wid_u);
                            tvGoodThingsAbtU.setText("Good things about you -->  "+good_things_about_u);
                            tvBadThingsAbtU.setText("Bad things about you -->  "+bad_things_about_u);
                            tvFriendshipToMe.setText("Friendship to me is like -->  "+friendship_to_me_is);
                            tvFavColor.setText("Favourite color -->  "+fav_color);
                            tvFavCelebrities.setText("Favourite celebrities -->  "+fav_celebrities);
                            tvFavRoleModel.setText("Favourite Role model -->  "+fav_role_model);
                            tvFavTvShow.setText("Favourite Tv show -->  "+fav_tv_show);
                            tvFavMusicBand.setText("Favourite Music band -->  "+fav_music_band);
                            tvFavFood.setText("Favourite food -->  "+fav_food);
                            tvFavSport.setText("Favourite sport -->  "+fav_sport);

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

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .cacheOnDisk(false)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

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

        tvWhenMHappy1 = (TextView) findViewById(R.id.tvWhenMHappy1);
        tvWhenMSad1 = (TextView) findViewById(R.id.tvWhenMSad1);
        tvFb1 = (TextView) findViewById(R.id.tvFb1);
        tvAddress1 = (TextView) findViewById(R.id.tvAddress1);
        tvPhoneNumber1 = (TextView) findViewById(R.id.tvPhoneNumber1);
        tvWebsite1 = (TextView) findViewById(R.id.tvWebsite1);
        tvTwitter1 = (TextView) findViewById(R.id.tvTwitter1);
        tvInstagram1 = (TextView) findViewById(R.id.tvInstagram1);
        tvFavColor1 = (TextView) findViewById(R.id.tvFavColor1);
        tvFavCelebrities1 = (TextView) findViewById(R.id.tvFavCelebrities1);
        tvFavRoleModel1 = (TextView) findViewById(R.id.tvFavRoleModel1);
        tvFavTvShow1 = (TextView) findViewById(R.id.tvFavTvShow1);
        tvFavMusicBand1 = (TextView) findViewById(R.id.tvFavMusicBand1);
        tvFavFood1 = (TextView) findViewById(R.id.tvFavFood1);
        tvFavSport1 = (TextView) findViewById(R.id.tvFavSport1);

        utils = new Utils();

        utils.setFont(Slams.this,tvWhenMHappy1);
        utils.setFont(Slams.this,tvWhenMSad1);
        utils.setFont(Slams.this,tvFb1);
        utils.setFont(Slams.this,tvAddress1);
        utils.setFont(Slams.this,tvPhoneNumber1);
        utils.setFont(Slams.this,tvWebsite1);
        utils.setFont(Slams.this,tvTwitter1);
        utils.setFont(Slams.this,tvInstagram1);
        utils.setFont(Slams.this,tvFavColor1);
        utils.setFont(Slams.this,tvFavCelebrities1);
        utils.setFont(Slams.this,tvFavRoleModel1);
        utils.setFont(Slams.this,tvFavTvShow1);
        utils.setFont(Slams.this,tvFavMusicBand1);
        utils.setFont(Slams.this,tvFavFood1);
        utils.setFont(Slams.this,tvFavSport1);

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
