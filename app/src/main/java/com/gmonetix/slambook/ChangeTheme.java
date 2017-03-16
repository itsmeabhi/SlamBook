package com.gmonetix.slambook;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gmonetix.slambook.helper.Utils;
import com.gmonetix.slambook.user_login.UserLoginActivity;
import com.gmonetix.slambook.user_profile.UserHome;

public class ChangeTheme extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private LinearLayout changeTheme, resetTheme;
    private TextView tvChangeTheme, tvResetTheme;
    private RadioGroup group;
    private RadioButton defaultColor, red, pink, purple, deepPurple, indigo, blue, lightBlue, cyan, teal, green, lightGreen, lime, yellow,
                        amber, deepOrange, brown, grey, blueGrey;

    private Utils utils;
    private int themeId = R.style.AppTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new Utils();
        utils.setThemeOnApp(ChangeTheme.this,utils.getTheme(ChangeTheme.this));
        setContentView(R.layout.activity_change_theme);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ChangeTheme.this.setTitle("");

        init();
        changeTheme.setOnClickListener(this);
        resetTheme.setOnClickListener(this);

        if (utils.getTheme(ChangeTheme.this) == R.style.Red)
            red.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Pink)
            pink.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Purple)
            purple.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.DeepPurple)
            deepPurple.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Indigo)
            indigo.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Blue)
            blue.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.LightBlue)
            lightBlue.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Cyan)
            cyan.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Teal)
            teal.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Green)
            green.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.LightGreen)
            lightGreen.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Lime)
            lime.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Yellow)
            yellow.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Amber)
            amber.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.DeepOrange)
            deepOrange.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Brown)
            brown.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.Grey)
            grey.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.BlueGrey)
            blueGrey.setChecked(true);
        else if (utils.getTheme(ChangeTheme.this) == R.style.AppTheme)
            defaultColor.setChecked(true);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.red_btn:
                        themeId = R.style.Red;
                        Toast.makeText(ChangeTheme.this,"Red",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pink_btn:
                        themeId = R.style.Pink;
                        Toast.makeText(ChangeTheme.this,"Pink",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.purple_btn:
                        themeId = R.style.Purple;
                        Toast.makeText(ChangeTheme.this,"Purple",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.deep_purple_btn:
                        themeId = R.style.DeepPurple;
                        Toast.makeText(ChangeTheme.this,"Deep Purple",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.indigo_btn:
                        themeId = R.style.Indigo;
                        Toast.makeText(ChangeTheme.this,"Indigo",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.blue_btn:
                        themeId = R.style.Blue;
                        Toast.makeText(ChangeTheme.this,"Blue",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.light_blue_btn:
                        themeId = R.style.LightBlue;
                        Toast.makeText(ChangeTheme.this,"Light Blue",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cyan_btn:
                        themeId = R.style.Cyan;
                        Toast.makeText(ChangeTheme.this,"Cyan",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.teal_btn:
                        themeId = R.style.Teal;
                        Toast.makeText(ChangeTheme.this,"Teal",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.green_btn:
                        themeId = R.style.Green;
                        Toast.makeText(ChangeTheme.this,"Green",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.light_green_btn:
                        themeId = R.style.LightGreen;
                        Toast.makeText(ChangeTheme.this,"Light Green",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.lime_btn:
                        themeId = R.style.Lime;
                        Toast.makeText(ChangeTheme.this,"Lime",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.yellow_btn:
                        themeId = R.style.Yellow;
                        Toast.makeText(ChangeTheme.this,"Yellow",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.amber_btn:
                        themeId = R.style.Amber;
                        Toast.makeText(ChangeTheme.this,"Amber",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.deep_orange_btn:
                        themeId = R.style.DeepOrange;
                        Toast.makeText(ChangeTheme.this,"Deep Orange",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.brown_btn:
                        themeId = R.style.Brown;
                        Toast.makeText(ChangeTheme.this,"Brown",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.grey_btn:
                        themeId = R.style.Grey;
                        Toast.makeText(ChangeTheme.this,"Grey",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.blue_grey_btn:
                        themeId = R.style.BlueGrey;
                        Toast.makeText(ChangeTheme.this,"Blue Grey",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    private void init() {
        changeTheme = (LinearLayout) findViewById(R.id.ll_change_theme);
        resetTheme = (LinearLayout) findViewById(R.id.ll_change_theme_reset);

        group = (RadioGroup) findViewById(R.id.change_theme_radio_group);
        defaultColor = (RadioButton) findViewById(R.id.default_btn);
        red = (RadioButton) findViewById(R.id.red_btn);
        pink = (RadioButton) findViewById(R.id.pink_btn);
        purple = (RadioButton) findViewById(R.id.purple_btn);
        deepPurple = (RadioButton) findViewById(R.id.deep_purple_btn);
        indigo = (RadioButton) findViewById(R.id.indigo_btn);
        blue = (RadioButton) findViewById(R.id.blue_btn);
        lightBlue = (RadioButton) findViewById(R.id.light_blue_btn);
        cyan = (RadioButton) findViewById(R.id.cyan_btn);
        teal = (RadioButton) findViewById(R.id.teal_btn);
        green = (RadioButton) findViewById(R.id.green_btn);
        lightGreen = (RadioButton) findViewById(R.id.light_green_btn);
        lime = (RadioButton) findViewById(R.id.lime_btn);
        yellow = (RadioButton) findViewById(R.id.yellow_btn);
        amber = (RadioButton) findViewById(R.id.amber_btn);
        deepOrange = (RadioButton) findViewById(R.id.deep_orange_btn);
        brown = (RadioButton) findViewById(R.id.brown_btn);
        grey = (RadioButton) findViewById(R.id.grey_btn);
        blueGrey = (RadioButton) findViewById(R.id.blue_grey_btn);

        tvChangeTheme = (TextView) findViewById(R.id.tv_change_theme);
        tvResetTheme = (TextView) findViewById(R.id.tv_change_theme_reset);
        utils.setFont(ChangeTheme.this,tvChangeTheme);
        utils.setFont(ChangeTheme.this,tvResetTheme);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_change_theme:
                utils.setTheme(ChangeTheme.this,themeId);
                utils.setThemeOnApp(ChangeTheme.this,themeId);
                Intent intent1 = new Intent(ChangeTheme.this,UserLoginActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;

            case R.id.ll_change_theme_reset:
                utils.setTheme(ChangeTheme.this,R.style.AppTheme);
                utils.setThemeOnApp(ChangeTheme.this,R.style.AppTheme);
                Intent intent2 = new Intent(ChangeTheme.this,UserLoginActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
        }
    }
}
