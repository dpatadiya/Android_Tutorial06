package com.example.tutorial06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {


    EditText edtUsername;
    EditText edtPassword;
    Button btn;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername=(EditText) findViewById(R.id.edtUsername);
        edtPassword=(EditText) findViewById(R.id.edtPassword);

        btn=(Button) findViewById(R.id.btn);


        preferences =getSharedPreferences("college",MODE_PRIVATE);
        editor = preferences.edit();

        String userPreference = preferences.getString("username","");

        if(!userPreference.equals("")){
            Intent intent= new Intent(Login.this,display.class);
            intent.putExtra("userdata",userPreference);
            startActivity(intent);
            finish();
        }


        btn.setOnClickListener(new View.OnClickListener() {
            private Object Message;

            @Override

            public void onClick(View view) {

                String valusername = edtUsername.getText().toString();
                String valpassword = edtPassword.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if(valusername.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }
                else {

                    if (valusername.matches(emailPattern)) {
                        Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();

                        editor.putString("username",valusername);
                        editor.commit();

                        Intent intent= new Intent(Login.this,display.class);
                        intent.putExtra("userdata",valusername);
                        startActivity(intent);
                        finish();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }
        );


    }



}