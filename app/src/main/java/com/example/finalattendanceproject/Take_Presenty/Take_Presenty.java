package com.example.finalattendanceproject.Take_Presenty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalattendanceproject.Take_Presenty.BE_C_BDA.BE_CSE_C_BDA;
import com.example.finalattendanceproject.R;
import com.example.finalattendanceproject.Take_Presenty.BE_C_WT.BE_CSE_C_WT;
import com.example.finalattendanceproject.Take_Presenty.ICS.BE_CSE_C_ICS;
import com.example.finalattendanceproject.Take_Presenty.MIS.BE_CSE_C_MIS;
import com.example.finalattendanceproject.Take_Presenty.STQA.BE_CSE_C_STQA;

public class Take_Presenty extends AppCompatActivity {
    Spinner P_Department,P_Dep_Class,P_Div,subject_spinnner;
    Button start_presenty;
    private String P_Department_name="";
    private String P_Class_name="";
    private String P_Div_name="";
    private String subject="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take__presenty);

        P_Department=findViewById(R.id.p_dept);
        P_Dep_Class=findViewById(R.id.p_class);
        P_Div=findViewById(R.id.p_div);
        subject_spinnner=findViewById(R.id.sub);
        start_presenty=findViewById(R.id.p_start);




        //assigning departments
        final ArrayAdapter<CharSequence> department_array = ArrayAdapter.createFromResource(this,R.array.dept_names, R.layout.spinner_layout);
        department_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        P_Department.setAdapter(department_array);


        //assigning classes
        final ArrayAdapter<CharSequence> class_array = ArrayAdapter.createFromResource(this,R.array.dept_classes, R.layout.spinner_layout);
        class_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        P_Dep_Class.setAdapter(class_array);

        //assigning divisions
        final ArrayAdapter<CharSequence> div_array = ArrayAdapter.createFromResource(this,R.array.dept_div, R.layout.spinner_layout);
        div_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        P_Div.setAdapter(div_array);

        //TE subjects
        final ArrayAdapter<CharSequence> TE_sub_array = ArrayAdapter.createFromResource(getApplicationContext(),R.array.TE_SUb, R.layout.spinner_layout);
        TE_sub_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //BE Subjects
        final ArrayAdapter<CharSequence> BE_sub_array = ArrayAdapter.createFromResource(getApplicationContext(),R.array.BE_SUb, R.layout.spinner_layout);
        BE_sub_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        P_Department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch(arg2){
                    case 0:
                        P_Department_name="CSE";

                        break;
                    case 1:
                        P_Department_name="IT";
                        break;
                    case 2:
                        P_Department_name="MEC";
                        break;
                    case 3:
                        P_Department_name="ENTC";
                        break;
                    case 4:
                        P_Department_name="CIVIL";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



        P_Dep_Class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch(arg2){
                    case 0:
                        P_Class_name="SE";

                        break;
                    case 1:
                        P_Class_name="TE";


                    case 2:
                        P_Class_name="BE";
                        subject_spinnner.setAdapter(BE_sub_array);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



        P_Div.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch(arg2){
                    case 0:
                        P_Div_name="A";
                        break;
                    case 1:
                        P_Div_name="B";
                        break;
                    case 2:
                        P_Div_name="C";

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        subject_spinnner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch(arg2){
                    case 0:
                        subject="BDA";

                        break;
                    case 1:
                        subject="ICS";
                        break;
                    case 2:
                        subject="WT";
                        break;
                    case 3:
                        subject="MIS";
                        break;
                    case 4:
                        subject="STQA";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        start_presenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        //BE CSE C BDA
                     if(P_Department_name.equals("CSE") && P_Class_name.equals("BE") && P_Div_name.equals("C") && subject.equals("BDA")){
                            startActivity(new Intent(getApplicationContext(), BE_CSE_C_BDA.class));

                     }

                //BE CSE C WT

                if(P_Department_name.equals("CSE") && P_Class_name.equals("BE") && P_Div_name.equals("C") && subject.equals("WT")){
                    startActivity(new Intent(getApplicationContext(), BE_CSE_C_WT.class));

                }

                //BE CSE C MIS

                if(P_Department_name.equals("CSE") && P_Class_name.equals("BE") && P_Div_name.equals("C") && subject.equals("MIS")){
                    startActivity(new Intent(getApplicationContext(), BE_CSE_C_MIS.class));

                }

                //BE CSE C MIS

                if(P_Department_name.equals("CSE") && P_Class_name.equals("BE") && P_Div_name.equals("C") && subject.equals("ICS")){
                    startActivity(new Intent(getApplicationContext(), BE_CSE_C_ICS.class));

                }

                //BE CSE C STQA
                if(P_Department_name.equals("CSE") && P_Class_name.equals("BE") && P_Div_name.equals("C") && subject.equals("STQA")){
                    startActivity(new Intent(getApplicationContext(), BE_CSE_C_STQA.class));

                }





            }
        });
    }
}
