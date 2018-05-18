package com.example.shylock.a2048zh;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {


    public MainActivity(){
        mainActivity=this;
    }
    @SuppressLint("StaticFieldLeak")
    private static MainActivity mainActivity=null;
    public static MainActivity getMainActivity() {
        return mainActivity;
    }
    Button setting_btn;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    int color;
    TextView score_text;
    String username;
    boolean isPlay = true;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting_btn = findViewById(R.id.setting_btn);
        tvScore = (TextView) findViewById(R.id.tvScore);
        score_text = findViewById(R.id.score_text);
        player = MediaPlayer.create(this,R.raw.bgm);
        player.setLooping(true);
        player.start();

        Intent i = getIntent();
        Log.d("Main", "onCreate: "+ i.getStringExtra("myName") );
        username = i.getStringExtra("myName");
        score_text.setText(username + "'s Score:");
        popliset();



    }
    public void restart(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("你好").setMessage("确认重新开始？");
        builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clearScore();
                GameView.getGameview().startGame();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();

    }
    public void clearScore(){
        score = 0;
        showScore();
    }
    public void showScore(){
        tvScore.setText(score + "");
    }
    public void addScore(int s){
        score += s;
        showScore();
    }


    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
    }

    private int score = 0;
    private TextView tvScore;

    public void popliset(){
        setting_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                GameView.getGameView().startGame();
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
                popupMenu.inflate(R.menu.main_menu);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.music:
                                Toast.makeText(MainActivity.this,"pop "+getResources().getString(R.string.music_seting),Toast.LENGTH_SHORT).show();
                                if(isPlay){
                                    player.pause();
                                    isPlay = false;
                                }else{
                                    player.start();
                                    isPlay = true;
                                }
                                break;
                            case R.id.color:
                                Toast.makeText(MainActivity.this,"pop "+getResources().getString(R.string.color_setting),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this,ColorSettingActivity.class);
                                startActivityForResult(intent,1);
                                break;
                            case R.id.clear:
                                Toast.makeText(MainActivity.this,"pop "+getResources().getString(R.string.clear_note),Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                color = data.getIntExtra("color", Color.RED);
                View v = findViewById(R.id.layout);
                v.setBackgroundColor(color);
                break;
        }
    }

    public void store_score(){

    }

    private long lastBack = 0;
    @Override
    public void onBackPressed() {
        if (lastBack == 0 || System.currentTimeMillis() - lastBack > 2000) {
            Toast.makeText(MainActivity.this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
            lastBack = System.currentTimeMillis();
            return;
        }
        super.onBackPressed();
    }
}
