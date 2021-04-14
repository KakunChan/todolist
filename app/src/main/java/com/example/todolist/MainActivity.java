package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView tv_date_today;
    TextView tv_date_tom;
    TextView tv_date_yes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_date_today = findViewById(R.id.tv_date_today);
        tv_date_tom = findViewById(R.id.tv_date_tom);
        tv_date_yes = findViewById(R.id.tv_date_yes);
        new TimeThread().start();

    }
    public class TimeThread extends Thread{
        @Override
        public void run(){
            super.run();
            do{
                try{
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }while (true);
        }
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    tv_date_today.setText(new SimpleDateFormat("MM月dd日").format(new Date(System.currentTimeMillis()  )));
                    tv_date_tom.setText(new SimpleDateFormat("MM月dd日").format(new Date(System.currentTimeMillis() +86400*1000 )));
                    tv_date_yes.setText(new SimpleDateFormat("MM月dd日").format(new Date(System.currentTimeMillis() - 86400*1000  )));
                    break;
            }
            return false;
        }
    });

}