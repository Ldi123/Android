package edu.jlu.com;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.LinkedList;
public class FourthActivity extends AppCompatActivity {
    SwitchButton switchButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth);
        switchButton = findViewById(R.id.switch_button);
        switchButton.setOnSwitchListener(new OnSwitchListener() {
            @Override
            public void onSwitchChange() {
                Toast.makeText(FourthActivity.this, "状态改变", Toast.LENGTH_SHORT).show();
            }
        });
    }
}