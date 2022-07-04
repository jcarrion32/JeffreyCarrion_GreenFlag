package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMoveToCreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveToCreate = findViewById(R.id.btn_move_to_create);

        btnMoveToCreate.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreateAccount.class);
            startActivity(intent);
        });
    }
}