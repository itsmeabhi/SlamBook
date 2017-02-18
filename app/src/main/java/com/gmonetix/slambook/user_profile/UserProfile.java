package com.gmonetix.slambook.user_profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmonetix.slambook.R;
import com.gmonetix.slambook.helper.Utils;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tvUserName, tv21, tvName, tvEmail, tvPhoneNumber, tvDob, tvGender, tvDescription;
    private LinearLayout editProfile;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UserProfile.this.setTitle("");

        init();

        editProfile.setOnClickListener(this);




    }

    private void init() {
        tv21 = (TextView) findViewById(R.id.tv21);
        tvName = (TextView) findViewById(R.id.tv_user_profile_name);
        tvEmail = (TextView) findViewById(R.id.tv_user_profile_email);
        tvPhoneNumber = (TextView) findViewById(R.id.tv_user_profile_phone_number);
        tvDob = (TextView) findViewById(R.id.tv_user_profile_dob);
        tvGender = (TextView) findViewById(R.id.tv_user_profile_gender);
        tvDescription = (TextView) findViewById(R.id.tv_user_profile_description);
        tvUserName = (TextView) findViewById(R.id.tv_user_profile_username);

        editProfile = (LinearLayout) findViewById(R.id.edit_user_profile);

        utils = new Utils();
        utils.setFont(UserProfile.this,tv21);
        utils.setFont(UserProfile.this,tvName);
        utils.setFont(UserProfile.this,tvEmail);
        utils.setFont(UserProfile.this,tvPhoneNumber);
        utils.setFont(UserProfile.this,tvDob);
        utils.setFont(UserProfile.this,tvGender);
        utils.setFont(UserProfile.this,tvDescription);
        utils.setFont(UserProfile.this,tvUserName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.edit_user_profile:
                break;

        }
    }
}
