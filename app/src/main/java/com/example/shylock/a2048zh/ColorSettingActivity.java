package com.example.shylock.a2048zh;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ColorSettingActivity extends AppCompatActivity {

    int[] colors = {Color.rgb(187,173,160),Color.rgb(255,106,106),Color.rgb(144,238,144),Color.rgb(30,144,255),Color.rgb(238,221,130),Color.rgb(139,137,137)};
    String[] string_color = {"默认","粉色","绿色","蓝色","黄色","灰色"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_setting);
        ListView listView = findViewById(R.id.listView);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,string_color);

        listView.setAdapter(arrayAdapter);

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
