package com.example.isidigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText login;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        login = findViewById(R.id.login);
        Password = findViewById(R.id.Password);
    }
    public  void onclick(View v)
    {
        changeActivity();
    }
    public  void changeActivity()
    {
        Intent inter=new Intent(this, Main.class);
        inter.putExtra("login",login.getText().toString());
        inter.putExtra("pass",Password.getText().toString());
        startActivity(inter);
    }


}