package com.example.expwork3;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String[] animals = new String[]{"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    private Integer[] imageIds = new Integer[]{R.drawable.lion,R.drawable.tiger,R.drawable.monkey,
                                    R.drawable.dog,R.drawable.cat,R.drawable.elephant};

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        final List<Map<String,Object>> listItems = new ArrayList<>();
        for (int i = 0; i < animals.length; i++) {
            Map<String,Object> listItem = new HashMap<>();
            listItem.put("animalName",animals[i]);
            listItem.put("image",imageIds[i]);
            listItems.add(listItem);
        }

//        创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,
                                        R.layout.simple_item,new String[]{"animalName","image"},
                                        new int[]{R.id.animal,R.id.image});
        final ListView listView = findViewById(R.id.mylist);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast toast= Toast.makeText(context,animals[position],Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
