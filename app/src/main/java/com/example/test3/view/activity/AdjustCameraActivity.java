package com.example.test3.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test3.R;

public class AdjustCameraActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_camera);
        Button button1 = (Button)findViewById(R.id.button);
//        button.setOnClickListener(this);
//        button.requestFocus();
//        button.setOnFocusChangeListener(this);
//        button2.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View v) {
//        v.id
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
//        switch (v.getId()){
//            case buttnId:
//                break;
//        }
    }
}
