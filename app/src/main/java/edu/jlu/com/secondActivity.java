package edu.jlu.com;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
public class secondActivity extends AppCompatActivity {
    ProgressBar pg;
    int count;
    Button but1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line0);
        but1=findViewById(R.id.button0);
        pg = (ProgressBar) findViewById(R.id.progressbar);
        new Thread(){
            public void run() {
                count = 0;
                while( count <= pg.getMax() ){
                    pg.setProgress(count);
                    count = count + 10;
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent();
                inte.setClass(secondActivity.this,thridActivity.class);
                startActivity(inte);
            }
        });
    }
}
