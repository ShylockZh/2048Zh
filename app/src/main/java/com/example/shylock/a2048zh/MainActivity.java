package com.example.shylock.a2048zh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public MainActivity(){
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = (TextView) findViewById(R.id.tvScore);
    }

    public void clearScore(){

    }

//    private
    private TextView tvScore;
    private  static MainActivity mainActivity = null;

    public static MainActivity getMainActivity(){
        return mainActivity;
    }
}
