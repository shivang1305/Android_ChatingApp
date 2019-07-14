package com.example.chatingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tv;
    Button login,show;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.txtview_main);
        login=findViewById(R.id.login_btn);
        show=findViewById(R.id.show_btn);
        ll=findViewById(R.id.ll_main);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,Chat.class);
                in.putExtra("sender",tv.getText().toString());
                startActivity(in);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyData md = new MyData();
                String data = md.login();
                Toast.makeText(MainActivity.this, "Data mil gaye"+data, Toast.LENGTH_SHORT).show();
                ll.removeAllViews();
                String msgs[] = data.split(";");
                for (String msg : msgs) {
                        String c[] = msg.split(",");
                    TextView t = new TextView(MainActivity.this);
                    Toast.makeText(MainActivity.this, "Length is "+msg+" "+c.length, Toast.LENGTH_SHORT).show();
                    t.setText(c[1]);
                    t.setTextSize(20);
                    TextView t1 = new TextView(MainActivity.this);
                    t1.setText(c[0]);
                    t1.setTextSize(10);
                    ll.addView(t);
                    ll.addView(t1);
                }
            }
        });
    }
}
