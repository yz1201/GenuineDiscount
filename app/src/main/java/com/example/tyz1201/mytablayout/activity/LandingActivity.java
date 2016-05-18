package com.example.tyz1201.mytablayout.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tyz1201.mytablayout.R;
import com.example.tyz1201.mytablayout.commonmodel.LandingDataModel;
import com.example.tyz1201.mytablayout.util.NetworkRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tyz1201 on 2016/4/22.
 */
public class LandingActivity extends AppCompatActivity {
    EditText password;
    EditText logIn;
    ImageView imageView;
    Toolbar toolbar;
    private Map<String, String> params = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_landing);
        super.onCreate(savedInstanceState);

        imageView = (ImageView) findViewById(R.id.iv_land_toolbar);
        imageView.setImageResource(R.mipmap.fragment_landing_return);
        toolbar = (Toolbar) findViewById(R.id.landing_toobar);
        logIn = (EditText) findViewById(R.id.et_landing_lon_in);
        password = (EditText) findViewById(R.id.et_landing_password);

        setSupportActionBar(toolbar);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button button = (Button) findViewById(R.id.bt_landing);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logIn.getText().toString().equals("")) {
                    Toast.makeText(LandingActivity.this, "请输入用户名或者手机号", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().equals("")) {
                    Toast.makeText(LandingActivity.this, "密码不能空，请输入", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LandingActivity.this, "Loading......", Toast.LENGTH_SHORT).show();
                    params.put("name", logIn.getText().toString());
                    params.put("pwd", password.getText().toString());
                    params.put("device_id", "");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String token = NetworkRequest.postRequestData("http://api.zpzk100.com/client/login", params);
                            Log.d("ysjzyt", token);

                            LandingDataModel landingData = new Gson().fromJson(token, LandingDataModel.class);
                            if (landingData.getFlag() == "true") {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LandingActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LandingActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                        }
                    }).start();
                }


            }
        });


    }
}
