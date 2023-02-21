package com.example.organizer2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class search_decsion extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_decsion);

        Button BackBtn = findViewById(R.id.search_descionbackBtn);

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BackBtn = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(BackBtn);
            }
        });

        Button PartBtn = findViewById(R.id.part_Decison_Btn);

        PartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PartIntent = new Intent(getApplicationContext(), part_search.class);
                startActivity(PartIntent);
            }
        });

        Button StorageUnitBtn = findViewById(R.id.storage_unit_decisonBtn);

        StorageUnitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent StorageUnitIntent = new Intent(getApplicationContext(), storage_unit_search.class);
                startActivity(StorageUnitIntent);
            }
        });
    }
}
