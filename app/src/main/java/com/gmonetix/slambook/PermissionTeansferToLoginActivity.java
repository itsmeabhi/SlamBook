package com.gmonetix.slambook;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.gmonetix.slambook.helper.RuntimePermission;
import com.gmonetix.slambook.user_login.UserLoginActivity;

public class PermissionTeansferToLoginActivity extends RuntimePermission{
    private static final int REQUEST_PERMISSION = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        requestAppPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.INTERNET},R.string.permission,REQUEST_PERMISSION);
    }

    @Override
    public void onPermissionGranted(int requestCode) {
        startActivity(new Intent(getApplicationContext(),UserLoginActivity.class));
        finish();
    }
}
