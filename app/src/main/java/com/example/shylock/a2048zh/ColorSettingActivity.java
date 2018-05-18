package com.example.shylock.a2048zh;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ColorSettingActivity extends AppCompatActivity {

    int[] colors = {Color.rgb(187,173,160),Color.rgb(255,106,106),Color.rgb(144,238,144),Color.rgb(30,144,255),Color.rgb(238,221,130),Color.rgb(139,137,137)};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_setting);

        ListView listView = findViewById(R.id.listView);

        ArrayList<HashMap<String,Object>> arrayList = new ArrayList<>();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("color","默认");
        hashMap.put("pic",R.drawable.defaultcol);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("color","红色");
        hashMap.put("pic",R.drawable.red);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("color","绿色");
        hashMap.put("pic",R.drawable.green);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("color","蓝色");
        hashMap.put("pic",R.drawable.blue);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("color","黄色");
        hashMap.put("pic",R.drawable.yellow);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("color","灰色");
        hashMap.put("pic",R.drawable.grey);
        arrayList.add(hashMap);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,arrayList,R.layout.colorsettinglayout,new String[]{"color","pic"},new int[]{R.id.textView_color,R.id.imageView});
        listView.setAdapter(simpleAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int color = colors[position];
                Intent intent = new Intent();
                intent.putExtra("color",color);
                setResult(0,intent);
                finish();
            }
        });
    }
}
