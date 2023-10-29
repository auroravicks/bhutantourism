package com.example.bhutantourism;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class booking extends AppCompatActivity
{

    String n,e,p,d,res;
    public static String bh="bhutan";
    String filename="file.txt";
    EditText name,email,phone,days;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Window window=this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }
    public void submit(View view)
    {
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        days=findViewById(R.id.days);

        n=name.getText().toString();
        e=email.getText().toString();
        p=phone.getText().toString();
        d=days.getText().toString();

        if(n.equals(""))
        {
            name.requestFocus();
            name.setError("Name cannot be empty");
        }
        else if(!n.matches("[a-zA-Z ]+"))
        {
            name.requestFocus();
            name.setError("Name cannot contain numbers or special symbols");
        }
        else if(!e.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
        {
            email.requestFocus();
            email.setError("Invalid email id");
        }
        else if(p.length()!=10)
        {
            phone.requestFocus();
            phone.setError("Invalid phone number");
        }
        else if(d.length()==0)
        {
            days.requestFocus();
            days.setError("Empty field not allowed");
        }
        else if(Integer.parseInt(d)>10)
        {
            days.requestFocus();
            days.setError("We do not offer trips longer than 10 days");
        }
        else
        {
            res=n+"\n"+e+"\n"+p+"\n"+d+" days stay";

            AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Do you want to submit these details?");
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    try
                    {
                        FileOutputStream fos=openFileOutput(filename,MODE_PRIVATE);
                        fos.write(res.getBytes());
                        fos.close();
                        Toast.makeText(booking.this,"File Saved",Toast.LENGTH_LONG).show();
                    }
                    catch(FileNotFoundException e)
                    {
                        throw new RuntimeException(e);
                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(booking.this, "Your details have been saved", Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(booking.this,detail.class);
                    in.putExtra(bh,Integer.parseInt(d));
                    startActivity(in);
                }
            });
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    //Toast.makeText(booking.this, "Your details have been saved", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog=alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}