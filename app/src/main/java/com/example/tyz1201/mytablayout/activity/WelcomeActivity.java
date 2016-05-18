package com.example.tyz1201.mytablayout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.tyz1201.mytablayout.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by tyz1201 on 2016/5/18.
 */
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_welcome);
        super.onCreate(savedInstanceState);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);//这里停留时间为1000=1s。
    }
}
