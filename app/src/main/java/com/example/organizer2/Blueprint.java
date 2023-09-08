package com.example.organizer2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;


public class Blueprint extends androidx.appcompat.widget.AppCompatImageView

    {

        Matrix matrix = new Matrix();
        Boolean[][] hasstuff_or_no ;
        ArrayList<Storage_Unit> Storage = new ArrayList<>();

        private Drawable blueprintImg = Drawable.createFromPath("blueprint_square");
        private Drawable blueprint = null;

        int Length;
        int Height;

        String name;

        ImageView BluePrintSquare;


        static final int NONE = 0;

        static final int DRAG = 1;

        static final int ZOOM = 2;

        static final int CLICK = 3;

        int mode = NONE;



        PointF last = new PointF();

        PointF start = new PointF();

        float minScale = 1f;

        float maxScale = 4f;

        float[] m;



        float redundantXSpace, redundantYSpace;

        float width, height;

        float saveScale = 1f;

        float right, bottom, origWidth, origHeight, bmWidth, bmHeight;



        ScaleGestureDetector mScaleDetector;





        public Blueprint(Context context, Editable SizeX, Editable SizeY, Editable name, Matrix matrix)
        {
            super(context);


            super.setClickable(true);

            //this.context = context;

            mScaleDetector = new ScaleGestureDetector(context, new ScaleListener(this));

            String tempx =  SizeX.toString();
            String tempy =  SizeY.toString();
            int Tempx = Integer.parseInt(tempx);
            int Tempy = Integer.parseInt(tempy);
            Length = Tempx;
            Height = Tempy;
            this.name = name.toString();




            m = new float[9];

            setImageMatrix(matrix);

            setScaleType(ScaleType.MATRIX);



            setOnTouchListener(new OnTouchListener()

            {



                @Override

                public boolean onTouch(View v, MotionEvent event)

                {

                    mScaleDetector.onTouchEvent(event);



                    matrix.getValues(m);

                    float x = m[Matrix.MTRANS_X];

                    float y = m[Matrix.MTRANS_Y];

                    PointF curr = new PointF(event.getX(), event.getY());



                    switch (event.getAction())

                    {

                        //when one finger is touching

                        //set the mode to DRAG

                        case MotionEvent.ACTION_DOWN:

                            last.set(event.getX(), event.getY());

                            start.set(last);

                            mode = DRAG;

                            break;

                        //when two fingers are touching

                        //set the mode to ZOOM

                        case MotionEvent.ACTION_POINTER_DOWN:

                            last.set(event.getX(), event.getY());

                            start.set(last);

                            mode = ZOOM;

                            break;

                        //when a finger moves

                        //If mode is applicable move image

                        case MotionEvent.ACTION_MOVE:

                            //if the mode is ZOOM or

                            //if the mode is DRAG and already zoomed

                            if (mode == ZOOM || (mode == DRAG && saveScale > minScale))

                            {

                                float deltaX = curr.x - last.x;// x difference

                                float deltaY = curr.y - last.y;// y difference

                                float scaleWidth = Math.round(origWidth * saveScale);// width after applying current scale

                                float scaleHeight = Math.round(origHeight * saveScale);// height after applying current scale

                                //if scaleWidth is smaller than the views width

                                //in other words if the image width fits in the view

                                //limit left and right movement

                                if (scaleWidth < width)

                                {

                                    deltaX = 0;

                                    if (y + deltaY > 0)

                                        deltaY = -y;

                                    else if (y + deltaY < -bottom)

                                        deltaY = -(y + bottom);

                                }

                                //if scaleHeight is smaller than the views height

                                //in other words if the image height fits in the view

                                //limit up and down movement

                                else if (scaleHeight < height)

                                {

                                    deltaY = 0;

                                    if (x + deltaX > 0)

                                        deltaX = -x;

                                    else if (x + deltaX < -right)

                                        deltaX = -(x + right);

                                }

                                //if the image doesnt fit in the width or height

                                //limit both up and down and left and right

                                else

                                {

                                    if (x + deltaX > 0)

                                        deltaX = -x;

                                    else if (x + deltaX < -right)

                                        deltaX = -(x + right);



                                    if (y + deltaY > 0)

                                        deltaY = -y;

                                    else if (y + deltaY < -bottom)

                                        deltaY = -(y + bottom);

                                }

                                //move the image with the matrix

                                matrix.postTranslate(deltaX, deltaY);

                                //set the last touch location to the current

                                last.set(curr.x, curr.y);

                            }

                            break;

                        //first finger is lifted

                        case MotionEvent.ACTION_UP:

                            mode = NONE;

                            int xDiff = (int) Math.abs(curr.x - start.x);

                            int yDiff = (int) Math.abs(curr.y - start.y);

                            if (xDiff < CLICK && yDiff < CLICK)

                                performClick();

                            break;

                        // second finger is lifted

                        case MotionEvent.ACTION_POINTER_UP:

                            mode = NONE;

                            break;

                    }

                    setImageMatrix(matrix);

                    invalidate();

                    return true;

                }



            });

        }



        @Override

        public void setImageBitmap(Bitmap bm)

        {

            super.setImageBitmap(bm);

            bmWidth = bm.getWidth();

            bmHeight = bm.getHeight();

        }



        public void setMaxZoom(float x)

        {

            maxScale = x;

        }


        @Override

        protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)

        {

            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            width = MeasureSpec.getSize(widthMeasureSpec);

            height = MeasureSpec.getSize(heightMeasureSpec);

            //Fit to screen.

            float scale;

            float scaleX =  width / bmWidth;

            float scaleY = height / bmHeight;

            scale = Math.min(scaleX, scaleY);

            matrix.setScale(scale, scale);

            setImageMatrix(matrix);

            saveScale = 1f;



            // Center the image

            redundantYSpace = height - (scale * bmHeight) ;

            redundantXSpace = width - (scale * bmWidth);

            redundantYSpace /= 2;

            redundantXSpace /= 2;



            matrix.postTranslate(redundantXSpace, redundantYSpace);



            origWidth = width - 2 * redundantXSpace;

            origHeight = height - 2 * redundantYSpace;

            right = width * saveScale - width - (2 * redundantXSpace * saveScale);

            bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);

            setImageMatrix(matrix);

        }
        public String getName()
        {
            return name;
        }




        @RequiresApi(api = Build.VERSION_CODES.S)
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            ArrayList<Rect> horizantal_lines  = new ArrayList<>();
            ArrayList<Rect> verticale_lines  = new ArrayList<>();
            ArrayList<Rect> storage_spaces = new ArrayList<>();

            Rect blueprintbackground = new Rect();
            blueprintbackground.set(0, canvas.getHeight()/2, canvas.getWidth(), canvas.getHeight());

            Paint blue = new Paint();
            blue.setColor(Color.BLUE);
            blue.setStyle(Paint.Style.FILL);
            Paint black = new Paint();
            black.setColor(Color.BLACK);
            black.setStyle(Paint.Style.FILL);

            canvas.drawRect(blueprintbackground,black);

            int blueprintlength = blueprintbackground.left - blueprintbackground.right;
            int blueprintheight = blueprintbackground.top - blueprintbackground.bottom;

            for (int x = 0; x < height ; x++)
            {
                horizantal_lines.add(new Rect(blueprintbackground.left,
                        (int)(blueprintheight/height)*x+blueprintbackground.top-1,
                        blueprintbackground.right,
                        (int)(blueprintheight/height)*x+blueprintbackground.top));
                canvas.drawRect(horizantal_lines.get(x),black);
            }
            for (int x = 0; x < width ; x++)
            {
                verticale_lines.add(new Rect((int)(blueprintlength/width)*x-1,
                        blueprintbackground.top,
                        (int)(blueprintlength/width)*x,
                        blueprintbackground.bottom));
                canvas.drawRect(verticale_lines.get(x),black);
            }

            for (int x = 0;x <Storage.size(); x++)
            {
                storage_spaces.add(new Rect());
            }


            draw(canvas);



        }

        public void addstorage_unit(Storage_Unit su)
        {

            Storage.add(su);
            hasstuff_or_no[su.X_coordinate][su.Y_coordinate] = true;

        }



    }

