package com.gmonetix.slambook;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.gmonetix.slambook.user_login.UserLoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class PasswordChangeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tv1,tv2,tv3,tv4;
    private EditText etOldPassword, etNewPassword, etNewPasswordAgain;
    private LinearLayout Change;

    private Utils utils;
    private String oldPassword, newPassword, newPasswordAgain;

    private static final String INTENT_USERNAME = "intent_username";
    private static final String INTENT_PASSWORD = "intent_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PasswordChangeActivity.this.setTitle("Change Password");

        init();

        Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPassword = etOldPassword.getText().toString().trim();
                newPassword = etNewPassword.getText().toString().trim();
                newPasswordAgain = etNewPasswordAgain.getText().toString().trim();

                if (oldPassword.equals("") || newPassword.equals("") || newPasswordAgain.equals("")) {
                    Toast.makeText(PasswordChangeActivity.this,"Please fill up the text boxes !", Toast.LENGTH_SHORT).show();
                } else {
                    if (newPassword.equals(newPasswordAgain)) {
                        if (oldPassword.equals(utils.getPassword(PasswordChangeActivity.this))) {
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.change_password,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {
                                            JSONArray jsonArray = null;
                                            try {
                                                jsonArray = new JSONArray(s);
                                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                                String code = jsonObject.getString("code");
                                                if (code.equals("success")) {
                                                    Toast.makeText(PasswordChangeActivity.this,"Password changed successfully", Toast.LENGTH_LONG).show();
                                                    utils.setPassword(PasswordChangeActivity.this,newPassword);
                                                    Intent intent = new Intent(PasswordChangeActivity.this, UserLoginActivity.class);
                                                    intent.putExtra(INTENT_USERNAME,utils.getUserName(PasswordChangeActivity.this));
                                                    intent.putExtra(INTENT_PASSWORD,newPassword);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    Toast.makeText(PasswordChangeActivity.this,"Error Occured! Try again Later !", Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    volleyError.printStackTrace();
                                    Toast.makeText(PasswordChangeActivity.this,"Error Occured! Try again Later !", Toast.LENGTH_SHORT).show();
                                }
                            }){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {

                                    Map<String,String> params = new Hashtable<String, String>();

                                    params.put(Const.USER_ACCOUNT_DATA_USER_NAME,utils.getUserName(PasswordChangeActivity.this));
                                    params.put(Const.USER_ACCOUNT_DATA_PASSWORD,utils.getPassword(PasswordChangeActivity.this));
                                    params.put("new_password",newPassword);
                                    return params;
                                };
                            };
                            RequestQueue queue = Volley.newRequestQueue(PasswordChangeActivity.this);
                            queue.add(stringRequest);
                        } else {
                            Toast.makeText(PasswordChangeActivity.this,"Old password does not match !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PasswordChangeActivity.this,"New Password are not same !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void init() {
        tv1 = (TextView) findViewById(R.id.tv_changePassword1);
        tv2 = (TextView) findViewById(R.id.tv_changePassword2);
        tv3 = (TextView) findViewById(R.id.tv_changePassword3);
        tv4 = (TextView) findViewById(R.id.tv_changePassword4);
        etNewPassword = (EditText) findViewById(R.id.et_settings_new_password);
        etNewPasswordAgain = (EditText) findViewById(R.id.et_settings_new_password_again);
        etOldPassword = (EditText) findViewById(R.id.et_settings_old_password);
        Change = (LinearLayout) findViewById(R.id.ll_change_password);

        utils = new Utils();
        utils.setFont(PasswordChangeActivity.this,tv1);
        utils.setFont(PasswordChangeActivity.this,tv2);
        utils.setFont(PasswordChangeActivity.this,tv3);
        utils.setFont(PasswordChangeActivity.this,tv4);
        utils.setFont(PasswordChangeActivity.this,etNewPassword);
        utils.setFont(PasswordChangeActivity.this,etNewPasswordAgain);
        utils.setFont(PasswordChangeActivity.this,etOldPassword);
    }
}
