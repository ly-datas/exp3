package com.example.expwork3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        text = findViewById(R.id.text);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem mi){
        if(mi.isCheckable()){
            mi.setChecked(true);
        }
        switch (mi.getItemId()){
            case R.id.font_10:
                text.setTextSize(10);
                break;
            case R.id.font_16:
                text.setTextSize(16);
                break;
            case R.id.font_20:
                text.setTextSize(20);
                break;
            case R.id.red_font:
                text.setTextColor(Color.RED);
                break;
            case R.id.black_font:
                text.setTextColor(Color.BLACK);
                break;
            case R.id.plain_item:
                Toast.makeText(ThirdActivity.this,"您选择了普通样式",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
