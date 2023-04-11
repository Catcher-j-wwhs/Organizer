package com.example.organizer2;

import android.content.Context;
import android.graphics.Movie;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BlueprintAdapter extends ArrayAdapter<Blueprint> {

    private Context bContext;
    List<Blueprint> blist;


    public BlueprintAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Blueprint> objects) {
        super(context, resource, textViewResourceId, objects);
        blist = objects;
        bContext = context;
    }


    // called for every line in the list (every object in the arraylist)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(bContext).inflate(R.layout.blueprint_item_view,parent,false);

        Blueprint currentBlueprint = blist.get(position);

        TextView name =  listItem.findViewById(R.id.name_text_view);
        name.setText(currentBlueprint.getName());

        return listItem;
    }
}
