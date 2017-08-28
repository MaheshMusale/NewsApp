package com.example.hp.navigationwithtab.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.hp.navigationwithtab.R;

/**
 * Created by HP on 30/04/2017.
 */

public class Welcome extends Activity {
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        Handler handel=new Handler();
        handel.postDelayed(new Runnable() {

            public void run() {
                // TODO Auto-generated method stub
                Intent in=new Intent(Welcome.this, MainActivity.class);
                finish();
                startActivity(in);

            }

        },  5000);

    }
}
