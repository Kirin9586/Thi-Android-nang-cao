package com.example.giuakyanroidnangcao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    Button btnLogin;
    String mUsername ="";
    String mPassword ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        onIt();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onValiForm()){
                    Map<String,String>map = new HashMap<>();
                    map.put("user_name",mUsername);
                    map.put("password",mPassword);
                    new LoginAsynTask(LoginActivity.this, new Iview() {
                        @Override
                        public void onLoginSuccess(String m) {
                            Toast.makeText(LoginActivity.this,m,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onLoginFailer(String m) {
                            Toast.makeText(LoginActivity.this,m,Toast.LENGTH_SHORT).show();

                        }
                    },map).execute("http://www.vidophp.tk/api/account/signin");
                }
            }
        });
    }
private boolean onValiForm(){
        mUsername = user.getText().toString();
        if(mUsername.length()<1){
            user.setError("null");
            return false;
        }
        mPassword = pass.getText().toString();
        if (mPassword.length()<1){
            pass.setError("null");
            return false;
        }
        return true;
}

    private void onIt() {
        user = findViewById(R.id.edt_name);
        pass = findViewById(R.id.edt_pass);
        btnLogin = findViewById(R.id.btn_login);
    }
}
