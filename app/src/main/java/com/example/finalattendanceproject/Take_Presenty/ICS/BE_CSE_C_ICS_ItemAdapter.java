package com.example.finalattendanceproject.Take_Presenty.ICS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalattendanceproject.Add_Stud_info.AddStudData;
import com.example.finalattendanceproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BE_CSE_C_ICS_ItemAdapter extends RecyclerView.Adapter<BE_CSE_C_ICS_ItemAdapter.ViewHolder> {
    private Context context;
    private List<BE_CSE_C_ICS_Dataitem> items;
    FirebaseDatabase database;
    DatabaseReference reference;

    public BE_CSE_C_ICS_ItemAdapter(Context context, List<BE_CSE_C_ICS_Dataitem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public BE_CSE_C_ICS_ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.be_cse_c_stud_info,parent,false);
        return new BE_CSE_C_ICS_ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BE_CSE_C_ICS_ItemAdapter.ViewHolder holder, int position) {
        final BE_CSE_C_ICS_Dataitem dataitem = items.get(position);
        holder.tvname.setText(dataitem.name);
        holder.tvroll_no.setText(dataitem.roll_no);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final String currentdate = simpleDateFormat.format(date);

        final String stud_Name = holder.tvname.getText().toString();
        final String stud_RollNo = holder.tvroll_no.getText().toString();


       holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked)
               {
                   Query query= FirebaseDatabase.getInstance().getReference("Present Stud Data").child("CSE").child("BE").child("C").child(currentdate).child("ICS").orderByChild("roll_no").equalTo(stud_RollNo);

                   query.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                           if(dataSnapshot.getChildrenCount()>0) {
                               Toast.makeText(context.getApplicationContext(),stud_RollNo+" Allready Present",Toast.LENGTH_SHORT).show();
                           }else {



                               database = FirebaseDatabase.getInstance();
                               reference =  database.getReference("Presenty Data").child("CSE").child("BE").child("C").child(currentdate).child("ICS").child(stud_RollNo);

                               AddStudData addStudData = new AddStudData(stud_Name, stud_RollNo);

                               if (!stud_Name.isEmpty() && !stud_RollNo.isEmpty()) {
                                   reference.child(stud_RollNo).setValue(addStudData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                       @Override
                                       public void onSuccess(Void aVoid) {
                                           Toast.makeText(context.getApplicationContext(), stud_RollNo+" Present", Toast.LENGTH_SHORT).show();
                                       }
                                   }).addOnFailureListener(new OnFailureListener() {
                                       @Override
                                       public void onFailure(@NonNull Exception e) {
                                           Toast.makeText(context.getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();

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
               else if(!isChecked){
                   final String stud_Name = holder.tvname.getText().toString();
                   final String stud_RollNo = holder.tvroll_no.getText().toString();
                   Query query= FirebaseDatabase.getInstance().getReference("Present Stud Data").child("CSE").child("BE").child("C").child(currentdate).child("ICS").orderByChild("roll_no").equalTo(stud_RollNo);
                   query.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           reference = (DatabaseReference) database.getReference("Presenty Data").child("CSE").child("BE").child("C").child(currentdate).child("ICS");
                           database = FirebaseDatabase.getInstance();

                           reference.child(stud_RollNo).removeValue();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });

               }
           }
       });

    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvname,tvroll_no;

        Switch aSwitch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tv_name);
            tvroll_no=itemView.findViewById(R.id.tv_roll_no);

            aSwitch=itemView.findViewById(R.id.status);

        }
    }
}


