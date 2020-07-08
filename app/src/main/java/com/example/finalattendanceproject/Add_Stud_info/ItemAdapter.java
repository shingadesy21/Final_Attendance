package com.example.finalattendanceproject.Add_Stud_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalattendanceproject.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    private List<Dataitem> items;

    public ItemAdapter(Context context, List<Dataitem> items) {
        this.context = context;
        this.items = items;
    }



    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.stud_info,parent,false);
        return new ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        final  Dataitem dataitem=items.get(position);
        holder.tvname.setText(dataitem.name);
        holder.tvroll_no.setText(dataitem.roll_no);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvname,tvroll_no;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tv_name);
            tvroll_no=itemView.findViewById(R.id.tv_roll_no);
        }
    }
}
