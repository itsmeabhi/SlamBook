package com.gmonetix.slambook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class WriteSlam extends AppCompatActivity {

    private EditText et_realName,et_nickName,et_phoneNumber,et_email,et_fbId,et_birthday,et_hobbies,et_aimInLife,et_hpyMomentWithU,
    et_sadMomentWithU,et_hpystMomentOfLife,et_worstMomentOfLife,et_favrteFood,et_favrteMusic,et_placesIWannaVisit,et_favrteColor,
    et_favrteGame,et_roleModel,et_goodAboutMe,et_badAboutMe,et_other;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_slam);

        init();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(WriteSlam.this);
                StringRequest stringrqst = new StringRequest(Request.Method.POST, "fe",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {



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
                   //     params.put("name",name);
                        return params;
                    }
                };
                queue.add(stringrqst);

            }
        });

    }

    private void init() {

        et_realName = (EditText) findViewById(R.id.et_ws_real_name);
        et_nickName = (EditText) findViewById(R.id.et_ws_nick_name);
        et_phoneNumber = (EditText) findViewById(R.id.et_ws_phone_number);
        et_email = (EditText) findViewById(R.id.et_ws_email);
        et_fbId = (EditText) findViewById(R.id.et_ws_fb_id);
        et_birthday = (EditText) findViewById(R.id.et_ws_birthday);
        et_hobbies = (EditText) findViewById(R.id.et_ws_hobbies);
        et_aimInLife = (EditText) findViewById(R.id.et_ws_aim_in_life);
        et_hpyMomentWithU = (EditText) findViewById(R.id.et_ws_hpy_moment_with_me);
        et_sadMomentWithU = (EditText) findViewById(R.id.et_ws_sad_moment_with_me);
        et_hpystMomentOfLife = (EditText) findViewById(R.id.et_ws_happiest_moment_of_life);
        et_worstMomentOfLife = (EditText) findViewById(R.id.et_ws_sad_moment_of_life);
        et_favrteFood = (EditText) findViewById(R.id.wt_ws_favrte_food);
        et_favrteMusic = (EditText) findViewById(R.id.et_ws_fav_music);
        et_placesIWannaVisit = (EditText) findViewById(R.id.et_ws_places_i_wanna_visit);
        et_favrteColor = (EditText) findViewById(R.id.et_ws_favrte_color);
        et_favrteGame = (EditText) findViewById(R.id.et_ws_favrte_game);
        et_roleModel = (EditText) findViewById(R.id.et_ws_role_model);
        et_goodAboutMe = (EditText) findViewById(R.id.et_ws_good_thing_about_me);
        et_badAboutMe = (EditText) findViewById(R.id.et_ws_bad_thing_about_me);
        et_other = (EditText) findViewById(R.id.et_ws_other);
        submit = (Button) findViewById(R.id.submit_slam);

    }
}
