package edu.jlu.com;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String TODO = null;
    Button but1;
    Context mcontext;
    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        but1=findViewById(R.id.butt0);
        mcontext = getApplicationContext();
        //声明一个对话框对象
        AlertDialog dialog;
        //绑定当前界面窗口
        dialog = new AlertDialog.Builder(this).setTitle("授权获取手机IMEI")
                .setMessage("是否确认授权？")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("确定",  new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                        //MainActivity.this.finish();
                        showInformation();
                    }

                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        MainActivity.this.finish();
                    }
                })
                .create();
        dialog.show();
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.butt0){
                    Intent inte=new Intent();
                    inte.setClass(MainActivity.this, SecondActivity.class);
                    startActivity(inte);
                }
            }
        });
    }

    public void showInformation() {
        //获取IMEI地址
        String imei = getIMEI(mcontext);
        //imei存储到SharedPreferences存储
        SharedPreferences sp = getSharedPreferences("Imei", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("imei", imei);
        editor.commit();
        //验证是否成功
        Map<String, String> data = new HashMap<String, String>();
        data.put("Imei", sp.getString("imei", ""));
        System.out.println(data);
        //imei存储到文件
        String state= Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            File SDpath=Environment.getExternalStorageDirectory();
            File file=new File(SDpath,"data.txt");
            FileOutputStream fos;
            try {
                fos=new FileOutputStream(file);//把文件输出到data中
                fos.write(("imei"+":"+imei).getBytes());//将我们写入的字符串变成字符数组）
                System.out.println("write successfully!");
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return TODO;
        }
        String imei = telephonyManager.getDeviceId();
        return imei;
    }
}
