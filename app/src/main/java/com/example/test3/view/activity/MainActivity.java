package com.example.test3.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.test3.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*click*/
    public void settingClick(View view) {
        startSettingActivity();
    }

    public void testDownloadClick(View view) {
        startTestDownloadActivity();
    }

    /*startActivity*/
    private void startSettingActivity() {
        Intent intent = new Intent(this, SettingActivity.class);

        startActivity(intent);
    }

    private void startTestDownloadActivity() {
        Intent intent = new Intent(this, TestDownloadActivity.class);

        startActivity(intent);
    }
}
