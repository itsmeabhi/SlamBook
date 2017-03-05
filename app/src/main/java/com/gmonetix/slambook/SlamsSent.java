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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SlamsSent extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton editSlam, home;
    private ImageView profilePic;
    private ProgressBar progressBar;
    private TextView tvFromUsername,tvName,tvNickName,tvDob,tvHobbies,tvOnFamousNameChange,tvAim,tvLoveWearing,tvZodiacSign,
            tvHangoutPlace,tvTreatForBirthday,tvWeekendActivity,tvMemorableMoment,tvEmbarassingMoment,tvThingsToDoBeforeDie,tvWhatBoresMe,
            tvMcrazyAbout,tvMyBiggestStrength,tvThingsIHate,tvWhenMHappy,tvWhenMSad,tvWhenMMad,tvWorstHabit,tvBestThingAbtMe,tvFeelPowerfullWhen,
            tvBiggestAchievement,tvMyTeddyKnows,tvFb,tvAddress,tvPhoneNumber,tvWebsite,tvTwitter,tvInstagram,tvHpyMomentWidU,tvSadMomentWidU,
            tvGoodThingsAbtU,tvBadThingsAbtU,tvFriendshipToMe,tvFavColor,tvFavCelebrities,tvFavRoleModel,tvFavTvShow,tvFavMusicBand,tvFavFood,tvFavSport,

    tvWhenMHappy1,tvWhenMSad1,tvFb1,tvAddress1,tvPhoneNumber1,tvWebsite1,tvTwitter1,tvInstagram1,tvFavColor1,tvFavCelebrities1,tvFavRoleModel1,tvFavTvShow1,tvFavMusicBand1,tvFavFood1,tvFavSport1;

    private final static String INTENT_USERNAME = "username";
    private final static String INTENT_NAME = "name";
    private final static String INTENT_IMAGE = "image";
    private final static String INTENT_GENDER = "gender";
    private final static String INTENT_SENT_ON = "sent_on";
    private final static String INTENT_UPDATED_ON = "updated_on";

    private Utils utils;
    String username="", name="", image="", gender="", sentOn="", updatedOn="";
    private String nick_name, dob, hobbies, on_famous_name_change_to, mood, aim,love_wearing, zodiac_sign, hangout_place,
            treat_for_birthday, weekend_activity, memorable_moment, embarrassing_moment, things_want_to_do_before_die, what_bores_me_most,
            m_crazy_about, my_biggest_strength, things_i_hate, when_m_happy, when_m_sad, when_m_mad, my_worst_habit, best_thing_abt_me,
            feel_powerful_when, biggest_achievement, my_teddy_knows, fb, address, phone_number, website, twitter, instagram, hpy_moment_wid_u,
            sad_moment_wid_u, good_things_about_u, bad_things_about_u, friendship_to_me_is, fav_color, fav_celebrities, fav_role_model, fav_tv_show,
            fav_music_band, fav_food, fav_sport;

    boolean success = false;
    private String jsonData = "";
    private final static String INTENT_JSON_DATA = "json_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slams);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SlamsSent.this.setTitle("");

        init();
        
        home.setOnClickListener(this);
        editSlam.setOnClickListener(this);

        if (getIntent().hasExtra(INTENT_USERNAME)) {
            username = getIntent().getExtras().getString(INTENT_USERNAME);
            name = getIntent().getExtras().getString(INTENT_NAME);
            image = getIntent().getExtras().getString(INTENT_IMAGE);
            gender = getIntent().getExtras().getString(INTENT_GENDER);
            sentOn = getIntent().getExtras().getString(INTENT_SENT_ON);
            updatedOn = getIntent().getExtras().getString(INTENT_UPDATED_ON);
        }

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

        StringRequest rqst = new StringRequest(Request.Method.POST, Const.slams_sent_detials,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONArray jsonArray = new JSONArray(s);
                            JSONObject object = jsonArray.getJSONObject(0);
                            if (object.getString("code").equals("success_present")) {
                                success = true;
                                jsonData = s;
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
                                Toast.makeText(SlamsSent.this,"Error Occurred ! Try again later !",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            tvFromUsername.setText(username);
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

                params.put("to_user_name",username);
                params.put("from_user_name",utils.getUserName(SlamsSent.this));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(SlamsSent.this);
        queue.add(rqst);

    }

    private void init() {
        utils = new Utils();
        
        editSlam = (FloatingActionButton) findViewById(R.id.btn_edit_slam);
        home = (FloatingActionButton) findViewById(R.id.btn_home);

        utils.getUilInstance(SlamsSent.this);

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

        utils.setFont(SlamsSent.this,tvWhenMHappy1);
        utils.setFont(SlamsSent.this,tvWhenMSad1);
        utils.setFont(SlamsSent.this,tvFb1);
        utils.setFont(SlamsSent.this,tvAddress1);
        utils.setFont(SlamsSent.this,tvPhoneNumber1);
        utils.setFont(SlamsSent.this,tvWebsite1);
        utils.setFont(SlamsSent.this,tvTwitter1);
        utils.setFont(SlamsSent.this,tvInstagram1);
        utils.setFont(SlamsSent.this,tvFavColor1);
        utils.setFont(SlamsSent.this,tvFavCelebrities1);
        utils.setFont(SlamsSent.this,tvFavRoleModel1);
        utils.setFont(SlamsSent.this,tvFavTvShow1);
        utils.setFont(SlamsSent.this,tvFavMusicBand1);
        utils.setFont(SlamsSent.this,tvFavFood1);
        utils.setFont(SlamsSent.this,tvFavSport1);

        utils.setFont(SlamsSent.this,tvFromUsername);
        utils.setFont(SlamsSent.this,tvName);
        utils.setFont(SlamsSent.this,tvNickName);
        utils.setFont(SlamsSent.this,tvDob);
        utils.setFont(SlamsSent.this,tvHobbies);
        utils.setFont(SlamsSent.this,tvOnFamousNameChange);
        utils.setFont(SlamsSent.this,tvAim);
        utils.setFont(SlamsSent.this,tvLoveWearing);
        utils.setFont(SlamsSent.this,tvZodiacSign);
        utils.setFont(SlamsSent.this,tvHangoutPlace);
        utils.setFont(SlamsSent.this,tvTreatForBirthday);
        utils.setFont(SlamsSent.this,tvWeekendActivity);
        utils.setFont(SlamsSent.this,tvMemorableMoment);
        utils.setFont(SlamsSent.this,tvEmbarassingMoment);
        utils.setFont(SlamsSent.this,tvThingsToDoBeforeDie);
        utils.setFont(SlamsSent.this,tvWhatBoresMe);
        utils.setFont(SlamsSent.this,tvMcrazyAbout);
        utils.setFont(SlamsSent.this,tvMyBiggestStrength);
        utils.setFont(SlamsSent.this,tvThingsIHate);
        utils.setFont(SlamsSent.this,tvWhenMHappy);
        utils.setFont(SlamsSent.this,tvWhenMSad);
        utils.setFont(SlamsSent.this,tvWhenMMad);
        utils.setFont(SlamsSent.this,tvWorstHabit);
        utils.setFont(SlamsSent.this,tvBestThingAbtMe);
        utils.setFont(SlamsSent.this,tvFeelPowerfullWhen);
        utils.setFont(SlamsSent.this,tvBiggestAchievement);
        utils.setFont(SlamsSent.this,tvMyTeddyKnows);
        utils.setFont(SlamsSent.this,tvFb);
        utils.setFont(SlamsSent.this,tvAddress);
        utils.setFont(SlamsSent.this,tvPhoneNumber);
        utils.setFont(SlamsSent.this,tvWebsite);
        utils.setFont(SlamsSent.this,tvTwitter);
        utils.setFont(SlamsSent.this,tvInstagram);
        utils.setFont(SlamsSent.this,tvHpyMomentWidU);
        utils.setFont(SlamsSent.this,tvSadMomentWidU);
        utils.setFont(SlamsSent.this,tvGoodThingsAbtU);
        utils.setFont(SlamsSent.this,tvBadThingsAbtU);
        utils.setFont(SlamsSent.this,tvFriendshipToMe);
        utils.setFont(SlamsSent.this,tvFavColor);
        utils.setFont(SlamsSent.this,tvFavCelebrities);
        utils.setFont(SlamsSent.this,tvFavRoleModel);
        utils.setFont(SlamsSent.this,tvFavTvShow);
        utils.setFont(SlamsSent.this,tvFavMusicBand);
        utils.setFont(SlamsSent.this,tvFavFood);
        utils.setFont(SlamsSent.this,tvFavSport);

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit_slam:
                if (success) {
                    Intent intent = new Intent(SlamsSent.this,UpdateSentSlam.class);
                    intent.putExtra(INTENT_JSON_DATA,jsonData);
                    intent.putExtra(INTENT_USERNAME,username);
                    intent.putExtra(INTENT_NAME,name);
                    intent.putExtra(INTENT_IMAGE,image);
                    intent.putExtra(INTENT_GENDER,gender);
                    intent.putExtra(INTENT_SENT_ON,sentOn);
                    intent.putExtra(INTENT_UPDATED_ON,updatedOn);
                    startActivity(intent);
                }
                break;
            
            case R.id.btn_home:
                startActivity(new Intent(SlamsSent.this, UserHome.class));
                finish();
                break;
        }
    }
}
