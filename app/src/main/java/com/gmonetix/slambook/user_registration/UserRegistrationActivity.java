package com.gmonetix.slambook.user_registration;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.gmonetix.slambook.user_login.UserLoginActivity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class UserRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView profileImage;
    private EditText etName, etEmail, etUsername, etPassword, etDescription, etPhoneNumber;
    private RadioGroup radioGroup;
    private RadioButton rbMale,rbFemale;
    private LinearLayout register;
    private ImageButton Dob;
    private TextView tv11,tv12,tv13,tv14,tv15,tv16,tv_dob,loginHere, tv_register;

    private String name, email, username, password, description, dob, phonenumber, gender, registered_at;
    private int year_x,month_x,day_x;
    private Utils utils;
    private Bitmap bitmap;

    private static final int DATE_PICKER_DIALOG_ID = 0;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String INTENT_USERNAME = "intent_username";
    private static final String INTENT_PASSWORD = "intent_password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UserRegistrationActivity.this.setTitle("REGISTER");

        init();

        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR)-10;
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);


        rbMale.setChecked(true);
        gender = "MALE";
        registered_at = DateFormat.getDateInstance().format(new Date());

        profileImage.setOnClickListener(this);
        register.setOnClickListener(this);
        loginHere.setOnClickListener(this);
        Dob.setOnClickListener(this);
        tv_dob.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.maleRadioBtn:
                        gender = "MALE";
                        break;
                    case R.id.femaleRadioBtn:
                        gender = "FEMALE";
                        break;
                    default:
                        gender = "MALE";
                        break;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_profile_image:
                utils.showFileChooser(UserRegistrationActivity.this,PICK_IMAGE_REQUEST);
                break;

            case R.id.ll_register:
                register();
                break;

            case R.id.login_here_tv:
                startActivity(new Intent(UserRegistrationActivity.this, UserLoginActivity.class));
                break;

            case R.id.ib_dob:
                showDialog(DATE_PICKER_DIALOG_ID);
                break;

            case R.id.tv_dob:
                showDialog(DATE_PICKER_DIALOG_ID);
                break;
        }
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
            tv_dob.setText("Date of birth : " + String.valueOf(day_x) + "/" + String.valueOf(month_x) + "/" + String.valueOf(year_x));
        }
    };

    private void init() {
        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        etDescription = (EditText) findViewById(R.id.et_description);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        Dob = (ImageButton) findViewById(R.id.ib_dob);
        register = (LinearLayout) findViewById(R.id.ll_register);
        profileImage = (ImageView) findViewById(R.id.iv_profile_image);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_container);
        rbMale = (RadioButton) findViewById(R.id.maleRadioBtn);
        rbFemale = (RadioButton) findViewById(R.id.femaleRadioBtn);
        tv11 = (TextView) findViewById(R.id.tv11);
        tv12 = (TextView) findViewById(R.id.tv12);
        tv13 = (TextView) findViewById(R.id.tv13);
        tv14 = (TextView) findViewById(R.id.tv14);
        tv15 = (TextView) findViewById(R.id.tv15);
        tv16 = (TextView) findViewById(R.id.tv16);
        tv_dob = (TextView) findViewById(R.id.tv_dob);
        tv_register = (TextView) findViewById(R.id.tv_register);
        loginHere = (TextView) findViewById(R.id.login_here_tv);

        utils = new Utils();
        utils.setFont(UserRegistrationActivity.this,tv11);
        utils.setFont(UserRegistrationActivity.this,tv12);
        utils.setFont(UserRegistrationActivity.this,tv13);
        utils.setFont(UserRegistrationActivity.this,tv14);
        utils.setFont(UserRegistrationActivity.this,tv15);
        utils.setFont(UserRegistrationActivity.this,tv16);
        utils.setFont(UserRegistrationActivity.this,tv_dob);
        utils.setFont(UserRegistrationActivity.this,etName);
        utils.setFont(UserRegistrationActivity.this,etEmail);
        utils.setFont(UserRegistrationActivity.this,etUsername);
        utils.setFont(UserRegistrationActivity.this,etPassword);
        utils.setFont(UserRegistrationActivity.this,etDescription);
        utils.setFont(UserRegistrationActivity.this,etPhoneNumber);
        utils.setFont(UserRegistrationActivity.this,rbMale);
        utils.setFont(UserRegistrationActivity.this,rbFemale);
        utils.setFont(UserRegistrationActivity.this,loginHere);
        utils.setFont(UserRegistrationActivity.this,tv_register);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                profileImage.setImageDrawable(getResources().getDrawable(R.drawable.click));
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void register(){

        final ProgressDialog loading = ProgressDialog.show(this,"Registering...","Please wait...",false,false);

        String image = "";
        if (bitmap != null)
        {
            image = getStringImage(bitmap);
        } else {
            bitmap  = BitmapFactory.decodeResource(getResources(),R.drawable.profile);
            image = getStringImage(bitmap);
        }

        name = etName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        dob = String.valueOf(day_x) + "/" + String.valueOf(month_x) + "/" + String.valueOf(year_x);
        description = etDescription.getText().toString().trim();
        phonenumber = etPhoneNumber.getText().toString().trim();

        final String finalImage = image;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.register_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();

                        Toast.makeText(UserRegistrationActivity.this,"Registration successfull ! Login with your credentials",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(UserRegistrationActivity.this, UserLoginActivity.class);
                        intent.putExtra(INTENT_USERNAME,username);
                        intent.putExtra(INTENT_PASSWORD,password);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();
                        volleyError.printStackTrace();
                        Toast.makeText(UserRegistrationActivity.this, "Some error occurred ! Try again later", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new Hashtable<String, String>();

                params.put(Const.USER_ACCOUNT_DATA_IMAGE, finalImage);
                params.put(Const.USER_ACCOUNT_DATA_NAME, name);
                params.put(Const.USER_ACCOUNT_DATA_USER_NAME,username);
                params.put(Const.USER_ACCOUNT_DATA_EMAIL,email);
                params.put(Const.USER_ACCOUNT_DATA_PASSWORD,password);
                params.put(Const.USER_ACCOUNT_DATA_DOB,dob);
                params.put(Const.USER_ACCOUNT_DATA_DESCRIPTION,description);
                params.put(Const.USER_ACCOUNT_DATA_PHONE_NUMBER,phonenumber);
                params.put(Const.USER_ACCOUNT_DATA_REGISTERED_AT,registered_at);
                params.put(Const.USER_ACCOUNT_DATA_GENDER,gender);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
