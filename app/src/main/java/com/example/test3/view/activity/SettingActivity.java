package com.example.test3.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.test3.R;

public class SettingActivity extends BaseActivity {

    private TextView mVersionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        mVersionTextView = (TextView) findViewById(R.id.activity_setting_version_text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPermissionGroup(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE});
    }

    public void updateVersion(String text) {
        mVersionTextView.setText(text);
    }

    /*click*/
    public void upgradeClick(View view) {
        Intent intent = new Intent(this, UpgradeActivity.class);

        startActivity(intent);
    }

    public void adjustCameraClick(View view) {

    }
}
