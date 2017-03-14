package com.gmonetix.slambook.user_profile;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText etName, etEmail, etPhoneNumber, etDescription;
    private LinearLayout llDob, llSave;
    private TextView tv01, tv02, tv03, tv04, tv05, tvDob;
    private RadioGroup radioGroup;
    private RadioButton radioMale, radioFemale;
    private ImageView profilePic;
    private FloatingActionButton home;

    private Utils utils;
    private Bitmap bitmap;

    private static final int DATE_PICKER_DIALOG_ID = 11;
    private int PICK_IMAGE_REQUEST = 12;
    private final static String INTENT_NAME = "name";
    private final static String INTENT_EMAIL = "email";
    private final static String INTENT_PHONENUMBER = "phone_number";
    private final static String INTENT_DOB = "dob";
    private final static String INTENT_GENDER = "gender";
    private final static String INTENT_DESCRIPTION = "description";
    private final static String INTENT_IMAGE = "image";

    private String name, email, phoneNumber, dob, gender, description,image;
    private int year_x,month_x,day_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditProfile.this.setTitle("Edit Profile");

        init();
        if (getIntent().hasExtra(INTENT_NAME)) {
            name = getIntent().getExtras().getString(INTENT_NAME);
            email = getIntent().getExtras().getString(INTENT_EMAIL);
            phoneNumber = getIntent().getExtras().getString(INTENT_PHONENUMBER);
            dob = getIntent().getExtras().getString(INTENT_DOB);
            gender = getIntent().getExtras().getString(INTENT_GENDER);
            description = getIntent().getExtras().getString(INTENT_DESCRIPTION);
            image = getIntent().getExtras().getString(INTENT_IMAGE);
        } else {
            name = "";
            email = "";
            phoneNumber = "";
            dob = "";
            gender = "";
            description = "";
            image = "";
        }

        etName.setText(name);
        etEmail.setText(email);
        etPhoneNumber.setText(phoneNumber);
        etDescription.setText(description);
        tvDob.setText(dob);
        if (gender.equals("MALE"))
            radioMale.setChecked(true);
        else radioFemale.setChecked(true);
        String date[] = dob.split("/");
        day_x = Integer.parseInt(date[0]);
        month_x = Integer.parseInt(date[1]);
        year_x = Integer.parseInt(date[2]);

        ImageLoader.getInstance().displayImage(image, profilePic, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {}

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                profilePic.setImageDrawable(getResources().getDrawable(R.drawable.profile));
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {}

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

        if (image.equals(""))
        {
            profilePic.setImageDrawable(getResources().getDrawable(R.drawable.profile));
        }

        llDob.setOnClickListener(this);
        llSave.setOnClickListener(this);
        profilePic.setOnClickListener(this);
        home.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.edit_profile_maleRadioBtn:
                        gender = "MALE";
                        break;
                    case R.id.edit_profile_femaleRadioBtn:
                        gender = "FEMALE";
                        break;
                    default:
                        gender = "MALE";
                        break;
                }
            }
        });
    }

    private void init() {
        utils = new Utils();
        utils.getUilInstance(EditProfile.this);

        etName = (EditText) findViewById(R.id.edit_profile_name);
        etEmail = (EditText) findViewById(R.id.edit_profile_email);
        etPhoneNumber = (EditText) findViewById(R.id.edit_profile_phone_number);
        etDescription = (EditText) findViewById(R.id.edit_profile_description);

        llDob = (LinearLayout) findViewById(R.id.edit_profile_ll_dob);
        llSave = (LinearLayout) findViewById(R.id.edit_profile_ll_save_changes);

        tv01 = (TextView) findViewById(R.id.tv01);
        tv02 = (TextView) findViewById(R.id.tv02);
        tv03 = (TextView) findViewById(R.id.tv03);
        tv04 = (TextView) findViewById(R.id.tv04);
        tv05 = (TextView) findViewById(R.id.tv05);
        tvDob = (TextView) findViewById(R.id.tv_edit_profile_dob);

        radioGroup = (RadioGroup) findViewById(R.id.edit_profile_radio_group_container);
        radioMale = (RadioButton) findViewById(R.id.edit_profile_maleRadioBtn);
        radioFemale = (RadioButton) findViewById(R.id.edit_profile_femaleRadioBtn);

        profilePic = (ImageView) findViewById(R.id.iv_edit_profile_image);

        home = (FloatingActionButton) findViewById(R.id.btn_home);

        utils.setFont(EditProfile.this,tv01);
        utils.setFont(EditProfile.this,tv02);
        utils.setFont(EditProfile.this,tv03);
        utils.setFont(EditProfile.this,tv04);
        utils.setFont(EditProfile.this,tv05);
        utils.setFont(EditProfile.this,tvDob);
        utils.setFont(EditProfile.this,etName);
        utils.setFont(EditProfile.this,etEmail);
        utils.setFont(EditProfile.this,etPhoneNumber);
        utils.setFont(EditProfile.this,etDescription);
        utils.setFont(EditProfile.this,radioMale);
        utils.setFont(EditProfile.this,radioFemale);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATE_PICKER_DIALOG_ID) {
            return new DatePickerDialog(this, dPickerListener, year_x, month_x, day_x);
        }
        return  null;
    }

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;
            tvDob.setText("Date of birth : " + String.valueOf(day_x) + "/" + String.valueOf(month_x) + "/" + String.valueOf(year_x));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_profile_ll_dob:
                showDialog(DATE_PICKER_DIALOG_ID);
                break;

            case R.id.edit_profile_ll_save_changes:
                updateInfo();
                break;

            case R.id.iv_edit_profile_image:
                utils.showFileChooser(EditProfile.this,PICK_IMAGE_REQUEST);
                break;

            case R.id.btn_home:
                Intent intent = new Intent(EditProfile.this,UserHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

        }
    }

    private void updateInfo(){

        final ProgressDialog loading = ProgressDialog.show(this,"Registering...","Please wait...",false,false);

        String image = "";
        if (bitmap != null)
        {
            image = getStringImage(bitmap);
        }

        name = etName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        dob = String.valueOf(day_x) + "/" + String.valueOf(month_x) + "/" + String.valueOf(year_x);
        description = etDescription.getText().toString().trim();
        phoneNumber = etPhoneNumber.getText().toString().trim();

        final String finalImage = image;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.update_account_details,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                        Toast.makeText(EditProfile.this,s,Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();
                        volleyError.printStackTrace();
                        Toast.makeText(EditProfile.this, "Some error occurred ! Try again later", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new Hashtable<String, String>();

                params.put(Const.USER_ACCOUNT_DATA_IMAGE, finalImage);
                params.put(Const.USER_ACCOUNT_DATA_NAME, name);
                params.put(Const.USER_ACCOUNT_DATA_USER_NAME, utils.getUserName(EditProfile.this));
                params.put(Const.USER_ACCOUNT_DATA_PASSWORD, utils.getPassword(EditProfile.this));
                params.put(Const.USER_ACCOUNT_DATA_EMAIL,email);
                params.put(Const.USER_ACCOUNT_DATA_DOB,dob);
                params.put(Const.USER_ACCOUNT_DATA_DESCRIPTION,description);
                params.put(Const.USER_ACCOUNT_DATA_PHONE_NUMBER,phoneNumber);
                params.put(Const.USER_ACCOUNT_DATA_GENDER,gender);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
