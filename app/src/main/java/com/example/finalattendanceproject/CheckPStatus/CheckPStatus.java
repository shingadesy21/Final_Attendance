package com.example.finalattendanceproject.CheckPStatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalattendanceproject.Add_Stud_info.Dataitem;
import com.example.finalattendanceproject.Add_Stud_info.ItemAdapter;
import com.example.finalattendanceproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CheckPStatus extends AppCompatActivity {
    DatabaseReference reference;
    Spinner UP_Department,UP_Dep_Class,UP_Div,subject;
    private String UP_Department_name="";
    private String UP_Class_name="";
    private String UP_Div_name="";
    String date="";
    Button checkbtn;

    private String subject_name="";
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Dataitem> items;
    DatePicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_p_status);
        UP_Department=findViewById(R.id.dept_status);
        UP_Dep_Class=findViewById(R.id.class_status);
        UP_Div=findViewById(R.id.div_status);
        subject=findViewById(R.id.sub_status);
        checkbtn=findViewById(R.id.chec);
        recyclerView=findViewById(R.id.recycle);
        picker=findViewById(R.id.date_picker);

//        final String date=getCurrentDate();

        //assigning departments
        final ArrayAdapter<CharSequence> department_array = ArrayAdapter.createFromResource(this,R.array.dept_names, R.layout.spinner_layout);
        department_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UP_Department.setAdapter(department_array);


        //assigning classes
        final ArrayAdapter<CharSequence> class_array = ArrayAdapter.createFromResource(this,R.array.dept_classes, R.layout.spinner_layout);
        class_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UP_Dep_Class.setAdapter(class_array);

        //assigning divisions
        final ArrayAdapter<CharSequence> div_array = ArrayAdapter.createFromResource(this,R.array.dept_div, R.layout.spinner_layout);
        div_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UP_Div.setAdapter(div_array);

        //assigning subjects
        final ArrayAdapter<CharSequence> sub_array = ArrayAdapter.createFromResource(this,R.array.BE_SUb, R.layout.spinner_layout);
        sub_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        UP_Department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch(arg2){
                    case 0:
                        UP_Department_name="CSE";

                        break;
                    case 1:
                        UP_Department_name="IT";
                        break;
                    case 2:
                        UP_Department_name="MEC";
                        break;
                    case 3:
                        UP_Department_name="ENTC";
                        break;
                    case 4:
                        UP_Department_name="CIVIL";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



        UP_Dep_Class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch(arg2){
                    case 0:
                        UP_Class_name="SE";

                        break;
                    case 1:
                        UP_Class_name="TE";
                        break;
                    case 2:
                        UP_Class_name="BE";
                        subject.setAdapter(sub_array);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



        UP_Div.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch(arg2){
                    case 0:
                        UP_Div_name="A";
                        break;
                    case 1:
                        UP_Div_name="B";
                        break;
                    case 2:
                        UP_Div_name="C";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch (arg2){
                    case 0:
                        subject_name="BDA";
                        break;
                    case 1:
                        subject_name="ICS";
                        break;
                    case 2:
                        subject_name="WT";
                        break;
                    case 3:
                        subject_name="MIS";
                        break;
                    case 4:
                        subject_name="STQA";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });




        //assigning recycler view for fetching data from firebase

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        //adding line divider
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        DividerItemDecoration  dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        items=new ArrayList<>();
         date=picker.getDayOfMonth()+"-"+picker.getMonth()+"-"+picker.getYear();

        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayData();
                //Toast.makeText(getApplicationContext(),date,Toast.LENGTH_LONG).show();
            }
        });

    }
    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();;
        builder.append(picker.getDayOfMonth()+"-");
        builder.append((picker.getMonth() + 1)+"-");//month is 0 based
        builder.append(picker.getYear());
        return builder.toString();
    }
    private void DisplayData() {

        reference= FirebaseDatabase.getInstance().getReference();
        Query query=FirebaseDatabase.getInstance().getReference("Presenty").child(UP_Department_name).child(UP_Class_name).child(UP_Div_name).child(date).child(subject_name);

        Toast.makeText(getApplicationContext(),UP_Department_name+" "+UP_Class_name+" "+UP_Div_name+" "+date+" "+subject_name,Toast.LENGTH_LONG).show();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Dataitem dataitem=dataSnapshot1.getValue(Dataitem.class);

                    items.add(dataitem);

                }
                adapter=new ItemAdapter(getApplicationContext(),items);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
