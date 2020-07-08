package com.example.finalattendanceproject.Update_stud_info;

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
import android.widget.Spinner;

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

public class Update_Student_Info extends AppCompatActivity {
    DatabaseReference reference;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Dataitem> items;
    Spinner UP_Department,UP_Dep_Class,UP_Div;
    private String UP_Department_name="";
    private String UP_Class_name="";
    private String UP_Div_name="";
    private Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__student__info);


        show=findViewById(R.id.show_data);
        UP_Department=findViewById(R.id.up_dept);
        UP_Dep_Class=findViewById(R.id.up_dept_class);
        UP_Div=findViewById(R.id.up_div);


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




        //assigning recycler view for fetching data from firebase
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        //adding line divider
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        DividerItemDecoration  dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        items=new ArrayList<>();


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayData();
            }
        });

    }

    private void DisplayData() {
        Query query=FirebaseDatabase.getInstance().getReference("Data").child(UP_Department_name).child(UP_Class_name).child(UP_Div_name).orderByChild("roll_no");

        reference= FirebaseDatabase.getInstance().getReference("Data");
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
