package com.example.organizer2;

import android.location.Location;
import android.media.Image;

public class Part {
    Image partImage;
    String Storage_location;

    public Part(Image partImage,String storage_location)
    {
        this.partImage = partImage;
        this.Storage_location = storage_location;
    }

}
