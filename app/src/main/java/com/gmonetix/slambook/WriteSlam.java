package com.gmonetix.slambook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class WriteSlam extends AppCompatActivity {

    private EditText etNickName,etDob,etHobbies,etOnFamousNameChange,etAim,etLoveWearing,etZodiacSign,
            etHangoutPlace,etTreatForBirthday,etWeekendActivity,etMemorableMoment,etEmbarassingMoment,etThingsToDoBeforeDie,etWhatBoresMe,
            etMcrazyAbout,etMyBiggestStrength,etThingsIHate,etWhenMHappy,etWhenMSad,etWhenMMad,etWorstHabit,etBestThingAbtMe,etFeelPowerfullWhen,
            etBiggestAchievement,etMyTeddyKnows,etFb,etAddress,etPhoneNumber,etWebsite,etTwitter,etInstagram,etHpyMomentWidU,etSadMomentWidU,
            etGoodThingsAbtU,etBadThingsAbtU,etFriendshipToMe,etFavColor,etFavCelebrities,etFavRoleModel,etFavetShow,etFavMusicBand,etFavFood,etFavSport;
    private TextView tvNickName,tvDob,tvHobbies,tvOnFamousNameChange,tvAim,tvLoveWearing,tvZodiacSign,
            tvHangoutPlace,tvTreatForBirthday,tvWeekendActivity,tvMemorableMoment,tvEmbarassingMoment,tvThingsToDoBeforeDie,tvWhatBoresMe,
            tvMcrazyAbout,tvMyBiggestStrength,tvThingsIHate,tvWhenMHappy,tvWhenMSad,tvWhenMMad,tvWorstHabit,tvBestThingAbtMe,tvFeelPowerfullWhen,
            tvBiggestAchievement,tvMyTeddyKnows,tvFb,tvAddress,tvPhoneNumber,tvWebsite,tvTwitter,tvInstagram,tvHpyMomentWidU,tvSadMomentWidU,
            tvGoodThingsAbtU,tvBadThingsAbtU,tvFriendshipToMe,tvFavColor,tvFavCelebrities,tvFavRoleModel,tvFavTvShow,tvFavMusicBand,tvFavFood,tvFavSport;
    private LinearLayout submit;
    
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_slam);

        init();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                                    } else {
                                        Toast.makeText(WriteSlam.this,object.getString("message"),Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
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

                       // params.put("name",name);

                        return params;
                    }
                };
                queue.add(stringrqst);
            }
        });

    }

    private void init() {

        utils = new Utils();
        utils.setFont(WriteSlam.this,tvNickName);
        utils.setFont(WriteSlam.this,tvDob);
        utils.setFont(WriteSlam.this,tvHobbies);
        utils.setFont(WriteSlam.this,tvOnFamousNameChange);
        utils.setFont(WriteSlam.this,tvAim);
        utils.setFont(WriteSlam.this,tvLoveWearing);
        utils.setFont(WriteSlam.this,tvZodiacSign);
        utils.setFont(WriteSlam.this,tvHangoutPlace);
        utils.setFont(WriteSlam.this,tvTreatForBirthday);
        utils.setFont(WriteSlam.this,tvWeekendActivity);
        utils.setFont(WriteSlam.this,tvMemorableMoment);
        utils.setFont(WriteSlam.this,tvEmbarassingMoment);
        utils.setFont(WriteSlam.this,tvThingsToDoBeforeDie);
        utils.setFont(WriteSlam.this,tvWhatBoresMe);
        utils.setFont(WriteSlam.this,tvMcrazyAbout);
        utils.setFont(WriteSlam.this,tvMyBiggestStrength);
        utils.setFont(WriteSlam.this,tvThingsIHate);
        utils.setFont(WriteSlam.this,tvWhenMHappy);
        utils.setFont(WriteSlam.this,tvWhenMSad);
        utils.setFont(WriteSlam.this,tvWhenMMad);
        utils.setFont(WriteSlam.this,tvWorstHabit);
        utils.setFont(WriteSlam.this,tvBestThingAbtMe);
        utils.setFont(WriteSlam.this,tvFeelPowerfullWhen);
        utils.setFont(WriteSlam.this,tvBiggestAchievement);
        utils.setFont(WriteSlam.this,tvMyTeddyKnows);
        utils.setFont(WriteSlam.this,tvFb);
        utils.setFont(WriteSlam.this,tvAddress);
        utils.setFont(WriteSlam.this,tvPhoneNumber);
        utils.setFont(WriteSlam.this,tvWebsite);
        utils.setFont(WriteSlam.this,tvTwitter);
        utils.setFont(WriteSlam.this,tvInstagram);
        utils.setFont(WriteSlam.this,tvHpyMomentWidU);
        utils.setFont(WriteSlam.this,tvSadMomentWidU);
        utils.setFont(WriteSlam.this,tvGoodThingsAbtU);
        utils.setFont(WriteSlam.this,tvBadThingsAbtU);
        utils.setFont(WriteSlam.this,tvFriendshipToMe);
        utils.setFont(WriteSlam.this,tvFavColor);
        utils.setFont(WriteSlam.this,tvFavCelebrities);
        utils.setFont(WriteSlam.this,tvFavRoleModel);
        utils.setFont(WriteSlam.this,tvFavTvShow);
        utils.setFont(WriteSlam.this,tvFavMusicBand);
        utils.setFont(WriteSlam.this,tvFavFood);
        utils.setFont(WriteSlam.this,tvFavSport);
    }
}
