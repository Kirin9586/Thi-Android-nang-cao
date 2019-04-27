package com.example.giuakyanroidnangcao;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {
    private Context context;
    private List<ModelMonHoc>models;
    private int GroupView;
    public AdapterRecycler(Context context,int vg,List<ModelMonHoc>model){
        this.context = context;
        this.GroupView = vg;
        this.models = model;
    }
    @NonNull
    @Override
    public AdapterRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(GroupView,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler.ViewHolder viewHolder, int i) {
        final ModelMonHoc model = models.get(i);
        viewHolder.mon.setText(model.getSubjectname());
        viewHolder.lop.setText(model.getSubjectcode());
        viewHolder.tinchi.setText(String.valueOf(model.getCredit()));
        viewHolder.thongtin.setText(model.getDespi());
        final int position = 1;
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("subject", models.get(position));
                    context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText mon;
        EditText lop;
        EditText tinchi;
        EditText thongtin;
        ImageView imageView;
        Button btn_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mon = itemView.findViewById(R.id.book_edt_code);
            this.lop = itemView.findViewById(R.id.book_edt_lop);
            this.tinchi = itemView.findViewById(R.id.book_edt_tin);
            this.thongtin = itemView.findViewById(R.id.book_edt_infor);
            this.imageView = itemView.findViewById(R.id.magic);
            this.btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
