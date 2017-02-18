package com.gmonetix.slambook.user_profile;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.gmonetix.slambook.R;
import com.gmonetix.slambook.helper.Utils;

import java.io.IOException;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText etName, etEmail, etPhoneNumber, etDescription;
    private LinearLayout llDob, llSave;
    private TextView tv01, tv02, tv03, tv04, tv05, tvDob;
    private RadioGroup radioGroup;
    private RadioButton radioMale, radioFemale;
    private ImageView profilePic;

    private Utils utils;

    private Bitmap bitmap;

    private static final int DATE_PICKER_DIALOG_ID = 11;
    private int PICK_IMAGE_REQUEST = 12;

    int year_x,month_x,day_x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        llDob.setOnClickListener(this);
        llSave.setOnClickListener(this);
        profilePic.setOnClickListener(this);
    }

    private void init() {
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

        utils = new Utils();
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
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
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

                break;

            case R.id.iv_edit_profile_image:
                utils.showFileChooser(EditProfile.this,PICK_IMAGE_REQUEST);
                break;

        }
    }
}
