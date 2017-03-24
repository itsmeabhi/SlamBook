package com.gmonetix.slambook.user_profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.gmonetix.slambook.R;
import com.gmonetix.slambook.helper.Const;
import com.gmonetix.slambook.helper.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tvUserName, tv21, tvName, tvEmail, tvPhoneNumber, tvDob, tvGender, tvDescription;
    private String name, email, phoneNumber, dob, gender, description,image;
    private LinearLayout editProfile;
    private ImageView profileImage, genderImage;
    private ProgressBar progressBar;
    private FloatingActionButton home;


    private Utils utils;

    private final static String INTENT_NAME = "name";
    private final static String INTENT_EMAIL = "email";
    private final static String INTENT_PHONENUMBER = "phone_number";
    private final static String INTENT_DOB = "dob";
    private final static String INTENT_GENDER = "gender";
    private final static String INTENT_DESCRIPTION = "description";
    private final static String INTENT_IMAGE = "image";

    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new Utils();
        utils.setThemeOnApp(UserProfile.this,utils.getTheme(UserProfile.this));
        setContentView(R.layout.activity_user_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UserProfile.this.setTitle("");

        init();
        tvUserName.setText(utils.getUserName(UserProfile.this));
        editProfile.setOnClickListener(this);
        home.setOnClickListener(this);

        try{
            JSONArray jsonArray = new JSONArray(utils.readUserData(UserProfile.this, utils.getUserName(UserProfile.this)));
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            name = jsonObject.getString(Const.USER_ACCOUNT_DATA_NAME);
            email = jsonObject.getString(Const.USER_ACCOUNT_DATA_EMAIL);
            phoneNumber = jsonObject.getString(Const.USER_ACCOUNT_DATA_PHONE_NUMBER);
            dob = jsonObject.getString(Const.USER_ACCOUNT_DATA_DOB);
            description = jsonObject.getString(Const.USER_ACCOUNT_DATA_DESCRIPTION);
            image = jsonObject.getString(Const.USER_ACCOUNT_DATA_IMAGE);
            gender = jsonObject.getString(Const.USER_ACCOUNT_DATA_GENDER);
        } catch (JSONException e) {
            // if data is not saved here do string request for user data
            e.printStackTrace();
        }

        tvName.setText("Name - "+name);
        tvEmail.setText("Email - "+email);
        tvPhoneNumber.setText("Phone Number - "+phoneNumber);
        tvDob.setText("Date Of Birth - "+dob);
        tvDescription.setText("About Me - "+description);
        if (gender.equals("MALE"))
            genderImage.setImageDrawable(getResources().getDrawable(R.drawable.male));
        else genderImage.setImageDrawable(getResources().getDrawable(R.drawable.female));

            ImageLoader.getInstance().displayImage(image, profileImage, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {
                    profileImage.setImageDrawable(getResources().getDrawable(R.drawable.profile));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    progressBar.setVisibility(View.GONE);
                    imageBitmap = bitmap;
                }

                @Override
                public void onLoadingCancelled(String s, View view) {

                }
            });
            if (image.equals(""))
            {
                profileImage.setImageDrawable(getResources().getDrawable(R.drawable.profile));
                progressBar.setVisibility(View.GONE);
            }

    }
    private void init() {
        utils.getUilInstance(UserProfile.this);

        tv21 = (TextView) findViewById(R.id.tv21);
        tvName = (TextView) findViewById(R.id.tv_user_profile_name);
        tvEmail = (TextView) findViewById(R.id.tv_user_profile_email);
        tvPhoneNumber = (TextView) findViewById(R.id.tv_user_profile_phone_number);
        tvDob = (TextView) findViewById(R.id.tv_user_profile_dob);
        tvGender = (TextView) findViewById(R.id.tv_user_profile_gender);
        tvDescription = (TextView) findViewById(R.id.tv_user_profile_description);
        tvUserName = (TextView) findViewById(R.id.tv_user_profile_username);
        editProfile = (LinearLayout) findViewById(R.id.edit_user_profile);
        profileImage = (ImageView) findViewById(R.id.iv_user_profile_image1);
        genderImage = (ImageView) findViewById(R.id.user_profile_gender_image);
        progressBar = (ProgressBar) findViewById(R.id.user_profile_image_progress_bar);
        home = (FloatingActionButton) findViewById(R.id.btn_home);

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
                Intent intent1 = new Intent(UserProfile.this,EditProfile.class);
                intent1.putExtra(INTENT_NAME,name);
                intent1.putExtra(INTENT_DESCRIPTION,description);
                intent1.putExtra(INTENT_DOB,dob);
                intent1.putExtra(INTENT_GENDER,gender);
                intent1.putExtra(INTENT_EMAIL,email);
                intent1.putExtra(INTENT_PHONENUMBER,phoneNumber);
                intent1.putExtra(INTENT_IMAGE,image);
                startActivity(intent1);
                break;

            case R.id.btn_home:
                Intent intent2 = new Intent(UserProfile.this,UserHome.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;

        }
    }
}
