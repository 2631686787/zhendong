package com.example.a123.zhendong;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView zhendongshichang_text;
    private SeekBar zhendongshichang_seekbar;
    private TextView zhendongjiange_text;
    private SeekBar zhendongjiange_seekbar;
    private Button qiangdu_di;
    private Button qiangdu_zhong;
    private Button qiangdu_gao;
    private Button kaiguan;
    private Vibrator vibrator;
    private int num = 1;//设置seekbar最小值为1



    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //绑定对象
        zhendongshichang_text = findViewById(R.id.zhendongshichang_text);
        zhendongshichang_seekbar = findViewById(R.id.zhendongshichang_seekbar);
        zhendongjiange_text = findViewById(R.id.zhendongjiange_text);
        zhendongjiange_seekbar = findViewById(R.id.zhendongjiange_seekbar);
        qiangdu_di = findViewById(R.id.qiangdu_di);
        qiangdu_zhong = findViewById(R.id.qinagdu_zhong);
        qiangdu_gao = findViewById(R.id.qiangdu_gao);
        kaiguan = findViewById(R.id.kg);


        //振动时长的seekbar
        zhendongshichang_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                zhendongshichang_text.setText(Integer.toString(progress+num));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(kaiguan.getText().equals("停止"))
                {
                   start_vibrator();
                }
            }
        });

        //振动间隔的seekbar
        zhendongjiange_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                zhendongjiange_text.setText(Integer.toString(progress+num));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(kaiguan.getText().equals("停止"))
                {
                    start_vibrator();
                }
            }
        });

        //预设振动强度设置
        qiangdu_di.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setseekbarprogress(99,499);
            }
        });
        qiangdu_zhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setseekbarprogress(299,299);
            }
        });
        qiangdu_gao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setseekbarprogress(499,99);
            }
        });

        //开始按钮
        kaiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kaiguan.getText().equals("开始"))
                {
                    start_vibrator();
                }else
                {
                    stop_vibrator();
                }
            }
        });
    }//主方法结束

    //定义一个启动方法，减少代码重复
    public void start_vibrator()
    {
            vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
            long[] patter = {zhendongjiange_seekbar.getProgress()+num,zhendongshichang_seekbar.getProgress()+num};
            vibrator.vibrate(patter,0);
            kaiguan.setText("停止");
    }

    //定义一个停止方法，减少代码重复
    public void stop_vibrator()
    {
        vibrator.cancel();
        kaiguan.setText("开始");
    }

    //设置seekbar值，减少代码重复
    public void setseekbarprogress(int shichang_num,int jiange_num)
    {
        zhendongshichang_seekbar.setProgress(shichang_num);
        zhendongjiange_seekbar.setProgress(jiange_num);
        if(kaiguan.getText().equals("停止"))
        {
            start_vibrator();
        }
    }



}
