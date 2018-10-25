package com.yiyeye.hencodehomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    private DashView dashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dashView = findViewById(R.id.dash);
//        editText = findViewById(R.id.et);
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int cur = Integer.parseInt(editText.getText().toString());
//                dashView.setCurDash(cur);
//            }
//        });
    }
}
