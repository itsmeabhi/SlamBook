package com.gmonetix.slambook.user_login;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmonetix.slambook.R;
import com.gmonetix.slambook.helper.Const;
import com.gmonetix.slambook.helper.Utils;
import com.gmonetix.slambook.user_registration.UserRegistrationActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText loginUserName, loginPassword;
    private String username, password;
    private Button signIn;
    private TextView signUp, tv1, tv2, sampleTextLogin;
    private Utils utils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        initialize();

        ImageView imageView = (ImageView) findViewById(R.id.sample_image_login_screen);
        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.boy2),2000);
        animation.addFrame(getResources().getDrawable(R.drawable.face_hi_girl),2000);
        animation.setOneShot(false);
        imageView.setBackgroundDrawable(animation);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        animation.start();

        utils.setFont(UserLoginActivity.this,tv1);
        utils.setFont(UserLoginActivity.this,signUp);
        utils.setFont(UserLoginActivity.this,tv2);
        utils.setFont(UserLoginActivity.this,sampleTextLogin);
        utils.setFont(UserLoginActivity.this,loginUserName);
        utils.setFont(UserLoginActivity.this,loginPassword);

        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);



    }

    private void initialize() {

        signIn = (Button) findViewById(R.id.signin);
        loginPassword = (EditText) findViewById(R.id.password_login);
        loginUserName = (EditText) findViewById(R.id.username_login);
        signUp = (TextView) findViewById(R.id.register_from_login_screen);
        sampleTextLogin = (TextView) findViewById(R.id.tvLoginHere);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        utils = new Utils();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin: //signIn

                username = loginUserName.getText().toString();
                password = loginPassword.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.login_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                try {
                                    JSONArray jsonArray = new JSONArray(s);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String code = jsonObject.getString("code");
                                    Toast.makeText(getApplicationContext(),s + code,Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(UserLoginActivity.this, volleyError.getMessage()+ "Error", Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String, String>();
                        params.put("user_name",username);
                        params.put("password",password);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

                break;
            case R.id.register_from_login_screen: //signUp

                startActivity(new Intent(UserLoginActivity.this, UserRegistrationActivity.class));

                break;
        }
    }
}