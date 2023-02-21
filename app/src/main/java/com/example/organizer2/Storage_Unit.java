package com.example.organizer2;

public class Storage_Unit {
    double Size_x;
    double Size_y;
    double Size_z;
    double X_coordinate;
    double Y_coordinate;
    int Numb_of_drawers;

    public Storage_Unit(double size_x, double size_y, double size_z, int numb_of_drawers, double Xcoordinate, double Ycoordinate  )
    {
        this.Size_x = size_x;
        this.Size_y = size_y;
        this.Size_z = size_z;
        this.Numb_of_drawers = numb_of_drawers;
        this.X_coordinate = Xcoordinate;
        this.Y_coordinate = Ycoordinate;
    }
}
