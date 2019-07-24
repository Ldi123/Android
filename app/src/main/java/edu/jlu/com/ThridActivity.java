package edu.jlu.com;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class ThridActivity extends AppCompatActivity {
    Button but1;
    Button but2;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thrid);
        mcontext = getApplicationContext();
        but1=findViewById(R.id.bu0);
        but2=findViewById(R.id.bu1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse("http://10.46.190.179:8080/TestWebA/NewFile.jsp"));
                startActivity(intent);
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent();
                inte.setClass(ThridActivity.this, FourthActivity.class);
                startActivity(inte);
            }
        });
    }
}
