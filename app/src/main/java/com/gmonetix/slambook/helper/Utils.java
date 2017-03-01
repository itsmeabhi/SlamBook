package com.gmonetix.slambook.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Utils {

    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;
    private static final String LOGIN_STATUS_SP = "login_status_SP";
    private static final String LOGIN_STATUS = "login_status";
    private static final String USERNAME_SP = "username_SP";
    private static final String USERNAME = "username";
    private static final String PASSWORD_SP = "password_SP";
    private static final String PASSWORD = "password";

    private static final String SLAMS_SENT_SP = "slams_sent_sp";
    private static final String SLAMS_SENT = "slams_sent";
    private static final String SLAMS_RECEIVED_SP = "slams_received_sp";
    private static final String SLAMS_RECEIVED = "slams_received";



    public void setFont(Context _context, TextView textView) {
        Typeface roboto = Typeface.createFromAsset(_context.getAssets(), "font.otf");
        textView.setTypeface(roboto);
    }

    public void showFileChooser(Activity activty, int REQUEST_CODE) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activty.startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE);
    }

    public boolean getLoginStatus(Activity activity) {

        boolean status = false;
        sharedPreference = activity.getSharedPreferences(LOGIN_STATUS_SP , Context.MODE_PRIVATE);
        if(sharedPreference.contains(LOGIN_STATUS)){
            status = sharedPreference.getBoolean(LOGIN_STATUS,false);
        }
        return status;
    }

    public void setLoginStatus(Activity activity, boolean status) {
        sharedPreference = activity.getSharedPreferences(LOGIN_STATUS_SP , Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
        editor.putBoolean(LOGIN_STATUS,status);
        editor.commit();
    }
    
    public void setUserName(Activity activity, String username) {
        sharedPreference = activity.getSharedPreferences(USERNAME_SP , Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
        editor.putString(USERNAME,username);
        editor.commit();
    }

    public String getUserName(Activity activity) {
        String username = "";
        sharedPreference = activity.getSharedPreferences(USERNAME_SP , Context.MODE_PRIVATE);
        if(sharedPreference.contains(USERNAME)){
            username = sharedPreference.getString(USERNAME,"");
        }
        return username;
    }

    public void setPassword(Activity activity, String password) {
        sharedPreference = activity.getSharedPreferences(PASSWORD_SP , Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
        editor.putString(PASSWORD,password);
        editor.commit();
    }

    public String getPassword(Activity activity) {
        String password = "";
        sharedPreference = activity.getSharedPreferences(PASSWORD_SP , Context.MODE_PRIVATE);
        if(sharedPreference.contains(PASSWORD)){
            password = sharedPreference.getString(PASSWORD,"");
        }
        return password;
    }

    public void setSlamsSent(Activity activity, int number) {
        sharedPreference = activity.getSharedPreferences(SLAMS_SENT_SP , Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
        editor.putInt(SLAMS_SENT,number);
        editor.commit();
    }

    public int getSlamsSent(Activity activity) {
        int number = 0;
        sharedPreference = activity.getSharedPreferences(SLAMS_SENT_SP , Context.MODE_PRIVATE);
        if(sharedPreference.contains(SLAMS_SENT)){
            number = sharedPreference.getInt(SLAMS_SENT,0);
        }
        return number;
    }

    public void setSlamsReceived(Activity activity, int number) {
        sharedPreference = activity.getSharedPreferences(SLAMS_RECEIVED_SP , Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
        editor.putInt(SLAMS_RECEIVED,number);
        editor.commit();
    }

    public int getSlamsReceived(Activity activity) {
        int number = 0;
        sharedPreference = activity.getSharedPreferences(SLAMS_RECEIVED_SP , Context.MODE_PRIVATE);
        if(sharedPreference.contains(SLAMS_RECEIVED)){
            number = sharedPreference.getInt(SLAMS_RECEIVED,0);
        }
        return number;
    }

    public void storeUserData(String userJsonData , String fileName, Activity activity){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(activity.openFileOutput(fileName+".txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(userJsonData);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public String readUserData(Activity activity, String fileName){
        String result = "";
        try {
            InputStream inputStream = activity.openFileInput(fileName+".txt");
            if (inputStream!=null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((tempString = bufferedReader.readLine())!=null){
                    stringBuilder.append(tempString);
                }
                inputStream.close();
                result = stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
