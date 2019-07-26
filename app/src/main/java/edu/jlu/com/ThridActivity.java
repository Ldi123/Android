package edu.jlu.com;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
public class ThridActivity extends AppCompatActivity {
    Button but1;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thrid);
        mcontext = getApplicationContext();
        but1=findViewById(R.id.bu0);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText et = new EditText(ThridActivity.this);
                AlertDialog dialog=new AlertDialog.Builder(ThridActivity.this).setTitle("请输入房间名称")
                        .setIcon(android.R.drawable.sym_def_app_icon)
                        .setView(et)
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.dismiss();
                                //MainActivity.this.finish();
                                generateBtnList(et.getText().toString());
                            }

                        }).show();
//                Intent intent=new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(Uri.parse("http://10.46.190.179:8080/TestWebA/NewFile.jsp"));
//                startActivity(intent);
            }
        });
    }
    //在Linerlayout布局中加入按钮
    private void generateBtnList(String text){
        mBtnListLayout = (LinearLayout)findViewById( R.id.btnListLayout );
        int index = 0;
            Button codeBtn = new Button( this );
            setBtnAttribute( codeBtn,text, index, Color.rgb(255,128,0), Color.BLACK, 24 );
            mBtnListLayout.addView( codeBtn );
            index++;
    }

//设置按钮属性
    private void setBtnAttribute( Button codeBtn,String text,int id, int backGroundColor, int textColor, int textSize ){

        if( null == codeBtn ){

            return;
        }
        codeBtn.setBackgroundColor(backGroundColor );
        codeBtn.setTextColor( ( textColor >= 0 )?textColor:Color.BLUE);
        codeBtn.setTextSize( ( textSize > 16 )?textSize:24 );
        codeBtn.setId( id );
        codeBtn.setText(text);
        codeBtn.setGravity( Gravity.LEFT );
        codeBtn.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse("http://10.46.190.179:8080/TestWebA/NewFile.jsp"));
                startActivity(intent);
            }

        });
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        rlp.addRule( RelativeLayout.ALIGN_PARENT_LEFT );
        codeBtn.setLayoutParams( rlp );
    }
    private LinearLayout mBtnListLayout = null;
}
