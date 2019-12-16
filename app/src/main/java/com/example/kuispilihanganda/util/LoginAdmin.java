package com.example.kuispilihanganda.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kuispilihanganda.AboutActivity;
import com.example.kuispilihanganda.AdminActivity;
import com.example.kuispilihanganda.DashboardSoal;
import com.example.kuispilihanganda.MainActivity;
import com.example.kuispilihanganda.R;

public class LoginAdmin extends Activity {

    EditText EdUser,passED;
    Button login;
    String user,pass;
    ImageButton move;
    //private String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login_admin);

        EdUser =(EditText)findViewById(R.id.username);
        passED =(EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.appCompatButtonLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateAdmin();
            }
        });

        move = (ImageButton) findViewById(R.id.back);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAdmin.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ValidateAdmin(){

        user = EdUser.getText().toString();
        pass = passED.getText().toString();

        System.out.println("User"+user);
        System.out.println("Pass"+pass);

        if(user.equals("admin") && pass.equals("admin")){
            startActivity(new Intent(getApplicationContext(), AdminActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
        }

    }
}