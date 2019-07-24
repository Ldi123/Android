package edu.jlu.com;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
public class SecondActivity extends AppCompatActivity {
    Button but1;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // handler自带方法实现定时器
                System.out.println("33331");
                handler.postDelayed(this, 1000*3);//每隔3s执行
            }
        };
        handler.postDelayed(runnable, 1000*60);//延时多长时间启动定时器
        but1=findViewById(R.id.button0);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent();
                inte.setClass(SecondActivity.this, ThridActivity.class);
                startActivity(inte);
            }
        });
    }
}
