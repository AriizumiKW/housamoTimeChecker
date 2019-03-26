package com.ariizumi.housamoTimeChecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText currentPower;
    EditText limitPower;
    Button startCompute;
    Button reloadLevel;

    private final int MINUTE_NEEDED_RESTORE = 8;

    private int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    protected void initView(){
        currentPower = findViewById(R.id.currentPower);
        limitPower = findViewById(R.id.limitPower);
        startCompute = findViewById(R.id.startCompute);
        reloadLevel = findViewById(R.id.reloadLevel);

        startCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPowerInt = Integer.parseInt(currentPower.getText().toString());
                int limitPowerInt = Integer.parseInt(limitPower.getText().toString());

                int diff = limitPowerInt - currentPowerInt;
                if(diff<0){
                    Toast.makeText(MainActivity.this,"非法输入，请检查",Toast.LENGTH_SHORT).show();
                }else{
                    countResult(diff);
                }
            }
        });

        reloadLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("该功能待补全");
            }
        });

    }

    protected void getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        System.out.println(hour+":"+minute);
    }

    protected void countResult(int diff){
        getCurrentTime();

        minute = minute + diff * MINUTE_NEEDED_RESTORE;
        while(minute>=60){
            minute = minute - 60;
            hour++;
        }
        if(hour>24){
            hour = hour - 24;

            if(hour<=24){
                String output = "体力回满的时间:\n明天 "+hour+"点"+minute+"分\n注意养肝";
                Toast.makeText(MainActivity.this,output,Toast.LENGTH_LONG).show();
            }else if(hour==24) {
                String output = "体力回满的时间:\n明天 0点"+minute+"分\n注意养肝";
                Toast.makeText(MainActivity.this,output,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this,"非法输入，请检查",Toast.LENGTH_SHORT).show();
            }
        }else{
            String output = "体力回满的时间:\n今天 "+hour+"点"+minute+"分\n注意养肝";
            Toast.makeText(MainActivity.this,output,Toast.LENGTH_LONG).show();
        }

    }
}
