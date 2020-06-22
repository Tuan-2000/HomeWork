package com.example.myview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private float mDowX;
    private float mDowY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDowX = event.getX();
                mDowY = event.getY();

                Log.e("tagag", mDowX + "++++++mDowX+++++");
                Log.e("tagag", mDowY + "++++++mDowY+++++");
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();

                float vX = moveX - mDowX;   //X轴终点坐标-X轴起点坐标>0  从左向右滑   如果小于0 从右向左滑
                float vY = moveY - mDowY;  // Y轴终点坐标-Y轴起点坐标>0   从上向下滑  如果小于0 从下向上滑

                //Button左上角在当前父容器里面的位置
                float x = getX();
                float y = getY();

                //button自身左上角的位置(x,y)+移动的距离=最后的自身的位置
                setTranslationX(x + vX);
                setTranslationY(y + vY);
                break;

            case MotionEvent.ACTION_UP:
                break;


        }
        return true;
    }
    public float getmX(){
        return getX();
    }
    public float getmY(){
        return getY();
    }
}
