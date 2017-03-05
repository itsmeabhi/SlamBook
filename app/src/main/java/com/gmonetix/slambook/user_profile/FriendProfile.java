package com.gmonetix.slambook.user_profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gmonetix.slambook.R;
import com.gmonetix.slambook.WriteSlam;
import com.gmonetix.slambook.helper.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class FriendProfile extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton home;
    private Toolbar toolbar;
    private ImageView profileImage, genderImage;
    private TextView tvname, tvemail, tvdescription,tvusername,tvdob, tv01;
    private LinearLayout llWriteSlam;
    private ProgressBar progressBar;
    private static final String NAME_INTENT = "name";
    private static final String EMAIL_INTENT = "email";
    private static final String PHOTO_INTENT = "photo";
    private static final String USERNAME_INTENT = "username";
    private static final String USERNAME_INTENT1 = "username1";
    private static final String DESCRIPTION_INTENT = "description";
    private static final String DOB_INTENT = "dob";
    private static final String GENDER_INTENT = "gender";

    String name,email,photo_url,username,description,dob,gender;

    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        name = getIntent().getExtras().getString(NAME_INTENT);
        email = getIntent().getExtras().getString(EMAIL_INTENT);
        photo_url = getIntent().getExtras().getString(PHOTO_INTENT);
        username = getIntent().getExtras().getString(USERNAME_INTENT);
        description = getIntent().getExtras().getString(DESCRIPTION_INTENT);
        dob = getIntent().getExtras().getString(DOB_INTENT);
        gender = getIntent().getExtras().getString(GENDER_INTENT);

        llWriteSlam.setOnClickListener(this);
        home.setOnClickListener(this);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .cacheOnDisk(false)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

        tvname.setText("Name : " + name);
        tvemail.setText("Email : " + email);
        tvdescription.setText("About me : " + description);
        tvusername.setText("Username : " + username);
        tvdob.setText("Date of birth : " + dob);

        if (gender.equals("MALE")) {
            genderImage.setImageDrawable(getResources().getDrawable(R.drawable.boy1));
        } else if (gender.equals("FEMALE")){
            genderImage.setImageDrawable(getResources().getDrawable(R.drawable.face4));
        }

        ImageLoader.getInstance().displayImage(photo_url, profileImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                profileImage.setImageResource(R.drawable.profile);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

    }

    private void init() {
        profileImage = (ImageView) findViewById(R.id.iv_friend_profile_image);
        genderImage = (ImageView) findViewById(R.id.iv_friend_profile_gender);

        tvname = (TextView) findViewById(R.id.tv_friend_profile_name);
        tvemail = (TextView) findViewById(R.id.tv_friend_profile_email);
        tvdescription = (TextView) findViewById(R.id.tv_friend_profile_description);
        tvusername = (TextView) findViewById(R.id.tv_friend_profile_username);
        tvdob = (TextView) findViewById(R.id.tv_friend_profile_dob);
        tv01 = (TextView) findViewById(R.id.tv_friend_profile_write_slam);

        llWriteSlam = (LinearLayout) findViewById(R.id.ll_friend_profile_write_slam);
        home = (FloatingActionButton) findViewById(R.id.btn_home);
        progressBar = (ProgressBar) findViewById(R.id.friend_profile_progress_bar);

        utils = new Utils();
        utils.setFont(FriendProfile.this,tvname);
        utils.setFont(FriendProfile.this,tvemail);
        utils.setFont(FriendProfile.this,tvdescription);
        utils.setFont(FriendProfile.this,tvusername);
        utils.setFont(FriendProfile.this,tvdob);
        utils.setFont(FriendProfile.this,tv01);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_friend_profile_write_slam:
                Intent intent = new Intent(FriendProfile.this,WriteSlam.class);
                intent.putExtra(USERNAME_INTENT1,username);
                startActivity(intent);
                break;

            case R.id.btn_home:
                startActivity(new Intent(FriendProfile.this,UserHome.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
