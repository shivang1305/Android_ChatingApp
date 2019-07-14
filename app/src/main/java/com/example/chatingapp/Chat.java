package com.example.chatingapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Chat extends AppCompatActivity {

    String sender;
    Button snd_btn;
    EditText txt;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        snd_btn=findViewById(R.id.sendbtn);
        txt=findViewById(R.id.txt_msg);
        ll=findViewById(R.id.ll);

        Intent in=getIntent();
        sender=in.getStringExtra("sender");

        txt.setText("null");

        Toast.makeText(this, "Opened", Toast.LENGTH_SHORT).show();

        MyData d=new MyData();
        d.save(sender,txt.getText().toString());
        d.login();
        String data = d.login();
        ll.removeAllViews();
        String msgs[] = data.split(";");
        for (String msg : msgs) {
            if (msg.contains(",")) {
                String c[] = msg.split(",");
                TextView t = new TextView(Chat.this);
                t.setText(c[1]);
                t.setTextSize(20);
                TextView t1 = new TextView(Chat.this);
                t1.setText(c[0]);
                t1.setTextSize(10);
                ll.addView(t);
                ll.addView(t1);
            }
        }


        snd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyData d=new MyData();
                d.save(sender,txt.getText().toString());
                d.login();
                String data = d.login();
                ll.removeAllViews();
                String msgs[] = data.split(";");
                for (String msg : msgs) {
                    if (msg.contains(",")) {
                        String c[] = msg.split(",");
                        TextView t = new TextView(Chat.this);
                        t.setText(c[1]);
                        t.setTextSize(20);
                        TextView t1 = new TextView(Chat.this);
                        t1.setText(c[0]);
                        t1.setTextSize(10);
                        ll.addView(t);
                        ll.addView(t1);
                    }
                }
            }
        });



        CountDownTimer ct=new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long l){
                Toast.makeText(Chat.this, "on tick working", Toast.LENGTH_SHORT).show();
                MyData mydata=new MyData();
                mydata.login();
                String data=mydata.login();
                ll.removeAllViews();
                String msgs[]=data.split(";");
                for(String msg:msgs)
                {
                    if(msg.contains(","))
                    {
                        String c[]=msg.split(",");
                        TextView t=new TextView(Chat.this);
                        t.setText(c[1]);
                        t.setTextSize(20);
                        TextView t1=new TextView(Chat.this);
                        t1.setText(c[0]);
                        t1.setTextSize(10);
                        ll.addView(t);
                        ll.addView(t1);
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        };
    }
}
