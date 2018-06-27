package com.example.shylock.a2048zh;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.ArrayList;


/**
 * Created by Shylock on 2018/5/8.
 */

public class GameView extends GridLayout {
    public GameView(Context context) {
        super(context);
        gameView = this;
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameView = this;
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gameView = this;
        initGameView();
    }

    private void  initGameView(){
        setColumnCount(4);
        addCards(GetCardWidth(),GetCardWidth());
        setOnTouchListener(new View.OnTouchListener(){

            private float startX,startY,offsetX,offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();//开始坐标
                        startY = event.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;//坐标变化值
                        offsetY = event.getY() - startY;

                        if(Math.abs(offsetX)>Math.abs(offsetY)){
                            if(offsetX < -5){
                                swipeLeft();
                            }else if(offsetX > 5){
                                swipeRight();
                            }
                        }else{
                            if(offsetY < -5){
                                swipeUp();
                            }else if(offsetX > 5){
                                swipeDown();
                            }
                        }
                        break;
                }

                return true;
            }
        });
    }

    //屏幕宽高改变时 获取宽高
    private int GetCardWidth(){
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();

        int cardWidth;
        cardWidth = displayMetrics.widthPixels;

        return (cardWidth - 10) / 4;


    }

    private void addCards(int cardWith, int cardHeight){
        Card c;
        for(int y = 0;y < 4;y++){
            for(int x = 0;x < 4;x++){
                c = new Card(getContext());
                c.setNum(0);
                addView(c,cardWith,cardHeight);
                cardsMap[x][y] = c;//记录卡片位置状态
            }
        }
//        MainActivity.getMainActivity().clearScore();
        startGame();
    }

    public void startGame(){
        for(int y = 0;y < 4;y++){
            for(int x = 0;x <4;x++){
                cardsMap[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();
    }

    private void addRandomNum(){

        emptyPoints.clear();

        for(int y = 0;y < 4;y++){
            for(int x = 0;x < 4;x++){
                if(cardsMap[x][y].getNum() <= 0){ //是空点
                    emptyPoints.add(new Point(x,y));
                }
            }
        }
        Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));//随机产生一个空点
        cardsMap[p.x][p.y].setNum(Math.random() > 0.08 ? 2 : 4);//2：4
    }

    private void swipeLeft(){
//        System.out.println("left");
        boolean flag = false;
        for(int y = 0;y < 4;y++){
            for(int x = 0;x < 4;x++){

                for(int x1 = x + 1;x1 < 4;x1++){//向右遍历
                    if(cardsMap[x1][y].getNum() > 0){

                        if(cardsMap[x][y].getNum() <= 0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x--;
                            flag = true;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){//数字相同
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            flag = true;
                        }
                        break;
                    }
                }

            }
        }
        if(flag){
            addRandomNum();
            checkComplete();
        }
    }
    private void swipeRight(){
//        System.out.println("right");
        boolean flag = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {

                for (int x1 = x - 1; x1 >=0; x1--) {
                    if (cardsMap[x1][y].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x++;
                            flag = true;
                        } else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            flag = true;
                        }
                        break;
                    }
                }
            }
        }
        if(flag){
            addRandomNum();
            checkComplete();
        }
    }
    private void swipeUp(){
//        System.out.println("up");
        boolean flag = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {

                for (int y1 = y + 1; y1 < 4; y1++) {
                    if (cardsMap[x][y1].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y--;
                            flag = true;
                        } else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            flag = true;
                        }
                        break;
                    }
                }
            }
        }
        if(flag){
            addRandomNum();
            checkComplete();
        }
    }
    private void swipeDown(){
//        System.out.println("down");
        boolean flag = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >=0; y--) {

                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (cardsMap[x][y1].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y++;
                            flag = true;
                        } else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            flag = true;
                        }
                        break;
                    }
                }
            }
        }
        if(flag){
            addRandomNum();
            checkComplete();
        }
    }

    private void checkComplete(){

        boolean complete = true;

        ALL:
        for(int y = 0;y < 4;y++){
            for(int x = 0;x < 4;x++){
                if(cardsMap[x][y].getNum() == 0 ||
                        (x > 1 && cardsMap[x][y].equals(cardsMap[x - 1][y]))||
                        (x < 3 && cardsMap[x][y].equals(cardsMap[x + 1][y]))||
                        (y > 0 && cardsMap[x][y].equals(cardsMap[x][y - 1]))||
                        (y < 3 && cardsMap[x][y].equals(cardsMap[x][y + 1]))){
                    complete = false;
                    break ALL;
                }
            }
        }
        if(complete){
            new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束").setPositiveButton("再来一次", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startGame();
                }
            }).show();
        }
    }



    private Card[][] cardsMap = new Card[4][4];
    private ArrayList<Point> emptyPoints = new ArrayList<Point>();
    private static GameView gameView=null;
    public static GameView getGameview() {
        return gameView;
    }

}