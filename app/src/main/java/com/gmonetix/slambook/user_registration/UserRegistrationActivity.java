package com.gmonetix.slambook.user_registration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gmonetix.slambook.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class UserRegistrationActivity extends AppCompatActivity {

    private ImageView circleimageview;
    private static final int GALLERY_INTENT_ACTIVITY = 1;
    private String name, email, username, password, description, dob, phonenumber;
    private EditText Name, Email, Username, Password, Description, Dob, PhoneNumber;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        circleimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery_intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery_intent,GALLERY_INTENT_ACTIVITY);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap profile = ((BitmapDrawable) circleimageview.getDrawable()).getBitmap();
            }
        });
    }

    private void init() {
        Name = (EditText) findViewById(R.id.et_name);
        Email = (EditText) findViewById(R.id.et_email);
        Username = (EditText) findViewById(R.id.et_username);
        Password = (EditText) findViewById(R.id.et_password);
        Description = (EditText) findViewById(R.id.et_description);
        Dob = (EditText) findViewById(R.id.et_dob);
        PhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        submit = (Button) findViewById(R.id.register_btn);
        circleimageview = (ImageView) findViewById(R.id.iv_profile_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT_ACTIVITY && resultCode == RESULT_OK && data != null){
            Uri selected_image = data.getData();
            circleimageview.setImageURI(selected_image);
        }
    }

    private class RegisterInBackground extends AsyncTask<Void,Void,Void>{

        Bitmap image;
        String profilePicName;

        public RegisterInBackground(Bitmap image, String profilePicName)
        {
            this.image = image;
            this.profilePicName = profilePicName;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // 100 is the best quality
            image.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            String encodedImage = Base64.encodeToString(outputStream.toByteArray(),Base64.DEFAULT);

            // org.Apache.http
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            //dataToSend.add(new BasicNameValuePair());

            return null;
        }
    }
}
