package com.example.finalattendanceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalattendanceproject.Add_Stud_info.AddStudent;
import com.example.finalattendanceproject.Update_stud_info.Update_Student_Info;

public class Student_Information extends AppCompatActivity {
Button add,update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__information);
        add=findViewById(R.id.stud_add);
        update=findViewById(R.id.Update);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddStudent.class));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Update_Student_Info.class));
            }
        });
    }
}
