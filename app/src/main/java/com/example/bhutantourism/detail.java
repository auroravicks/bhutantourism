package com.example.bhutantourism;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class detail extends AppCompatActivity
{
    String filename="file.txt",txt;
    TextView tv;
    int d;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i=getIntent();
        d=i.getIntExtra(booking.bh,0);

        Window window=this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
        tv=(TextView)findViewById(R.id.tv);

        txt="";
        try
        {
            FileInputStream fis=openFileInput(filename);
            int size=fis.available();
            byte[]buffer=new byte[size];
            fis.read(buffer);
            txt=new String(buffer);
            fis.close();
        }
        catch(FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        txt+="\nExpected price of your trip: "+(d*10000)+" Rs\n\nOur agents will get in touch with you soon.\nOnly correspond with calls from 5558756245 and mails from visit.bhutan@gmail.com. ";
        tv.setText(txt);
    }
}