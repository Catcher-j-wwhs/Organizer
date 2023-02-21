package com.example.organizer2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class part_search extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part_search);

        Button SettingsBtn = findViewById(R.id.Part_SearchBackBtn);

        SettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BackIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(BackIntent);
            }
        });
    }
}
