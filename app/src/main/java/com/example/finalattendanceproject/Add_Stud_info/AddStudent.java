package com.example.finalattendanceproject.Add_Stud_info;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalattendanceproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AddStudent extends AppCompatActivity {
    EditText s_roll_no, s_name;
    Button s_add;
    Spinner Department,Dep_Class,Div;
    FirebaseDatabase database;
    DatabaseReference reference;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Dataitem> items;
    private String Department_name="";
    private String Class_name="";
    private String Div_name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        s_roll_no = findViewById(R.id.stud_roll_no);
        s_name = findViewById(R.id.stud_name);
        s_add = findViewById(R.id.stud_add);
        Department=findViewById(R.id.dept);
        Dep_Class=findViewById(R.id.dept_class);
        Div=findViewById(R.id.div);

        //assigning departments
        final ArrayAdapter<CharSequence> department_array = ArrayAdapter.createFromResource(this,R.array.dept_names, R.layout.spinner_layout);
        department_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Department.setAdapter(department_array);


        //assigning classes
        final ArrayAdapter<CharSequence> class_array = ArrayAdapter.createFromResource(this,R.array.dept_classes, R.layout.spinner_layout);
        class_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Dep_Class.setAdapter(class_array);

        //assigning divisions
        final ArrayAdapter<CharSequence> div_array = ArrayAdapter.createFromResource(this,R.array.dept_div, R.layout.spinner_layout);
        div_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Div.setAdapter(div_array);

        Department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch(arg2){
                    case 0:
                        Department_name="CSE";

                        break;
                    case 1:
                        Department_name="IT";
                        break;
                    case 2:
                        Department_name="MEC";
                        break;
                    case 3:
                        Department_name="IT";
                        break;
                    case 4:
                        Department_name="ENTC";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



        Dep_Class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch(arg2){

                    case 0:
                        Class_name="SE";
                        break;
                    case 1:
                        Class_name="TE";
                        break;
                    case 2:
                        Class_name="BE";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



        Div.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                switch(arg2){
                    case 0:
                        Div_name="A";
                        break;
                    case 1:
                        Div_name="B";
                        break;
                    case 2:
                        Div_name="C";
                        break;
                    case 3:
                        Div_name="D";
                        break;
                    case 4:
                        Div_name="E";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });




        s_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stud_Name = s_name.getText().toString();
               final String stud_RollNo = s_roll_no.getText().toString();
                Query query=FirebaseDatabase.getInstance().getReference("Data").child(Department_name).child(Class_name).child(Div_name).orderByChild("roll_no").equalTo(stud_RollNo);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Toast.makeText(getApplicationContext(),"Roll Number Already Exist",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            database = FirebaseDatabase.getInstance();
                            reference = database.getReference("Data").child(Department_name).child(Class_name).child(Div_name);

                            AddStudData addStudData = new AddStudData(stud_Name, stud_RollNo);
                            long DateTime = 9999999999999L - System.currentTimeMillis();
                            String OrderTime = String.valueOf(DateTime);
                            if (!stud_Name.isEmpty() && !stud_RollNo.isEmpty()) {
                                reference.child(OrderTime).setValue(addStudData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();

                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }



}
