package edu.jlu.com;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
public class SecondActivity extends AppCompatActivity{
    public long SPLASH_LENGTH = 3000;
    ProgressBar pg;
    int i=0;
    int pgmax=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        pg=findViewById(R.id.bar);
        pgmax=pg.getMax();
        Handler handler = new Handler();
                handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转
                    public void run() {
                        while (i<pgmax){
                            pg.setProgress(i);
                            ++i;
                        }
                        Intent intent = new Intent(SecondActivity.this, ThridActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    }, SPLASH_LENGTH);//3秒后跳转至应用主界面下一个界面
    }
}
