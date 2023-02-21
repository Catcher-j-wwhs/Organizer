package com.example.organizer2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class add_part extends AppCompatActivity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_part);
    Button BackBtn  = findViewById(R.id.BtnAddPartBack);

        BackBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent BackBtn = new Intent(add_part.this, MainActivity.class);
            startActivity(BackBtn);
        }
    });
    }
}
