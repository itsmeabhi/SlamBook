package com.gmonetix.slambook;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import com.gmonetix.slambook.user_profile.EditProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tv_settings_change_password, tv_settings_delete_account;
    private LinearLayout llChangePassword, llDeleteAccount;

    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SettingsActivity.this.setTitle("Settings");

        init();

        llChangePassword.setOnClickListener(this);
        llDeleteAccount.setOnClickListener(this);

    }

    private void init() {
        tv_settings_change_password = (TextView) findViewById(R.id.tv_settings_change_password);
        tv_settings_delete_account = (TextView) findViewById(R.id.tv_settings_delete_account);
        llChangePassword = (LinearLayout) findViewById(R.id.ll_settings_change_password);
        llDeleteAccount = (LinearLayout) findViewById(R.id.ll_settings_delete_account);

        utils = new Utils();
        utils.setFont(SettingsActivity.this,tv_settings_change_password);
        utils.setFont(SettingsActivity.this,tv_settings_delete_account);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_settings_change_password:
                startActivity(new Intent(SettingsActivity.this,PasswordChangeActivity.class));
                break;

            case R.id.ll_settings_delete_account:
                final AlertDialog.Builder deleteDialogue = new AlertDialog.Builder(SettingsActivity.this);
                deleteDialogue.setTitle("Confirm delete !");
                deleteDialogue.setMessage("Are you sure you want to delete the account?");
                deleteDialogue.setIcon(R.drawable.ic_warning);
                deleteDialogue.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.delete_account,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String s) {
                                        dialog.cancel();
                                        try {
                                            JSONArray jsonArray = new JSONArray(s);
                                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                                            String code = jsonObject.getString("code");
                                            if (code.equals("success")) {
                                                Toast.makeText(SettingsActivity.this,"Account deleted successfully !", Toast.LENGTH_LONG).show();
                                                utils.setPassword(SettingsActivity.this,"");
                                                utils.setUserName(SettingsActivity.this,"");
                                                Intent intent = new Intent(SettingsActivity.this, UserLoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(SettingsActivity.this,"Error Occured! Try again Later !", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {
                                        dialog.cancel();
                                        volleyError.printStackTrace();
                                        Toast.makeText(SettingsActivity.this, "Some error occurred ! Try again later", Toast.LENGTH_LONG).show();
                                    }
                                }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String,String> params = new Hashtable<String, String>();

                                params.put(Const.USER_ACCOUNT_DATA_USER_NAME, utils.getUserName(SettingsActivity.this));
                                params.put(Const.USER_ACCOUNT_DATA_PASSWORD, utils.getPassword(SettingsActivity.this));
                                return params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(SettingsActivity.this);
                        requestQueue.add(stringRequest);
                    }
                });
                deleteDialogue.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                deleteDialogue.show();
                break;
        }

    }
}
