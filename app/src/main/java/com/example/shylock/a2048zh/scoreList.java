package com.example.shylock.a2048zh;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class scoreList extends AppCompatActivity {

    ListView listView;
    MyDBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);
//
        listView = findViewById(R.id.scorelist);
//
        dbHelper = new MyDBHelper(this,"db.db",null,1);
        db = dbHelper.getWritableDatabase();

        cursor = db.rawQuery("select * from getscore",null);
        cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,new String[]{"name","score"},new int[]{android.R.id.text1,android.R.id.text2},SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(cursorAdapter);
//        cursorAdapter.swapCursor(cursor);
    }
}
