package com.gmonetix.slambook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UpdateSentSlam extends AppCompatActivity implements View.OnClickListener {

    private ImageView profilePic;
    private ProgressBar progressBarProfilePic;
    private TextView tvUsername, tvName;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private FloatingActionButton update, home;
    private EditText etNickName,etHobbies,etOnFamousNameChange,etAim,etLoveWearing,etZodiacSign,
            etHangoutPlace,etTreatForBirthday,etWeekendActivity,etMemorableMoment,etEmbarassingMoment,etThingsToDoBeforeDie,etWhatBoresMe,
            etMcrazyAbout,etMyBiggestStrength,etThingsIHate,etWhenMHappy,etWhenMSad,etWhenMMad,etWorstHabit,etBestThingAbtMe,etFeelPowerfullWhen,
            etBiggestAchievement,etMyTeddyKnows,etFb,etAddress,etPhoneNumber,etWebsite,etTwitter,etInstagram,etHpyMomentWidU,etSadMomentWidU,
            etGoodThingsAbtU,etBadThingsAbtU,etFriendshipToMe,etFavColor,etFavCelebrities,etFavRoleModel,etFavTvShow,etFavMusicBand,etFavFood,etFavSport;
    private TextView etNickName1,etDob1,etHobbies1,etOnFamousNameChange1,etAim1,etLoveWearing1,etZodiacSign1,
            etHangoutPlace1,etTreatForBirthday1,etWeekendActivity1,etMemorableMoment1,etEmbarassingMoment1,etThingsToDoBeforeDie1,etWhatBoresMe1,
            etMcrazyAbout1,etMyBiggestStrength1,etThingsIHate1,etWhenMHappy1,etWhenMSad1,etWhenMMad1,etWorstHabit1,etBestThingAbtMe1,etFeelPowerfullWhen1,
            etBiggestAchievement1,etMyTeddyKnows1,etFb1,etAddress1,etPhoneNumber1,etWebsite1,etTwitter1,etInstagram1,etHpyMomentWidU1,etSadMomentWidU1,
            etGoodThingsAbtU1,etBadThingsAbtU1,etFriendshipToMe1,etFavColor1,etFavCelebrities1,etFavRoleModel1,etFavTvShow1,etFavMusicBand1,etFavFood1,etFavSport1;

    String username="", name="", image="", gender="", sentOn="", updatedOn="";
    private String nick_name, dob, hobbies, on_famous_name_change_to, mood, aim,love_wearing, zodiac_sign, hangout_place,
            treat_for_birthday, weekend_activity, memorable_moment, embarrassing_moment, things_want_to_do_before_die, what_bores_me_most,
            m_crazy_about, my_biggest_strength, things_i_hate, when_m_happy, when_m_sad, when_m_mad, my_worst_habit, best_thing_abt_me,
            feel_powerful_when, biggest_achievement, my_teddy_knows, fb, address, phone_number, website, twitter, instagram, hpy_moment_wid_u,
            sad_moment_wid_u, good_things_about_u, bad_things_about_u, friendship_to_me_is, fav_color, fav_celebrities, fav_role_model, fav_tv_show,
            fav_music_band, fav_food, fav_sport;
    private String NickName = "",Hobbies = "",OnFamousNameChange = "",Aim = "",LoveWearing = "",ZodiacSign = "",
            HangoutPlace = "",TreatForBirthday = "",WeekendActivity = "",MemorableMoment = "",EmbarassingMoment = "",ThingsToDoBeforeDie = "",WhatBoresMe = "",
            McrazyAbout = "",MyBiggestStrength = "",ThingsIHate = "",WhenMHappy = "",WhenMSad = "",WhenMMad = "",WorstHabit = "",BestThingAbtMe = "",FeelPowerfullWhen = "",
            BiggestAchievement = "",MyTeddyKnows = "",Fb = "",Address = "",PhoneNumber = "",Website = "",Twitter = "",Instagram = "",HpyMomentWidU = "",SadMomentWidU = "",
            GoodThingsAbtU = "",BadThingsAbtU = "",FriendshipToMe = "",FavColor = "",FavCelebrities = "",FavRoleModel = "",FavTvShow = "",FavMusicBand = "",FavFood = "",FavSport = "";
    private Utils utils;

    private String jsonData;
    private final static String INTENT_JSON_DATA = "json_data";
    private final static String INTENT_USERNAME = "username";
    private final static String INTENT_NAME = "name";
    private final static String INTENT_IMAGE = "image";
    private final static String INTENT_GENDER = "gender";
    private final static String INTENT_SENT_ON = "sent_on";
    private final static String INTENT_UPDATED_ON = "updated_on";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sent_slam);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UpdateSentSlam.this.setTitle("");

        init();
        if (getIntent().hasExtra(INTENT_JSON_DATA)) {
            jsonData = getIntent().getExtras().getString(INTENT_JSON_DATA);
            username = getIntent().getExtras().getString(INTENT_USERNAME);
            name = getIntent().getExtras().getString(INTENT_NAME);
            image = getIntent().getExtras().getString(INTENT_IMAGE);
            gender = getIntent().getExtras().getString(INTENT_GENDER);
            sentOn = getIntent().getExtras().getString(INTENT_SENT_ON);
            updatedOn = getIntent().getExtras().getString(INTENT_UPDATED_ON);
        }
        new JsonParser().execute(jsonData);

        tvUsername.setText(username);
        tvName.setText(name);
        ImageLoader.getInstance().displayImage(image, profilePic, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                progressBarProfilePic.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                profilePic.setImageResource(R.drawable.profile);
                progressBarProfilePic.setVisibility(View.GONE);

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                progressBarProfilePic.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {}
        });

        update.setOnClickListener(this);
        home.setOnClickListener(this);
        
    }

    private void init() {
        utils = new Utils();
        
        update = (FloatingActionButton) findViewById(R.id.btn_update);
        home = (FloatingActionButton) findViewById(R.id.btn_home);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_update_sent_slam);
        progressBar.setVisibility(View.VISIBLE);

        progressBarProfilePic = (ProgressBar) findViewById(R.id.iv_profile_image_read_slam_progressBar);
        profilePic = (ImageView) findViewById(R.id.iv_profile_image_read_slam_final);
        tvUsername = (TextView) findViewById(R.id.tvFromUsername);
        tvName = (TextView) findViewById(R.id.tvName);

        etNickName = (EditText) findViewById(R.id.etNickName);
        etHobbies = (EditText) findViewById(R.id.etHobbies);
        etOnFamousNameChange = (EditText) findViewById(R.id.etOnFamousNameChange);
        etAim = (EditText) findViewById(R.id.etAim);
        etLoveWearing = (EditText) findViewById(R.id.etLoveWearing);
        etZodiacSign = (EditText) findViewById(R.id.etZodiacSign);
        etHangoutPlace = (EditText) findViewById(R.id.etHangoutPlace);
        etTreatForBirthday = (EditText) findViewById(R.id.etTreatForBirthday);
        etWeekendActivity = (EditText) findViewById(R.id.etWeekendActivity);
        etMemorableMoment = (EditText) findViewById(R.id.etMemorableMoment);
        etEmbarassingMoment = (EditText) findViewById(R.id.etEmbarassingMoment);
        etThingsToDoBeforeDie = (EditText) findViewById(R.id.etThingsToDoBeforeDie);
        etWhatBoresMe = (EditText) findViewById(R.id.etWhatBoresMe);
        etMcrazyAbout = (EditText) findViewById(R.id.etMcrazyAbout);
        etMyBiggestStrength = (EditText) findViewById(R.id.etMyBiggestStrength);
        etThingsIHate = (EditText) findViewById(R.id.etThingsIHate);
        etWhenMHappy = (EditText) findViewById(R.id.etWhenMHappy);
        etWhenMSad = (EditText) findViewById(R.id.etWhenMSad);
        etWhenMMad = (EditText) findViewById(R.id.etWhenMMad);
        etWorstHabit = (EditText) findViewById(R.id.etWorstHabit);
        etBestThingAbtMe = (EditText) findViewById(R.id.etBestThingAbtMe);
        etFeelPowerfullWhen = (EditText) findViewById(R.id.etFeelPowerfullWhen);
        etBiggestAchievement = (EditText) findViewById(R.id.etBiggestAchievement);
        etMyTeddyKnows = (EditText) findViewById(R.id.etMyTeddyKnows);
        etFb = (EditText) findViewById(R.id.etFb);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etWebsite = (EditText) findViewById(R.id.etWebsite);
        etTwitter = (EditText) findViewById(R.id.etTwitter);
        etInstagram = (EditText) findViewById(R.id.etInstagram);
        etHpyMomentWidU = (EditText) findViewById(R.id.etHpyMomentWidU);
        etSadMomentWidU = (EditText) findViewById(R.id.etSadMomentWidU);
        etGoodThingsAbtU = (EditText) findViewById(R.id.etGoodThingsAbtU);
        etBadThingsAbtU = (EditText) findViewById(R.id.etBadThingsAbtU);
        etFriendshipToMe = (EditText) findViewById(R.id.etFriendshipToMe);
        etFavColor = (EditText) findViewById(R.id.etFavColor);
        etFavCelebrities = (EditText) findViewById(R.id.etFavCelebrities);
        etFavRoleModel = (EditText) findViewById(R.id.etFavRoleModel);
        etFavTvShow = (EditText) findViewById(R.id.etFavTvShow);
        etFavMusicBand = (EditText) findViewById(R.id.etFavMusicBand);
        etFavFood = (EditText) findViewById(R.id.etFavFood);
        etFavSport = (EditText) findViewById(R.id.etFavSport);

        etNickName1 = (TextView) findViewById(R.id.etNickName1);
        etDob1 = (TextView) findViewById(R.id.etDob1);
        etHobbies1 = (TextView) findViewById(R.id.etHobbies1);
        etOnFamousNameChange1 = (TextView) findViewById(R.id.etOnFamousNameChange1);
        etAim1 = (TextView) findViewById(R.id.etAim1);
        etLoveWearing1 = (TextView) findViewById(R.id.etLoveWearing1);
        etZodiacSign1 = (TextView) findViewById(R.id.etZodiacSign1);
        etHangoutPlace1 = (TextView) findViewById(R.id.etHangoutPlace1);
        etTreatForBirthday1 = (TextView) findViewById(R.id.etTreatForBirthday1);
        etWeekendActivity1 = (TextView) findViewById(R.id.etWeekendActivity1);
        etMemorableMoment1 = (TextView) findViewById(R.id.etMemorableMoment1);
        etEmbarassingMoment1 = (TextView) findViewById(R.id.etEmbarassingMoment1);
        etThingsToDoBeforeDie1 = (TextView) findViewById(R.id.etThingsToDoBeforeDie1);
        etWhatBoresMe1 = (TextView) findViewById(R.id.etWhatBoresMe1);
        etMcrazyAbout1 = (TextView) findViewById(R.id.etMcrazyAbout1);
        etMyBiggestStrength1 = (TextView) findViewById(R.id.etMyBiggestStrength1);
        etThingsIHate1 = (TextView) findViewById(R.id.etThingsIHate1);
        etWhenMHappy1 = (TextView) findViewById(R.id.etWhenMHappy1);
        etWhenMSad1 = (TextView) findViewById(R.id.etWhenMSad1);
        etWhenMMad1 = (TextView) findViewById(R.id.etWhenMMad1);
        etWorstHabit1 = (TextView) findViewById(R.id.etWorstHabit1);
        etBestThingAbtMe1 = (TextView) findViewById(R.id.etBestThingAbtMe1);
        etFeelPowerfullWhen1 = (TextView) findViewById(R.id.etFeelPowerfullWhen1);
        etBiggestAchievement1 = (TextView) findViewById(R.id.etBiggestAchievement1);
        etMyTeddyKnows1 = (TextView) findViewById(R.id.etMyTeddyKnows1);
        etFb1 = (TextView) findViewById(R.id.etFb1);
        etAddress1 = (TextView) findViewById(R.id.etAddress1);
        etPhoneNumber1 = (TextView) findViewById(R.id.etPhoneNumber1);
        etWebsite1 = (TextView) findViewById(R.id.etWebsite1);
        etTwitter1 = (TextView) findViewById(R.id.etTwitter1);
        etInstagram1 = (TextView) findViewById(R.id.etInstagram1);
        etHpyMomentWidU1 = (TextView) findViewById(R.id.etHpyMomentWidU1);
        etSadMomentWidU1 = (TextView) findViewById(R.id.etSadMomentWidU1);
        etGoodThingsAbtU1 = (TextView) findViewById(R.id.etGoodThingsAbtU1);
        etBadThingsAbtU1 = (TextView) findViewById(R.id.etBadThingsAbtU1);
        etFriendshipToMe1 = (TextView) findViewById(R.id.etFriendshipToMe1);
        etFavColor1 = (TextView) findViewById(R.id.etFavColor1);
        etFavCelebrities1 = (TextView) findViewById(R.id.etFavCelebrities1);
        etFavRoleModel1 = (TextView) findViewById(R.id.etFavRoleModel1);
        etFavTvShow1 = (TextView) findViewById(R.id.etFavTvShow1);
        etFavMusicBand1 = (TextView) findViewById(R.id.etFavMusicBand1);
        etFavFood1 = (TextView) findViewById(R.id.etFavFood1);
        etFavSport1 = (TextView) findViewById(R.id.etFavSport1);

        utils.setFont(UpdateSentSlam.this,etNickName);
        utils.setFont(UpdateSentSlam.this,etHobbies);
        utils.setFont(UpdateSentSlam.this,etOnFamousNameChange);
        utils.setFont(UpdateSentSlam.this,etAim);
        utils.setFont(UpdateSentSlam.this,etLoveWearing);
        utils.setFont(UpdateSentSlam.this,etZodiacSign);
        utils.setFont(UpdateSentSlam.this,etHangoutPlace);
        utils.setFont(UpdateSentSlam.this,etTreatForBirthday);
        utils.setFont(UpdateSentSlam.this,etWeekendActivity);
        utils.setFont(UpdateSentSlam.this,etMemorableMoment);
        utils.setFont(UpdateSentSlam.this,etEmbarassingMoment);
        utils.setFont(UpdateSentSlam.this,etThingsToDoBeforeDie);
        utils.setFont(UpdateSentSlam.this,etWhatBoresMe);
        utils.setFont(UpdateSentSlam.this,etMcrazyAbout);
        utils.setFont(UpdateSentSlam.this,etMyBiggestStrength);
        utils.setFont(UpdateSentSlam.this,etThingsIHate);
        utils.setFont(UpdateSentSlam.this,etWhenMHappy);
        utils.setFont(UpdateSentSlam.this,etWhenMSad);
        utils.setFont(UpdateSentSlam.this,etWhenMMad);
        utils.setFont(UpdateSentSlam.this,etWorstHabit);
        utils.setFont(UpdateSentSlam.this,etBestThingAbtMe);
        utils.setFont(UpdateSentSlam.this,etFeelPowerfullWhen);
        utils.setFont(UpdateSentSlam.this,etBiggestAchievement);
        utils.setFont(UpdateSentSlam.this,etMyTeddyKnows);
        utils.setFont(UpdateSentSlam.this,etFb);
        utils.setFont(UpdateSentSlam.this,etAddress);
        utils.setFont(UpdateSentSlam.this,etPhoneNumber);
        utils.setFont(UpdateSentSlam.this,etWebsite);
        utils.setFont(UpdateSentSlam.this,etTwitter);
        utils.setFont(UpdateSentSlam.this,etInstagram);
        utils.setFont(UpdateSentSlam.this,etHpyMomentWidU);
        utils.setFont(UpdateSentSlam.this,etSadMomentWidU);
        utils.setFont(UpdateSentSlam.this,etGoodThingsAbtU);
        utils.setFont(UpdateSentSlam.this,etBadThingsAbtU);
        utils.setFont(UpdateSentSlam.this,etFriendshipToMe);
        utils.setFont(UpdateSentSlam.this,etFavColor);
        utils.setFont(UpdateSentSlam.this,etFavCelebrities);
        utils.setFont(UpdateSentSlam.this,etFavRoleModel);
        utils.setFont(UpdateSentSlam.this,etFavTvShow);
        utils.setFont(UpdateSentSlam.this,etFavMusicBand);
        utils.setFont(UpdateSentSlam.this,etFavFood);
        utils.setFont(UpdateSentSlam.this,etFavSport);

        utils.setFont(UpdateSentSlam.this,etNickName1);
        utils.setFont(UpdateSentSlam.this,etDob1);
        utils.setFont(UpdateSentSlam.this,etHobbies1);
        utils.setFont(UpdateSentSlam.this,etOnFamousNameChange1);
        utils.setFont(UpdateSentSlam.this,etAim1);
        utils.setFont(UpdateSentSlam.this,etLoveWearing1);
        utils.setFont(UpdateSentSlam.this,etZodiacSign1);
        utils.setFont(UpdateSentSlam.this,etHangoutPlace1);
        utils.setFont(UpdateSentSlam.this,etTreatForBirthday1);
        utils.setFont(UpdateSentSlam.this,etWeekendActivity1);
        utils.setFont(UpdateSentSlam.this,etMemorableMoment1);
        utils.setFont(UpdateSentSlam.this,etEmbarassingMoment1);
        utils.setFont(UpdateSentSlam.this,etThingsToDoBeforeDie1);
        utils.setFont(UpdateSentSlam.this,etWhatBoresMe1);
        utils.setFont(UpdateSentSlam.this,etMcrazyAbout1);
        utils.setFont(UpdateSentSlam.this,etMyBiggestStrength1);
        utils.setFont(UpdateSentSlam.this,etThingsIHate1);
        utils.setFont(UpdateSentSlam.this,etWhenMHappy1);
        utils.setFont(UpdateSentSlam.this,etWhenMSad1);
        utils.setFont(UpdateSentSlam.this,etWhenMMad1);
        utils.setFont(UpdateSentSlam.this,etWorstHabit1);
        utils.setFont(UpdateSentSlam.this,etBestThingAbtMe1);
        utils.setFont(UpdateSentSlam.this,etFeelPowerfullWhen1);
        utils.setFont(UpdateSentSlam.this,etBiggestAchievement1);
        utils.setFont(UpdateSentSlam.this,etMyTeddyKnows1);
        utils.setFont(UpdateSentSlam.this,etFb1);
        utils.setFont(UpdateSentSlam.this,etAddress1);
        utils.setFont(UpdateSentSlam.this,etPhoneNumber1);
        utils.setFont(UpdateSentSlam.this,etWebsite1);
        utils.setFont(UpdateSentSlam.this,etTwitter1);
        utils.setFont(UpdateSentSlam.this,etInstagram1);
        utils.setFont(UpdateSentSlam.this,etHpyMomentWidU1);
        utils.setFont(UpdateSentSlam.this,etSadMomentWidU1);
        utils.setFont(UpdateSentSlam.this,etGoodThingsAbtU1);
        utils.setFont(UpdateSentSlam.this,etBadThingsAbtU1);
        utils.setFont(UpdateSentSlam.this,etFriendshipToMe1);
        utils.setFont(UpdateSentSlam.this,etFavColor1);
        utils.setFont(UpdateSentSlam.this,etFavCelebrities1);
        utils.setFont(UpdateSentSlam.this,etFavRoleModel1);
        utils.setFont(UpdateSentSlam.this,etFavTvShow1);
        utils.setFont(UpdateSentSlam.this,etFavMusicBand1);
        utils.setFont(UpdateSentSlam.this,etFavFood1);
        utils.setFont(UpdateSentSlam.this,etFavSport1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:

                NickName = etNickName.getText().toString().trim();
                Hobbies = etHobbies.getText().toString().trim();
                OnFamousNameChange = etOnFamousNameChange.getText().toString().trim();
                Aim = etAim.getText().toString().trim();
                LoveWearing = etLoveWearing.getText().toString().trim();
                ZodiacSign = etZodiacSign.getText().toString().trim();
                HangoutPlace = etHangoutPlace.getText().toString().trim();
                TreatForBirthday = etTreatForBirthday.getText().toString().trim();
                WeekendActivity = etWeekendActivity.getText().toString().trim();
                MemorableMoment = etMemorableMoment.getText().toString().trim();
                EmbarassingMoment = etEmbarassingMoment.getText().toString().trim();
                ThingsToDoBeforeDie = etThingsToDoBeforeDie.getText().toString().trim();
                WhatBoresMe = etWhatBoresMe.getText().toString().trim();
                McrazyAbout = etMcrazyAbout.getText().toString().trim();
                MyBiggestStrength = etMyBiggestStrength.getText().toString().trim();
                ThingsIHate = etThingsIHate.getText().toString().trim();
                WhenMHappy = etWhenMHappy.getText().toString().trim();
                WhenMSad = etWhenMSad.getText().toString().trim();
                WhenMMad = etWhenMMad.getText().toString().trim();
                WorstHabit = etWorstHabit.getText().toString().trim();
                BestThingAbtMe = etBestThingAbtMe.getText().toString().trim();
                FeelPowerfullWhen = etFeelPowerfullWhen.getText().toString().trim();
                BiggestAchievement = etBiggestAchievement.getText().toString().trim();
                MyTeddyKnows = etMyTeddyKnows.getText().toString().trim();
                Fb = etFb.getText().toString().trim();
                Address = etAddress.getText().toString().trim();
                PhoneNumber = etPhoneNumber.getText().toString().trim();
                Website = etWebsite.getText().toString().trim();
                Twitter = etTwitter.getText().toString().trim();
                Instagram = etInstagram.getText().toString().trim();
                HpyMomentWidU = etHpyMomentWidU.getText().toString().trim();
                SadMomentWidU = etSadMomentWidU.getText().toString().trim();
                GoodThingsAbtU = etGoodThingsAbtU.getText().toString().trim();
                BadThingsAbtU = etBadThingsAbtU.getText().toString().trim();
                FriendshipToMe = etFriendshipToMe.getText().toString().trim();
                FavColor = etFavColor.getText().toString().trim();
                FavCelebrities = etFavCelebrities.getText().toString().trim();
                FavRoleModel = etFavRoleModel.getText().toString().trim();
                FavTvShow = etFavTvShow.getText().toString().trim();
                FavMusicBand = etFavMusicBand.getText().toString().trim();
                FavFood = etFavFood.getText().toString().trim();
                FavSport = etFavSport.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.update_slam_sent,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                try {
                                    JSONArray jsonArray = new JSONArray(s);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    if (jsonObject.getString("code").equals("success_present")) {
                                        Toast.makeText(UpdateSentSlam.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                    } else if (jsonObject.getString("code").equals("unsuccess")) {
                                        Toast.makeText(UpdateSentSlam.this,"Error occurred ! " +jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                        }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(UpdateSentSlam.this,"Error occurred ! ",Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        volleyError.printStackTrace();
                        Toast.makeText(UpdateSentSlam.this,"Error occurred ! ",Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("to_user_name",username);
                        params.put("from_user_name",utils.getUserName(UpdateSentSlam.this));
                        params.put("updated_on", DateFormat.getDateTimeInstance().format(new Date()));

                        params.put("name",name);
                        params.put("nick_name",NickName);
                  //      params.put("dob",Dob);
                        params.put("hobbies",Hobbies);
                        params.put("on_famous_name_change_to",OnFamousNameChange);
                  //      params.put("mood",Mood);
                        params.put("aim",Aim);
                        params.put("love_wearing",LoveWearing);
                        params.put("zodiac_sign",ZodiacSign);
                        params.put("hangout_place",HangoutPlace);
                        params.put("treat_for_birthday",TreatForBirthday);
                        params.put("weekend_activity",WeekendActivity);
                        params.put("memorable_moment",MemorableMoment);
                        params.put("embarrassing_moment",EmbarassingMoment);
                        params.put("things_want_to_do_before_die",ThingsToDoBeforeDie);
                        params.put("what_bores_me_most",WhatBoresMe);
                        params.put("m_crazy_about",McrazyAbout);
                        params.put("my_biggest_strength",MyBiggestStrength);
                        params.put("things_i_hate",ThingsIHate);
                        params.put("when_m_happy",WhenMHappy);
                        params.put("when_m_sad",WhenMMad);
                        params.put("when_m_mad",WhenMMad);
                        params.put("my_worst_habit",WorstHabit);
                        params.put("best_thing_abt_me",BestThingAbtMe);
                        params.put("feel_powerful_when",FeelPowerfullWhen);
                        params.put("biggest_achievement",BiggestAchievement);
                        params.put("my_teddy_knows",MyTeddyKnows);
                        params.put("fb",Fb);
                        params.put("address",Address);
                        params.put("phone_number",PhoneNumber);
                        params.put("website",Website);
                        params.put("twitter",Twitter);
                        params.put("instagram",Instagram);
                        params.put("hpy_moment_wid_u",HpyMomentWidU);
                        params.put("sad_moment_wid_u",SadMomentWidU);
                        params.put("good_things_about_u",GoodThingsAbtU);
                        params.put("bad_things_about_u",BadThingsAbtU);
                        params.put("friendship_to_me_is",FriendshipToMe);
                        params.put("fav_color",FavColor);
                        params.put("fav_celebrities",FavCelebrities);
                        params.put("fav_role_model",FavRoleModel);
                        params.put("fav_tv_show",FavTvShow);
                        params.put("fav_music_band",FavMusicBand);
                        params.put("fav_food",FavFood);
                        params.put("fav_sport",FavSport);

                        return checkParams(params);
                    }

                    private Map<String, String> checkParams(Map<String, String> map){
                        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                            if(pairs.getValue()==null){
                                map.put(pairs.getKey(), "");
                            }
                        }
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(UpdateSentSlam.this);
                requestQueue.add(stringRequest);
                
                break;
            
            case R.id.btn_home:
                Intent intent = new Intent(UpdateSentSlam.this,UserHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    private class JsonParser extends AsyncTask<String,Void,Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            etNickName.setText(nick_name);
            etHobbies.setText(hobbies);
            etOnFamousNameChange.setText(on_famous_name_change_to);
            etAim.setText(aim);
            etLoveWearing.setText(love_wearing);
            etZodiacSign.setText(zodiac_sign);
            etHangoutPlace.setText(hangout_place);
            etTreatForBirthday.setText(treat_for_birthday);
            etWeekendActivity.setText(weekend_activity);
            etMemorableMoment.setText(memorable_moment);
            etEmbarassingMoment.setText(embarrassing_moment);
            etThingsToDoBeforeDie.setText(things_want_to_do_before_die);
            etWhatBoresMe.setText(what_bores_me_most);
            etMcrazyAbout.setText(m_crazy_about);
            etMyBiggestStrength.setText(my_biggest_strength);
            etThingsIHate.setText(things_i_hate);
            etWhenMHappy.setText(when_m_happy);
            etWhenMSad.setText(when_m_sad);
            etWhenMMad.setText(when_m_mad);
            etWorstHabit.setText(my_worst_habit);
            etBestThingAbtMe.setText(best_thing_abt_me);
            etFeelPowerfullWhen.setText(feel_powerful_when);
            etBiggestAchievement.setText(biggest_achievement);
            etMyTeddyKnows.setText(my_teddy_knows);
            etFb.setText(fb);
            etAddress.setText(address);
            etPhoneNumber.setText(phone_number);
            etWebsite.setText(website);
            etTwitter.setText(twitter);
            etInstagram.setText(instagram);
            etHpyMomentWidU.setText(hpy_moment_wid_u);
            etSadMomentWidU.setText(sad_moment_wid_u);
            etGoodThingsAbtU.setText(good_things_about_u);
            etBadThingsAbtU.setText(bad_things_about_u);
            etFriendshipToMe.setText(friendship_to_me_is);
            etFavColor.setText(fav_color);
            etFavCelebrities.setText(fav_celebrities);
            etFavRoleModel.setText(fav_role_model);
            etFavTvShow.setText(fav_tv_show);
            etFavMusicBand.setText(fav_music_band);
            etFavFood.setText(fav_food);
            etFavSport.setText(fav_sport);

            progressBar.setVisibility(View.GONE);

        }

        @Override
        protected Void doInBackground(String... params) {
            String jsonData = params[0];
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                JSONObject object = jsonArray.getJSONObject(0);
                String code = object.getString("code");
                if (code.equals("success_present")) {
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
                } else {
                    Toast.makeText(UpdateSentSlam.this,"Unknown Error ! Try again later !",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(UpdateSentSlam.this,"Unknown Error ! Try again later !",Toast.LENGTH_LONG).show();
            }
            return null;
        }
    }

}
