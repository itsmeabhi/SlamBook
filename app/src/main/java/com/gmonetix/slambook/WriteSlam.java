package com.gmonetix.slambook;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmonetix.slambook.helper.Const;
import com.gmonetix.slambook.helper.Utils;
import com.gmonetix.slambook.user_profile.UserHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WriteSlam extends AppCompatActivity implements View.OnClickListener {

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
    private FloatingActionButton submit, home;
    private LinearLayout dob;
    
    private Utils utils;
    private String NickName = "",Hobbies = "",OnFamousNameChange = "",Aim = "",LoveWearing = "",ZodiacSign = "",
            HangoutPlace = "",TreatForBirthday = "",WeekendActivity = "",MemorableMoment = "",EmbarassingMoment = "",ThingsToDoBeforeDie = "",WhatBoresMe = "",
            McrazyAbout = "",MyBiggestStrength = "",ThingsIHate = "",WhenMHappy = "",WhenMSad = "",WhenMMad = "",WorstHabit = "",BestThingAbtMe = "",FeelPowerfullWhen = "",
            BiggestAchievement = "",MyTeddyKnows = "",Fb = "",Address = "",PhoneNumber = "",Website = "",Twitter = "",Instagram = "",HpyMomentWidU = "",SadMomentWidU = "",
            GoodThingsAbtU = "",BadThingsAbtU = "",FriendshipToMe = "",FavColor = "",FavCelebrities = "",FavRoleModel = "",FavTvShow = "",FavMusicBand = "",FavFood = "",FavSport = "";
    private String toUserName = "",Name = "",Dob="";

    private static final String USERNAME_INTENT1 = "username1";
    private static final int DATE_PICKER_DIALOG_ID = 0;
    private int year_x,month_x,day_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new Utils();
        utils.setThemeOnApp(WriteSlam.this,utils.getTheme(WriteSlam.this));
        setContentView(R.layout.activity_write_slam);

        init();

        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR)-10;
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);

        if (getIntent().hasExtra(USERNAME_INTENT1)) {
            toUserName = getIntent().getExtras().getString(USERNAME_INTENT1);
        }
        submit.setOnClickListener(this);
        home.setOnClickListener(this);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATE_PICKER_DIALOG_ID) {
            return new DatePickerDialog(this, dPickerListener, year_x, month_x, day_x);
        }
        return  null;
    }

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;
            Dob = "Date of birth : " + String.valueOf(day_x) + "/" + String.valueOf(month_x) + "/" + String.valueOf(year_x);
            etDob1.setText(Dob);
        }
    };

    private void init() {
        
        submit = (FloatingActionButton) findViewById(R.id.btn_send);
        home = (FloatingActionButton) findViewById(R.id.btn_home);

        dob = (LinearLayout) findViewById(R.id.ll_write_slam_dob);


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

        utils.setFont(WriteSlam.this,etNickName);
        utils.setFont(WriteSlam.this,etHobbies);
        utils.setFont(WriteSlam.this,etOnFamousNameChange);
        utils.setFont(WriteSlam.this,etAim);
        utils.setFont(WriteSlam.this,etLoveWearing);
        utils.setFont(WriteSlam.this,etZodiacSign);
        utils.setFont(WriteSlam.this,etHangoutPlace);
        utils.setFont(WriteSlam.this,etTreatForBirthday);
        utils.setFont(WriteSlam.this,etWeekendActivity);
        utils.setFont(WriteSlam.this,etMemorableMoment);
        utils.setFont(WriteSlam.this,etEmbarassingMoment);
        utils.setFont(WriteSlam.this,etThingsToDoBeforeDie);
        utils.setFont(WriteSlam.this,etWhatBoresMe);
        utils.setFont(WriteSlam.this,etMcrazyAbout);
        utils.setFont(WriteSlam.this,etMyBiggestStrength);
        utils.setFont(WriteSlam.this,etThingsIHate);
        utils.setFont(WriteSlam.this,etWhenMHappy);
        utils.setFont(WriteSlam.this,etWhenMSad);
        utils.setFont(WriteSlam.this,etWhenMMad);
        utils.setFont(WriteSlam.this,etWorstHabit);
        utils.setFont(WriteSlam.this,etBestThingAbtMe);
        utils.setFont(WriteSlam.this,etFeelPowerfullWhen);
        utils.setFont(WriteSlam.this,etBiggestAchievement);
        utils.setFont(WriteSlam.this,etMyTeddyKnows);
        utils.setFont(WriteSlam.this,etFb);
        utils.setFont(WriteSlam.this,etAddress);
        utils.setFont(WriteSlam.this,etPhoneNumber);
        utils.setFont(WriteSlam.this,etWebsite);
        utils.setFont(WriteSlam.this,etTwitter);
        utils.setFont(WriteSlam.this,etInstagram);
        utils.setFont(WriteSlam.this,etHpyMomentWidU);
        utils.setFont(WriteSlam.this,etSadMomentWidU);
        utils.setFont(WriteSlam.this,etGoodThingsAbtU);
        utils.setFont(WriteSlam.this,etBadThingsAbtU);
        utils.setFont(WriteSlam.this,etFriendshipToMe);
        utils.setFont(WriteSlam.this,etFavColor);
        utils.setFont(WriteSlam.this,etFavCelebrities);
        utils.setFont(WriteSlam.this,etFavRoleModel);
        utils.setFont(WriteSlam.this,etFavTvShow);
        utils.setFont(WriteSlam.this,etFavMusicBand);
        utils.setFont(WriteSlam.this,etFavFood);
        utils.setFont(WriteSlam.this,etFavSport);

        utils.setFont(WriteSlam.this,etNickName1);
        utils.setFont(WriteSlam.this,etDob1);
        utils.setFont(WriteSlam.this,etHobbies1);
        utils.setFont(WriteSlam.this,etOnFamousNameChange1);
        utils.setFont(WriteSlam.this,etAim1);
        utils.setFont(WriteSlam.this,etLoveWearing1);
        utils.setFont(WriteSlam.this,etZodiacSign1);
        utils.setFont(WriteSlam.this,etHangoutPlace1);
        utils.setFont(WriteSlam.this,etTreatForBirthday1);
        utils.setFont(WriteSlam.this,etWeekendActivity1);
        utils.setFont(WriteSlam.this,etMemorableMoment1);
        utils.setFont(WriteSlam.this,etEmbarassingMoment1);
        utils.setFont(WriteSlam.this,etThingsToDoBeforeDie1);
        utils.setFont(WriteSlam.this,etWhatBoresMe1);
        utils.setFont(WriteSlam.this,etMcrazyAbout1);
        utils.setFont(WriteSlam.this,etMyBiggestStrength1);
        utils.setFont(WriteSlam.this,etThingsIHate1);
        utils.setFont(WriteSlam.this,etWhenMHappy1);
        utils.setFont(WriteSlam.this,etWhenMSad1);
        utils.setFont(WriteSlam.this,etWhenMMad1);
        utils.setFont(WriteSlam.this,etWorstHabit1);
        utils.setFont(WriteSlam.this,etBestThingAbtMe1);
        utils.setFont(WriteSlam.this,etFeelPowerfullWhen1);
        utils.setFont(WriteSlam.this,etBiggestAchievement1);
        utils.setFont(WriteSlam.this,etMyTeddyKnows1);
        utils.setFont(WriteSlam.this,etFb1);
        utils.setFont(WriteSlam.this,etAddress1);
        utils.setFont(WriteSlam.this,etPhoneNumber1);
        utils.setFont(WriteSlam.this,etWebsite1);
        utils.setFont(WriteSlam.this,etTwitter1);
        utils.setFont(WriteSlam.this,etInstagram1);
        utils.setFont(WriteSlam.this,etHpyMomentWidU1);
        utils.setFont(WriteSlam.this,etSadMomentWidU1);
        utils.setFont(WriteSlam.this,etGoodThingsAbtU1);
        utils.setFont(WriteSlam.this,etBadThingsAbtU1);
        utils.setFont(WriteSlam.this,etFriendshipToMe1);
        utils.setFont(WriteSlam.this,etFavColor1);
        utils.setFont(WriteSlam.this,etFavCelebrities1);
        utils.setFont(WriteSlam.this,etFavRoleModel1);
        utils.setFont(WriteSlam.this,etFavTvShow1);
        utils.setFont(WriteSlam.this,etFavMusicBand1);
        utils.setFont(WriteSlam.this,etFavFood1);
        utils.setFont(WriteSlam.this,etFavSport1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:

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

                RequestQueue queue = Volley.newRequestQueue(WriteSlam.this);
                StringRequest stringrqst = new StringRequest(Request.Method.POST, Const.write_slam,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                try {
                                    JSONArray jsonArray = new JSONArray(s);
                                    JSONObject object = jsonArray.getJSONObject(0);
                                    String code = object.getString("code");
                                    if (code.equals("success")) {
                                        Toast.makeText(WriteSlam.this,object.getString("message"),Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(WriteSlam.this, UserHome.class));
                                        finish();
                                    } else if (code.equals("unsuccess_already_present")){
                                        Toast.makeText(WriteSlam.this,object.getString("message"),Toast.LENGTH_LONG).show();
                                    } else if (code.equals("unsuccess_not_sent")){
                                        Toast.makeText(WriteSlam.this,object.getString("message"),Toast.LENGTH_LONG).show();
                                    } else Toast.makeText(WriteSlam.this,"Unknown Error !",Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(WriteSlam.this,"Unknown Error ! Try again later !",Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        volleyError.printStackTrace();
                        Toast.makeText(getApplicationContext(),volleyError + "Error",Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("to_user_name",toUserName);
                        params.put("from_user_name",utils.getUserName(WriteSlam.this));
                        params.put("sent_on",DateFormat.getDateTimeInstance().format(new Date()));
                        params.put("name",Name);
                        params.put("nick_name",NickName);
                        params.put("dob",Dob);
                        params.put("hobbies",Hobbies);
                        params.put("on_famous_name_change_to",OnFamousNameChange);
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
                queue.add(stringrqst);
                stringrqst.setRetryPolicy(new RetryPolicy() {
                    @Override
                    public int getCurrentTimeout() {
                        return 50000;
                    }

                    @Override
                    public int getCurrentRetryCount() {
                        return 50000;
                    }

                    @Override
                    public void retry(VolleyError error) throws VolleyError {

                    }
                });
                break;

            case R.id.btn_home:
                Intent intent = new Intent(WriteSlam.this,UserHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
