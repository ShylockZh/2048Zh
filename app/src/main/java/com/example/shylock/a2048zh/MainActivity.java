package com.example.shylock.a2048zh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button setting_btn;
    public MainActivity(){
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting_btn = findViewById(R.id.setting_btn);
        tvScore = (TextView) findViewById(R.id.tvScore);
        popliset();
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


    private int score = 0;
    private TextView tvScore;
    private  static MainActivity mainActivity = null;

    public static MainActivity getMainActivity(){
        return mainActivity;
    }

    public void popliset(){
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
                popupMenu.inflate(R.menu.main_menu);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.music:
                                Toast.makeText(MainActivity.this,"pop "+getResources().getString(R.string.music_seting),Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.color:
                                Toast.makeText(MainActivity.this,"pop "+getResources().getString(R.string.color_setting),Toast.LENGTH_SHORT).show();
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

}
