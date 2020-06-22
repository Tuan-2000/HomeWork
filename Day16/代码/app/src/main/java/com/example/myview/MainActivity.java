package com.example.myview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyButton mMybt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        mMybt = (MyButton) findViewById(R.id.mybt);
        mMybt.setOnClickListener(this);
        mMybt.setTranslationX(getSharedPreferences("name",MODE_PRIVATE).getInt("x",0));
        mMybt.setTranslationY(getSharedPreferences("name",MODE_PRIVATE).getInt("y",0));
    }

    protected void onPaush(){
        super.onPause();
        SharedPreferences name = getSharedPreferences("name", MODE_PRIVATE);
        SharedPreferences.Editor editor = name.edit();
        float lastx = mMybt.getX();
        float lasty = mMybt.getmY();
        editor.putInt("x", (int) lastx);
        editor.putInt("y", (int) lasty);
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mybt:
                // TODO 20/06/22
                break;
            default:
                break;
        }
    }
}
