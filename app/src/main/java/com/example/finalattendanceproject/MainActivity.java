package com.example.finalattendanceproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalattendanceproject.CheckPStatus.CheckPStatus;
import com.example.finalattendanceproject.Take_Presenty.Take_Presenty;

public class MainActivity extends AppCompatActivity {

    Button add_stud,take_presenty,check_p_status,defaulters;
    EditText result;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_stud=findViewById(R.id.add_stud_info);
        take_presenty=findViewById(R.id.take_p);
        check_p_status=findViewById(R.id.check_presenty_staus);
        defaulters=findViewById(R.id.check_defaulters);
        //
        // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        add_stud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        //result.setText(userInput.getText());
                                        String staff_id=userInput.getText().toString();
                                        if(staff_id.equals("CC")){
                                            startActivity(new Intent(getApplicationContext(),Student_Information.class));
                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(),"Enter Valid Id",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

        take_presenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Take_Presenty.class));
            }
        });
        check_p_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(), CheckPStatus.class));
            }
        });
    }
}