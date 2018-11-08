package com.yiyeye.hencodehomework;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TransView transView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_LONG).show();
            }
        });
        //transView = findViewById(R.id.view);

//        ObjectAnimator bottomAnimtor = ObjectAnimator.ofFloat(transView,"bottomDegress",-45);
//        bottomAnimtor.setDuration(1500);
//
//        ObjectAnimator topAnimtor = ObjectAnimator.ofFloat(transView,"topDegress",45);
//        topAnimtor.setDuration(1500);
////        bottomAnimtor.setStartDelay(1000);
////        bottomAnimtor.start();
//
//        ObjectAnimator cameraRotateAnimtor = ObjectAnimator.ofFloat(transView,"picDegress",270);
//        cameraRotateAnimtor.setDuration(1500);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setStartDelay(1000);
//        animatorSet.playSequentially(bottomAnimtor,cameraRotateAnimtor,topAnimtor);
//        animatorSet.start();
    }
}
