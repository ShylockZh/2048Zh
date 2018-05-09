package com.example.shylock.a2048zh;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;


/**
 * Created by Shylock on 2018/5/8.
 */

public class GameView extends GridLayout {
    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initGameView();
    }

    private void initGameView(){
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
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
                                swipeleft();
                            }else if(offsetX > 5){
                                swiperight();
                            }
                        }else{
                            if(offsetY < -5){
                                swipeup();
                            }else if(offsetX > 5){
                                swipedown();
                            }
                        }
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

        return (cardWidth - 15) / 4;


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
        startGame();
    }

    private void startGame(){
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
        cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);//2：4=9:1
    }

    private void swipeleft(){
        System.out.println("left");
    }
    private void swiperight(){
        System.out.println("right");
    }
    private void swipeup(){
        System.out.println("up");
    }
    private void swipedown(){
        System.out.println("down");
    }

    private Card[][] cardsMap = new Card[4][4];
    private ArrayList<Point> emptyPoints = new ArrayList<Point>();
}
