package com.example.expwork3;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second);

        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this)
                            .setView(R.layout.activity_second)
                            .create();
        alertDialog.show();
    }
}
