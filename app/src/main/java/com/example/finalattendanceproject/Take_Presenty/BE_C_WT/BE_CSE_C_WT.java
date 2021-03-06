package com.example.finalattendanceproject.Take_Presenty.BE_C_WT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalattendanceproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BE_CSE_C_WT extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<BE_CSE_C_WT_Dataitem> item;
private BE_CSE_C_WT_ItemAdapter adapter;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cse__cse__c);
        recyclerView=findViewById(R.id.bda_recyclerview);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        item=new ArrayList<BE_CSE_C_WT_Dataitem>();

        Display();


    }

    private void Display() {
        Query query= FirebaseDatabase.getInstance().getReference("Data").child("CSE").child("BE").child("C").orderByChild("roll_no");

        reference= FirebaseDatabase.getInstance().getReference("Data");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                item.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    BE_CSE_C_WT_Dataitem dataitem=dataSnapshot1.getValue(BE_CSE_C_WT_Dataitem.class);

                    item.add(dataitem);


                }
                adapter=new BE_CSE_C_WT_ItemAdapter(getApplicationContext(),item);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
