package edu.jlu.com;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.FileOutputStream;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class thridActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String TODO = null;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcontext = getApplicationContext();
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        showInformation();
        String[] strs = {"基神","B神","翔神","曹神","J神"};
        //创建ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,strs);
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        ListView list_test = (ListView) findViewById(R.id.list_item);
        list_test.setAdapter(adapter);
    }

    public void showInformation() {
        //获取Mac地址
        String newMac = getNewMac();
        tv1.setText("MAC:    " + newMac);
        //获取IMEI地址
        String imei = getIMEI(mcontext);
        tv2.setText("IMEI:    " + imei);
        //获取MSISDN
        String msisdn = getMSISDN(mcontext);
        if (msisdn != null) {
            tv3.setText("手机号:    " + msisdn);
            Log.d(TAG, "showInformation: 非空");
        } else {
            Log.d(TAG, "showInformation: 空");
            tv3.setText("手机号:    " + "无法获取手机号码");
        }
        //获取IMSI
        String imsi = getIMSI(mcontext);
        tv4.setText("IMSI:    " + imsi);
        //获取ICCID
        String iccid = getICCID(mcontext);
        tv5.setText("ICCID:    " + iccid);
        FileOutputStream fos;
        try {
            fos=openFileOutput("date.txt",MODE_APPEND);//把文件输出到data中
            fos.write(imei.getBytes());//将我们写入的字符串变成字符数组）
             fos.close();
            } catch (Exception e) {
            e.printStackTrace();
            }
    }

    /**
     * 通过网络接口取
     * 获取wifiMac地址
     *
     * @return
     */
    private static String getNewMac() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return null;
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 获取手机IMEI号
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return TODO;
        }
        String imei = telephonyManager.getDeviceId();

        return imei;
    }

    /**
     * 获取手机号
     */
    public static String getMSISDN(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return TODO;
        }
        String msisdn = telephonyManager.getLine1Number();
        return msisdn;
    }

    /**
     * 获取手机MSISDN号
     */
    public static String getIMSI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return TODO;
        }
        String imsi = telephonyManager.getSubscriberId();
        return imsi;
    }

    /**
     * 获取手机ICCID号
     */
    public static String getICCID(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return TODO;
        }
        String iccid = telephonyManager.getSimSerialNumber();
        return iccid;
    }

    @Override
    protected void onPause() {
        super.onPause();
        //8.0动态权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int checkPermission = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1); //后面的1为请求码

                Log.d(TAG, "onpause(),未授权,去授权");
                //展示信息
                showInformation();
                return;

            }
            //展示信息
            showInformation();
            Log.d(TAG, "onpause()已授权...");

        } else {
            //展示信息
            showInformation();
            Log.d(TAG, "onpause()版本<=6.0");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //8.0动态权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int checkPermission = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1); //后面的1为请求码

                Log.d(TAG, "ondestroy(),未授权,去授权");
                //展示信息
                showInformation();
                return;

            }
            //展示信息
            showInformation();
            Log.d(TAG, "ondestroy(),已授权...");

        } else {
            //展示信息
            showInformation();
            Log.d(TAG, "ondestroy(),版本<=6.0");
        }

    }
}
