package com.gmonetix.slambook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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
import com.gmonetix.slambook.helper.Const;
import com.gmonetix.slambook.helper.Utils;
import com.gmonetix.slambook.user_login.UserLoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgotPassword extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout getPasswordBtn;
    private TextView tv1, tv2;
    private EditText etEmail;
    private FloatingActionButton home;
    private ProgressBar progressBar;

    private Utils utils;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ForgotPassword.this.setTitle("");

        init();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ForgotPassword.this,UserLoginActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });

        getPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString().trim();
                if (email.equals("")){
                    Toast.makeText(ForgotPassword.this,"Please enter your registered email !",Toast.LENGTH_LONG).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    RequestQueue requestQueue = Volley.newRequestQueue(ForgotPassword.this);
                    StringRequest rqst = new StringRequest(Request.Method.POST,Const.forgot_password,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String s) {
                                    progressBar.setVisibility(View.GONE);
                                    try {
                                        JSONArray array = new JSONArray(s);
                                        JSONObject object = array.getJSONObject(0);
                                        if (object.getString("code").equals("success")) {
                                            Toast.makeText(ForgotPassword.this,object.getString("message"),Toast.LENGTH_LONG).show();
                                            Intent intent2 = new Intent(ForgotPassword.this,UserLoginActivity.class);
                                            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent2);
                                        } else if (object.getString("code").equals("failed_unkown")) {
                                            Toast.makeText(ForgotPassword.this,object.getString("message"),Toast.LENGTH_LONG).show();
                                        } else if (object.getString("code").equals("failed")) {
                                            Toast.makeText(ForgotPassword.this,object.getString("message"),Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ForgotPassword.this,"Unkown error occured ! Try again later !",Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(ForgotPassword.this,"Unkown error occured ! Try again later !",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            volleyError.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ForgotPassword.this,"Unkown error occured ! Try again later !",Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put(Const.USER_ACCOUNT_DATA_EMAIL,email);
                            return  params;
                        }
                    };
                    requestQueue.add(rqst);
                }
            }
        });
    }

    private void init() {
        utils = new Utils();
        getPasswordBtn = (LinearLayout) findViewById(R.id.forgot_password_get_password_ll);
        tv1 = (TextView) findViewById(R.id.forgot_password_tv1);
        tv2 = (TextView) findViewById(R.id.forgot_password_tv2);
        etEmail = (EditText) findViewById(R.id.forgot_password_email_et);
        home = (FloatingActionButton) findViewById(R.id.btn_home);
        progressBar = (ProgressBar) findViewById(R.id.forgot_password_progress_bar);

        utils.setFont(ForgotPassword.this,tv1);
        utils.setFont(ForgotPassword.this,tv2);
        utils.setFont(ForgotPassword.this,etEmail);
    }

}
