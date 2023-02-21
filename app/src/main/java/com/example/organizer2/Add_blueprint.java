package com.example.organizer2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Add_blueprint extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_blueprint);
        Button BackBtn  = findViewById(R.id.add_blueprintBackBtn);

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BackBtn = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(BackBtn);
            }
        });
    }
}
