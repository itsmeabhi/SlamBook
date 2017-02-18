package com.gmonetix.slambook.user_registration;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.gmonetix.slambook.user_profile.UserHome;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class UserRegistrationActivity extends AppCompatActivity{

    private ImageView circleimageview;
    private String name, email, username, password, description, dob, phonenumber, gender, registered_at;
    private EditText Name, Email, Username, Password, Description, PhoneNumber;
    RadioGroup radioGroup;
    RadioButton male,female;
    private ImageButton Dob;
    Button submit;
    TextView loginHere;
    int year_x,month_x,day_x;
    private static final int DATE_PICKER_DIALOG_ID = 0;
    Utils utils;

    private TextView tv11,tv12,tv13,tv14,tv15,tv16,tv17;
    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);

        registered_at = DateFormat.getDateInstance().format(new Date());

        utils.setFont(UserRegistrationActivity.this,tv11);
        utils.setFont(UserRegistrationActivity.this,tv12);
        utils.setFont(UserRegistrationActivity.this,tv13);
        utils.setFont(UserRegistrationActivity.this,tv14);
        utils.setFont(UserRegistrationActivity.this,tv15);
        utils.setFont(UserRegistrationActivity.this,tv16);
        utils.setFont(UserRegistrationActivity.this,tv17);
        utils.setFont(UserRegistrationActivity.this,Name);
        utils.setFont(UserRegistrationActivity.this,Email);
        utils.setFont(UserRegistrationActivity.this,Username);
        utils.setFont(UserRegistrationActivity.this,Password);
        utils.setFont(UserRegistrationActivity.this,Description);
        utils.setFont(UserRegistrationActivity.this,PhoneNumber);
        utils.setFont(UserRegistrationActivity.this,male);
        utils.setFont(UserRegistrationActivity.this,female);
        utils.setFont(UserRegistrationActivity.this,loginHere);

        male.setChecked(true);

        circleimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.showFileChooser(UserRegistrationActivity.this,PICK_IMAGE_REQUEST);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        loginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRegistrationActivity.this, UserLoginActivity.class));
            }
        });

        Dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_DIALOG_ID);
            }
        });

        tv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_DIALOG_ID);
            }
        });

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
            tv17.setText("Date of birth : " + String.valueOf(day_x) + "/" + String.valueOf(month_x) + "/" + String.valueOf(year_x));
        }
    };

    private void init() {
        Name = (EditText) findViewById(R.id.et_name);
        Email = (EditText) findViewById(R.id.et_email);
        Username = (EditText) findViewById(R.id.et_username);
        Password = (EditText) findViewById(R.id.et_password);
        Description = (EditText) findViewById(R.id.et_description);
        Dob = (ImageButton) findViewById(R.id.et_dob);
        PhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        submit = (Button) findViewById(R.id.register_btn);
        circleimageview = (ImageView) findViewById(R.id.iv_profile_image);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_container);
        loginHere = (TextView) findViewById(R.id.login_here_tv);
        male = (RadioButton) findViewById(R.id.maleRadioBtn);
        female = (RadioButton) findViewById(R.id.femaleRadioBtn);
        tv11 = (TextView) findViewById(R.id.tv11);
        tv12 = (TextView) findViewById(R.id.tv12);
        tv13 = (TextView) findViewById(R.id.tv13);
        tv14 = (TextView) findViewById(R.id.tv14);
        tv15 = (TextView) findViewById(R.id.tv15);
        tv16 = (TextView) findViewById(R.id.tv16);
        tv17 = (TextView) findViewById(R.id.tv17);
        utils = new Utils();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                circleimageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
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
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Registering...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.register_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(UserRegistrationActivity.this, s , Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(UserRegistrationActivity.this, UserHome.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(UserRegistrationActivity.this, volleyError.getMessage() +"Error", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = "";
                if (bitmap != null)
                {
                    image = getStringImage(bitmap);
                }

                //Getting Image Name
                name = Name.getText().toString().trim();
                email = Email.getText().toString().trim();
                username = Username.getText().toString().trim();
                password = Password.getText().toString().trim();
                dob = String.valueOf(day_x) + "/" + String.valueOf(month_x) + "/" + String.valueOf(year_x);
                description = Description.getText().toString().trim();
                phonenumber = PhoneNumber.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);
                params.put("user_name",username);
                params.put("email",email);
                params.put("password",password);
                params.put("dob",dob);
                params.put("description",description);
                params.put("phone_number",phonenumber);
                params.put("registered_at",registered_at);
                params.put("gender",gender);

                //returning parameters
                return params;
            }

        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

}
