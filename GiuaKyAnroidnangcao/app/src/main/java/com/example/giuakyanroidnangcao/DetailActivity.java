package com.example.giuakyanroidnangcao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    EditText mon;
    EditText lop;
    EditText tinchi;
    EditText thongtin;
    Map<String,String>map = new HashMap<>();
    Button btn_edit;
    Button btn_update;
    Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        onIt();
        Intent intent = getIntent();
        final ModelMonHoc model = (ModelMonHoc) intent.getSerializableExtra("subject");
        mon.setText(model.getSubjectname());
        lop.setText(model.getSubjectcode());
        tinchi.setText(String.valueOf(model.getCredit()));
        thongtin.setText(model.getDespi());
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alow();
                map.put("name",mon.getText().toString());
                map.put("number",tinchi.getText().toString());
                map.put("code",lop.getText().toString());
                map.put("description",thongtin.getText().toString());
                map.put("id",String.valueOf(model.getID()));
                map.put("user_id","32");
                new MonHocAsynctask(DetailActivity.this, new Imon() {
                    @Override
                    public void onDataSuccess(JSONArray jsonArray) {

                    }
                },map).execute("http://www.vidophp.tk/api/account/dataaction?context=*");
            }
        });
    }
    private void onIt() {
        mon = findViewById(R.id.book_edt_code1);
        lop = findViewById(R.id.book_edt_lop1);
        tinchi = findViewById(R.id.book_edt_tinchi);
        thongtin = findViewById(R.id.book_edt_infor2);
        btn_edit = findViewById(R.id.btn_edit);
        btn_insert = findViewById(R.id.btn_insert);
        btn_update = findViewById(R.id.btn_update);
    }
    private  void Alow(){
        mon.setEnabled(true);
        lop.setEnabled(true);
        tinchi.setEnabled(true);
        thongtin.setEnabled(true);
    }
    private void nonAllow(){
        mon.setEnabled(false);
        lop.setEnabled(false);
        tinchi.setEnabled(false);
        thongtin.setEnabled(false);
    }

}
