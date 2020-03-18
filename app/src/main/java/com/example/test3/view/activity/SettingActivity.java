package com.example.test3.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.test3.R;

public class SettingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void upgradeClick(View view) {
        Intent intent = new Intent(this, UpgradeActivity.class);

        startActivity(intent);
    }

    public void adjustCameraClick(View view) {

    }
}
