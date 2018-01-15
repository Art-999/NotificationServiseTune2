package com.example.user.notificationservisetune2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.notifTextView);

        Intent intent=getIntent();
        String filename=intent.getStringExtra("filename");
        textView.setText(filename);
    }
    public void onClick(View v){
        if(v.getId()==R.id.startButton){
            textView.setText("");
            startService(new Intent(this,MyService.class));
        }
        if(v.getId()==R.id.stopButton){
            stopService(new Intent(this,MyService.class));
        }
    }
}
