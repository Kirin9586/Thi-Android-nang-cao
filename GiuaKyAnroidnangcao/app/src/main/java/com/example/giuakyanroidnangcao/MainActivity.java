package com.example.giuakyanroidnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ModelMonHoc> models = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        Map<String,String> map = new HashMap<>();
        map.put("id","32");
        new MonHocAsynctask(MainActivity.this, new Imon() {
            @Override
            public void onDataSuccess(JSONArray jsonArray) {
                for (int i = 0; i< jsonArray.length();i++){
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ModelMonHoc model = new ModelMonHoc();
                        model.setSubjectname(jsonObject.getString("subject_name"));
                        model.setSubjectcode(jsonObject.getString("subject_code"));
                        model.setCredit(Integer.valueOf(jsonObject.getString("credits")));
                        model.setDespi(jsonObject.getString("description"));
                        model.setID(Integer.valueOf(jsonObject.getString("id")));
                        models.add(model);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                AdapterRecycler adapter = new AdapterRecycler(MainActivity.this,R.layout.monhoc_item,models);
                recyclerView.setAdapter(adapter);
            }
        },map).execute("http://www.vidophp.tk/api/account/getdata");
}
}
