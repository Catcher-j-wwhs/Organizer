package com.example.organizer2;

import android.view.View;
import android.widget.Toast;


public class OnClickListener  {
    public void onClick(View view)
    {
        Toast.makeText(view.getContext(),
                "open Home screen" , Toast.LENGTH_SHORT).show();
    }

}
