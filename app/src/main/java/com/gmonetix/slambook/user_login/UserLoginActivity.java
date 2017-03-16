package com.gmonetix.slambook.user_login;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.gmonetix.slambook.ChangeTheme;
import com.gmonetix.slambook.R;
import com.gmonetix.slambook.helper.Const;
import com.gmonetix.slambook.helper.Utils;
import com.gmonetix.slambook.user_profile.UserHome;
import com.gmonetix.slambook.user_registration.UserRegistrationActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText loginUserName, loginPassword;
    private LinearLayout signIn;
    private TextView signUp, tv1, tv2, sampleTextLogin, tv_login;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    private Utils utils;
    private String username, password;

    private static final String INTENT_USERNAME = "intent_username";
    private static final String INTENT_PASSWORD = "intent_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UserLoginActivity.this.setTitle("");

        initialize();

        if (utils.getLoginStatus(UserLoginActivity.this)) {
            startActivity(new Intent(UserLoginActivity.this,UserHome.class));
            finish();
        }

        if (getIntent().hasExtra(INTENT_USERNAME) && getIntent().hasExtra(INTENT_PASSWORD)) {
            loginUserName.setText(getIntent().getExtras().getString(INTENT_USERNAME));
            loginPassword.setText(getIntent().getExtras().getString(INTENT_PASSWORD));
        }

        ImageView animImage = (ImageView) findViewById(R.id.sample_image_login_screen);
        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.boy2),2000);
        animation.addFrame(getResources().getDrawable(R.drawable.face_hi_girl),2000);
        animation.setOneShot(false);
        animImage.setBackgroundDrawable(animation);
        animImage.setScaleType(ImageView.ScaleType.FIT_XY);
        animation.start();

        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);

    }

    private void initialize() {
        utils = new Utils();

        signIn = (LinearLayout) findViewById(R.id.ll_login);
        loginPassword = (EditText) findViewById(R.id.password_login);
        loginUserName = (EditText) findViewById(R.id.username_login);
        signUp = (TextView) findViewById(R.id.register_from_login_screen);
        sampleTextLogin = (TextView) findViewById(R.id.tvLoginHere);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv_login = (TextView) findViewById(R.id.tv_login);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_login_activity);

        utils.setFont(UserLoginActivity.this,tv1);
        utils.setFont(UserLoginActivity.this,signUp);
        utils.setFont(UserLoginActivity.this,tv2);
        utils.setFont(UserLoginActivity.this,sampleTextLogin);
        utils.setFont(UserLoginActivity.this,loginUserName);
        utils.setFont(UserLoginActivity.this,loginPassword);
        utils.setFont(UserLoginActivity.this,tv_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_login:
                username = loginUserName.getText().toString();
                password = loginPassword.getText().toString();

                if (username.equals("") && password.equals("")) {
                    Toast.makeText(UserLoginActivity.this,"Enter Username and Password !",Toast.LENGTH_SHORT).show();
                } else if (username.equals("") || password.equals("")) {
                    Toast.makeText(UserLoginActivity.this,"Enter Username and Password !",Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.login_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String data) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(data);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");
                                        if (code.equals("login_success")) {
                                            progressBar.setVisibility(View.GONE);
                                            utils.storeUserData(data,username,UserLoginActivity.this);
                                            utils.setUserName(UserLoginActivity.this, username);
                                            utils.setPassword(UserLoginActivity.this, password);
                                            utils.setLoginStatus(UserLoginActivity.this,true);
                                            Toast.makeText(UserLoginActivity.this,"Successfully logged in !",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(UserLoginActivity.this, UserHome.class));
                                            finish();
                                        } else {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(UserLoginActivity.this,"Login Error ! User not found !",Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(UserLoginActivity.this,"Login Error !",Toast.LENGTH_LONG).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    volleyError.printStackTrace();
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(UserLoginActivity.this, "Some error occurred ! Try again later !", Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> params = new HashMap<String, String>();
                            params.put(Const.USER_ACCOUNT_DATA_USER_NAME,username);
                            params.put(Const.USER_ACCOUNT_DATA_PASSWORD,password);
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }

                break;

            case R.id.register_from_login_screen:
                startActivity(new Intent(UserLoginActivity.this, UserRegistrationActivity.class));
                break;
        }
    }
}