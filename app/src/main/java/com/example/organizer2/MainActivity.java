package com.example.organizer2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        Button Add_partBtn  = findViewById(R.id.add_part);

        Add_partBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add_partInt = new Intent(MainActivity.this, add_part.class);
                startActivity(add_partInt);
            }
        });
        Button Add_storage_unitBtn  = findViewById(R.id.add_storageunit);

        Add_storage_unitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Add_storage_unitIntent = new Intent(MainActivity.this, add_storage_unit.class);
                startActivity(Add_storage_unitIntent);
            }
        });
        Button searchBtn  = findViewById(R.id.search);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(MainActivity.this, search_decsion.class);
                startActivity(searchIntent);
            }
        });
        Button AddBlueprintBtn  = findViewById(R.id.add_blueprint);

        AddBlueprintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddBlueprintIntent = new Intent(MainActivity.this, Add_blueprint.class);
                startActivity(AddBlueprintIntent);
            }
        });

    
}}