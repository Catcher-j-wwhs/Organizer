package com.example.organizer2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    ArrayList<Blueprint> blueprintList = new ArrayList<>();

    private EditText Length, Width,name;
    private ListView BlueprintListView;

    public Context context ;
    public Matrix matrix = new Matrix();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();

        setContentView(R.layout.main_screen);



        BlueprintListView = findViewById(R.id.BlueprintList);
        BlueprintAdapter blueprintAdapter =  new BlueprintAdapter(context,R.id.BlueprintList,R.id.Blueprint_item_view,blueprintList);
        BlueprintListView.setAdapter(blueprintAdapter);

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

        if (Add_blueprint.BlueprintisDone)
        {
            blueprintList.add(new Blueprint(context,Length.getText(),Width.getText(),name.getText(),matrix));
            Add_blueprint.BlueprintisDone = false;

        }
    }
}